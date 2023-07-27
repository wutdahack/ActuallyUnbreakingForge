package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wutdahack.actuallyunbreaking.config.AUConfig;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "attemptDamageItem", at = @At(value = "HEAD"))
    private void makeUnbreakable(int amount, Random rand, EntityPlayerMP damager, CallbackInfoReturnable<Boolean> cir) {

        if (AUConfig.useUnbreakableTag) {

            int unbreakingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, (ItemStack) (Object) this);

            if (AUConfig.useUnbreakableAtLevel) {
                if (unbreakingLevel >= AUConfig.unbreakableAtLevel) {
                    actuallyUnbreaking$addUnbreakableTag((ItemStack) (Object) this);
                }
            } else if (AUConfig.maxLevelOnly) {
                if (unbreakingLevel >= Enchantments.UNBREAKING.getMaxLevel()) {
                    actuallyUnbreaking$addUnbreakableTag((ItemStack) (Object) this);
                }
            } else if (unbreakingLevel > 0) {
                actuallyUnbreaking$addUnbreakableTag((ItemStack)(Object) this);
            }
        }

    }

    @Unique
    private void actuallyUnbreaking$addUnbreakableTag(ItemStack item) {

        int mendingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, (ItemStack) (Object) this);

        if (item.getTagCompound() == null) {
            item.setTagCompound(new NBTTagCompound());
        }

        item.getTagCompound().setBoolean("Unbreakable", true); // add the unbreakable tag
        item.setItemDamage(0); // set item damage to 0 to remove the tool's durability bar

        Map<Enchantment, Integer> enchantmentMap = EnchantmentHelper.getEnchantments(item).entrySet().stream()
                .filter(entry -> entry.getKey() != Enchantments.UNBREAKING) // remove unbreaking from the map
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (mendingLevel > 0) { // if tool has mending
            enchantmentMap = enchantmentMap.entrySet().stream()
                    .filter(entry -> entry.getKey() != Enchantments.MENDING) // remove mending from the map
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        EnchantmentHelper.setEnchantments(
                enchantmentMap,
                item
        ); // apply enchantment map to the tool

    }

}
