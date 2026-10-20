package fr.raphou406.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;

import io.papermc.paper.command.brigadier.CommandSourceStack;

@FunctionalInterface 
public interface IBuild {

    LiteralCommandNode<CommandSourceStack> build();
}
