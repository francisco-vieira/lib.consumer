package br.margay.com.acbr.enums;

import java.util.HashMap;
import java.util.Map;

public enum NivelLog {
    LOG_NENHUM(0),
    LOG_SIMPLES(1),
    LOG_NORMAL(2),
    LOG_COMPLETO(3),
    LOG_PARANOICO(4);

    private static final Map<Integer, NivelLog> map;
    private final int enumValue;

    static {
        map = new HashMap<>();
        for (NivelLog value : NivelLog.values()) {
            map.put(value.asInt(), value);
        }
    }

    public static NivelLog valueOf(int value) {
        return map.get(value);
    }

    private NivelLog(int id) {
        this.enumValue = id;
    }

    public int asInt() {
        return enumValue;
    }
}
