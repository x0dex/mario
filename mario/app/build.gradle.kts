plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

val lwjglVersion = "3.2.3"
val jomlVersion = "1.10.8"
val lwjglNatives = "natives-linux"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation(libs.guava)
	
    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

	  implementation("org.lwjgl",  "lwjgl")
	  implementation("org.lwjgl",  "lwjgl-assimp")
	  implementation("org.lwjgl",  "lwjgl-glfw")
	  implementation("org.lwjgl",  "lwjgl-nfd")
	  implementation("org.lwjgl",  "lwjgl-openal")
	  implementation("org.lwjgl",  "lwjgl-opengl")
	  implementation("org.lwjgl",  "lwjgl-stb")
	  implementation ("org.lwjgl", "lwjgl",        classifier = lwjglNatives)
	  implementation ("org.lwjgl", "lwjgl-assimp", classifier = lwjglNatives)
	  implementation ("org.lwjgl", "lwjgl-glfw",   classifier = lwjglNatives)
	  implementation ("org.lwjgl", "lwjgl-nfd",    classifier = lwjglNatives)
	  implementation ("org.lwjgl", "lwjgl-openal", classifier = lwjglNatives)
	  implementation ("org.lwjgl", "lwjgl-opengl", classifier = lwjglNatives)
	  implementation ("org.lwjgl", "lwjgl-stb",    classifier = lwjglNatives)
	  implementation("org.joml",   "joml", jomlVersion)
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Define the main class for the application.
    mainClass = "org.mario.App"
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
