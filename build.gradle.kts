import org.jetbrains.kotlin.allopen.gradle.AllOpenExtension

plugins {
    val kotlinVersion = "1.4.20"
    idea
    kotlin("jvm") version kotlinVersion
    id("me.champeau.gradle.jmh") version "0.5.2"
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
}

group = "poc"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

val log4j2Version = "2.13.3"
val slf4jVersion = "1.8.0-beta4" // compatible to log4j2
dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.apache.logging.log4j:log4j-slf4j18-impl:$log4j2Version")
    implementation("org.slf4j:osgi-over-slf4j:$slf4jVersion")
    implementation("org.slf4j:jul-to-slf4j:$slf4jVersion")
    implementation("org.slf4j:log4j-over-slf4j:$slf4jVersion")
    implementation("org.slf4j:jcl-over-slf4j:$slf4jVersion")
}

jmh {
    duplicateClassesStrategy = DuplicatesStrategy.WARN
}

configure<AllOpenExtension> {
    annotation("org.openjdk.jmh.annotations.State")
}
