package wutdahack.actuallyunbreaking.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentMending;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

// this enchantment is an UnbreakingEnchantment like enchantment

public class ActuallyUnbreakingEnchantment extends Enchantment {

    public ActuallyUnbreakingEnchantment(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
    }

    public int getMinEnchantability(int enchantmentLevel)
    {
        return 5 + (enchantmentLevel - 1) * 8;
    }

    public int getMaxEnchantability(int enchantmentLevel)
    {
        return super.getMinEnchantability(enchantmentLevel) + 50;
    }

    public int getMaxLevel()
    {
        return 1;
    }

    public boolean canApply(ItemStack stack) {
        return stack.isItemStackDamageable() || super.canApply(stack);
    }

    // items can't have mending and unbreaking together
    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof EnchantmentMending) && super.canApplyTogether(ench);
    }

}
