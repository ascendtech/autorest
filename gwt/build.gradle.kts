plugins {
    id("com.intendia.gwt.autorest.java-conventions")
}

dependencies {
    implementation(project(":autorest-core"))
    implementation(project(":autorest-core"))
    implementation("com.intendia.gwt:rxjava2-gwt:2.2.10-gwt1")
    implementation("com.intendia.gwt:rxjava2-gwt:2.2.10-gwt1")
    implementation("com.google.elemental2:elemental2-dom:1.1.0")
    implementation("javax.ws.rs:jsr311-api:1.1.1")
    implementation("javax.inject:javax.inject:1")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    compileOnly("com.google.gwt:gwt-user:2.9.0")
    compileOnly("com.google.gwt:gwt-dev:2.9.0")
}

description = "AutoREST :: GWT"
