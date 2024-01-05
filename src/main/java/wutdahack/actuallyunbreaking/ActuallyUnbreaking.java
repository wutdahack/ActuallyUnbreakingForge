package wutdahack.actuallyunbreaking;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
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

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AUConfig.SPEC, "actuallyunbreaking.toml");

        if (ModList.get().isLoaded("cloth_config")) {
            DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> AUConfigGUI::registerConfigGUI);
        }
    }


}
