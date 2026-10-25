package fr.raphou406;

import org.bukkit.plugin.java.JavaPlugin;

import fr.raphou406.commands.CommandRegistry;
import fr.raphou406.items.*;
import fr.raphou406.listener.CustomItemListener;

public class AdminAbuse extends JavaPlugin {
    private final ItemRegistry iRegistry = new ItemRegistry();
    
    @Override
    public void onEnable() {

        iRegistry.register(this, 
            new RubySword(),
            new Jumper()
        );

        getServer().getPluginManager().registerEvents(new CustomItemListener(iRegistry), this);
        CommandRegistry.registerAll(this, iRegistry);

        System.out.println("AdminAbusePlugin loaded !");
    }
}
