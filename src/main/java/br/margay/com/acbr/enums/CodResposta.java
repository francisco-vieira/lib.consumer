package br.margay.com.acbr.enums;

import java.util.HashMap;
import java.util.Map;

public enum CodResposta {
    UTF8(0),
    ANSI(1);


    private static final Map<Integer, CodResposta> intMap;
    private final int enumValue;

    static {
        intMap = new HashMap<>();
        for (CodResposta value : CodResposta.values()) {
            intMap.put(value.asInt(), value);
        }
    }

    public static CodResposta valueOf(int value) {
        return intMap.get(value);
    }

    private CodResposta(int id) {
        this.enumValue = id;
    }

    public int asInt() {
        return enumValue;
    }
}

