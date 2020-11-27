rootProject.name = "autorest-parent"
include(":autorest-processor")
include(":autorest-core")
include(":autorest-gwt")
include(":autorest-jre")
project(":autorest-processor").projectDir = file("processor")
project(":autorest-core").projectDir = file("core")
project(":autorest-gwt").projectDir = file("gwt")
project(":autorest-jre").projectDir = file("jre")
