package wutdahack.actuallyunbreaking;

import me.shedaniel.autoconfig1u.AutoConfig;
import me.shedaniel.autoconfig1u.serializer.JanksonConfigSerializer;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import wutdahack.actuallyunbreaking.enchantment.ModEnchantments;

@Mod("actuallyunbreaking")
public class ActuallyUnbreaking {

    public ActuallyUnbreaking() {
        // registering config
        AutoConfig.register(AUConfig.class, JanksonConfigSerializer::new);
        // registering enchantment
        ModEnchantments.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        // registering config gui if cloth config is installed
      if (ModList.get().isLoaded("cloth-config")) {
        ModLoadingContext.get().registerExtensionPoint(
                    ExtensionPoint.CONFIGGUIFACTORY,
                    () -> (mc, screen) -> AutoConfig.getConfigScreen(AUConfig.class, screen).get()
        );
      }
    }
}
