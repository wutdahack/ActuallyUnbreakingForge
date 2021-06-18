package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.extensions.IForgeItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wutdahack.actuallyunbreaking.enchantment.ActuallyUnbreakingEnchantment;
import wutdahack.actuallyunbreaking.enchantment.ModEnchantments;

import java.util.Random;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin extends CapabilityProvider<ItemStack> implements IForgeItemStack // according to here https://mixin-wiki.readthedocs.io/tricks/ i need to extend and implement these to trick java into thinking that the mixin can be called exactly like the target.
{

    @Shadow
    public abstract void setDamage(int damage);

    private ItemStackMixin(Class<ItemStack> baseClass) {
        super(baseClass);
    }

    @Inject(method = "attemptDamageItem", cancellable = true, at = @At(value = "HEAD"))
    public void attemptDamageItem(int amount, Random rand, ServerPlayerEntity damager, CallbackInfoReturnable<Boolean> cir) {
        // preventing damage if item has the enchantment
        int i = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.UNBREAKING.get(), (ItemStack) (Object) this);
        if (i > 0) {
            if (ActuallyUnbreakingEnchantment.preventDamage((ItemStack) (Object) this)) {
                setDamage(-2147483648); // setting items that didn't have unbreaking before and lost durability have full durability
                cir.setReturnValue(false); // making sure that it doesn't attempt to damage
                // if preventDamage is false then use the normal ItemStack's behaviour
            } else if (!ActuallyUnbreakingEnchantment.preventDamage((ItemStack) (Object) this)) {
                if (amount > 0) {
                    int j = 0;

                    for (int k = 0; k < amount; ++k) {
                        if (UnbreakingEnchantment.negateDamage((ItemStack) (Object) this, i, rand)) {
                            ++j;
                        }
                    }

                    amount -= j;
                    if (amount <= 0) {
                        cir.setReturnValue(false);
                    }
                }
            }
        }
    }
}

