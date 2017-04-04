package io.github.sihenzhang.cursedlasso;

import cpw.mods.fml.common.registry.GameRegistry;
import io.github.sihenzhang.cursedlasso.item.ItemCursedLasso;

public class CommonProxy {
    public void preInit() {
        GameRegistry.registerItem(new ItemCursedLasso(), "cursed_lasso");
    }

    public void init() {
    }

    public void posInit() {
    }
}
