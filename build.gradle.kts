val ktor_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "2.0.20"
}

group = "ru.sug4chy"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.code.gson:gson:2.11.0")

    // Ktor.Client
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")

    // Ktor.Client serialization
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")

    // Ktor.Client logging
    implementation("ch.qos.logback:logback-classic:$logback_version")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass = "ru.sug4chy.Application"
}

tasks.jar {
    manifest {
        attributes("Main-Class" to "ru.sug4chy.ApplicationKt")
    }
    from(sourceSets.main.get().allSource)
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}


tasks.named<Test>("test") {
    useJUnitPlatform()
}