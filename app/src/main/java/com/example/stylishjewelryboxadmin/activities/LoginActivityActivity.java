package com.example.stylishjewelryboxadmin.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stylishjewelryboxadmin.R;
import com.example.stylishjewelryboxadmin.networkAPis.WebServices;
import com.example.stylishjewelryboxadmin.networkAPis.getLoginDetails.GetLoginDetail;
import com.example.stylishjewelryboxadmin.networkAPis.getLoginDetails.GetLoginDetailResponse;
import com.example.stylishjewelryboxadmin.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityActivity extends AppCompatActivity {
    public static final String NAME = "name";
    public static final String PHONENUMBER = "PHONENUMBER";
    public static final String UUID = "UUID";
    public static final String LOGIN_ID = "login_id";
    private TextInputEditText ed_name, ed_phone;
    private TextInputLayout til_name, til_phone;
    private WebServices webServices;
    private FirebaseAuth mAuth;
    List<GetLoginDetail> list;
    Button btn_login;
    String code;
    String strname;
    String removeLeadingZerosPHONE;
    ProgressBar progress_signin;
    private String CODERECEIVED;
    @SuppressLint("HardwareIds")
    String deviceId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        boolean mobileDataEnable = Utils.isMobileDataEnable(this);
        boolean wifiEnable = Utils.WifiEnable(this);
        if (mobileDataEnable || wifiEnable) {


            initviews();

        } else {
            Utils.showCustomDialog(this, "No Internet connection ");

        }

    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback = new
            PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    code = phoneAuthCredential.getSmsCode();

                    if (code != null) {
                        verify_OTP(code);


                        Toast.makeText(LoginActivityActivity.this, "in IF ---onVerificationCompleted\n" + code, Toast.LENGTH_SHORT).show();
                    } else {
                        progress_signin.setVisibility(View.GONE);

                        showCustomDialog(LoginActivityActivity.this, "Do you want to safe credentials?");
                    }

                }

                @Override
                public void onVerificationFailed(FirebaseException e) {

                    btn_login.setBackgroundResource(R.drawable.button_corner);
                    btn_login.setEnabled(true);
                    Toast.makeText(LoginActivityActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCodeSent(String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    CODERECEIVED = s;
                    Toast.makeText(LoginActivityActivity.this, "Code Sent", Toast.LENGTH_SHORT).show();

                }
            };

    public void singUpform(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void initviews() {
        progress_signin = findViewById(R.id.progress_signin);
        webServices = WebServices.RETROFIT.create(WebServices.class);
        mAuth = FirebaseAuth.getInstance();
        til_name = findViewById(R.id.tvtilname);
        til_phone = findViewById(R.id.tvtilphone);
        ed_name = findViewById(R.id.tvetname);
        ed_phone = findViewById(R.id.tvetphone);
        btn_login = findViewById(R.id.btn_login);
    }

    private void sendverificationcode(String phonenumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallback
        );
    }

    @SuppressLint("HardwareIds")
    public void lognIn(View view) {


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
        String strphone = til_phone.getEditText().getText().toString();
        strname = til_name.getEditText().getText().toString();
        if (TextUtils.isEmpty(strname)) {
            ed_name.setError("Can't be Empty");
        } else if (TextUtils.isEmpty(strphone)) {
            ed_phone.setError("Can't be Empty");
        } else if (strphone.length() < 11) {
            ed_phone.setError("Invalid Number");
        } else {
            btn_login.setBackgroundResource(R.drawable.button_corner1);
            btn_login.setEnabled(false);
            removeLeadingZerosPHONE = removeLeadingZeros(strphone);
            progress_signin.setVisibility(View.VISIBLE);
            webServices.getLogin("+92" + removeLeadingZerosPHONE, strname).enqueue(new Callback<GetLoginDetailResponse>() {
                @Override
                public void onResponse(Call<GetLoginDetailResponse> call, Response<GetLoginDetailResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        list = response.body().getGetLoginDetail();
                        Boolean status = response.body().getStatus();
                        if (status) {
                            for (int x = 0; x < list.size(); x++) {
//                                String jdbName = pendinglist.get(x).getJdbName();
//                                String jdbPhone = pendinglist.get(x).getJdbPhone();
                                String jdbUid = list.get(x).getJdbUid();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                                        Toast.makeText(LoginActivityActivity.this, "Read PHONE State Permission Not Granted ", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }

                                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                                if (telephonyManager != null) {
                                    deviceId = telephonyManager.getDeviceId();
                                    if (jdbUid.equalsIgnoreCase(deviceId)) {
                                        sendverificationcode("+92" + removeLeadingZerosPHONE);


                                    } else {
                                        Toast.makeText(LoginActivityActivity.this, "Please Login From Registered Device", Toast.LENGTH_LONG).show();
                                        progress_signin.setVisibility(View.GONE);

                                        btn_login.setBackgroundResource(R.drawable.button_corner);
                                        btn_login.setEnabled(true);

                                    }

                                }


                            }
                        } else {
                            Toast.makeText(LoginActivityActivity.this, "Phone or User Name does not match", Toast.LENGTH_SHORT).show();

                            btn_login.setBackgroundResource(R.drawable.button_corner);
                            btn_login.setEnabled(true);
                            progress_signin.setVisibility(View.GONE);

                        }
                    }
                }

                @Override
                public void onFailure(Call<GetLoginDetailResponse> call, Throwable t) {
                    progress_signin.setVisibility(View.GONE);

                    btn_login.setBackgroundResource(R.drawable.button_corner);
                    btn_login.setEnabled(true);
                    Toast.makeText(LoginActivityActivity.this, "on fail " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void verify_OTP(String entered_otp) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(CODERECEIVED, entered_otp);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        progress_signin.setVisibility(View.GONE);
                        showCustomDialog(this, "Do you want to safe credentials?");

                    } else {
                        progress_signin.setVisibility(View.GONE);

                        Toast.makeText(LoginActivityActivity.this, "Auto Verification Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public String removeLeadingZeros(String digits) {
        //String.format("%.0f", Double.parseDouble(digits)) //Alternate Solution
        String regex = "^0+";
        return digits.replaceAll(regex, "");
    }


    public void showCustomDialog(final Context context, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (dialog, which) -> {
            savecredits(strname, "+92" + removeLeadingZerosPHONE, deviceId);
            Intent intent = new Intent(LoginActivityActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            String jdbId = list.get(0).getJdbId();
            Utils.savePreferences(LOGIN_ID, jdbId, this);
            Utils.savePreferences(NAME, strname, this);
            Intent intent = new Intent(LoginActivityActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void savecredits(String name, String phonenumber, String uuid) {
        Utils.savePreferences(NAME, name, this);
        Utils.savePreferences(PHONENUMBER, phonenumber, this);
        Utils.savePreferences(UUID, uuid, this);
        String jdbId = list.get(0).getJdbId();
        Utils.savePreferences(LOGIN_ID, jdbId, this);
    }
}
