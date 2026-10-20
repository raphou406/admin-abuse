package fr.raphou406.commands.tests;

import org.bukkit.entity.Player;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;



public class HelloPlayer {
    private final String name = "hello";


    public LiteralCommandNode<CommandSourceStack> build(){
        return  Commands.literal(name)
            .then(Commands.argument("player", ArgumentTypes.player())
                .executes(this::run))
            .build();
    }

    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver  resolver = context.getArgument("player", PlayerSelectorArgumentResolver .class);
        final Player player = resolver.resolve(context.getSource()).getFirst();
        
        System.out.println(player.getName());
        System.out.println("Hello "+player.getName()+" !");
        context.getSource().getSender()
            .sendMessage(Component.text("Hello "+player.getName()+" !")
            .color(NamedTextColor.AQUA));
        return com.mojang.brigadier.Command.SINGLE_SUCCESS;
    }
}