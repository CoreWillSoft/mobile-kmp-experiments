buildscript {
    dependencies {
        classpath(Deps.IO.Database.PLUGIN)
        classpath(kotlin("serialization", version = BuildPluginsVersions.KOTLIN))
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(BuildPluginsVersions.AGP).apply(false)
    id("com.android.library").version(BuildPluginsVersions.AGP).apply(false)
    kotlin("android").version(BuildPluginsVersions.KOTLIN).apply(false)
    kotlin("multiplatform").version(BuildPluginsVersions.KOTLIN).apply(false)

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
