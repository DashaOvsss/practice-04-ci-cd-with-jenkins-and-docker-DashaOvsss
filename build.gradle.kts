plugins {
    groovy
}

group = "ua.kpi.its.devops"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.jenkins-ci.org/releases/")
    }
}

dependencies {
    testImplementation(localGroovy())

    testImplementation("com.lesfurets:jenkins-pipeline-unit:1.23")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.3")
}


tasks.test {
    useJUnitPlatform()
}