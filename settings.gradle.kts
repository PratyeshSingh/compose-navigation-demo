pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}


fun include(name: String, filePath: String? = null) {
    settings.include(name)
    val project = project(name)
    project.configureProjectDir(filePath)
    project.configureBuildFileName(name)
}

fun ProjectDescriptor.configureProjectDir(filePath: String? = null) {
    if (filePath != null) {
        projectDir = File(rootDir, filePath)
    }
    if (!projectDir.exists()) {
        throw GradleException("Path : $projectDir does not exist, Cannot include project: $name")
    }
    if (!projectDir.isDirectory) {
        throw GradleException("Path : $projectDir is a file instead of a directory.Cannot include project: $name")
    }
}

fun ProjectDescriptor.configureBuildFileName(projectName: String) {

    val name = projectName.substringAfterLast(":")
    buildFileName = "$name.gradle"
    if (!buildFile.exists()) {
        buildFileName = "$name.gradle.kts"
    }
    if (!buildFile.exists()) {
        throw GradleException("Build file: $name.gradle of $name.gradle.kts does not exist.Cannot include project: $name")
    }
}

rootProject.name = "compose-navigation-demo"
include(":app")
include(":demo")
include(":lib")
//include(":home")
include(":compose-nav")

include(":home-expereince:home-public", "home-expereince/home-public")
include(":home-expereince:home-private", "home-expereince/home-private")
include(":home-expereince:home-wiring", "home-expereince/home-wiring")

