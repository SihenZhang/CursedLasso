package io.github.sihenzhang.cursedlasso;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import io.github.sihenzhang.cursedlasso.item.ItemCursedLasso;

public class CommonProxy {
public static Item ItemCursedLasso;
    public void preInit() {
    	ItemCursedLasso = new ItemCursedLasso();
        GameRegistry.registerItem(ItemCursedLasso, "cursed_lasso");
    }

    public void init() {
    	new CraftingLoader();
    }

    public void posInit() {
    }
}
