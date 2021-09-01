package wutdahack.actuallyunbreaking;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ActuallyUnbreaking.MOD_ID, category = "Actually Unbreaking")
public class AUConfig {

    @Config.Name("max level only")
    @Config.Comment("only the last level of unbreaking will set the tool\nto be unbreakable if this is true. default = false")
    public static boolean maxLevelOnly = false;

    @Config.Name("is incompatible with mending")
    @Config.Comment("unbreaking will be incompatible with mending\nif this is true. default = true")
    public static boolean mendingIncompatibility = true;

    @Mod.EventBusSubscriber(modid = ActuallyUnbreaking.MOD_ID)
    private static class EventHandler {

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(ActuallyUnbreaking.MOD_ID)) {
                ConfigManager.sync(ActuallyUnbreaking.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }

}
