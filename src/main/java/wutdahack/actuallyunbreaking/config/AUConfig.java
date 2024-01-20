package wutdahack.actuallyunbreaking.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import wutdahack.actuallyunbreaking.ActuallyUnbreaking;

@Config(modid = ActuallyUnbreaking.MOD_ID, category = "Actually Unbreaking")
public class AUConfig {

    @Config.Name("max level only")
    @Config.Comment("only the last level of unbreaking will set the tool\nto be unbreakable if this is true.")
    public static boolean maxLevelOnly = false;

    @Config.Name("use the unbreakable at level number")
    @Config.Comment("if this is true, the tool will only be\nunbreakable at a specified level\nand above that level. (overrides max level option).")
    public static boolean useUnbreakableAtLevel = false;

    @Config.Name("unbreakable at level")
    @Config.Comment("the specified level the tool will be unbreakable at. default = 3")
    public static int unbreakableAtLevel = 3;

    @Config.Name("only make the tool unbreakable at one specified level")
    @Config.Comment("if this is true, it makes the tool\nunbreakable only at a specified level.\n(overrides max level and unbreakable at options)")
    public static boolean useOnlyUnbreakableAtLevel = false;

    @Config.Name("the only level that makes the tool unbreakable")
    @Config.Comment("the specified level the tool will be unbreakable at")
    public static int onlyUnbreakableAtLevel = 3;

    @Config.Name("is incompatible with mending")
    @Config.Comment("unbreaking will be incompatible with mending\nif this is true.")
    public static boolean mendingIncompatibility = true;

    @Config.Name("use the unbreakable tag")
    @Config.Comment("when the tool takes damage, the unbreakable\nnbt tag will be added and unbreaking (and mending) will be\nremoved from the tool (you will no longer be able\nto add unbreaking and mending to the tool) if this is true.")
    public static boolean useUnbreakableTag = true;

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
