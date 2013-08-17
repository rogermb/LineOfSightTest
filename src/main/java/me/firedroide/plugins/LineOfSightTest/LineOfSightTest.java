package me.firedroide.plugins.LineOfSightTest;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class LineOfSightTest extends JavaPlugin implements Runnable {
	
	@Override
	public void onEnable() {
		Bukkit.getScheduler().runTaskTimer(this, this, 200, 200);
	}
	
	@Override
	public void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
	}
	
	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			List<Entity> nearby = p.getNearbyEntities(10, 10, 10);
			for (Entity entity : nearby) {
				String type = entity.getType().getName();
				double distance = p.getLocation().distance(entity.getLocation());
				boolean lineOfSight = p.hasLineOfSight(entity);
				StringBuilder sb = new StringBuilder();
				sb.append("Entity type: ").append(type);
				sb.append("; distance: ").append(String.valueOf(distance).substring(0, 5));
				sb.append("; Line of sight: ").append(lineOfSight);
				p.sendMessage(sb.toString());
			}
		}
	}
}
