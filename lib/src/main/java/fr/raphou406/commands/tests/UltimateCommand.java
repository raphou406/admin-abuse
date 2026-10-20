package fr.raphou406.commands.tests;

import java.util.concurrent.CompletableFuture;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;

import fr.raphou406.commands.IBuild;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class UltimateCommand implements IBuild{

    @Override
    public LiteralCommandNode<CommandSourceStack> build() {
        return Commands.literal("ultimate")
            .requires(this::require)
            .then(
                Commands.argument("brainRot", StringArgumentType.word())
                    .suggests(this::suggestion)
                    .executes(this::run)
                )
            .executes(this::fallback)
            .build();
    }

    private boolean require(CommandSourceStack source){
        return source.getSender().isOp()
                && source.getSender() instanceof Player player
                && player.getInventory().contains(Material.DIAMOND);
    }

    private CompletableFuture<Suggestions> suggestion(final CommandContext<CommandSourceStack> context, final SuggestionsBuilder builder) {
        builder.suggest("TripleT");
        builder.suggest("CapuccinaBalerina");
        builder.suggest("BombardinoCrocodilo");
        return builder.buildFuture();
    }
    
    public int run(CommandContext<CommandSourceStack> context) {
        String brainRot = context.getArgument("brainRot", String.class);

        Component message = Component.text(brainRot + " Will eradicate your world in 5 sec")
                                     .color(NamedTextColor.RED)
                                     .decorate(TextDecoration.BOLD);
        context.getSource().getSender().sendMessage(message);

        return Command.SINGLE_SUCCESS;
    }

    public int fallback(CommandContext<CommandSourceStack> context) {
        context.getSource().getSender()
            .sendMessage(Component.text("Dude... Realy ?")
            .color(NamedTextColor.GRAY));
        return com.mojang.brigadier.Command.SINGLE_SUCCESS;
    }
}
