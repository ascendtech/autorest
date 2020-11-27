plugins {
    id("us.ascend-tech.autorest.java-conventions")
}

dependencies {
    implementation(project(":autorest-core"))
    implementation("javax.ws.rs:jsr311-api:1.1.1")
    implementation("javax.inject:javax.inject:1")
    implementation("com.google.auto:auto-common:0.11")
    implementation("com.squareup:javapoet:1.13.0")
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.mockito:mockito-core:3.6.28")
    testImplementation("com.google.testing.compile:compile-testing:0.15")

    testAnnotationProcessor(project(":autorest-processor"))
}

description = "AutoREST :: Processor"
