package wutdahack.actuallyunbreaking;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import wutdahack.actuallyunbreaking.config.AUConfig;

@Mod(ActuallyUnbreaking.MOD_ID)
public class ActuallyUnbreaking {

    public static final String MOD_ID = "actuallyunbreaking";

    public ActuallyUnbreaking() {

        // registering config
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AUConfig.SPEC, "actuallyunbreaking.toml");
    }
}
