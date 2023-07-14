package wutdahack.actuallyunbreaking.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class AUConfigGUI {

    public Screen getConfigScreen(Screen parent, boolean isTransparent) {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Component.translatable("text.config.actuallyunbreaking.title"));
        builder.setDefaultBackgroundTexture(new ResourceLocation("minecraft:textures/block/dirt.png"));
        ConfigCategory general = builder.getOrCreateCategory(Component.literal("general"));
        ConfigEntryBuilder configEntryBuilder = builder.entryBuilder();

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(Component.translatable("text.config.actuallyunbreaking.option.maxLevelOnly"), AUConfig.CONFIG.maxLevelOnly.get())
                        .setDefaultValue(false)
                        .setSaveConsumer(AUConfig.CONFIG.maxLevelOnly::set)
                        .setTooltip(Component.translatable("text.config.actuallyunbreaking.comment.maxLevelOnly"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(Component.translatable("text.config.actuallyunbreaking.option.useUnbreakableAtLevel"), AUConfig.CONFIG.useUnbreakableAtLevel.get())
                        .setDefaultValue(false)
                        .setSaveConsumer(AUConfig.CONFIG.useUnbreakableAtLevel::set)
                        .setTooltip(Component.translatable("text.config.actuallyunbreaking.comment.useUnbreakableAtLevel"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startIntField(Component.translatable("text.config.actuallyunbreaking.option.unbreakableAtLevel"), AUConfig.CONFIG.unbreakableAtLevel.get())
                        .setDefaultValue(3)
                        .setSaveConsumer(AUConfig.CONFIG.unbreakableAtLevel::set)
                        .setTooltip(Component.translatable("text.config.actuallyunbreaking.comment.unbreakableAtLevel"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(Component.translatable("text.config.actuallyunbreaking.option.mendingIncompatibility"), AUConfig.CONFIG.mendingIncompatibility.get())
                        .setDefaultValue(true)
                        .setSaveConsumer(AUConfig.CONFIG.mendingIncompatibility::set)
                        .setTooltip(Component.translatable("text.config.actuallyunbreaking.comment.mendingIncompatibility"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(Component.translatable("text.config.actuallyunbreaking.option.useUnbreakableTag"), AUConfig.CONFIG.useUnbreakableTag.get())
                        .setDefaultValue(true)
                        .setSaveConsumer(AUConfig.CONFIG.useUnbreakableTag::set)
                        .setTooltip(Component.translatable("text.config.actuallyunbreaking.comment.useUnbreakableTag"))
                        .build()
        );

        return builder.setTransparentBackground(isTransparent).build();

    }


}
