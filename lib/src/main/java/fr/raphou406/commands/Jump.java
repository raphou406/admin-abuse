package fr.raphou406.commands;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.EntitySelectorArgumentResolver;

public class Jump implements IBuild {

    @Override
    public LiteralCommandNode<CommandSourceStack> build() {
        return Commands.literal("jump")
            .then(
                Commands.argument("Entity", ArgumentTypes.entities())
                    .then(
                        Commands.argument("Force", FloatArgumentType.floatArg())
                        .executes(this::action)
                    )
                )
            .build();
    }

    private int action(CommandContext<CommandSourceStack> context){
        EntitySelectorArgumentResolver resolver = context.getArgument("Entity", EntitySelectorArgumentResolver.class);
        CommandSourceStack source = context.getSource();
        List<Entity> entities;
        float force = context.getArgument("Force", float.class);
        try {
            entities = resolver.resolve(source);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }

        

        for (Entity entity : entities) {
            entity.setVelocity(new Vector(0, 1 * force, 0));
            entity.playSound(Sound.sound(Key.key("block.potent_sulfur.geyser_eruption"), Sound.Source.AMBIENT, 1f, 1f));
        }

        return  1;
    }
}
