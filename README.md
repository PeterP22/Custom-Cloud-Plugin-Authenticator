# Example RuneScape Plugin with Authentication

This repository contains a basic example of a RuneScape plugin that implements a simple authentication system using a license key. The plugin checks the provided license key against a unique hardware identifier (HWID) to ensure that each license key is used by a single user. This system is intended to demonstrate a straightforward method for adding authentication to RuneScape plugins developed for the RuneLite client.

## Features

- **License Key Configuration**: Users can enter their license key through the plugin's configuration in the RuneLite client.
- **HWID Generation**: The plugin generates a unique HWID based on the user's system properties to support the authentication process.
- **Authentication**: Validates the entered license key against the generated HWID to ensure that the key is valid and unique to the user's machine.

## Setup and Installation

To use this example plugin, follow these steps:

1. Clone this repository to your local machine.
2. Build the project using your preferred IDE or build tool (e.g., Maven or Gradle) that is compatible with RuneLite plugin development.
3. Follow the RuneLite developer documentation to load your plugin into the RuneLite client for testing.

## Configuration

After loading the plugin into the RuneLite client, you can configure it through the plugin settings:

- **License Key**: Enter your valid license key provided by the plugin distributor.
- **Enable Plugin**: Toggle the plugin's functionality on or off.

## Extending the Plugin

This plugin is designed as a starting point for developers looking to implement authentication in their own RuneLite plugins. To extend this plugin:

1. Modify `ExamplePlugin.java` to add new functionality or change existing behaviors.
2. Update `ExampleConfig.java` if you need to add new configuration options for your plugin.
3. Use `HWIDUtil.java` as a utility for generating and validating HWIDs, or adapt it to your needs.

## Contributing

Contributions to improve this example plugin or extend its functionality are welcome. Please feel free to fork the repository, make your changes, and submit a pull request with your improvements.


