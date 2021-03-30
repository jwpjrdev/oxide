package com.jwpjrdev.oxide.proxy.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

@Plugin(
        id = "proxy-velocity",
        name = "Oxide",
        version = "${pluginVersion}",
        description = "Server management suite for Spigot.",
        authors = {"jwpjr"}
)
public class OxidePlugin {
    
    @Inject
    private Logger logger;
    
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }
}
