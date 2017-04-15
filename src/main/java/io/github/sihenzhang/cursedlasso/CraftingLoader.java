package io.github.sihenzhang.cursedlasso;

import io.github.sihenzhang.cursedlasso.item.ItemLoader;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import io.github.sihenzhang.cursedlasso.item.ItemCursedLasso;
import io.github.sihenzhang.cursedlasso.CommonProxy;

public class CraftingLoader {
	public CraftingLoader(){
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.ItemCursedLasso), new Object[]{"aba","bcb","aba",'a',Items.ghast_tear,'b',Items.string,'c',Items.ender_eye});
	}
}
