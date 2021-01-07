package us.ascendtech.gwt.autorest.client;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ResourceVisitorTest {

	@Test
	public void collector_visitor_works() {
		// check root path is accepted (this is a bit obscure, currently anything starting with ".*?//" is a root path)
		RequestResourceBuilder rb0 = new RequestResourceBuilder("http://base/");
		assertThat("", rb0.uri(), equalTo("http://base"));
		// basic path an param check
		RequestResourceBuilder rb1 = new RequestResourceBuilder("http://base").path("path").param("p1", "v1");
		assertThat("", rb1.uri(), equalTo("http://base/path?p1=v1"));
		// check nested path and params combine correctly
		RequestResourceBuilder rb2 = new RequestResourceBuilder("http://base/").path("path").param("p1", "v1").path("nest").param("p2", asList("v2a", "v2b"));
		assertThat("", rb2.uri(), equalTo("http://base/path/nest?p1=v1&p2=v2a&p2=v2b"));
		// check paths (except root) and params are escaped
		RequestResourceBuilder rb3 = new RequestResourceBuilder("http://base:/").path("path:").param("p:", "v:");
		assertThat("", rb3.uri(), equalTo("http://base:/path%3A?p%3A=v%3A"));
	}

}
