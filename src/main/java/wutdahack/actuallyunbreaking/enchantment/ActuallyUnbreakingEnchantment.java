package wutdahack.actuallyunbreaking.enchantment;

import me.shedaniel.autoconfig1u.AutoConfig;
import net.minecraft.enchantment.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import wutdahack.actuallyunbreaking.AUConfig;

import java.util.Random;

// this enchantment is an UnbreakingEnchantment like enchantment

public class ActuallyUnbreakingEnchantment extends Enchantment {

    static AUConfig config = AutoConfig.getConfigHolder(AUConfig.class).getConfig();

    public ActuallyUnbreakingEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 5 + (enchantmentLevel - 1) * 8;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    public int getMaxLevel() {

        if (config.maxLevelOnly) {
            return 3;
        } else if (!config.maxLevelOnly) {
            return 1;
        }
        return 1;
    }

    public boolean canApply(ItemStack stack) {
        return stack.isDamageable() || super.canApply(stack);
    }

    // items can't have mending and unbreaking together
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof MendingEnchantment) && super.canApplyTogether(ench);
    }

    public static boolean preventDamage(ItemStack stack) {

        int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.UNBREAKING.get(), stack);

        if (config.maxLevelOnly) {

            if (level == ModEnchantments.UNBREAKING.get().getMaxLevel() || level == ModEnchantments.UNBREAKING.get().getMaxLevel() * 2) {
                return true;
            } else if (level < ModEnchantments.UNBREAKING.get().getMaxLevel()) {
                return false;
            }
        }  else if (!config.maxLevelOnly && level > 0) {
            return true;
        }

        return false;
    }

}
