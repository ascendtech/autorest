plugins {
    id("us.ascend-tech.autorest.java-conventions")
}

dependencies {
    implementation(project(":autorest-core"))
    api("com.intendia.gwt:rxjava2-gwt:2.2.10-gwt1")
    implementation("com.google.elemental2:elemental2-dom:1.1.0")
    api("javax.ws.rs:jsr311-api:1.1.1")
    implementation("javax.inject:javax.inject:1")

}

description = "AutoREST :: GWT"
