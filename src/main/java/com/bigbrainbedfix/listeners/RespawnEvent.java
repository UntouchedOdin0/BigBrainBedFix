package com.bigbrainbedfix.listeners;

import com.bigbrainbedfix.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.File;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class RespawnEvent implements Listener {

    Main main = getPlugin(Main.class);
    File bedFile = new File(main.getDataFolder(), "beds.yml");
    YamlConfiguration bedConfig = YamlConfiguration.loadConfiguration(bedFile);

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (bedConfig.contains("respawn-location." + player.getUniqueId())) {
            String worldname = bedConfig.getString(player.getUniqueId().toString() + ".world");
            double x = bedConfig.getDouble(player.getUniqueId().toString() + ".x");
            double y = bedConfig.getDouble(player.getUniqueId().toString() + ".y");
            double z = bedConfig.getDouble(player.getUniqueId().toString() + ".z");
            if (worldname != null) {
                World world = Bukkit.getWorld(worldname);
                Location loc = new Location(world, x, y, z);
                player.sendMessage("Respawning you at your bed location of " + loc);
                player.teleport(loc);
            }
        }
    }
}
