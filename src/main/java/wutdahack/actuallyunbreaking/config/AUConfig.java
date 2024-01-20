package wutdahack.actuallyunbreaking.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class AUConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final AUConfig CONFIG = new AUConfig(BUILDER);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public final ForgeConfigSpec.BooleanValue maxLevelOnly;

    public final ForgeConfigSpec.BooleanValue useUnbreakableAtLevel;

    public final ForgeConfigSpec.BooleanValue useOnlyUnbreakableAtLevel;

    public final ForgeConfigSpec.ConfigValue<Integer> onlyUnbreakableAtLevel;

    public final ForgeConfigSpec.ConfigValue<Integer> unbreakableAtLevel; // can't use ForgeConfigSpec.IntValue or the value will have to be set into a range

    public final ForgeConfigSpec.BooleanValue mendingIncompatibility;

    public final ForgeConfigSpec.BooleanValue useUnbreakableTag;

    public AUConfig(ForgeConfigSpec.Builder builder) {

        builder.push("Actually Unbreaking Config");

        maxLevelOnly = builder
                .comment("only the last level of unbreaking will set the tool\nto be unbreakable if this is true. default = false")
                .define("maxLevelOnly", false);

        useUnbreakableAtLevel = builder
                .comment("if this is true, the tool will only be\nunbreakable at a specified level\nand above that level. (overrides max level option). default = false")
                .define("useUnbreakableAtLevel", false);

        unbreakableAtLevel = builder
                .comment("the specified level the tool will be unbreakable at. default = 3")
                .define("unbreakableAtLevel", 3);

        useOnlyUnbreakableAtLevel = builder
                .comment("if this is true, it makes the tool\nunbreakable only at a specified level.\n(overrides max level and unbreakable at options)")
                .define("useOnlyUnbreakableAtLevel", false);

        onlyUnbreakableAtLevel = builder
                .comment("the specified level the tool will be unbreakable at. default = 3")
                .define("onlyUnbreakableAtLevel", 3);
        mendingIncompatibility = builder
                .comment("unbreaking will be incompatible with mending\nif this is true. default = true")
                .define("mendingIncompatibility", true);

        useUnbreakableTag = builder
                .comment("when the tool takes damage, the unbreakable\nnbt tag will be added and unbreaking (and mending) will be\nremoved from the tool (you will no longer be able\nto add unbreaking and mending to the tool) if this is true.\ndefault = true")
                .define("useUnbreakableTag", true);

        builder.pop();

    }

}
