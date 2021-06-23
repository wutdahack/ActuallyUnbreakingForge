package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wutdahack.actuallyunbreaking.enchantment.ActuallyUnbreakingEnchantment;
import wutdahack.actuallyunbreaking.enchantment.ModEnchantments;

import javax.annotation.Nullable;
import java.util.Random;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ICapabilitySerializable<NBTTagCompound> // according to here https://mixin-wiki.readthedocs.io/tricks/ i need to implement this to trick java into thinking that the mixin can be called exactly like the target.
{
    @Shadow
    public abstract void setItemDamage(int meta);

    @Inject(method = "attemptDamageItem(ILjava/util/Random;Lnet/minecraft/entity/player/EntityPlayerMP;)Z", cancellable = true, at = @At(value = "HEAD"))
    public void preventDamageItem(int amount, Random rand, @Nullable EntityPlayerMP damager, CallbackInfoReturnable<Boolean> cir) {

        // preventing damage if item has the enchantment
        int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.UNBREAKING, (ItemStack) (Object) this);
        if (level > 0) {
            if (ActuallyUnbreakingEnchantment.preventDamage((ItemStack) (Object) this)) {
                setItemDamage(-2147483648); // setting items that didn't have unbreaking before and lost durability have full durability
                cir.setReturnValue(false); // making sure that it doesn't attempt to damage
                // if preventDamage is false then use the normal ItemStack's behaviour
            } else if (!ActuallyUnbreakingEnchantment.preventDamage((ItemStack) (Object) this)) {
                if (amount > 0) {
                    int j = 0;

                    for (int k = 0; k < amount; ++k) {
                        if (EnchantmentDurability.negateDamage((ItemStack) (Object) this, level, rand)) {
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
