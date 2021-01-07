plugins {
    id("us.ascend-tech.autorest.java-conventions")
}

dependencies {
    testImplementation("junit:junit:4.13.1")
    api("javax.ws.rs:jsr311-api:1.1.1")
    api("javax.inject:javax.inject:1")
    implementation("com.google.elemental2:elemental2-dom:1.1.0")
}

description = "autorest-core"
