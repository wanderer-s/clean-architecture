plugins {
    id("org.springframework.boot") version "3.4.6"
    id("io.spring.dependency-management") version "1.1.5"

    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

group = "org.example"
version = "1.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_17


repositories {
    mavenCentral()
}

dependencies {
    implementation("com.password4j:password4j:1.8.1")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.9.0")

    implementation("jakarta.validation:jakarta.validation-api:3.1.0")
    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.20")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}