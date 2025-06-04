plugins {
    kotlin("jvm") version "2.1.20"
}

group = "org.gromber"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.zaxxer:HikariCP:5.0.1")       // Pool de conexiones HikariCP
    implementation("org.postgresql:postgresql:42.7.2") // Driver JDBC para PostgreSQL
    implementation("org.jetbrains.exposed:exposed-core:0.45.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.45.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.45.0")
    implementation("at.favre.lib:bcrypt:0.9.0")        // BCrypt para cifrar contraseñas
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}