package wutdahack.actuallyunbreaking.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fmlclient.ConfigGuiHandler;

public class AUConfigGUI {

    public Screen getConfigScreen(Screen parent, boolean isTransparent) {

        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(new TranslatableComponent("text.config.actuallyunbreaking.title"));
        builder.setDefaultBackgroundTexture(new ResourceLocation("minecraft:textures/block/dirt.png"));
        ConfigCategory general = builder.getOrCreateCategory(new TextComponent("general"));
        ConfigEntryBuilder configEntryBuilder = builder.entryBuilder();

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslatableComponent("text.config.actuallyunbreaking.option.maxLevelOnly"), AUConfig.CONFIG.maxLevelOnly.get())
                        .setDefaultValue(false)
                        .setSaveConsumer(AUConfig.CONFIG.maxLevelOnly::set)
                        .setTooltip(new TranslatableComponent("text.config.actuallyunbreaking.comment.maxLevelOnly"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslatableComponent("text.config.actuallyunbreaking.option.useUnbreakableAtLevel"), AUConfig.CONFIG.useUnbreakableAtLevel.get())
                        .setDefaultValue(false)
                        .setSaveConsumer(AUConfig.CONFIG.useUnbreakableAtLevel::set)
                        .setTooltip(new TranslatableComponent("text.config.actuallyunbreaking.comment.useUnbreakableAtLevel"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startIntField(new TranslatableComponent("text.config.actuallyunbreaking.option.unbreakableAtLevel"), AUConfig.CONFIG.unbreakableAtLevel.get())
                        .setDefaultValue(3)
                        .setSaveConsumer(AUConfig.CONFIG.unbreakableAtLevel::set)
                        .setTooltip(new TranslatableComponent("text.config.actuallyunbreaking.comment.unbreakableAtLevel"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslatableComponent("text.config.actuallyunbreaking.option.useOnlyUnbreakableAtLevel"), AUConfig.CONFIG.useOnlyUnbreakableAtLevel.get())
                        .setDefaultValue(false)
                        .setSaveConsumer(AUConfig.CONFIG.useOnlyUnbreakableAtLevel::set)
                        .setTooltip(new TranslatableComponent("text.config.actuallyunbreaking.comment.useOnlyUnbreakableAtLevel"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startIntField(new TranslatableComponent("text.config.actuallyunbreaking.option.onlyUnbreakableAtLevel"), AUConfig.CONFIG.onlyUnbreakableAtLevel.get())
                        .setDefaultValue(3)
                        .setSaveConsumer(AUConfig.CONFIG.onlyUnbreakableAtLevel::set)
                        .setTooltip(new TranslatableComponent("text.config.actuallyunbreaking.comment.onlyUnbreakableAtLevel"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslatableComponent("text.config.actuallyunbreaking.option.mendingIncompatibility"), AUConfig.CONFIG.mendingIncompatibility.get())
                        .setDefaultValue(true)
                        .setSaveConsumer(AUConfig.CONFIG.mendingIncompatibility::set)
                        .setTooltip(new TranslatableComponent("text.config.actuallyunbreaking.comment.mendingIncompatibility"))
                        .build()
        );

        general.addEntry(
                configEntryBuilder
                        .startBooleanToggle(new TranslatableComponent("text.config.actuallyunbreaking.option.useUnbreakableTag"), AUConfig.CONFIG.useUnbreakableTag.get())
                        .setDefaultValue(true)
                        .setSaveConsumer(AUConfig.CONFIG.useUnbreakableTag::set)
                        .setTooltip(new TranslatableComponent("text.config.actuallyunbreaking.comment.useUnbreakableTag"))
                        .build()
        );

        return builder.setTransparentBackground(isTransparent).build();

    }

    public static void registerConfigGUI() {
        AUConfigGUI configGUI = new AUConfigGUI();

        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class,
                () -> new ConfigGuiHandler.ConfigGuiFactory(
                        (client, parent) -> configGUI.getConfigScreen(parent, client.level != null)
                ));
    }

}
