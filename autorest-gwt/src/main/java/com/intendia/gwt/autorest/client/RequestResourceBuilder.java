package com.intendia.gwt.autorest.client;

import com.intendia.gwt.autorest.client.RequestResponseException.FailedStatusCodeException;
import elemental2.core.Global;
import elemental2.dom.FormData;
import elemental2.dom.XMLHttpRequest;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import jsinterop.base.Js;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import static elemental2.core.Global.encodeURIComponent;
import static java.util.Objects.requireNonNull;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA;

@SuppressWarnings("GwtInconsistentSerializableClass")
public class RequestResourceBuilder extends CollectorResourceVisitor {
	public static final Function<RequestResourceBuilder, XMLHttpRequest> DEFAULT_REQUEST_FACTORY = data -> {
		XMLHttpRequest xhr = new XMLHttpRequest();
		xhr.open(data.method(), data.uri());
		return xhr;
	};
	public static final BiFunction<Single<XMLHttpRequest>, RequestResourceBuilder, Single<XMLHttpRequest>> DEFAULT_REQUEST_TRANSFORMER = (xml, data) -> xml;
	public static final Function<XMLHttpRequest, RequestResponseException.FailedStatusCodeException> DEFAULT_UNEXPECTED_MAPPER = xhr -> new RequestResponseException.FailedStatusCodeException(
			xhr.status, xhr.responseText);

	private Function<RequestResourceBuilder, XMLHttpRequest> requestFactory = DEFAULT_REQUEST_FACTORY;
	private Function<XMLHttpRequest, FailedStatusCodeException> unexpectedMapper = DEFAULT_UNEXPECTED_MAPPER;
	private BiFunction<Single<XMLHttpRequest>, RequestResourceBuilder, Single<XMLHttpRequest>> requestTransformer = DEFAULT_REQUEST_TRANSFORMER;

	public RequestResourceBuilder(String base) {
		super(base);
	}

	@Override
	protected String encodeComponent(String str) {
		return encodeURIComponent(str).replaceAll("%20", "+");
	}

	public RequestResourceBuilder requestFactory(Function<RequestResourceBuilder, XMLHttpRequest> fn) {
		this.requestFactory = fn;
		return this;
	}

	public RequestResourceBuilder unexpectedMapper(Function<XMLHttpRequest, FailedStatusCodeException> fn) {
		this.unexpectedMapper = fn;
		return this;
	}

	public RequestResourceBuilder requestTransformer(BiFunction<Single<XMLHttpRequest>, RequestResourceBuilder, Single<XMLHttpRequest>> fn) {
		this.requestTransformer = fn;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T as(Class<? super T> container, Class<?> type) {
		if (Completable.class.equals(container))
			return (T) request().toCompletable();
		if (Maybe.class.equals(container))
			return (T) request().flatMapMaybe(ctx -> {
				Object decode = decode(ctx);
				return decode == null ? Maybe.empty() : Maybe.just(decode);
			});
		if (Single.class.equals(container))
			return (T) request().map(ctx -> {
				Object decode = decode(ctx);
				return requireNonNull(decode, "null response forbidden, use Maybe instead");
			});
		if (Observable.class.equals(container))
			return (T) request().toObservable().flatMapIterable(ctx -> {
				Object[] decode = decode(ctx);
				return decode == null ? Collections.emptyList() : Arrays.asList(decode);
			});
		throw new UnsupportedOperationException("unsupported type " + container);
	}

	private <T> T decode(XMLHttpRequest ctx) {
		try {
			String text = ctx.response.asString();
			if (text != null && !text.isEmpty()) {
				return Js.cast(Global.JSON.parse(text));
			}

			return null;
		}
		catch (Throwable e) {
			if (ctx.response.isString()) {
				return Js.cast(ctx.response.asString());
			}
			else {
				try {
					Js.cast(Global.JSON.parse(ctx.response.asString()));
				}
				catch (Throwable e2) {
					throw new RequestResponseException.ResponseFormatException("Parsing response error", e);
				}
			}
			throw new RequestResponseException.ResponseFormatException("Parsing response error", e);
		}
	}

	public Single<XMLHttpRequest> request() {
		return Single.<XMLHttpRequest>create(em -> {
			XMLHttpRequest xhr = requestFactory.apply(this);
			Map<String, String> headers = new HashMap<>();
			for (Param h : headerParams)
				headers.put(h.k, Objects.toString(h.v));
			for (Map.Entry<String, String> h : headers.entrySet())
				xhr.setRequestHeader(h.getKey(), h.getValue());

			try {
				xhr.onreadystatechange = evt -> {
					if (em.isDisposed())
						return null;
					if (xhr.readyState == XMLHttpRequest.DONE) {
						if (isExpected(uri(), xhr.status))
							em.onSuccess(xhr);
						else
							em.tryOnError(unexpectedMapper.apply(xhr));
					}
					return null;
				};
				em.setCancellable(() -> {
					if (xhr.readyState != XMLHttpRequest.DONE)
						xhr.abort();
				});

				if (!formParams.isEmpty()) {
					xhr.setRequestHeader(CONTENT_TYPE, MULTIPART_FORM_DATA);
					FormData form = new FormData();
					formParams.forEach(p -> form.append(p.k, Objects.toString(p.v)));
					xhr.send(form);
				}
				else {
					if (!headers.containsKey(CONTENT_TYPE))
						xhr.setRequestHeader(CONTENT_TYPE, APPLICATION_JSON);
					if (!headers.containsKey(ACCEPT))
						xhr.setRequestHeader(ACCEPT, APPLICATION_JSON);
					if (data != null)
						xhr.send(Global.JSON.stringify(data));
					else
						xhr.send();
				}
			}
			catch (Throwable e) {
				em.tryOnError(new RequestResponseException("", e));
			}
		}).compose(o -> requestTransformer.apply(o, this));
	}
}