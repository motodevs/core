package com.openvehicletracking.core;

public class Reply {

    private byte[] data;

    public Reply(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
