package fr.raphou406.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import fr.raphou406.items.AbstractCustomItem;
import fr.raphou406.items.ItemRegistry;

public class CustomItemListener implements Listener {

    private final ItemRegistry registry;

    public CustomItemListener(ItemRegistry registry) {
        this.registry = registry;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (!e.getAction().isRightClick()) return;

        ItemStack item = e.getItem();
        String id = AbstractCustomItem.idOf(item);
        if (id == null) return;

        registry.get(id).ifPresent(custom ->
                custom.onRightClick(e.getPlayer(), item, e));
    }
}