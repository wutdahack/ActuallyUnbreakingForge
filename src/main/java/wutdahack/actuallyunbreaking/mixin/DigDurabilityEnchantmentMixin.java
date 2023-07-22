package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DigDurabilityEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.MendingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wutdahack.actuallyunbreaking.config.AUConfig;

import java.util.Random;

@Mixin(DigDurabilityEnchantment.class)
public abstract class DigDurabilityEnchantmentMixin extends Enchantment {

    private DigDurabilityEnchantmentMixin(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {

        if (AUConfig.CONFIG.mendingIncompatibility.get()) {
            return !(pOther instanceof MendingEnchantment) && super.checkCompatibility(pOther); // mending with unbreaking is redundant
        } else {
            return super.checkCompatibility(pOther);
        }

    }

    @Inject(method = "canEnchant", at = @At(value = "HEAD"), cancellable = true)
    private void dontAcceptUnbreakableItems(ItemStack pStack, CallbackInfoReturnable<Boolean> cir) {

        if (AUConfig.CONFIG.useUnbreakableTag.get()) {
            cir.setReturnValue(pStack.getTag() != null && !pStack.getTag().getBoolean("Unbreakable")); // item can't have unbreaking if it has the unbreaking tag as that's also redundant
        }

    }

    @Inject(method = "shouldIgnoreDurabilityDrop", at = @At(value = "HEAD"), cancellable = true)
    private static void makeUnbreakable(ItemStack pStack, int pLevel, Random pRand, CallbackInfoReturnable<Boolean> cir) {

        if (!AUConfig.CONFIG.useUnbreakableTag.get()) {
            if (AUConfig.CONFIG.useUnbreakableAtLevel.get() && pLevel >= AUConfig.CONFIG.unbreakableAtLevel.get()) {
                pStack.setDamageValue(0); // removes damage bar
                cir.setReturnValue(true);
            } else if (AUConfig.CONFIG.maxLevelOnly.get() && pLevel >= Enchantments.UNBREAKING.getMaxLevel()) {
                pStack.setDamageValue(0);
                cir.setReturnValue(true);
            } else if (!(AUConfig.CONFIG.maxLevelOnly.get() || AUConfig.CONFIG.useUnbreakableAtLevel.get()) && pLevel > 0) {
                pStack.setDamageValue(0);
                cir.setReturnValue(true);
            }
        }

    }

}
