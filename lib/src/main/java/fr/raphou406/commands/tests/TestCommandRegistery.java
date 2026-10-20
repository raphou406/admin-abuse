package fr.raphou406.commands.tests;

import org.bukkit.plugin.java.JavaPlugin;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

public final class TestCommandRegistery {
    public static void registerAll(JavaPlugin plugin) {
        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            Commands registrar = event.registrar();
            registrar.register(new HelloWorld().build());
            registrar.register(new HelloPlayer().build());
            registrar.register(new Root().build());
            registrar.register(new UltimateCommand().build()); // must be OP and have a diamon in your inv
            registrar.register(GiveItemCommand.build());
        });
    }
}
