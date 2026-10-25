package fr.raphou406.commands;

import org.bukkit.plugin.java.JavaPlugin;

import fr.raphou406.commands.tests.TestCommandRegistery;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

public final class CommandRegistry {
    public static void registerAll(JavaPlugin plugin) {
        TestCommandRegistery.registerAll(plugin);

        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            Commands registrar = event.registrar();
            registrar.register(new Jump().build());
        });
    }
}