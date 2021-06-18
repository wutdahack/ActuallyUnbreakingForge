package wutdahack.actuallyunbreaking;

import net.minecraftforge.common.ForgeConfigSpec;

public class AUConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final AUConfig CONFIG = new AUConfig(BUILDER);

    public final ForgeConfigSpec.BooleanValue maxLevelOnly;

    public AUConfig(ForgeConfigSpec.Builder builder) {
        builder.push("Actually Unbreaking Config");
        maxLevelOnly = builder
                     .comment("only the last level of unbreaking will set the tool\nto be unbreakable if this is true. default = false")
                     .define("maxLevelOnly", false);
        builder.pop();
    }

    public static final ForgeConfigSpec spec = BUILDER.build();

}
