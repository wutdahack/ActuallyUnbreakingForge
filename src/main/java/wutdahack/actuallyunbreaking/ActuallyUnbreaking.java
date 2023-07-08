package wutdahack.actuallyunbreaking;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(ActuallyUnbreaking.MOD_ID)
public class ActuallyUnbreaking {

    public static final String MOD_ID = "actuallyunbreaking";

    public ActuallyUnbreaking() {
        // registering config
        AUConfig.instance = AutoConfig.register(AUConfig.class, JanksonConfigSerializer::new).get();

        // registering config gui
        // TODO: gotta test if this works later
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (client, parent) -> AutoConfig.getConfigScreen(AUConfig.class, parent).get()
                ));
    }


}
