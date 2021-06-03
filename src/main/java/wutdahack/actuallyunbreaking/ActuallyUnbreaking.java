package wutdahack.actuallyunbreaking;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraftforge.fml.ExtensionPoint;
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
        // registering config gui
        ModLoadingContext.get().registerExtensionPoint(
                    ExtensionPoint.CONFIGGUIFACTORY,
                    () -> (mc, screen) -> AutoConfig.getConfigScreen(AUConfig.class, screen).get()
        );
    }
}
