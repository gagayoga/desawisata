package com.example.kelompok_8.lib.data.resetpassword;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ResetPasswordResponseError {
    @SerializedName("Status")
    private int status;

    @SerializedName("Message")
    private Map<String, String[]> message;

    public int getStatus() {
        return status;
    }

    public Map<String, String[]> getMessage() {
        return message;
    }
}
