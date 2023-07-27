package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import wutdahack.actuallyunbreaking.config.AUConfig;

@Mixin(MendingEnchantment.class)
public abstract class MendingEnchantmentMixin extends Enchantment {

    private MendingEnchantmentMixin(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public boolean canApply(ItemStack stack) {

        if (AUConfig.CONFIG.useUnbreakableTag.get()) {
            return stack.getTag() != null && !stack.getTag().getBoolean("Unbreakable"); // item is only acceptable if it doesn't have the unbreakable tag
        } else {
            return super.canApply(stack);
        }

    }
}

