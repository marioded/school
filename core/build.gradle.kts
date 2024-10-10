import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {
    implementation(project(":api"))
    implementation(project(mapOf("path" to ":exercises")))
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("core")
    archiveClassifier.set("")
    archiveVersion.set("")
}