package me.dess1rous.spleefduel.util;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HologramUtil {

    private final List<ArmorStand> entities = new ArrayList<>();
    private List<String> text;
    private final Location location;


    public HologramUtil(List<String> text, Location location) {
        this.text = text;
        this.location = location.add(new Vector(0, -2, 0));

        overrideHologram();
    }

    public void setText(List<String> text) {
        this.text = text;

        overrideHologram();
    }

    private void overrideHologram() {
        for (int i = 0; i < text.size(); i++) {
            if (entities.size() > i) {
                entities.get(i).setCustomName(ChatUtil.color(text.get(i)));
            }
            else {
                ArmorStand armorStand = location.getWorld().spawn(location.add(new Vector(0, i * 0.3, 0)), ArmorStand.class);

                armorStand.setCustomName(ChatUtil.color(text.get(i)));
                armorStand.setCustomNameVisible(true);
                armorStand.setGravity(false);
                armorStand.setVisible(false);
                armorStand.setInvulnerable(true);

                entities.add(armorStand);
            }
        }


        Iterator<ArmorStand> iterator = entities.subList(text.size(), entities.size()).stream().iterator();

        while (iterator.hasNext()) {
            iterator.next().remove();
        }
    }

    public void destroy() {
        for (ArmorStand armorStand: entities) {
            armorStand.remove();
        }
    }
}
