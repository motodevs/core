package com.openvehicletracking.core;

public class Reply {

    private byte[] data;

    public Reply(String data) {
        if (data != null) {
            this.data = data.getBytes();
        }
    }

    public Reply(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
