plugins {
    id("java")
    id("war")
}

group = "com.andriiiiiko.time"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("org.thymeleaf:thymeleaf:3.1.2.RELEASE")

}

tasks.test {
    useJUnitPlatform()
}