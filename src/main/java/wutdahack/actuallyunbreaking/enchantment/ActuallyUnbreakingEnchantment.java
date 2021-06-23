package wutdahack.actuallyunbreaking.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentMending;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import wutdahack.actuallyunbreaking.AUConfig;

// this enchantment is an UnbreakingEnchantment like enchantment

public class ActuallyUnbreakingEnchantment extends Enchantment {

    public ActuallyUnbreakingEnchantment(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel)
    {
        return 5 + (enchantmentLevel - 1) * 8;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel)
    {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    @Override
    public int getMaxLevel() {
        if (AUConfig.maxLevelOnly) {
                return 3;
        } else if (!AUConfig.maxLevelOnly) {
            return 1;
        }
        return 1;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.isItemStackDamageable() || super.canApply(stack);
    }

    // items can't have mending and unbreaking together
    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof EnchantmentMending) && super.canApplyTogether(ench);
    }

    public static boolean preventDamage(ItemStack stack) {

        int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.UNBREAKING, stack);

        if (AUConfig.maxLevelOnly) {
            if (level ==  ModEnchantments.UNBREAKING.getMaxLevel() || level == ModEnchantments.UNBREAKING.getMaxLevel() * 2) {
                return true;
            } else if (level < 3) {
                return false;
            }
        }  else if (!AUConfig.maxLevelOnly && level > 0) {
            return true;
        }

        return false;
    }

}
