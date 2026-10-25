package fr.raphou406.items;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.raphou406.commands.Jump;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class Jumper extends AbstractCustomItem{

    public Jumper() {
        super("jumper");
    }

    @Override
    protected ItemStack build() {
        ItemStack item = new ItemStack(Material.NAUTILUS_SHELL);
        item.editMeta(meta -> {
            meta.displayName(Component.text("JUMP", NamedTextColor.WHITE)
                    .decoration(TextDecoration.ITALIC, false));
            meta.lore(List.of(Component.text("JUMP HIGHER", NamedTextColor.GRAY)
                    .decoration(TextDecoration.ITALIC, false)));
        });
        return item;
    }

    @Override
    public void onRightClick(Player player, ItemStack item, PlayerInteractEvent event) {
        List<Entity> targets = Bukkit.selectEntities(player, "@e[distance=..5]");
        targets.forEach(e -> Jump.jump(e, 2));
    }
}
