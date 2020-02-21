package com.example.stylishjewelryboxadmin.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.networkAPis.DeliveryBoysignup;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    WebServices webServices;
    TextInputLayout til_name, til_phone;
    TextInputEditText ed_name, ed_phone;
    private String code, CODERECEIVED;
    String formatedDate;
    private FirebaseAuth mAuth;
    ProgressBar progress_signup;
    String deviceId = null;
    String strname;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        boolean mobileDataEnable = Utils.isMobileDataEnable(this);
        boolean wifiEnable = Utils.WifiEnable(this);
        if (mobileDataEnable || wifiEnable) {

            initviews();
        } else {
            Utils.showCustomDialog(this, "No Internet connection ");

        }
    }

    private void initviews() {
        mAuth = FirebaseAuth.getInstance();
        progress_signup = findViewById(R.id.progress_signup);
        webServices = WebServices.RETROFIT.create(WebServices.class);
        til_name = findViewById(R.id.tv_TIL_username);
        til_phone = findViewById(R.id.tv_TIL_phone);

        ed_name = findViewById(R.id.tv_ET_username);
        ed_phone = findViewById(R.id.tv_et_phone);


    }

    public void trylogin(View view) {
        super.onBackPressed();
    }

    @SuppressLint("HardwareIds")
    public void singUp(View view) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());

        formatedDate = simpleDateFormat.format(new Date());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read PHONE State Permission Not Granted ", Toast.LENGTH_SHORT).show();
                return;
            }
        }


        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            deviceId = telephonyManager.getDeviceId();
        }


        strname = til_name.getEditText().getText().toString();
        String strphone = til_phone.getEditText().getText().toString().trim();
        if (TextUtils.isEmpty(strname)) {
            ed_name.setError("Can't be Empty");
        } else if (TextUtils.isEmpty(strphone)) {
            ed_name.clearFocus();
            ed_phone.setError("Can't be Empty");
        } else if (strphone.length() < 10) {
            ed_phone.setError("Invalid Number");
        } else {
            phone = removeLeadingZeros(strphone);
            sendverificationcode("+92" + phone);
        }

    }

    public void showCustomDialog(Context context, String message) {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(false);

        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss();
            Intent intent = new Intent(SignUpActivity.this, LoginActivityActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        });

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public String removeLeadingZeros(String digits) {
        //String.format("%.0f", Double.parseDouble(digits)) //Alternate Solution
        String regex = "^0+";
        return digits.replaceAll(regex, "");
    }

    private void sendverificationcode(String phonenumber) {
        progress_signup.setVisibility(View.VISIBLE);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallback
        );
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback = new
            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    code = phoneAuthCredential.getSmsCode();

                    if (code != null) {
                        verify_OTP(code);

                        Toast.makeText(SignUpActivity.this, "in IF ---onVerificationCompleted\n" + code, Toast.LENGTH_SHORT).show();
                    } else {

                        singnUpAPI(deviceId, strname, formatedDate, phone);
                    }
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCodeSent(String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    CODERECEIVED = s;
                    Toast.makeText(SignUpActivity.this, "Code Sent", Toast.LENGTH_SHORT).show();

                }
            };

    private void verify_OTP(String entered_otp) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(CODERECEIVED, entered_otp);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        singnUpAPI(deviceId, strname, formatedDate, phone);


                    } else {
                        Toast.makeText(SignUpActivity.this, "Auto Verification Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void singnUpAPI(String deviceID, String name, String date, String phone) {
        webServices.deliveryBoySignUp(deviceID, name, "null", "null", "null", "null", date
                , "+92" + phone, "0").enqueue(new Callback<DeliveryBoysignup>() {
            @Override
            public void onResponse(Call<DeliveryBoysignup> call, Response<DeliveryBoysignup> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Boolean status = response.body().getStatus();
                    if (status) {

                        showCustomDialog(SignUpActivity.this, "User created  and will be approved by admin");
                        progress_signup.setVisibility(View.GONE);


                    } else {
                        progress_signup.setVisibility(View.GONE);
                        Toast.makeText(SignUpActivity.this, "Phone Number Already Registered!", Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<DeliveryBoysignup> call, Throwable t) {
                progress_signup.setVisibility(View.GONE);
                Toast.makeText(SignUpActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
