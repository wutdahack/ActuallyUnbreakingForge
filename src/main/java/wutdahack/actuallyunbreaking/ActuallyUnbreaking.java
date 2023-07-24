package wutdahack.actuallyunbreaking;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import wutdahack.actuallyunbreaking.config.AUConfig;
import wutdahack.actuallyunbreaking.config.AUConfigGUI;

@Mod(ActuallyUnbreaking.MOD_ID)
public class ActuallyUnbreaking {

    public static final String MOD_ID = "actuallyunbreaking";

    public ActuallyUnbreaking() {

        // registering config with forge config system so cloth config isn't needed
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AUConfig.SPEC, "actuallyunbreaking.toml");

        // registering config gui
        if (ModList.get().isLoaded("cloth-config")) {

            AUConfigGUI configGUI = new AUConfigGUI();

            ModLoadingContext.get().registerExtensionPoint(
                    ExtensionPoint.CONFIGGUIFACTORY,
                    () -> (mc, screen) -> configGUI.getConfigScreen(screen, mc.level != null)
            );
        }
    }
}
