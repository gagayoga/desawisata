package com.example.kelompok_8.lib.data.resetpassword;

import com.google.gson.annotations.SerializedName;

public class ResetPasswordResponse {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private ResetPasswordResponse.UserData data;

    // Buat constructor, getter, dan setter sesuai kebutuhan
    public ResetPasswordResponse(int status, String message, ResetPasswordResponse.UserData data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResetPasswordResponse.UserData getData() {
        return data;
    }

    public void setData(ResetPasswordResponse.UserData data) {
        this.data = data;
    }

    // Class untuk representasi data user
    public static class UserData {
        @SerializedName("id")
        private int id;

        @SerializedName("email")
        private String email;

        @SerializedName("new_password")
        private String new_password;

        // Buat constructor, getter, dan setter sesuai kebutuhan
        public UserData(int id, String email, String new_password) {
            this.id = id;
            this.email = email;
            this.new_password = new_password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNew_password() {
            return new_password;
        }

        public void setNew_password(String new_password) {
            this.new_password = new_password;
        }
    }
}
