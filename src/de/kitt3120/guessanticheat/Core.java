package de.kitt3120.guessanticheat;

import de.kitt3120.guessanticheat.managers.ModuleRegistry;
import de.kitt3120.guessanticheat.utils.LocationUtils;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by kitt3120 on 13.05.2017 at 23:19.
 */
public class Core extends JavaPlugin {

    public static Core instance;

    @Override
    public void onEnable() {
        instance = this;

        LocationUtils.setup();
        ModuleRegistry.setup();

        getLogger().info("Started");
    }

    @Override
    public void onDisable() {
        LocationUtils.onDisable();
        getLogger().info("Stopping location updaters");

        ModuleRegistry.onDisable();
    }
}
