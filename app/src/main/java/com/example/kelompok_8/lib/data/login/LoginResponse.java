package com.example.kelompok_8.lib.data.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private LoginResponse.UserData data;

    // Buat constructor, getter, dan setter sesuai kebutuhan
    public LoginResponse(int status, String message, LoginResponse.UserData data) {
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

    public LoginResponse.UserData getData() {
        return data;
    }

    public void setData(LoginResponse.UserData data) {
        this.data = data;
    }

    // Class untuk representasi data user
    public static class UserData {
        @SerializedName("id")
        private int id;

        @SerializedName("email")
        private String email;

        @SerializedName("name")
        private String username;

        @SerializedName("token")
        private String token;

        // Buat constructor, getter, dan setter sesuai kebutuhan
        public UserData(int id, String email, String username, String token) {
            this.id = id;
            this.email = email;
            this.username = username;
            this.token = token;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
