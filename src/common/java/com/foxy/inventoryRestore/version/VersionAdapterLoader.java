package com.foxy.inventoryRestore.version;

import com.foxy.inventoryRestore.InventoryRestore;
import org.bukkit.Bukkit;

public final class VersionAdapterLoader {

    private static final String ADAPTER_PACKAGE = "com.foxy.inventoryRestore.";

    private VersionAdapterLoader() {
    }

    public static VersionAdapter load(InventoryRestore plugin) {
        String minecraftVersion = Bukkit.getBukkitVersion().split("-", 2)[0];
        String adapterPackage;

        if (minecraftVersion.startsWith("1.21.") || minecraftVersion.equals("1.21")) {
            adapterPackage = "v1_21";
        } else if (minecraftVersion.startsWith("26.1.") || minecraftVersion.equals("26.1")) {
            adapterPackage = "v26_1";
        } else if (minecraftVersion.startsWith("26.2.") || minecraftVersion.equals("26.2")) {
            adapterPackage = "v26_2";
        } else if (minecraftVersion.startsWith("26.3.") || minecraftVersion.equals("26.3")) {
            adapterPackage = "v26_3";
        } else {
            throw new IllegalStateException("Unsupported Minecraft version " + minecraftVersion
                    + ". Supported versions are 1.21.x, 26.1.x, 26.2.x, and 26.3.x.");
        }

        String className = ADAPTER_PACKAGE + adapterPackage + ".PlatformAdapter";
        try {
            Class<? extends VersionAdapter> adapterClass = Class.forName(
                    className,
                    true,
                    plugin.getClass().getClassLoader()
            ).asSubclass(VersionAdapter.class);
            return adapterClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Failed to load the compatibility adapter for "
                    + minecraftVersion + " (" + className + ").", exception);
        }
    }
}
