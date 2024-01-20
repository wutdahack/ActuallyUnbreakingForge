package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wutdahack.actuallyunbreaking.config.AUConfig;

import java.util.Map;
import java.util.stream.Collectors;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Inject(method = "hurt", at = @At(value = "HEAD"))
    private void makeUnbreakable(int pAmount, RandomSource pRandom, ServerPlayer pUser, CallbackInfoReturnable<Boolean> cir) {

        if (AUConfig.CONFIG.useUnbreakableTag.get()) {

            int unbreakingLevel = ((ItemStack)(Object)this).getEnchantmentLevel(Enchantments.UNBREAKING);

            if (AUConfig.CONFIG.useOnlyUnbreakableAtLevel.get()) {
                if (unbreakingLevel == AUConfig.CONFIG.onlyUnbreakableAtLevel.get()) {
                    actuallyUnbreaking$addUnbreakableTag((ItemStack) (Object) this);
                }
            }
            else if (AUConfig.CONFIG.useUnbreakableAtLevel.get()) {
                if (unbreakingLevel >= AUConfig.CONFIG.unbreakableAtLevel.get()) {
                    actuallyUnbreaking$addUnbreakableTag((ItemStack) (Object) this);
                }
            } else if (AUConfig.CONFIG.maxLevelOnly.get()) {
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

        int mendingLevel = ((ItemStack)(Object)this).getEnchantmentLevel(Enchantments.MENDING);

        item.getOrCreateTag().putBoolean("Unbreakable", true); // add the unbreakable tag
        item.setDamageValue(0); // set item damage to 0 to remove the tool's durability bar

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
        ); // apply enchantment map on the tool

    }

}
