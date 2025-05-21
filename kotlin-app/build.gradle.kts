plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.password4j:password4j:1.8.1")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.9.0")

    implementation("jakarta.validation:jakarta.validation-api:3.1.0")
    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.20")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}