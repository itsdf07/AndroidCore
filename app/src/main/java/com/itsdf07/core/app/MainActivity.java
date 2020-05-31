package com.itsdf07.core.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.itsdf07.lib.alog.ALog;
import com.lib.core.TestLibCore;
import com.lib.core.net.NetInit;
import com.lib.core.net.callback.IError;
import com.lib.core.net.callback.IFailure;
import com.lib.core.net.callback.ISuccess;
import com.lib.core.net.rtf2.NetClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Aso", TestLibCore.onTestLibCore());
    }

    public void requestNet(View view){
        String BASE_URL_LOCAL = "http://192.168.3.3:8080/";
        NetInit.init(this)
                .withApiHost(BASE_URL_LOCAL)
                .configure();
        //http://192.168.3.3:8080/api/test/test2GetWithParam

        NetClient.create()
                .url("api/test/test2GetNoParam")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //responce:{"code":20000,"data":"这是一个无参数的Get请求方式API接口测试","msg":["Success","请求成功","请求成功"],"version":"当前版本信息：1.0.0403(dev)"}
                        ALog.dTag(TAG, "response:%s", response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        ALog.dTag(TAG, "code:%s,msg:%s", code, msg);
                    }
                }).build()
                .get();
        onNetGetWithParam();
        onNetPostWithParam();
    }

    public void onNetGetWithParam() {
        //http://192.168.3.3:8080/api/test/test2GetWithParam1?param1=77&param2=123456
        HashMap params = new HashMap();
        params.put("param1", 77);
        params.put("param2", "123456");
        NetClient.create()
                .url("api/test/test2GetWithParam1")
                .params(params)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //responce:{"code":20000,"data":"这是一个带参数的Get请求方式API接口测试:param1=77,param2=123456","msg":["Success","请求成功","请求成功"],"version":"当前版本信息：1.0.0403(dev)"}
                        ALog.dTag(TAG, "response:%s", response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        ALog.dTag(TAG, "code:%s,msg:%s", code, msg);
                    }
                }).build()
                .get();
    }


    public void onNetPostWithParam() {
//        HashMap params = new HashMap();
//        params.put("param1", 77);
//        params.put("param2", "123456");
//        NetClient.create()
//                .url("api/test/test2PostWithParam1")
//                .params(params)
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String responce) {
//                        //responce:{"code":20000,"data":"这是一个带参数的Get请求方式API接口测试:param1=77,param2=123456","msg":["Success","请求成功","请求成功"],"version":"当前版本信息：1.0.0403(dev)"}
//                        Toast.makeText(NetDemoActivity.this, "onSuccess:responce=" + responce.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .error(new IError() {
//                    @Override
//                    public void onError(int code, String msg) {
//                        Toast.makeText(NetDemoActivity.this, "onError:code=" + code + ",msg=" + msg, Toast.LENGTH_SHORT).show();
//                    }
//                }).build()
//                .post();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("param1", 77);
            jsonObject.put("param2", 123456);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        NetClient.create()
                .url("api/test/test2PostWithParam2")
                .raw(jsonObject.toString())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //responce:{"code":20000,"data":"这是一个带参数的Get请求方式API接口测试:param1=77,param2=123456","msg":["Success","请求成功","请求成功"],"version":"当前版本信息：1.0.0403(dev)"}
                        ALog.dTag(TAG, "response:%s", response);
                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {
                ALog.dTag(TAG, "onFailure:%s");
            }
        })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        ALog.dTag(TAG, "code:%s,msg:%s", code, msg);
                    }
                }).build()
                .postRaw();
    }
}
