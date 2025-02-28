plugins {
    id("application")
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

application {
    mainClass.set("tetris.Main")
}

tasks.test {
    useJUnitPlatform()
}
