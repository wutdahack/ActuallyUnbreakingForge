package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentMending;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import wutdahack.actuallyunbreaking.config.AUConfig;

@Mixin(EnchantmentMending.class)
public abstract class EnchantmentMendingMixin extends Enchantment {

    private EnchantmentMendingMixin(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public boolean canApply(ItemStack stack) {

        if (AUConfig.useUnbreakableTag) {
            return stack.getTagCompound() != null && !stack.getTagCompound().getBoolean("Unbreakable"); // item is only acceptable if it doesn't have the unbreakable tag
        } else {
            return super.canApply(stack);
        }

    }
}
