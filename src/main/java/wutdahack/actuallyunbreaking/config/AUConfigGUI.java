package wutdahack.actuallyunbreaking.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;

public class AUConfigGUI {

    public Screen getConfigScreen(Screen parent, boolean isTransparent) {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(new TranslationTextComponent("text.config.actuallyunbreaking.title"));
        builder.setDefaultBackgroundTexture(new ResourceLocation("minecraft:textures/block/dirt.png"));
        ConfigCategory general = builder.getOrCreateCategory(new StringTextComponent("general"));
        ConfigEntryBuilder configEntryBuilder = builder.entryBuilder();

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslationTextComponent("text.config.actuallyunbreaking.option.maxLevelOnly"), AUConfig.CONFIG.maxLevelOnly.get())
                        .setDefaultValue(false)
                        .setSaveConsumer(AUConfig.CONFIG.maxLevelOnly::set)
                        .setTooltip(new TranslationTextComponent("text.config.actuallyunbreaking.comment.maxLevelOnly"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslationTextComponent("text.config.actuallyunbreaking.option.useUnbreakableAtLevel"), AUConfig.CONFIG.useUnbreakableAtLevel.get())
                        .setDefaultValue(false)
                        .setSaveConsumer(AUConfig.CONFIG.useUnbreakableAtLevel::set)
                        .setTooltip(new TranslationTextComponent("text.config.actuallyunbreaking.comment.useUnbreakableAtLevel"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startIntField(new TranslationTextComponent("text.config.actuallyunbreaking.option.unbreakableAtLevel"), AUConfig.CONFIG.unbreakableAtLevel.get())
                        .setDefaultValue(3)
                        .setSaveConsumer(AUConfig.CONFIG.unbreakableAtLevel::set)
                        .setTooltip(new TranslationTextComponent("text.config.actuallyunbreaking.comment.unbreakableAtLevel"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslationTextComponent("text.config.actuallyunbreaking.option.useOnlyUnbreakableAtLevel"), AUConfig.CONFIG.useOnlyUnbreakableAtLevel.get())
                        .setDefaultValue(false)
                        .setSaveConsumer(AUConfig.CONFIG.useOnlyUnbreakableAtLevel::set)
                        .setTooltip(new TranslationTextComponent("text.config.actuallyunbreaking.comment.useOnlyUnbreakableAtLevel"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startIntField(new TranslationTextComponent("text.config.actuallyunbreaking.option.onlyUnbreakableAtLevel"), AUConfig.CONFIG.onlyUnbreakableAtLevel.get())
                        .setDefaultValue(3)
                        .setSaveConsumer(AUConfig.CONFIG.onlyUnbreakableAtLevel::set)
                        .setTooltip(new TranslationTextComponent("text.config.actuallyunbreaking.comment.onlyUnbreakableAtLevel"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslationTextComponent("text.config.actuallyunbreaking.option.mendingIncompatibility"), AUConfig.CONFIG.mendingIncompatibility.get())
                        .setDefaultValue(true)
                        .setSaveConsumer(AUConfig.CONFIG.mendingIncompatibility::set)
                        .setTooltip(new TranslationTextComponent("text.config.actuallyunbreaking.comment.mendingIncompatibility"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslationTextComponent("text.config.actuallyunbreaking.option.useUnbreakableTag"), AUConfig.CONFIG.useUnbreakableTag.get())
                        .setDefaultValue(true)
                        .setSaveConsumer(AUConfig.CONFIG.useUnbreakableTag::set)
                        .setTooltip(new TranslationTextComponent("text.config.actuallyunbreaking.comment.useUnbreakableTag"))
                        .build()
        );

        return builder.setTransparentBackground(isTransparent).build();

    }

    public static void registerConfigGUI() {
        AUConfigGUI configGUI = new AUConfigGUI();

        ModLoadingContext.get().registerExtensionPoint(
                ExtensionPoint.CONFIGGUIFACTORY,
                () -> (mc, screen) -> configGUI.getConfigScreen(screen, mc.level != null)
        );
    }

}
