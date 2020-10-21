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
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation("io.kotless", "kotless-lang", "0.1.6")
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