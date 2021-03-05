package wutdahack.actuallyunbreaking.core;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import javax.annotation.Nullable;
import java.util.Map;


/*
   this is just so i can use mixins
   let's see if any incompatibilities will be caused from this
   also i'm going to learn about coremods
 */
@IFMLLoadingPlugin.TransformerExclusions("wutdahack.actuallyunbreaking.core")
@IFMLLoadingPlugin.SortingIndex(13 // my lucky number
)
@IFMLLoadingPlugin.MCVersion("1.12.2")
public class AUCoreMod implements IFMLLoadingPlugin {

    public AUCoreMod() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.actuallyunbreaking.json");
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
