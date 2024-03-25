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
import wutdahack.actuallyunbreaking.config.AUConfig;

import java.util.Random;

@Mixin(EnchantmentDurability.class)
public abstract class EnchantmentDurabilityMixin extends Enchantment {

    private EnchantmentDurabilityMixin(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {

        if (AUConfig.mendingIncompatibility) {
            return !(ench instanceof EnchantmentMending) && super.canApplyTogether(ench); // mending with unbreaking is redundant
        } else {
            return super.canApplyTogether(ench);
        }

    }

    @Inject(method = "canApply", at = @At(value = "HEAD"), cancellable = true)
    private void dontAcceptUnbreakableItems(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {

        if (AUConfig.useUnbreakableTag) {
            cir.setReturnValue(stack.getTagCompound() != null && !stack.getTagCompound().getBoolean("Unbreakable")); // item can't have unbreaking if it has the unbreaking tag as that's also redundant
        }

    }

    @Inject(method = "negateDamage", at = @At(value = "HEAD"), cancellable = true)
    private static void makeUnbreakable(ItemStack item, int level, Random random, CallbackInfoReturnable<Boolean> cir) {

        if (!AUConfig.useUnbreakableTag) {
            if (AUConfig.useOnlyUnbreakableAtLevel) {
                if (level == AUConfig.onlyUnbreakableAtLevel) {
                    item.setItemDamage(0); // removes damage bar
                    cir.setReturnValue(true);
                }
            }
            else if (AUConfig.useUnbreakableAtLevel) {
                if (level >= AUConfig.unbreakableAtLevel) {
                    item.setItemDamage(0);
                    cir.setReturnValue(true);
                }
            }
            else if (AUConfig.maxLevelOnly) {
                if (level >= Enchantments.UNBREAKING.getMaxLevel()) {
                    item.setItemDamage(0);
                    cir.setReturnValue(true);
                }
            }
            else if (level > 0) {
                item.setItemDamage(0);
                cir.setReturnValue(true);
            }
        }

    }

}
