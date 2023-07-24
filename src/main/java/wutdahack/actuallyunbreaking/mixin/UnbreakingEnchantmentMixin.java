package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wutdahack.actuallyunbreaking.config.AUConfig;

import java.util.Random;

@Mixin(UnbreakingEnchantment.class)
public abstract class UnbreakingEnchantmentMixin extends Enchantment {

    private UnbreakingEnchantmentMixin(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
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
