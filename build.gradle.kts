import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.1.7"
  id("io.spring.dependency-management") version "1.1.4"
  kotlin("jvm") version "1.8.22"
  kotlin("plugin.spring") version "1.8.22"
  kotlin("plugin.jpa") version "1.8.22"
}

group = "com.ader"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-batch")
  implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")

  implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
  implementation("jakarta.annotation:jakarta.annotation-api")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.batch:spring-batch-test")

  runtimeOnly("com.mysql:mysql-connector-j")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.bootBuildImage {
  builder.set("paketobuildpacks/builder-jammy-base:latest")
}
