package org.raphou406.commands.tests;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;

public final class Root {
    
    public LiteralCommandNode<CommandSourceStack> build(){
        return Commands.literal("root")
                .then(Commands.literal("a1").executes(this::a1))
                .then(Commands.literal("a2").executes(event -> {return a2(event);}))
                .build();
    }

    private int a1(CommandContext<CommandSourceStack> event){
        event.getSource().getExecutor().sendMessage(Component.text("Event a1"));
        return  1;
    }
    
    private int a2(CommandContext<CommandSourceStack> event){
        event.getSource().getExecutor().sendMessage(Component.text("Event a2"));
        return  1;
    }
}
