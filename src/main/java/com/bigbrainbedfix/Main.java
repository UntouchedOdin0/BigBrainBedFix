package com.bigbrainbedfix;

import com.bigbrainbedfix.listeners.BedInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    private FileConfiguration bedsConfig;
    private Main main;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Loading BigBrainBedFix...");
        getServer().getPluginManager().registerEvents(new BedInteractEvent(), this);
        createAndSaveBedsFile();
        main = this;
    }

    private void createAndSaveBedsFile() {
        File bedFile = new File(getDataFolder(), "beds.yml");
        if (!bedFile.exists()) {
            bedFile.getParentFile().mkdirs();
            try {
                bedsConfig.save(bedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bedsConfig = YamlConfiguration.loadConfiguration(bedFile);
    }

    public Main getMain() {
        return main;
    }
}
