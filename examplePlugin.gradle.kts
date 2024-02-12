dependencies {
    implementation("org.json:json:20230618")
}
version = "1.0.2"

project.extra["PluginName"] = "Example Authentication Plugin"
project.extra["PluginDescription"] = ""
project.extra["ProjectSupportUrl"] = ""

tasks {
    jar {
        manifest {
            attributes(mapOf(
                    "Plugin-Version" to project.version,
                    "Plugin-Id" to nameToId(project.extra["PluginName"] as String),
                    "Plugin-Provider" to project.extra["PluginProvider"],
                    "Plugin-Description" to project.extra["PluginDescription"],
                    "Plugin-License" to project.extra["PluginLicense"]
            ))
        }
    }
}
