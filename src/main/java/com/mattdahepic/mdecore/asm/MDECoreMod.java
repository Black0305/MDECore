package com.mattdahepic.mdecore.asm;

import net.minecraftforge.fml.relauncher.IFMLCallHook;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.TransformerExclusions({"com.mattdahepic.mdecore"})
@IFMLLoadingPlugin.Name("MDECore-Core")
public class MDECoreMod implements IFMLLoadingPlugin, IFMLCallHook {
    @Override
    public String[] getASMTransformerClass () {
        return new String[]{"com.mattdahepic.mdecore.asm.TickrateTransformer"};
    }
    @Override
    public String getModContainerClass () {
        return "com.mattdahepic.mdecore.asm.MDECoreCoreDummyMod";
    }
    @Override
    public String getSetupClass () {
        return "com.mattdahepic.mdecore.asm.MDECoreMod";
    }
    @Override
    public void injectData (Map<String, Object> data) {}
    @Override
    public String getAccessTransformerClass () {
        return null;
    }
    @Override
    public Void call () throws Exception {
        return null;
    }
}
