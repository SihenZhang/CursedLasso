package io.github.sihenzhang.cursedlasso;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import io.github.sihenzhang.cursedlasso.item.ItemCursedLasso;
import io.github.sihenzhang.cursedlasso.CommonProxy;

public class CraftingLoader {
	public CraftingLoader(){
		GameRegistry.addShapedRecipe(new ItemStack(CommonProxy.ItemCursedLasso), new Object[]{"aba","bcb","aba",'a',Items.ghast_tear,'b',Items.string,'c',Items.ender_eye});
	}
}
