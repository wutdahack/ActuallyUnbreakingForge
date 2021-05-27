package wutdahack.actuallyunbreaking.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModEnchantments {

   public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, "minecraft");

   // overriding minecraft's enchantment because the UnbreakingEnchantment constructor is protected
   public static final RegistryObject<Enchantment> UNBREAKING = ENCHANTMENTS.register("unbreaking", () -> new ActuallyUnbreakingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentType.BREAKABLE, EquipmentSlotType.values() // this may be the fix to the armour problem
   ));


}
