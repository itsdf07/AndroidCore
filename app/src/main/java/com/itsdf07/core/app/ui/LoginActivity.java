package com.itsdf07.core.app.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.itsdf07.core.app.R;
import com.itsdf07.core.app.databinding.ActivityLoginBinding;
import com.itsdf07.core.app.mvvm.model.ILoginModel;
import com.itsdf07.core.app.mvvm.model.User;
import com.itsdf07.core.app.mvvm.model.impl.LoginModelImpl;

/**
 * @Description:
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/6/7
 */
public class LoginActivity extends AppCompatActivity {
    User mUser = null;
    ILoginModel mLoginModel = new LoginModelImpl();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mUser = new User("Aso", "123456");
        binding.setUser(mUser);

        findViewById(R.id.login).setOnClickListener(v -> {
            mUser.setLoginId("itsdf07");
            mLoginModel.login(mUser);
        });

    }
}
