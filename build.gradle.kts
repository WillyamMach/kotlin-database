plugins {
    kotlin("jvm") version "2.0.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib"))
    implementation("mysql:mysql-connector-java:8.0.33")
    testImplementation("junit", "junit", "4.12")
}

tasks.test {
    useJUnitPlatform()
}