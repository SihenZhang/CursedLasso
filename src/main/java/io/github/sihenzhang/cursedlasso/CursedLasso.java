package io.github.sihenzhang.cursedlasso;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "cursedlasso", name = "CursedLasso", version = "0.0.0")
public class CursedLasso {
    @SidedProxy(clientSide="io.github.sihenzhang.cursedlasso.ClientProxy",serverSide="io.github.sihenzhang.cursedlasso.CommonProxy")
    static public CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }
}