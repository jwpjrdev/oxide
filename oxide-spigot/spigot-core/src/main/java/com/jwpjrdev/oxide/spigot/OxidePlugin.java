package com.jwpjrdev.oxide.spigot;

import com.jwpjrdev.oxide.common.DatabaseHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class OxidePlugin extends JavaPlugin {
    
    private static OxidePlugin instance;
    
    private DatabaseHandler databaseHandler;
    
    public static OxidePlugin getInstance() {
        return OxidePlugin.instance;
    }
    
    @Override
    public void onEnable() {
        // databaseHandler = new DatabaseHandler("", "", 0, MongoCredential.createCredential(null, null, null, null));
    }
    
    @Override
    public void onDisable() {
    }
}
