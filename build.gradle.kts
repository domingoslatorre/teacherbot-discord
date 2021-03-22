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
}
