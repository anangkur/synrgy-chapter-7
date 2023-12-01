// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("com.karumi:shot:6.0.0")
    }
}

plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("com.android.library") version "8.1.3" apply false
    id("com.android.dynamic-feature") version "8.1.3" apply false
    id("land.sungbin.dependency.graph.plugin") version "1.1.0"
}

dependencyGraphConfig {
    projectName = null
    outputFormat = OutputFormat.PNG
    dependencyBuilder { project ->
        null
    }
}
