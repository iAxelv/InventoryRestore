package com.foxy.inventoryRestore.v1_21;

import com.foxy.inventoryRestore.version.VersionAdapter;

public final class PlatformAdapter implements VersionAdapter {

    @Override
    public String versionName() {
        return "Minecraft 1.21.x / Paper 1.21";
    }
}
