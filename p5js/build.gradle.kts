plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization") version "1.9.0"
}

group = "org.processing"
version = "4.3.3"

repositories {
    mavenCentral()
    google()
    maven { url = uri("https://jogamp.org/deployment/maven") }
}

dependencies {
    compileOnly(project(":app"))
    implementation(libs.ktxCoroutines)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")


    testImplementation(kotlin("test"))
}

tasks.jar {
    archiveVersion.set("")
}
tasks.create<Copy>("copyJars") {
    dependsOn(tasks.jar)
    from(layout.buildDirectory.dir("libs")){
        include("**/*.jar")
    }
    from(configurations.compileClasspath)
    into(layout.buildDirectory.dir("library/mode"))
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
tasks.create<Copy>("createLibrary") {
    from("library")
    into(layout.buildDirectory.dir("library"))
}
tasks.create<Copy>("installLibrary") {
    dependsOn(tasks.named("createLibrary"))
    dependsOn(tasks.named("copyJars"))
    from(layout.buildDirectory.dir("library"))
    into("${System.getProperty("user.home")}/Documents/Processing/modes/p5js")
}
tasks.test {
    useJUnitPlatform()
}
project(":app") {
    tasks.named("run"){
        dependsOn(":p5js:installLibrary")
    }
}