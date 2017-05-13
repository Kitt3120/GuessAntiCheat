package de.kitt3120.guessanticheat.utils;

import de.kitt3120.guessanticheat.Core;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

/**
 * Created by kitt3120 on 14.05.2017 at 00:48.
 */
public class LocationUtils {

    private static HashMap<Player, Location> lastTickLocations = new HashMap<Player, Location>();
    private static HashMap<Player, Location> lastSecondLocations = new HashMap<Player, Location>();
    private static HashMap<Player, Location> lastFiveSecondLocations = new HashMap<Player, Location>();
    private static HashMap<Player, Location> lastGroundLocation = new HashMap<Player, Location>();

    private static BukkitTask lastTickLocationUpdater;
    private static BukkitTask lastSecondLocationUpdater;
    private static BukkitTask lastFiveSecondLocationUpdater;

    public static void setup() {
        lastTickLocationUpdater = Core.instance.getServer().getScheduler().runTaskTimer(Core.instance, new Runnable() {
            @Override
            public void run() {
                for(Player player : Core.instance.getServer().getOnlinePlayers()) {
                    lastTickLocations.put(player, player.getLocation());
                    if(((LivingEntity)player).isOnGround()) { //Player.isOnGround() is deprecated so we use the LivingEntity one
                        lastGroundLocation.put(player, player.getLocation());
                    }
                }
            }
        }, 0, 0);
        lastSecondLocationUpdater = Core.instance.getServer().getScheduler().runTaskTimer(Core.instance, new Runnable() {
            @Override
            public void run() {
                for(Player player : Core.instance.getServer().getOnlinePlayers()) {
                    lastSecondLocations.put(player, player.getLocation());
                }
            }
        }, 0, 20L);
        lastFiveSecondLocationUpdater = Core.instance.getServer().getScheduler().runTaskTimer(Core.instance, new Runnable() {
            @Override
            public void run() {
                for(Player player : Core.instance.getServer().getOnlinePlayers()) {
                    lastFiveSecondLocations.put(player, player.getLocation());
                }
            }
        }, 0, 100L);
    }

    public static void onDisable() {
        lastTickLocationUpdater.cancel();
        lastSecondLocationUpdater.cancel();
        lastFiveSecondLocationUpdater.cancel();
    }

    public static Location getLastTickLocation(Player player) throws NullPointerException {
        if(!lastTickLocations.containsKey(player)) throw new NullPointerException();
        return lastTickLocations.get(player);
    }

    public static Location getSecondTickLocation(Player player) throws NullPointerException {
        if(!lastSecondLocations.containsKey(player)) throw new NullPointerException();
        return lastSecondLocations.get(player);
    }

    public static Location getLastFiveSecondLocation(Player player) throws NullPointerException {
        if(!lastFiveSecondLocations.containsKey(player)) throw new NullPointerException();
        return lastFiveSecondLocations.get(player);
    }

    public static Location getLastGroundLocation(Player player) throws NullPointerException {
        if(!lastGroundLocation.containsKey(player)) throw new NullPointerException();
        return lastGroundLocation.get(player);
    }

}
