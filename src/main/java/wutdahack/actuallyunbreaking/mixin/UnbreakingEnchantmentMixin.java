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
    protected boolean canApplyTogether(Enchantment other) {
        if (AUConfig.CONFIG.mendingIncompatibility.get()) {
            return !(other instanceof MendingEnchantment) && super.canApplyTogether(other); // mending with unbreaking is redundant
        } else {
            return super.canApplyTogether(other);
        }
    }


    @Inject(method = "canApply", at = @At(value = "HEAD"), cancellable = true)
    private void dontAcceptUnbreakableItems(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {

        if (AUConfig.CONFIG.useUnbreakableTag.get()) {
            cir.setReturnValue(stack.getTag() != null && !stack.getTag().getBoolean("Unbreakable")); // item can't have unbreaking if it has the unbreaking tag as that's also redundant
        }

    }

    @Inject(method = "negateDamage", at = @At(value = "HEAD"), cancellable = true)
    private static void makeUnbreakable(ItemStack stack, int level, Random random, CallbackInfoReturnable<Boolean> cir) {

        if (!AUConfig.CONFIG.useUnbreakableTag.get()) {
            if (AUConfig.CONFIG.useUnbreakableAtLevel.get() && level >= AUConfig.CONFIG.unbreakableAtLevel.get()) {
                stack.setDamage(0); // removes damage bar
                cir.setReturnValue(true);
            } else if (AUConfig.CONFIG.maxLevelOnly.get() && level >= Enchantments.UNBREAKING.getMaxLevel()) {
                stack.setDamage(0);
                cir.setReturnValue(true);
            } else if (!(AUConfig.CONFIG.maxLevelOnly.get() || AUConfig.CONFIG.useUnbreakableAtLevel.get()) && level > 0) {
                stack.setDamage(0);
                cir.setReturnValue(true);
            }
        }

    }

}
