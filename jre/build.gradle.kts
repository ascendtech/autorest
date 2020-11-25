plugins {
    id("us.ascend-tech.autorest.java-conventions")
}

dependencies {
    implementation(project(":autorest-core"))
    implementation("javax.ws.rs:jsr311-api:1.1.1")
    implementation("javax.inject:javax.inject:1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.10")
    implementation("com.google.code.gson:gson:2.8.2")
    testAnnotationProcessor(project(":autorest-processor"))
    testImplementation("junit:junit:4.12")
}

description = "AutoREST :: JRE"
