package fr.raphou406.items;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class ItemRegistry {

    private final Map<String, AbstractCustomItem> items = new LinkedHashMap<>();

    public void register(JavaPlugin plugin, AbstractCustomItem... item) {
        for (AbstractCustomItem i : item) {
            items.put(i.id(), i);
            Recipe recipe = i.recipe(plugin);
            if (recipe != null) Bukkit.addRecipe(recipe);
        }
    }

    public Optional<AbstractCustomItem> get(String id) {
        return Optional.ofNullable(items.get(id));
    }

    public Collection<String> ids() {
        return items.keySet();
    }
}