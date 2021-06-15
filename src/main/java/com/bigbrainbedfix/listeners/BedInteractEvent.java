package com.bigbrainbedfix.listeners;

import com.bigbrainbedfix.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;
import java.io.IOException;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class BedInteractEvent implements Listener {

    Main main = getPlugin(Main.class);
    File bedFile = new File(main.getDataFolder(), "beds.yml");
    YamlConfiguration bedConfig = YamlConfiguration.loadConfiguration(bedFile);

    int x;
    int y;
    int z;

    protected static final Material[] beds = {
            Material.WHITE_BED,
            Material.ORANGE_BED,
            Material.MAGENTA_BED,
            Material.LIGHT_BLUE_BED,
            Material.YELLOW_BED,
            Material.LIME_BED,
            Material.PINK_BED,
            Material.GRAY_BED,
            Material.LIGHT_GRAY_BED,
            Material.CYAN_BED,
            Material.PURPLE_BED,
            Material.BLUE_BED,
            Material.BROWN_BED,
            Material.GREEN_BED,
            Material.RED_BED
    };

    @EventHandler(priority = EventPriority.MONITOR)
    public void onRightClickBed(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getClickedBlock() != null) {
            Location location = event.getClickedBlock().getLocation();
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock() != null) {
                Material type = event.getClickedBlock().getType();
                for (Material material : beds) {
                    if (type.equals(material)) {
                        x = location.getBlockX();
                        y = location.getBlockY();
                        z = location.getBlockZ();

                        player.sendMessage(ChatColor.GREEN + "Setting your respawn location to (" + String.format("%d, %d, %d", x, y, z + ")"));
                        player.setBedSpawnLocation(location, true);
                        bedConfig.set("respawn-location." + player.getUniqueId(), location);
                    }
                }
                try {
                    bedConfig.save(bedFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
