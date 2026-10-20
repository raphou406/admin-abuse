package org.raphou406;

import org.bukkit.plugin.java.JavaPlugin;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class AdminAbuse extends JavaPlugin {
    
    @Override
    public void onEnable() {

        LiteralCommandNode<CommandSourceStack> buildCommand = Commands.literal("helloWorld")
            .executes(context -> {
                System.out.println("HelloWorld !");
                context.getSource().getSender().sendMessage(Component.text("Hello World !")
                                     .color(NamedTextColor.AQUA));
                return Command.SINGLE_SUCCESS;
            })
            .build();

        final BasicCommand basicCommand = new BasicCommand() {

            @Override
            public void execute(CommandSourceStack commandSourceStack, String[] args) throws CommandSyntaxException {
                System.out.println("Basic Command !");
            }
            
        };

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register("basics", basicCommand);
        });

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(buildCommand);
        });
        System.out.println("AdminAbusePlugin loaded !");
    }
}
