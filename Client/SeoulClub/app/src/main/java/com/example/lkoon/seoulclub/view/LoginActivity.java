package com.example.lkoon.seoulclub.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lkoon.seoulclub.R;
import com.example.lkoon.seoulclub.RetrofitManager;
import com.example.lkoon.seoulclub.model.User;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lbc on 2017-11-05.
 */

public class LoginActivity extends AppCompatActivity {

    EditText etId, etpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        etId = (EditText)findViewById(R.id.login_etLogin);
        etpwd =(EditText)findViewById(R.id.etPwd);


    }

    @OnClick(R.id.btnLogin_login)
     void login(View view) {
        User user = new User(etId.getText().toString(),etpwd.getText().toString());
        RetrofitManager.getInstance().getUrl().login(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                   if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "로그인에 성공 했습니다..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "로그인에 실패 했습니다.", Toast.LENGTH_SHORT).show();
                    Log.i(""+response.code(),""+response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "서버와 통신에 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnjoin_login)
     void Join(View view) {
        Intent intent = new Intent(LoginActivity.this,JoinActivity.class);
        startActivity(intent);
    }

}
