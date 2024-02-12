package net.runelite.client.plugins.examplePlugin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HWIDUtil
{
    public static String getHWID() throws NoSuchAlgorithmException
    {
        String hwidString = System.getProperty("os.name") +
                System.getProperty("os.arch") +
                System.getProperty("user.name");

        // "SHA-256"
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hash = digest.digest(hwidString.getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash)
        {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
