package com.openvehicletracking.core;

public enum GsmSignalStrength {

    NO_SIGNAL(0x00),
    EXTREMELY_WEAK_SIGNAL(0x01),
    VERY_WEAK_SIGNAL(0x02),
    GOOD_SIGNAL(0x03),
    STRONG_SIGNAL(0x04),
    UNKNOWN(0xFF);


    private final byte value;

    GsmSignalStrength(int i) {
        this.value = (byte) i;
    }

    public byte getValue() {
        return value;
    }

    public static GsmSignalStrength create(byte value) {

        switch (value) {
            case 0x00:
                return GsmSignalStrength.NO_SIGNAL;

            case 0x01:
                return GsmSignalStrength.EXTREMELY_WEAK_SIGNAL;

            case 0x02:
                return GsmSignalStrength.VERY_WEAK_SIGNAL;

            case 0x03:
                return GsmSignalStrength.GOOD_SIGNAL;

            case 0x04:
                return GsmSignalStrength.STRONG_SIGNAL;

        }

        return GsmSignalStrength.UNKNOWN;
    }

}
