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
include(":testing-demo")
include(":login-demo")
include(":lib")
include(":compose-nav")
include(":app-routes")
include(":ui-component")

include(":experience:home:home-public", "experience/home/home-public")
include(":experience:home:home-private", "experience/home/home-private")
include(":experience:home:home-wiring", "experience/home/home-wiring")

include(":experience:auth:auth-public", "experience/auth/auth-public")
include(":experience:auth:auth-private", "experience/auth/auth-private")
include(":experience:auth:auth-wiring", "experience/auth/auth-wiring")

