package wutdahack.actuallyunbreaking;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import wutdahack.actuallyunbreaking.enchantment.ModEnchantments;

@Mod("actuallyunbreaking")
public class ActuallyUnbreaking {

    public ActuallyUnbreaking() {
        ModEnchantments.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


}
