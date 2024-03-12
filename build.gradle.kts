plugins {
    kotlin("multiplatform") version "1.9.20-RC2"
}

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



kotlin {
    val hostOs = System.getProperty("os.name")
    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" && isArm64 -> macosArm64("nativec")
        hostOs == "Mac OS X" && !isArm64 -> macosX64("nativec")
        hostOs == "Linux" && isArm64 -> linuxArm64("nativec")
        hostOs == "Linux" && !isArm64 -> linuxX64("nativec")
        isMingwX64 -> mingwX64("nativec")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
            executable {
                entryPoint = "main"
                runTask?.standardInput = System.`in`
            }
        }
    }
    sourceSets {
        val nativecMain by getting
        val nativecTest by getting
    }

}

