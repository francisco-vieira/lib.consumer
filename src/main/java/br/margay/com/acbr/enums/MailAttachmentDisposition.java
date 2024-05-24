package br.margay.com.acbr.enums;

import java.util.HashMap;
import java.util.Map;

public enum MailAttachmentDisposition {
    ATTACHMENT(0),
    INLINE(1);

    private static final Map<Integer, MailAttachmentDisposition> map;
    private final int enumValue;

    static {
        map = new HashMap<>();
        for (MailAttachmentDisposition value : MailAttachmentDisposition.values()) {
            map.put(value.asInt(), value);
        }
    }

    public static MailAttachmentDisposition valueOf(int value) {
        return map.get(value);
    }

    MailAttachmentDisposition(int id) {
        this.enumValue = id;
    }

    public int asInt() {
        return enumValue;
    }
}