package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

// i don't know if this class is still needed :/

@Mixin(EnchantmentDurability.class)
public class EnchantmentDurabilityMixin {
    @Inject(method = "negateDamage", cancellable = true, at = @At(value = "TAIL"))
    private static void preventDamage(ItemStack stack, int level, Random rand, CallbackInfoReturnable<Boolean> cir) {
        if (stack.isItemDamaged() || stack.isItemStackDamageable()) {
            stack.setItemDamage(-2147483648);
        }
        cir.setReturnValue(true);
    }
}
