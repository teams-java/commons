plugins {
    java
    `maven-publish`
}

group = "com.manu"
version = System.getenv("VERSION") ?: "1.0.0-SNAPSHOT"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/TU_USUARIO/TU_REPO")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.1.0-M1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.1.0-M1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:6.1.0-M1")
    testImplementation("org.assertj:assertj-core:4.0.0-M1")
}

tasks.test {
    useJUnitPlatform()
}