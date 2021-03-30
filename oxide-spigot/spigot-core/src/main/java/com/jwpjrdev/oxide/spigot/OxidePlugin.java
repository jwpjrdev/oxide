package com.jwpjrdev.oxide.spigot;

import com.jwpjrdev.oxide.common.DatabaseHandler;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class OxidePlugin extends JavaPlugin {
    
    @Getter(AccessLevel.PUBLIC)
    private static OxidePlugin instance;
    
    private DatabaseHandler databaseHandler;
    
    @Override
    public void onEnable() {
        // databaseHandler = new DatabaseHandler("", "", 0, MongoCredential.createCredential(null, null, null, null));
    }
    
    @Override
    public void onDisable() {
    }
}
