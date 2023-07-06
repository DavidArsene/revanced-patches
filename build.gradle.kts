plugins {
    kotlin("jvm") version "1.9.0"
}

group = "app.revanced"

val githubUsername: String = project.findProperty("gpr.user") as? String ?: System.getenv("GITHUB_ACTOR")
val githubPassword: String = project.findProperty("gpr.key") as? String ?: System.getenv("GITHUB_TOKEN")

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://maven.pkg.github.com/revanced/revanced-patcher")
        credentials {
            username = githubUsername
            password = githubPassword
        }
    }
}

dependencies {
    implementation(project(":revanced-patcher"))
    implementation("app.revanced:multidexlib2:2.5.3-a3836654")
}

kotlin {
    jvmToolchain(18)
}
