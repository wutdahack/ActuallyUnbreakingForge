package wutdahack.actuallyunbreaking.enchantment;

import me.shedaniel.autoconfig.AutoConfig;
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

        if (config.level3Only) {
            return 3;
        } else if (!config.level3Only) {
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

        if (config.level3Only) {
            if (level == 3) {
                return true;
            } else if (level < 3) {
                UnbreakingEnchantment.negateDamage(stack, level, random);
            }

        }  else if (!config.level3Only && level > 0) {
            return true;
        }

        return false;
    }

}
