package online.flowerinsnow.consolefilter.util;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.Configuration;

import java.lang.reflect.Field;

public abstract class LoggerUtils {
    private LoggerUtils() {
    }

    public static Configuration getConfig(Logger logger) {
        try {
            Field fieldPrivateConfig = Logger.class.getDeclaredField("privateConfig");
            fieldPrivateConfig.setAccessible(true);
            Object privateConfig = fieldPrivateConfig.get(logger);
            Class<?> classPrivateConfig = Class.forName(Logger.class.getName() + "$PrivateConfig");
            Field fieldConfig = classPrivateConfig.getField("config");
            fieldConfig.setAccessible(true);
            return (Configuration) fieldConfig.get(privateConfig);
        } catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
