import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.6.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

val bootJar: BootJar by tasks
bootJar.enabled = false

java.sourceCompatibility = JavaVersion.VERSION_11

extra["springCloudVersion"] = "2021.0.0"


allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    group = "com.mby.springcloud"
    version = "1.0-SNAPSHOT"

    apply {
        plugin("kotlin")
        plugin("org.jetbrains.kotlin.jvm")

        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
    }


    dependencies {
        implementation(kotlin("stdlib"))

        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:" + properties["springCloudVersion"])
        }
    }


    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

