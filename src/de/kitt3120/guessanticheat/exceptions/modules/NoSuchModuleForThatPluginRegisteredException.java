package de.kitt3120.guessanticheat.exceptions.modules;

/**
 * Created by kitt3120 on 13.05.2017 at 23:36.
 */
public class NoSuchModuleForThatPluginRegisteredException extends Exception {
    public NoSuchModuleForThatPluginRegisteredException() {
    }

    public NoSuchModuleForThatPluginRegisteredException(String message) {
        super(message);
    }

    public NoSuchModuleForThatPluginRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchModuleForThatPluginRegisteredException(Throwable cause) {
        super(cause);
    }

    public NoSuchModuleForThatPluginRegisteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
