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
import wutdahack.actuallyunbreaking.AUConfig;

import java.util.Random;

@Mixin(UnbreakingEnchantment.class)
public abstract class UnbreakingEnchantmentMixin extends Enchantment {

    private UnbreakingEnchantmentMixin(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    // items can't have mending and unbreaking together if enabled in config
    @Override
    protected boolean canApplyTogether(Enchantment other) {
        if (AUConfig.CONFIG.mendingIncompatibility.get()) {
            return !(other instanceof MendingEnchantment) && super.canApplyTogether(other);
        } else {
            return super.canApplyTogether(other);
        }
    }

    // item won't take damage and the damage bar will
    // be removed depending on the level of the unbreaking enchantment
    @Inject(method = "negateDamage", at = @At(value = "HEAD"), cancellable = true)
    private static void makeUnbreakable(ItemStack item, int level, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (AUConfig.CONFIG.maxLevelOnly.get() ? level >= Enchantments.UNBREAKING.getMaxLevel() : level > 0) {
            item.setDamage(0);
            cir.setReturnValue(true);
        }
    }

}
