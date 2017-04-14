package io.github.sihenzhang.cursedlasso;

import io.github.sihenzhang.cursedlasso.item.ItemLoader;

public class CommonProxy {

    public void preInit() {
    	new ItemLoader();
    }

    public void init() {
    }

    public void posInit() {
    }
}
