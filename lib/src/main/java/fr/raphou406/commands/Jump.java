package fr.raphou406.commands;

import java.util.List;

import org.bukkit.Particle;
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

        entities.forEach(e -> Jump.jump(e, force));
        return  1;
    }

    public static void jump(Entity entity, float force){
        entity.setVelocity(new Vector(0, 1 * force, 0));
        entity.playSound(Sound.sound(Key.key("entity.wind_charge.wind_burst"), Sound.Source.AMBIENT, 1f, 1f));
        Particle.GUST_EMITTER_SMALL.builder()
            .location(entity.getLocation())
            .count(1)
            .spawn();
    }
}
