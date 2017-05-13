package de.kitt3120.guessanticheat.managers;

import de.kitt3120.guessanticheat.Core;
import de.kitt3120.guessanticheat.exceptions.modules.NoSuchModuleForThatPluginRegisteredException;
import de.kitt3120.guessanticheat.exceptions.modules.NoSuchPluginRegisteredException;
import de.kitt3120.guessanticheat.modules.Module;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kitt3120 on 13.05.2017 at 23:24.
 */
public class ModuleRegistry {

    private static HashMap<Plugin, List<Module>> registeredModules = new HashMap<Plugin, List<Module>>();

    private static BukkitTask tickerTask;

    public static void setup() {
        //TODO: Register own modules

        Core.instance.getLogger().info("ModuleRegistry set up");

        tickerTask = Core.instance.getServer().getScheduler().runTaskTimer(Core.instance, new Runnable() {
            @Override
            public void run() {
                for(Module module : getModules()) {
                    module.onTick();
                }
            }
        }, 5*20L, 1);
    }

    public static void onDisable() {
        for(Module module : getModules()) {
            module.setEnabled(false);
        }
        tickerTask.cancel();
    }

    public static void registerModule(Module module) {
        Plugin plugin = module.getPlugin();
        List<Module> modules;
        if(registeredModules.containsKey(plugin)) {
            modules = registeredModules.get(plugin);
        } else {
            modules = new ArrayList<Module>();
        }
        modules.add(module);
        registeredModules.put(plugin, modules);
        module.onRegister();
        if(module.shouldEnableOnRegister()) module.setEnabled(true);
        Core.instance.getLogger().fine("Registered Module \"" + module.getName() + "\n by plugin \"" + plugin.getName() + "\"");
    }

    public static List<Module> getModules() {
        List<Module> modules = new ArrayList<Module>();
        for(Map.Entry<Plugin, List<Module>> entry : registeredModules.entrySet()) {
            for(Module module : entry.getValue()) {
                modules.add(module);
            }
        }
        return modules;
    }

    public static List<Module> getModules(Plugin plugin) throws NoSuchPluginRegisteredException {
        if(!registeredModules.containsKey(plugin)) throw new NoSuchPluginRegisteredException("No plugin named \"" + plugin.getName() + " registered");
        List<Module> modules = new ArrayList<Module>();
        for(Map.Entry<Plugin, List<Module>> entry : registeredModules.entrySet()) {
            if(!entry.getKey().equals(plugin)) continue;
            for(Module module : entry.getValue()) {
                modules.add(module);
            }
        }
        return modules;
    }

    public static void enableModule(Plugin plugin, Module module) throws NoSuchPluginRegisteredException, NoSuchModuleForThatPluginRegisteredException {
        if(!registeredModules.containsKey(plugin)) throw new NoSuchPluginRegisteredException("No plugin named \"" + plugin.getName() + " registered");
        if(!registeredModules.get(plugin).contains(module)) throw new NoSuchModuleForThatPluginRegisteredException("No module named " + module.getName() + " registered for plugin " + plugin.getName());
        List<Module> pluginModules = getModules(plugin);
        for(Module m : pluginModules) {
            if(m.equals(module)) {
                m.setEnabled(true);
                return;
            }
        }
    }

    public static void disableModule(Plugin plugin, Module module) throws NoSuchPluginRegisteredException, NoSuchModuleForThatPluginRegisteredException {
        if(!registeredModules.containsKey(plugin)) throw new NoSuchPluginRegisteredException("No plugin named \"" + plugin.getName() + " registered");
        if(!registeredModules.get(plugin).contains(module)) throw new NoSuchModuleForThatPluginRegisteredException("No module named " + module.getName() + " registered for plugin " + plugin.getName());
        List<Module> pluginModules = getModules(plugin);
        for(Module m : pluginModules) {
            if(m.equals(module)) {
                m.setEnabled(false);
                return;
            }
        }
    }
}
