package wutdahack.actuallyunbreaking;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod("actuallyunbreaking")
public class ActuallyUnbreaking {

    public ActuallyUnbreaking() {
        // registering config
        AUConfig.instance = AutoConfig.register(AUConfig.class, JanksonConfigSerializer::new).get();

        // registering config gui
        ModLoadingContext.get().registerExtensionPoint(
                    ExtensionPoint.CONFIGGUIFACTORY,
                    () -> (mc, screen) -> AutoConfig.getConfigScreen(AUConfig.class, screen).get()
        );
    }
}
