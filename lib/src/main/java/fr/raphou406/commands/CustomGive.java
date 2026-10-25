package fr.raphou406.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

import net.kyori.adventure.text.Component;

import org.bukkit.entity.Player;

import fr.raphou406.items.ItemRegistry;

public class CustomGive implements IBuild {
    private ItemRegistry registry;

    public CustomGive(ItemRegistry registry) {
        this.registry = registry;
    }

    @Override
    public LiteralCommandNode<CommandSourceStack> build() {
        return Commands.literal("customgive")
                .requires(src -> src.getSender().hasPermission("monplugin.give"))
                .then(Commands.argument("id", StringArgumentType.word())
                        .suggests((ctx, builder) -> {
                            registry.ids().forEach(builder::suggest);
                            return builder.buildFuture();
                        })
                        .executes(ctx -> {
                            if (!(ctx.getSource().getExecutor() instanceof Player player)) {
                                ctx.getSource().getSender()
                                        .sendMessage(Component.text("Commande réservée aux joueurs."));
                                return 0;
                            }

                            String id = StringArgumentType.getString(ctx, "id");
                            registry.get(id).ifPresentOrElse(
                                    item -> player.getInventory().addItem(item.create()),
                                    () -> player.sendMessage(Component.text("Item inconnu : " + id)));
                            return Command.SINGLE_SUCCESS;
                        }))
                .build();
    }
}
