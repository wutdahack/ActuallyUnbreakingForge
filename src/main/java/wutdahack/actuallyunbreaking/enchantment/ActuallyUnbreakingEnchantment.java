package wutdahack.actuallyunbreaking.enchantment;

import net.minecraft.enchantment.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import wutdahack.actuallyunbreaking.AUConfig;

import java.util.Random;

// this enchantment is an UnbreakingEnchantment like enchantment

public class ActuallyUnbreakingEnchantment extends Enchantment {


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

        if (AUConfig.CONFIG.level3Only.get()) {
            return 3;
        } else if (!AUConfig.CONFIG.level3Only.get()) {
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

    public static boolean preventDamage(ItemStack stack, Random random) {

        int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.UNBREAKING.get(), stack);

        if (AUConfig.CONFIG.level3Only.get()) {
            if (level == 3) {
                return true;
            } else if (level < 3) {
                UnbreakingEnchantment.negateDamage(stack, level, random);
            }

        }  else if (!AUConfig.CONFIG.level3Only.get() && level > 0) {
            return true;
        }

        return false;
    }

}
