plugins {
    id("us.ascendtech.gwt.lib")
    id("us.ascendtech.gwt.modern")
}

gwt {
    modules.add("us.ascendtech.ToDo")
    libs.add("vue")
    libs.add("elemento-core")
    libs.add("ast-highcharts")
    libs.add("ast-aggrid")
    libs.add("ast-momentjs")
}

dependencies {
    compile(project(":autorest-core"))
    annotationProcessor(project(":autorest-processor"))
    annotationProcessor("com.axellience:vue-gwt-processors:1.0.1")
}

