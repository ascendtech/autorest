plugins {
    id("us.ascend-tech.autorest.java-conventions")
    id("us.ascendtech.gwt.modern") version "0.5.9"
}

gwt {
    modules.add("us.ascendtech.ToDo")
    libs.add("vue")
    libs.add("elemento-core")
    libs.add("ast-highcharts")
    libs.add("ast-aggrid")
    libs.add("ast-momentjs")
}

sourceSets {
    main {
        java {
            srcDir("src/main/java")
        }
        resources {
            srcDir("src/main/java")
        }
    }
}

tasks.withType<JavaCompile> {
    options.isDebug = true
    options.debugOptions.debugLevel = "source,lines,vars"
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

dependencies {
    implementation(project(":autorest-core"))
    annotationProcessor(project(":autorest-processor"))
}

