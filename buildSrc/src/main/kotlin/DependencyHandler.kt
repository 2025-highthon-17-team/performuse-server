import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.exclude

fun DependencyHandler.addDependency(
    configuration: String,
    library: Library,
) = add(configuration, library.toString())

fun DependencyHandler.implementation(library: Library) {
    addDependency("implementation", library)
}

fun DependencyHandler.runtimeOnly(library: Library) {
    addDependency("runtimeOnly", library)
}

fun Project.exclude(
    library: Library,
) {
    configurations.all {
        exclude(group = library.group, module = library.module)
    }
}
