plugins { application }

repositories { mavenCentral() }

dependencies {
    runtimeOnly("org.postgresql:postgresql:42.5.1")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.1")

    testImplementation("org.mockito:mockito-junit-jupiter:4.9.0")
}

application { mainClass.set("demo.App") }

tasks.named<Test>("test") { useJUnitPlatform(); include("**/unit/**") }

