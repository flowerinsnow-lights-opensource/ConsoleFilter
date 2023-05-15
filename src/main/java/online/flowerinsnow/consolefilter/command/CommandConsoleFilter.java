package online.flowerinsnow.consolefilter.command;

import online.flowerinsnow.consolefilter.ConsoleFilter;
import online.flowerinsnow.consolefilter.util.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandConsoleFilter implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof ConsoleCommandSender)) {
            return false;
        }
        if (args.length == 1) {
            if ("reload".equalsIgnoreCase(args[0])) {
                ConsoleFilter.getInstance().reloadConfig();
                ConsoleFilter.getInstance().clearFilters();
                ConsoleFilter.getInstance().putFilters();
                //noinspection DataFlowIssue
                sender.sendMessage(StringUtils.parseColour(ConsoleFilter.getInstance().getConfig().getString("messages.reload")));
                return true;
            }
        } else if (args.length > 2) {
            switch (args[0].toLowerCase()) {
                case "info":
                    ConsoleFilter.getInstance().getLogger().info(StringUtils.greedyString(args, 1, " "));
                    return true;
                case "warning":
                    ConsoleFilter.getInstance().getLogger().warning(StringUtils.greedyString(args, 1, " "));
                    return true;
                case "severe":
                    ConsoleFilter.getInstance().getLogger().severe(StringUtils.greedyString(args, 1, " "));
                    return true;
                case "fine":
                    ConsoleFilter.getInstance().getLogger().fine(StringUtils.greedyString(args, 1, " "));
                    return true;
                case "finer":
                    ConsoleFilter.getInstance().getLogger().finer(StringUtils.greedyString(args, 1, " "));
                    return true;
                case "finest":
                    ConsoleFilter.getInstance().getLogger().finest(StringUtils.greedyString(args, 1, " "));
                    return true;
                case "config":
                    ConsoleFilter.getInstance().getLogger().config(StringUtils.greedyString(args, 1, " "));
                    return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof ConsoleCommandSender)) {
            return new ArrayList<>();
        }
        if (args.length == 1) {
            List<String> subCommands = new ArrayList<>(Arrays.asList("reload", "info", "warning", "severe", "fine", "finer", "finest", "config"));
            subCommands.removeIf(s -> !s.toLowerCase().startsWith(args[0].toLowerCase()));
            return subCommands;
        }
        return new ArrayList<>();
    }
}
