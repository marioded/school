import java.util.*

plugins {
    java
}

subprojects {
    apply(plugin = "java")

    val properties = Properties().apply {
        load(file("../gradle.properties").inputStream())
    }

    group = "tech.zmario.school"
    version = properties["version"] as String

    repositories {
        mavenCentral()
        mavenLocal()
    }

    val javaVersion = JavaVersion.VERSION_17

    java {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    tasks.processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            expand(props)
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}