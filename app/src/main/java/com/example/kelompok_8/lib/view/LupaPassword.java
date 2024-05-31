package com.example.kelompok_8.lib.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kelompok_8.R;
import com.example.kelompok_8.lib.data.resetpassword.ResetPasswordRequest;
import com.example.kelompok_8.lib.data.resetpassword.ResetPasswordResponse;
import com.example.kelompok_8.lib.data.resetpassword.ResetPasswordResponseError;
import com.example.kelompok_8.lib.koneksi.ApiClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LupaPassword extends AppCompatActivity {

    ImageView btnBack;

    TextInputEditText edtEmail;

    MaterialButton buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        // Tombol kembali
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // inputan email
        edtEmail = findViewById(R.id.loginEmail);
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Tidak perlu melakukan apa-apa di sini
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Kembalikan warna teks ke hitam ketika pengguna mulai mengetik lagi
                edtEmail.setTextColor(Color.BLACK);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Tidak perlu melakukan apa-apa di sini
            }
        });

        // button reset
        buttonReset = findViewById(R.id.reset_button);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateInput();
            }
        });
    }

    public void isResetPassword() {
        String emailInput = edtEmail.getText().toString().trim();

        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setEmail(emailInput);

        Call<ResetPasswordResponse> resetPasswordResponseCall = ApiClient.getUserServices().postResetPassword(resetPasswordRequest);

        resetPasswordResponseCall.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResetPasswordResponse resetPasswordResponse = response.body();

                    String newPassword = resetPasswordResponse.getData().getNew_password().toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(LupaPassword.this);

                    LayoutInflater inflater = LayoutInflater.from(LupaPassword.this);
                    View view = inflater.inflate(R.layout.custom_alert, null);
                    TextView titleView = view.findViewById(R.id.titleTextView);
                    TextView additionalTextView = view.findViewById(R.id.additionalTextView);
                    TextView newPasswordView = view.findViewById(R.id.newPasswordTextView);

                    titleView.setText("Reset Password Berhasil");

                    additionalTextView.setText("Silakan simpan password baru Anda yang di generate otomatis oleh sistem aplikasi.");

                    newPasswordView.setText(newPassword);

                    builder.setView(view);

                    builder.setPositiveButton("OK", (dialog, which) -> {
                        // Arahkan ke halaman login atau lakukan tindakan lain jika perlu
                        Toast.makeText(LupaPassword.this, "Silakan login kembali dengan password baru anda", Toast.LENGTH_SHORT).show();
                        finish();
                    });

                    AlertDialog dialog = builder.create();
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                } else {
                    if (response.code() == 404) {
                        // Jika email tidak ditemukan
                        Toast.makeText(LupaPassword.this, "Email tidak di temukan!, silakan coba kembali", Toast.LENGTH_SHORT).show();
                        edtEmail.setTextColor(Color.rgb(255,0,0));
                        edtEmail.setFocusable(true);
                    } else {
                        Gson gson = new Gson();
                        try {
                            ResetPasswordResponseError errorsResponse = gson.fromJson(response.errorBody().charStream(), ResetPasswordResponseError.class);
                            String errorMessage = errorsResponse.getMessage().toString();

                            // Tampilkan pesan kesalahan ke pengguna menggunakan Toast
                            Toast.makeText(LupaPassword.this, errorMessage, Toast.LENGTH_SHORT).show();

                            // Fokuskan kembali ke field email jika diperlukan
                            if (errorMessage != null && errorMessage.contains("Email")) {
                                edtEmail.requestFocus();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                Toast.makeText(LupaPassword.this, "Reset password failed, please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void ValidateInput(){
        String emailInput;
        emailInput = edtEmail.getText().toString().trim();

        // validasi inputan kosong
        if(TextUtils.isEmpty(emailInput)){
            edtEmail.setError("Email wajib di isi!");
            edtEmail.setFocusable(true);
            return;
        }else if (!isValidEmail(emailInput)) {
            edtEmail.setError("Email harus berakhir dengan @gmail.com!");
            edtEmail.setFocusable(true);
            return;
        }
        else{
            isResetPassword();
        }
    }

    // Fungsi untuk memeriksa validitas email dengan ekspresi reguler
    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9_]+@gmail\\.com$";
        return email.matches(emailPattern);
    }
}