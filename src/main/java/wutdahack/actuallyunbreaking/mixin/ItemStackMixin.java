package wutdahack.actuallyunbreaking.mixin;

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
import wutdahack.actuallyunbreaking.RegistrationHandler;
import wutdahack.actuallyunbreaking.enchantment.ModEnchantments;

import java.util.Random;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ICapabilitySerializable<NBTTagCompound> // according to here https://mixin-wiki.readthedocs.io/tricks/ i need to implement this to trick java into thinking that the mixin can be called exactly like the target.
{

    @Shadow public abstract void setItemDamage(int meta);

    @Inject(method = "attemptDamageItem", cancellable = true, at = @At(value = "HEAD"))
    public void attemptDamageItem(int amount, Random rand, EntityPlayerMP damager, CallbackInfoReturnable<Boolean> cir) {
        // preventing damage if item has the enchantment
        int i = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.UNBREAKING, (ItemStack) (Object) this);
        if (i > 0) {
            setItemDamage(-2147483648); // setting items that didn't have unbreaking before and lost durability have full durability
            cir.setReturnValue(false); // making sure that it doesn't attempt to damage
        }
    }

}
