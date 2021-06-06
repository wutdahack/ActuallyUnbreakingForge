package wutdahack.actuallyunbreaking;

import net.minecraftforge.common.ForgeConfigSpec;

public class AUConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final AUConfig CONFIG = new AUConfig(BUILDER);

    public final ForgeConfigSpec.BooleanValue level3Only;


    public AUConfig(ForgeConfigSpec.Builder builder) {
        builder.push("Actually Unbreaking Config");
        level3Only = builder
                     .comment("only level 3 of unbreaking will set the tool\nto be unbreakable if this is true. default = false")
                     .define("level3Only", false);
        builder.pop();
    }

    public static final ForgeConfigSpec spec = BUILDER.build();

}
