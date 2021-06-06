package wutdahack.actuallyunbreaking;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import wutdahack.actuallyunbreaking.enchantment.ModEnchantments;

@Mod("actuallyunbreaking")
public class ActuallyUnbreaking {

    public ActuallyUnbreaking() {
        // registering config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AUConfig.spec, "actuallyunbreaking.toml");
        // registering enchantment
        ModEnchantments.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
