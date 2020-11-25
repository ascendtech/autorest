plugins {
    id("us.ascend-tech.autorest.java-conventions")
}

dependencies {
    testImplementation("junit:junit:4.12")
    compileOnly("javax.ws.rs:jsr311-api:1.1.1")
    compileOnly("javax.inject:javax.inject:1")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
}

description = "AutoREST :: core"
