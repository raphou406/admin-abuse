package org.raphou406;

import org.bukkit.plugin.java.JavaPlugin;
import org.raphou406.commands.CommandRegistry;

public class AdminAbuse extends JavaPlugin {
    
    @Override
    public void onEnable() {
        CommandRegistry.registerAll(this);

        System.out.println("AdminAbusePlugin loaded !");
    }
}
