package net.runelite.client.plugins.examplePlugin;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.config.ConfigManager;
import org.pf4j.Extension;

import javax.inject.Inject;
import java.awt.*;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Extension
@PluginDescriptor(name = "Example Authentication Plugin", description = "", tags = {""}, enabledByDefault = false)

@Slf4j
public class ExamplePlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private KeyManager keyManager;

    @Inject
    private ExampleConfig config;

    @Inject
    private ConfigManager configManager;

    @Inject
    private ChatMessageManager chatMessageManager;

    private OkHttpClient httpClient = new OkHttpClient();

    private boolean isLicenseValid = false; // The global boolean field

    private void validateLicenseKey()
    {
        String licenseKey = config.licenseKey();
        if (licenseKey.isEmpty())
        {
            isLicenseValid = false;
            sendGameMessage("License key not set", Color.RED);
            return;
        }

        String hwid = "";
        try
        {
            hwid = HWIDUtil.getHWID(); // Get the HWID
            sendGameMessage("HWID: " + hwid, Color.WHITE);
        }
        catch (Exception e)
        {
            log.error("Error generating HWID", e);
            sendGameMessage("Error generating HWID", Color.RED);
            return;
        }

        Request request = new Request.Builder()
                // this is not an actual URL, it's just an example but update it with your own
                .url("http://35.237.57.164:8000/validate_license?key=" + licenseKey + "&hwid=" + hwid)
                .build();

        try (Response response = httpClient.newCall(request).execute())
        {
            if (response.isSuccessful() && response.body() != null)
            {
                String responseBody = response.body().string();
                if (responseBody.contains("\"valid\":true"))
                {
                    sendGameMessage("License key validated", Color.GREEN);
                    isLicenseValid = true;
                }
                else
                {
                    sendGameMessage("Invalid license key", Color.ORANGE);
                    isLicenseValid = false;
                }
            }
            else
            {
                sendGameMessage("Failed to validate license key", Color.RED);
                isLicenseValid = false;
            }
        }
        catch (IOException e)
        {
            isLicenseValid = false;
            log.error("Error validating license key", e);
            sendGameMessage("Error checking license key", Color.RED);
        }
    }


    @Provides
    ExampleConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ExampleConfig.class);
    }


    @Override
    protected void startUp() throws Exception
    {
        validateLicenseKey();
    }

    @Override
    protected void shutDown()
    {

    }

    @Subscribe(priority = 1)
    public void onMenuOptionClicked(MenuOptionClicked event)
    {
        if (!isLicenseValid)
        {
            return;
        }

        if (client.getGameState() != GameState.LOGGED_IN)
        {
            return;
        }

        if (!config.examplePluginEnabled())
        {
            return;
        }
    }

    private void sendGameMessage(String message, Color color)
    {
        String chatMessage = new ChatMessageBuilder().append(color, message).build();
        chatMessageManager.queue(QueuedMessage.builder().type(ChatMessageType.CONSOLE).runeLiteFormattedMessage(chatMessage).build());
    }
}
