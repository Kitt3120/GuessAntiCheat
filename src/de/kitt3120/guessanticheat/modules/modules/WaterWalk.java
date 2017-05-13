package de.kitt3120.guessanticheat.modules.modules;

import de.kitt3120.guessanticheat.Core;
import de.kitt3120.guessanticheat.modules.Module;
import de.kitt3120.guessanticheat.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WaterWalk extends Module{

	public WaterWalk() {
		super(Core.instance, "WaterWalk", "Disable the Jesus/WaterWalk bypass", true);
	}

	@Override
	public void onRegister() {	
		
	}

	@Override
	public void onEnable() {
		
	}

	@Override
	public void onDisable() {
	}

	@Override
	public void onTick() {		
	}

	@Override
	public Listener getListener() {
		return this;
	}
	
	@EventHandler
	public void waterwalk(PlayerMoveEvent e){
		Player player = e.getPlayer();
		Location location = player.getLocation();

		if(location.distance(LocationUtils.getLastGroundLocation(player)) > 3) {
			Block belowBlock = player.getLocation().getBlock();
			while(belowBlock.getLocation().getY() > 0 && !belowBlock.getType().equals(Material.AIR)) {
				belowBlock = belowBlock.getRelative(BlockFace.DOWN);
			}
			if(belowBlock.getType().equals(Material.WATER) || belowBlock.getType().equals(Material.STATIONARY_WATER)) {
				if(belowBlock.getLocation().distance(player.getLocation()) < 2) {
					if(LocationUtils.getLastSecondLocation(player).getBlock().getType().equals(Material.AIR) || player.getLocation().getBlock().getType().equals(Material.AIR)) {
						player.teleport(LocationUtils.getLastGroundLocation(player));
					}
				}
			}
		}
	}

}
