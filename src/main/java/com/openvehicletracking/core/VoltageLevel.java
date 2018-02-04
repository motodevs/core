package com.openvehicletracking.core;

public enum VoltageLevel {

    UNKNOWN(0xFF),
    NO_POWER(0x00),
    EXTREMELY_LOW_BATTERY(0x01),
    VERY_LOW_BATTERY(0x02),
    LOW_BATTERY(0x03),
    MEDIUM(0x04),
    HIGH(0x05),
    VERY_HIGH(0x06);

    private final byte value;

    VoltageLevel(int i) {
        this.value = (byte) i;
    }

    public byte getValue() {
        return value;
    }

    public static VoltageLevel create(byte value) {

        switch (value) {
            case 0x00:
                return VoltageLevel.NO_POWER;

            case 0x01:
                return VoltageLevel.EXTREMELY_LOW_BATTERY;

            case 0x02:
                return VoltageLevel.VERY_LOW_BATTERY;

            case 0x03:
                return VoltageLevel.LOW_BATTERY;

            case 0x04:
                return VoltageLevel.MEDIUM;

            case 0x05:
                return VoltageLevel.HIGH;

            case 0x06:
                return VoltageLevel.VERY_HIGH;

        }

        return VoltageLevel.UNKNOWN;
    }
}
