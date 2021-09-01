package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentMending;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wutdahack.actuallyunbreaking.AUConfig;

import java.util.Random;

@Mixin(EnchantmentDurability.class)
public abstract class EnchantmentDurabilityMixin extends Enchantment {

    private EnchantmentDurabilityMixin(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
    }

    // items can't have mending and unbreaking together if enabled in config
    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        if (AUConfig.mendingIncompatibility) {
            return !(ench instanceof EnchantmentMending) && super.canApplyTogether(ench);
        } else {
            return super.canApplyTogether(ench);
        }
    }

    // item won't take damage and the damage bar will
    // be removed depending on the level of the unbreaking enchantment
    @Inject(method = "negateDamage", at = @At(value = "HEAD"), cancellable = true)
    private static void makeUnbreakable(ItemStack item, int level, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (AUConfig.maxLevelOnly ? level >= Enchantments.UNBREAKING.getMaxLevel() : level > 0) {
            item.setItemDamage(0);
            cir.setReturnValue(true);
        }
    }

}
