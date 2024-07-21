plugins {
    kotlin("jvm") version "1.7.21"
}

group = "kr.cosine.autoplant"
version = "1.1.0"

repositories {
    maven("https://maven.hqservice.kr/repository/maven-public")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("org.spigotmc", "spigot-api", "1.17.1-R0.1-SNAPSHOT")
    compileOnly("me.clip", "placeholderapi", "2.11.6")
    compileOnly("com.github.Xiao-MoMi", "Custom-Crops", "3.4.8")

    compileOnly("kr.hqservice", "hqframework-bukkit-core", "1.0.1-SNAPSHOT") {
        exclude("org.spigotmc")
        exclude("io.papermc.paper")
    }
    compileOnly("kr.hqservice", "hqframework-bukkit-command", "1.0.1-SNAPSHOT") {
        exclude("org.spigotmc")
        exclude("io.papermc.paper")
    }
    compileOnly("kr.hqservice", "hqframework-bukkit-inventory", "1.0.1-SNAPSHOT") {
        exclude("org.spigotmc")
        exclude("io.papermc.paper")
    }
    compileOnly("kr.hqservice", "hqframework-bukkit-database", "1.0.1-SNAPSHOT") {
        exclude("org.spigotmc")
        exclude("io.papermc.paper")
    }
    compileOnly("kr.hqservice", "hqframework-bukkit-nms", "1.0.1-SNAPSHOT") {
        exclude("org.spigotmc")
        exclude("io.papermc.paper")
    }

    testImplementation(kotlin("test"))
    testImplementation(kotlin("reflect"))
}

tasks {
    test {
        useJUnitPlatform()
    }
    jar {
        archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
        destinationDirectory.set(file("D:\\서버\\1.20.1 - 개발\\plugins"))
    }
}