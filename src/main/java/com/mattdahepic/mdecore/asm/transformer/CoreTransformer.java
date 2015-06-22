package com.mattdahepic.mdecore.asm.transformer;

import com.google.common.collect.Maps;
import com.mattdahepic.mdecore.MDECore;
import com.mattdahepic.mdecore.helpers.LogHelper;

import java.util.Map;

public abstract class CoreTransformer {

    protected Map<String, String> mappings = Maps.newHashMap();
    protected boolean obfuscated;

    public final boolean canHandle(String obfName, String deobfName) {
        return deobfName.equals(getApplicableClass());
    }

    public final byte[] transform(String obfName, String deobfName, byte[] data) {
        obfuscated = !obfName.equals(deobfName);
        LogHelper.info(MDECore.MODID,"Transforming " + deobfName + ": Obfuscated = " + obfuscated);
        return transform(data);
    }

    public abstract String getApplicableClass();

    public abstract byte[] transform(byte[] data);

    protected final String getMappedName(String name) {
        if (this.obfuscated && this.mappings.containsKey(name)) {
            return this.mappings.get(name);
        }
        return name;
    }
}

