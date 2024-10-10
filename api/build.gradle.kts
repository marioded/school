plugins {
    id("java-library")
}

dependencies {
    api("org.apache.logging.log4j:log4j-core:${properties["log4j.version"]}")
    api("org.jetbrains:annotations:${properties["annotations.version"]}")
}