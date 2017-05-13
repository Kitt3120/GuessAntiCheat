package de.kitt3120.guessanticheat;

import de.kitt3120.guessanticheat.managers.ModuleRegistry;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by kitt3120 on 13.05.2017 at 23:19.
 */
public class Core extends JavaPlugin {

    public static Core instance;

    @Override
    public void onEnable() {
        instance = this;

        ModuleRegistry.setup();

        getLogger().info("Started");
    }
}
