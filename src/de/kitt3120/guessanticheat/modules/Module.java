package de.kitt3120.guessanticheat.modules;

import de.kitt3120.guessanticheat.Core;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

/**
 * Created by kitt3120 on 13.05.2017 at 23:21.
 */
public abstract class Module implements Listener {

    private String name;
    private String description;
    private Plugin plugin;

    private boolean enableOnRegister = false;
    private boolean enabled = false;

    public Module(Plugin plugin, String name, String description, boolean enableOnRegister) {
        this.plugin = plugin;
        this.name = name;
        this.description = description;
        this.enableOnRegister = enableOnRegister;
    }

    /* Runs when the Module gets registered by the ModuleRegistry. Do not use to register Listeners! */
    public abstract void onRegister();

    /* Runs when the Module gets enabled */
    public abstract void onEnable();

    /* Runs when the Module gets disabled */
    public abstract void onDisable();

    /* Runs when every tick */
    public abstract void onTick();

    public Plugin getPlugin() {
        return plugin;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setEnabled(boolean enabled) {
        if(enabled) {
            if(!isEnabled()) {
                Core.instance.getServer().getPluginManager().registerEvents(this, getPlugin());
                onEnable();
            }
        } else {
            if(isEnabled()){
                HandlerList.unregisterAll(this);
                onDisable();
            }
        }
    }

    public boolean shouldEnableOnRegister() {
        return enableOnRegister;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
