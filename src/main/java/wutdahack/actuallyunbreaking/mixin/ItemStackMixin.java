package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wutdahack.actuallyunbreaking.enchantment.ActuallyUnbreakingEnchantment;

import java.util.Random;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "attemptDamageItem", cancellable = true, at = @At(value = "HEAD"))
    public void attemptDamageItem(int amount, Random rand, ServerPlayerEntity damager, CallbackInfoReturnable<Boolean> cir) {
        // preventing damage if item has the enchantment
        if (ActuallyUnbreakingEnchantment.preventDamage()) {
            cir.setReturnValue(false);
        }
    }
}

