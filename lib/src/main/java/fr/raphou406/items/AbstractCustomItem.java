package fr.raphou406.items;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractCustomItem {

    public static final NamespacedKey ID_KEY = new NamespacedKey("admin-abuse", "custom_item");

    private final String id;

    protected AbstractCustomItem(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    /** Construit l'item de base (matériau, nom, lore...). */
    protected abstract ItemStack build();

    /** Recette associée, null si aucune. */
    public Recipe recipe(JavaPlugin plugin) {
        return null;
    }

    /** Comportement au clic droit, vide par défaut. */
    public void onRightClick(Player player, ItemStack item, PlayerInteractEvent event) {
    }

    /** Item final avec le tag d'identification. */
    public final ItemStack create() {
        ItemStack item = build();
        item.editMeta(meta ->
                meta.getPersistentDataContainer().set(ID_KEY, PersistentDataType.STRING, id));
        return item;
    }

    /** Lit l'id custom d'un ItemStack, null si ce n'en est pas un. */
    public static String idOf(ItemStack stack) {
        if (stack == null || !stack.hasItemMeta()) return null;
        return stack.getItemMeta().getPersistentDataContainer()
                .get(ID_KEY, PersistentDataType.STRING);
    }
}