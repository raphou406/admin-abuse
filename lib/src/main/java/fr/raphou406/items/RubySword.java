package fr.raphou406.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class RubySword extends AbstractCustomItem {

    public RubySword() {
        super("ruby_sword");
    }

    @Override
    protected ItemStack build() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        item.editMeta(meta -> {
            meta.displayName(Component.text("Épée de rubis", NamedTextColor.RED)
                    .decoration(TextDecoration.ITALIC, false));
            meta.lore(List.of(Component.text("Forgée à la main", NamedTextColor.GRAY)
                    .decoration(TextDecoration.ITALIC, false)));
        });
        return item;
    }

    @Override
    public Recipe recipe(JavaPlugin plugin) {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, id()), create());
        recipe.shape(" R ", " R ", " S ");
        recipe.setIngredient('R', Material.REDSTONE_BLOCK);
        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    @Override
    public void onRightClick(Player player, ItemStack item, PlayerInteractEvent event) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
        player.sendMessage(Component.text("Vitesse !", NamedTextColor.RED));
    }
}