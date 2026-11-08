package com.foxy.inventoryRestore.version;

import com.foxy.inventoryRestore.InventoryRestore;

public interface VersionAdapter {

    String versionName();

    default void onEnable(InventoryRestore plugin) {
    }

    default void onDisable() {
    }
}
