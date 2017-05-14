package de.kitt3120.guessanticheat.modules.modules;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitTask;

import de.kitt3120.guessanticheat.Core;
import de.kitt3120.guessanticheat.modules.Module;

public class AntiSpam extends Module{

	private BukkitTask resetTask;
	private HashMap<Player,Integer> counters =  new HashMap<>();
	
	public AntiSpam() {
		super(Core.instance, "AntiSpam", "Blocks spam", true);
	}
	@Override
	public void onRegister() {
		
	}
	@Override
	public void onEnable() {
		resetTask = Core.instance.getServer().getScheduler().runTaskTimerAsynchronously(getPlugin(), new Runnable(){
			@Override
			public void run() {
				counters.clear();
			}
		}, 0, 3*20L);
	}
	@Override
	public void onDisable() {
		counters.clear();
		resetTask.cancel();
	}
	
	@EventHandler 
	public void onchat(AsyncPlayerChatEvent e){
		if(!e.isCancelled() && counters.containsKey(e.getPlayer()) && counters.get(e.getPlayer()) > 2) e.setCancelled(true);
		int c = 0;
		if(counters.containsKey(e.getPlayer())) c=counters.get(e.getPlayer());
		c++;
		counters.put(e.getPlayer(), c);
	}
	
	
	
	
	
	@Override
	public void onTick() {
		
	}
	@Override
	public Listener getListener() {
		return this;
	}
}
