package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.MendingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import wutdahack.actuallyunbreaking.config.AUConfig;

@Mixin(MendingEnchantment.class)
public abstract class MendingEnchantmentMixin extends Enchantment {

    private MendingEnchantmentMixin(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean canEnchant(ItemStack pStack) {

        if (AUConfig.CONFIG.useUnbreakableTag.get()) {
            return pStack.getTag() != null && !pStack.getTag().getBoolean("Unbreakable"); // item is only acceptable if it doesn't have the unbreakable tag
        } else {
            return super.canEnchant(pStack);
        }

    }

}
