package de.kitt3120.guessanticheat.exceptions.modules;

/**
 * Created by kitt3120 on 13.05.2017 at 23:36.
 */
public class NoSuchPluginRegisteredException extends Exception {
    public NoSuchPluginRegisteredException() {
    }

    public NoSuchPluginRegisteredException(String message) {
        super(message);
    }

    public NoSuchPluginRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchPluginRegisteredException(Throwable cause) {
        super(cause);
    }

    public NoSuchPluginRegisteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
