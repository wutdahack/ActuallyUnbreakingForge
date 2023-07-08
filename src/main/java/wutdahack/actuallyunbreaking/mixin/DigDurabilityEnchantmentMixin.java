package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.util.RandomSource;
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
import wutdahack.actuallyunbreaking.AUConfig;

import java.util.Random;

@Mixin(DigDurabilityEnchantment.class)
public abstract class DigDurabilityEnchantmentMixin extends Enchantment {

    private DigDurabilityEnchantmentMixin(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        if (AUConfig.instance.mendingIncompatibility) {
            return !(pOther instanceof MendingEnchantment) && super.checkCompatibility(pOther); // mending with unbreaking will be redundant
        } else {
            return super.checkCompatibility(pOther);
        }
    }

    @Inject(method = "shouldIgnoreDurabilityDrop", at = @At(value = "HEAD"), cancellable = true)
    private static void makeUnbreakable(ItemStack pStack, int pLevel, RandomSource pRandom, CallbackInfoReturnable<Boolean> cir) {
        if (AUConfig.instance.maxLevelOnly ? pLevel >= Enchantments.UNBREAKING.getMaxLevel() : pLevel > 0) {
            pStack.setDamageValue(0); // removes damage bar
            cir.setReturnValue(true);
        }
    }

}
