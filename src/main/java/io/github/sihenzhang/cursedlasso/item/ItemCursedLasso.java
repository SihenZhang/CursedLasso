package io.github.sihenzhang.cursedlasso.item;

import com.typesafe.config.ConfigException;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Facing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import java.util.List;

public class ItemCursedLasso extends Item {
    public ItemCursedLasso() {
        setCreativeTab(CreativeTabs.tabTools);
        setUnlocalizedName("cursedlasso.cursed_lasso");
        setTextureName("cursedlasso:cursed_lasso");
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack item, int pass) {
        return item.hasTagCompound();
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack item, EntityPlayer player, EntityLivingBase entity) {
        if (entity.worldObj.isRemote) {
            return false;
        }
        if (entity instanceof IMob) {
            if (item.hasTagCompound())
                return false;
            item.setTagCompound(new NBTTagCompound());
            NBTTagCompound mainTag = new NBTTagCompound();
            NBTTagCompound entityTag = new NBTTagCompound();
            entity.writeToNBT(entityTag);
            mainTag.setFloat("health",entity.getHealth());
            mainTag.setTag("data", entityTag);
            mainTag.setString("id", EntityList.getEntityString(entity));
            if (entity instanceof EntitySlime) {
                mainTag.setInteger("slimesize", ((EntitySlime) entity).getSlimeSize());
            }
            if(entity instanceof EntityZombie){
                mainTag.setBoolean("isBabyZombie",entity.isChild());
            }
            item.getTagCompound().setTag("entity",mainTag);
            player.setCurrentItemOrArmor(0, item);
            entity.setDead();
            return true;
        }
        return super.itemInteractionForEntity(item, player, entity);
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }
        if (!item.hasTagCompound()) {
            return false;
        }
        if(player == null) {
            return false;
        }

        String entityId = item.stackTagCompound.getCompoundTag("entity").getString("id");
        Entity entityToSpawn = EntityList.createEntityByName(entityId, world);

        Block blk = world.getBlock(x,y,z);
        double spawnX = x + Facing.offsetsXForSide[facing] + 0.5;
        double spawnY = y + Facing.offsetsYForSide[facing];
        double spawnZ = z + Facing.offsetsZForSide[facing] + 0.5;
        if(facing == ForgeDirection.UP.ordinal() && (blk instanceof BlockFence || blk instanceof BlockWall)) {
            spawnY += 0.5;
        }
        if(entityToSpawn instanceof EntitySlime) {
            ((EntitySlime) entityToSpawn).setSlimeSize(item.stackTagCompound.getCompoundTag("entity").getInteger("slimesize"));
        }
        if(entityToSpawn instanceof EntityZombie){
            if(item.stackTagCompound.getCompoundTag("entity").getBoolean("isBabyZombie"))
                ((EntityZombie) entityToSpawn).setChild(true);
            else
                ((EntityZombie) entityToSpawn).setChild(false);
        }
        entityToSpawn.setLocationAndAngles(spawnX, spawnY, spawnZ, world.rand.nextFloat() * 360.0F, 0);
        world.spawnEntityInWorld(entityToSpawn);
        if(entityToSpawn instanceof EntityLiving) {
            ((EntityLiving)entityToSpawn).playLivingSound();
            ((EntityLiving)entityToSpawn).setHealth(item.stackTagCompound.getCompoundTag("entity").getFloat("health"));
        }

        Entity riddenByEntity = entityToSpawn.riddenByEntity;
        while(riddenByEntity != null) {
            riddenByEntity.setLocationAndAngles(spawnX, spawnY, spawnZ, world.rand.nextFloat() * 360.0F, 0.0F);
            world.spawnEntityInWorld(riddenByEntity);
            if(riddenByEntity instanceof EntityLiving) {
                ((EntityLiving)riddenByEntity).playLivingSound();
            }
            riddenByEntity = riddenByEntity.riddenByEntity;
        }
        item.setTagCompound(null);
        player.setCurrentItemOrArmor(0, item);
        return true;
    }

    public String getMobTypeFromStack(ItemStack item) {
        if(!item.hasTagCompound()) {
            return null;
        }
        if(item.stackTagCompound == null) {
            return null;
        }
        return item.stackTagCompound.getCompoundTag("entity").getString("id");

    }

    public static String getDisplayNameForEntity(String mobName) {
        return StatCollector.translateToLocal("entity." + mobName + ".name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if(par1ItemStack != null) {
            String mobName = getMobTypeFromStack(par1ItemStack);
            if(mobName != null) {
                par3List.add(getDisplayNameForEntity(mobName));
            }
        }
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
    }
}