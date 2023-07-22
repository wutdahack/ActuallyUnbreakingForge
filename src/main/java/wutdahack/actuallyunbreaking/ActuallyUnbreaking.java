package wutdahack.actuallyunbreaking;

import net.minecraftforge.client.ConfigGuiHandler;
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
        if (ModList.get().isLoaded("cloth_config")) {

            AUConfigGUI configGUI = new AUConfigGUI();

            ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class,
                    () -> new ConfigGuiHandler.ConfigGuiFactory(
                            (client, parent) -> configGUI.getConfigScreen(parent, client.level != null)
                    ));
        }

    }


}
