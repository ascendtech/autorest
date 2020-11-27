plugins {
    id("us.ascend-tech.autorest.java-conventions")
}

dependencies {
    api(project(":autorest-core"))
    api("com.intendia.gwt:rxjava2-gwt:2.2.10-gwt1")
    api("javax.ws.rs:jsr311-api:1.1.1")
    implementation("com.google.elemental2:elemental2-dom:1.1.0")
    implementation("javax.inject:javax.inject:1")

}

description = "autorest-gwt"
