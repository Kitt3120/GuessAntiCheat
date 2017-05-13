package de.kitt3120.guessanticheat.modules.modules;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import de.kitt3120.guessanticheat.Core;
import de.kitt3120.guessanticheat.modules.Module;
import de.kitt3120.guessanticheat.utils.LocationUtils;

public class WaterWalk extends Module{

	public WaterWalk() {
		super(Core.instance, "WaterWalk", "Disable the Jesus/WaterWalk bypass", true);
	}

	@Override
	public void onRegister() {	}

	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
	}

	@Override
	public void onTick() {		
	}
	
	@EventHandler
	public void waterwalk(PlayerMoveEvent e){
		if(getFlat(e.getPlayer(), 3) == true){
			return;
		}
		if(e.getPlayer().getGameMode() == GameMode.CREATIVE){
			return;
		}
		if(e.getPlayer().isOp()){
			return;
		}
		if (e.getFrom().getY() - e.getTo().getY() == 0
				&& e.getPlayer().getLocation().subtract(0, 1, 0).getBlock().isLiquid()
				&& e.getPlayer().getLocation().getBlock().getType() == Material.AIR) {
			e.getPlayer().sendMessage("§4§lWaterWalk");
			
			e.getPlayer().teleport(LocationUtils.getLastGroundLocation(e.getPlayer()));
			
			
			
		}
	}
	public boolean getFlat(Player p,int radius){
		for(int x=p.getLocation().getBlockX(); x<p.getLocation().getBlockX() + radius; x++) {
			  for(int z=p.getLocation().getBlockZ(); z<p.getLocation().getBlockZ() + radius; z++) {
				  if(!p.getLocation().getWorld().getBlockAt(x-radius/2, p.getLocation().subtract(0, 1, 0).getBlockY(), z-radius/2).isLiquid()){
					  return true;
				  }
			 }
		}
		return false;
	}

}
