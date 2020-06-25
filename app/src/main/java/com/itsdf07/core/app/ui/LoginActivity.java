package com.itsdf07.core.app.ui;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.itsdf07.core.app.R;
import com.itsdf07.core.app.databinding.ActivityLoginBinding;
import com.itsdf07.core.app.mvvm.model.User;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/6/7
 */
public class LoginActivity extends AppCompatActivity {
    User mUser = null;

    Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mUser = new User("itsdf07", "123456");
        binding.setUser(mUser);

        handler.postDelayed(() -> {
            mUser.setLoginId("Aso");
            mUser.setPasswd("123");
        }, 3000);
    }
}
