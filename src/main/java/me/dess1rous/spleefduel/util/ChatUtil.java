package me.dess1rous.spleefduel.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class ChatUtil {

    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> applyArgs(List<String> text, Map<String, String> args) {
        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);

            for (String arg: args.keySet()) {
                line = line.replace(arg, args.get(arg));
            }

            text.set(i, color(line));
        }

        return text;
    }

    public static void sendMessage(CommandSender recipient, String message) {
        recipient.sendMessage(color(message));
    }

    public static void sendTitle(Player recipient, String message, String subMsg) {
        recipient.sendTitle(message, subMsg, 10, 40, 10);
    }
}
