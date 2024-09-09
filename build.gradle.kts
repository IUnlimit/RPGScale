@file:Suppress("PropertyName", "SpellCheckingInspection")

import io.izzel.taboolib.gradle.BUKKIT_ALL
import io.izzel.taboolib.gradle.UNIVERSAL
import io.izzel.taboolib.gradle.DATABASE
import io.izzel.taboolib.gradle.EXPANSION_COMMAND_HELPER
import io.izzel.taboolib.gradle.EXPANSION_PLAYER_DATABASE
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("io.izzel.taboolib") version "2.0.11"
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
}

subprojects {
    apply<JavaPlugin>()
    apply(plugin = "io.izzel.taboolib")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    // TabooLib 配置
    taboolib {
        // 环境配置（模块、仓库地址等）
        env {
            // 安装模块
            install(UNIVERSAL, BUKKIT_ALL, DATABASE, EXPANSION_COMMAND_HELPER, EXPANSION_PLAYER_DATABASE)
        }
        version { taboolib = "6.1.1-beta17" }

        description {
            contributors {
                name("IllTamer")
            }
            dependencies {
                name("MMOCore").with("bukkit").optional(true)
            }
        }
    }

    // 全局仓库
    repositories {
        mavenLocal()
        mavenCentral() // phoenix
        maven { setUrl("https://nexus.phoenixdevt.fr/repository/maven-public/") }
    }
    // 全局依赖
    dependencies {
        compileOnly(kotlin("stdlib"))
    }

    // 编译配置
    java {
        withSourcesJar()
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjvm-default=all", "-Xextended-compiler-checks")
        }
    }
}

gradle.buildFinished {
    buildDir.deleteRecursively()
}