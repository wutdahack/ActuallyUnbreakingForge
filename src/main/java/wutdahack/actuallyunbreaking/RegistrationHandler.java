package wutdahack.actuallyunbreaking;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wutdahack.actuallyunbreaking.enchantment.ActuallyUnbreakingEnchantment;

@Mod.EventBusSubscriber(modid = "actuallyunbreaking")
public class RegistrationHandler {

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        final Enchantment[] enchantments = {
                // overriding minecraft's enchantment because the UnbreakingEnchantment constructor is protected
                new ActuallyUnbreakingEnchantment(Enchantment.Rarity.UNCOMMON, EnumEnchantmentType.BREAKABLE, EntityEquipmentSlot.values() // this may be the fix to the armour problem
                ).setRegistryName("minecraft", "unbreaking").setName("durability")
        };
        event.getRegistry().registerAll(enchantments);
    }

}
