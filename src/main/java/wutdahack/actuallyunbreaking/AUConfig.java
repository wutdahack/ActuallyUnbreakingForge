package wutdahack.actuallyunbreaking;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

// TODO: future wutdahack please change the config code to be sort of like fabric thanks - past wutdahack
@Config(name = ActuallyUnbreaking.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/dirt.png")
public class AUConfig implements ConfigData {

    @ConfigEntry.Gui.Excluded
    public static AUConfig instance;

    @ConfigEntry.Gui.RequiresRestart(value = false)
    @Comment("only the last level of unbreaking will set the tool\nto be unbreakable if this is true. default = false")
    public boolean maxLevelOnly = false;

    @ConfigEntry.Gui.RequiresRestart(value = false)
    @Comment("if this is true, the tool will only be\nunbreakable at a specified level\n(overrides max level option). default = false")
    public boolean useUnbreakableAtLevel = false;

    @ConfigEntry.Gui.RequiresRestart(value = false)
    @Comment("if this is true, the tool will only be\nunbreakable at a specified level\n(overrides max level option). default = false")
    public int unbreakableAtLevel = 3;

    @ConfigEntry.Gui.RequiresRestart(value = false)
    @Comment("unbreaking will be incompatible with mending\nif this is true. default = true")
    public boolean mendingIncompatibility = true;

    @ConfigEntry.Gui.RequiresRestart(value = false)
    @Comment("when the tool takes damage, the unbreakable\nnbt tag will be added and unbreaking (and mending) will be\nremoved from the tool (you will no longer be able\nto add unbreaking and mending to the tool) if this is true.\ndefault = true")
    public boolean useUnbreakableTag = true;
}
