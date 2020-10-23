import io.kotless.plugin.gradle.dsl.Webapp.Route53
import io.kotless.plugin.gradle.dsl.kotless
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    id("io.kotless") version "0.1.6"
}
group = "io.github.dragneelfps"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    maven(url = "https://kotlin.bintray.com/kotlinx/")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

val ktor_version = "1.4.1"

dependencies {
    implementation("io.kotless", "kotless-lang", "0.1.6")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-okhttp:$ktor_version")
}


kotless {
    config {
        bucket = "io.github.dragneelfps.whatsyourip"

        terraform {
            profile = "default"
            region = "us-west-2"
        }

    }

    webapp {
    }
}