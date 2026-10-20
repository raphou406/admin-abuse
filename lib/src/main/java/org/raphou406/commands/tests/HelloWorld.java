package org.raphou406.commands.tests;

import org.raphou406.commands.IBuild;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;



public class HelloWorld implements IBuild{
    private final String name = "helloworld";


    public LiteralCommandNode<CommandSourceStack> build(){
        return  Commands.literal(name)
            .executes(this::run).build();
    }

    public int run(CommandContext<CommandSourceStack> context) {
        System.out.println("HelloWorld !");
        context.getSource().getSender()
            .sendMessage(Component.text("Hello World !")
            .color(NamedTextColor.AQUA));
        return com.mojang.brigadier.Command.SINGLE_SUCCESS;
    }
}