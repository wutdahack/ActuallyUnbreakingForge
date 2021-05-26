package wutdahack.actuallyunbreaking.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

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
        return 1;
    }

    public boolean canApply(ItemStack stack) {
        return stack.isDamageable() || super.canApply(stack);
    }

    // basically the equivalent of what my mixin is doing to UnbreakingEnchantment#negateDamage(ItemStack, int, Random)
    public static boolean preventDamage() {
        return true;
    }

}
