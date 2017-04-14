package io.github.sihenzhang.cursedlasso.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by serap on 2017/4/9.
 */
public class ItemLoader {
    public static Item ItemCursedLasso;
    public ItemLoader(){
        ItemCursedLasso = new ItemCursedLasso();
        GameRegistry.registerItem(ItemCursedLasso, "cursed_lasso");
    }
}
