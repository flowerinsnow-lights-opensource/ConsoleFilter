package online.flowerinsnow.consolefilter;

import online.flowerinsnow.consolefilter.command.CommandConsoleFilter;
import online.flowerinsnow.consolefilter.util.LoggerUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.filter.RegexFilter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ConsoleFilter extends JavaPlugin {
    private static ConsoleFilter instance;

    private final HashSet<RegexFilter> filters = new HashSet<>();

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        getLogger().info("Loaded " + putFilters() + " filters.");

        Optional.ofNullable(getCommand("consolefilter")).ifPresent(cmd -> {
            CommandConsoleFilter executor = new CommandConsoleFilter();
            cmd.setExecutor(executor);
            cmd.setTabCompleter(executor);
        });
    }

    @Override
    public void onDisable() {
        clearFilters();
    }

    public int putFilters() {
        Logger rootLogger = (Logger) LogManager.getRootLogger();
        List<String> list = getConfig().getStringList("filters");
        List<RegexFilter> added = new ArrayList<>();
        list.forEach(pattern -> {
            try {
                Pattern.compile(pattern);
                RegexFilter filter = RegexFilter.createFilter(
                        pattern, new String[0], false, Filter.Result.DENY, Filter.Result.NEUTRAL
                );
                rootLogger.addFilter(filter);
                added.add(filter);
            } catch (PatternSyntaxException e) {
                getLogger().warning("Regex '" + pattern + "' syntax error, ignoring.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        this.filters.addAll(added);
        return added.size();
    }

    public void clearFilters() {
        Logger rootLogger = (Logger) LogManager.getRootLogger();
        Configuration config = LoggerUtils.getConfig(rootLogger);
        filters.forEach(config::removeFilter);
        filters.clear();
    }

    public static ConsoleFilter getInstance() {
        return instance;
    }
}
