plugins {
    kotlin("jvm") version "1.4.31"
}

group = "com.domingoslatorre.teacherbot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("net.dv8tion:JDA:4.2.0_241")
    implementation("org.slf4j:slf4j-simple:1.7.30")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.4.3")
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.4.3")
    testImplementation("io.kotest:kotest-property-jvm:4.4.3")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xallow-result-return-type")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}