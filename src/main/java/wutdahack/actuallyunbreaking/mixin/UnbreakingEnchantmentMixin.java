package wutdahack.actuallyunbreaking.mixin;

import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(UnbreakingEnchantment.class)
public class UnbreakingEnchantmentMixin {

    // makes the unbreakabing enchantment actually make the tool unbreakable
     @Overwrite
     public static boolean negateDamage(ItemStack item, int level, Random rand) {
         if (item.isDamageable() || item.isDamaged()) {
             item.setDamage(-2147483648);
         }
         return true;
     }
}
