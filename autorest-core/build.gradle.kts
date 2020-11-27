plugins {
    id("us.ascend-tech.autorest.java-conventions")
}

dependencies {
    testImplementation("junit:junit:4.13.1")
    compileOnly("javax.ws.rs:jsr311-api:1.1.1")
    compileOnly("javax.inject:javax.inject:1")
}

description = "AutoREST :: core"
