package br.jlinformatica.com.acbr.enums;

import java.util.HashMap;
import java.util.Map;

public enum TipoResposta {
    FMT_INI(0),
    FMT_XML(1),
    FMT_JSON(2);


    private static final Map<Integer, TipoResposta> intMap;
    private final int enumValue;

    static {
        intMap = new HashMap<>();
        for (TipoResposta value : TipoResposta.values()) {
            intMap.put(value.asInt(), value);
        }
    }

    public static TipoResposta valueOf(int value) {
        return intMap.get(value);
    }

    private TipoResposta(int id) {
        this.enumValue = id;
    }

    public int asInt() {
        return enumValue;
    }
}

