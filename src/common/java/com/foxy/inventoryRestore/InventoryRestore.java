package com.foxy.inventoryRestore;

import com.foxy.inventoryRestore.bootstrap.PluginBootstrap;
import com.foxy.inventoryRestore.version.VersionAdapter;
import com.foxy.inventoryRestore.version.VersionAdapterLoader;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryRestore extends JavaPlugin {

    private static final int BSTATS_PLUGIN_ID = 28013;
    private PluginBootstrap bootstrap;
    private VersionAdapter versionAdapter;

    @Override
    public void onEnable() {
        versionAdapter = VersionAdapterLoader.load(this);
        getLogger().info("Using compatibility adapter: " + versionAdapter.versionName());
        versionAdapter.onEnable(this);

        String pluginName = getDescription().getName();
        String version = getDescription().getVersion();
        String author = getDescription().getAuthors().isEmpty()
                ? "Unknown"
                : String.join(", ", getDescription().getAuthors());

        String line = "----------------------------------------------";
        getServer().getConsoleSender().sendMessage(line);
        getServer().getConsoleSender().sendMessage("[" + pluginName + "] Version: " + version);
        getServer().getConsoleSender().sendMessage("[" + pluginName + "] Author: " + author);
        getServer().getConsoleSender().sendMessage("[" + pluginName + "] Enjoy the plugin! :)");
        getServer().getConsoleSender().sendMessage(line);

        bootstrap = new PluginBootstrap(this);
        bootstrap.enable();

        new Metrics(this, BSTATS_PLUGIN_ID);
    }

    @Override
    public void onDisable() {
        if (bootstrap != null) {
            bootstrap.disable();
        }
        if (versionAdapter != null) {
            versionAdapter.onDisable();
            versionAdapter = null;
        }
    }
}
