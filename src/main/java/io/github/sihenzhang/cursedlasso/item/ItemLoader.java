package io.github.sihenzhang.cursedlasso.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;


public class ItemLoader {
    public static Item ItemCursedLasso;
    public ItemLoader(){
        ItemCursedLasso = new ItemCursedLasso();
        GameRegistry.registerItem(ItemCursedLasso, "cursed_lasso");
    }
}
