package org.raphou406.commands.tests;

import java.util.concurrent.CompletableFuture;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class GiveItemCommand {

    public static LiteralCommandNode<CommandSourceStack> build() {
        // Create new command: /giveitem
        return Commands.literal("giveitem")

          // Declare a new ItemStack argument
          .then(Commands.argument("item", ArgumentTypes.itemStack())

              // Declare a new integer argument with the bounds of 1 to 99
              .then(Commands.argument("amount", IntegerArgumentType.integer(1, 99))

                  // Here, we defer to separate methods, since otherwise, our command definition would grow too big
                  // It also allows building optional arguments as shown below.
                  .suggests(GiveItemCommand::amountSuggestions)
                  .executes(context -> giveItem(
                      context,
                      context.getArgument("item", ItemStack.class),
                      IntegerArgumentType.getInteger(context, "amount")
                  ))
              )

              // Add a fallback path in case the amount is not specified which gives 64 items
              .executes(context -> giveItem(
                  context,
                  context.getArgument("item", ItemStack.class),
                  64
              ))
          )
          .build();
    }

    private static CompletableFuture<Suggestions> amountSuggestions(final CommandContext<CommandSourceStack> context, final SuggestionsBuilder builder) {
        // Suggest 1, 16, 32, and 64 to the user when they reach the 'amount' argument
        builder.suggest(1);
        builder.suggest(16);
        builder.suggest(32);
        builder.suggest(64);
        return builder.buildFuture();
    }

    private static int giveItem(
        final CommandContext<CommandSourceStack> context, final ItemStack item, final int amount
    ) {
        // We know that the executor will be a player, so we can just silently return.
        // Alternatively we could use CommandSourceStack#getPlayerOrThrow which will be used
        // in later chapters to simplify examples.
        if (!(context.getSource().getExecutor() instanceof Player player)) {
            return Command.SINGLE_SUCCESS;
        }

        // If the player has no empty slot, we tell the player that they have no free inventory space
        final int firstEmptySlot = player.getInventory().firstEmpty();
        if (firstEmptySlot == -1) {
            player.sendRichMessage("<light_purple>You do not have enough space in your inventory!");
            return Command.SINGLE_SUCCESS;
        }

        // Set the item's amount and give it to the player
        item.setAmount(amount);
        player.getInventory().setItem(firstEmptySlot, item);

        // Send a confirmation message
        player.sendRichMessage("<light_purple>You have been given <white><amount>x</white> <aqua><item></aqua>!",
            Placeholder.component("amount", Component.text(amount)),
            Placeholder.component("item", Component.translatable(item).hoverEvent(item))
        );
        return Command.SINGLE_SUCCESS;
    }
}