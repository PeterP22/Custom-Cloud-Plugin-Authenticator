package net.runelite.client.plugins.examplePlugin;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface ExampleConfig extends Config
{
    @ConfigItem(
            keyName = "licenseKey",
            name = "License Key",
            description = "Enter your license key here",
            position = 0
    )
    default String licenseKey()
    {
        return "";
    }

    // make a config boolean to toggle the plugin on and off
    @ConfigItem(
            keyName = "example",
            name = "Enable",
            description = "Turn the plugin on/off",
            position = 1
    )
    default boolean examplePluginEnabled()
    {
        return true;
    }
}
