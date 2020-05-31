package com.lib.core.net.rtf2;

import com.lib.core.net.callback.IError;
import com.lib.core.net.callback.IFailure;
import com.lib.core.net.callback.IRequest;
import com.lib.core.net.callback.ISuccess;
import com.lib.core.net.callback.RequestCallbacks;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @Description: 网络可用框架接口，提供给框架外使用的
 * @Author itsdf07
 * @E-Mail 923255742@qq.com
 * @Github https://github.com/itsdf07
 * @Date 2020/4/2
 */

public class NetClient {
    private final HashMap<String, Object> PARAMS;
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    public NetClient(HashMap<String, Object> params,
                     String url,
                     IRequest request,
                     ISuccess success,
                     IFailure failure,
                     IError error,
                     RequestBody body) {
        this.PARAMS = params;
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;

    }

    public static NetClientBuilder create() {
        return new NetClientBuilder();
    }

    /**
     * 开始实现真实的网络操作
     *
     * @param method
     */
    private void request(HttpMethod method) {
        final ApiService service = NetCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        switch (method) {
            case GET:
                if (null == PARAMS || PARAMS.size() == 0) {
                    call = service.get(URL);
                } else {
                    call = service.get(URL, PARAMS);
                }
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getReqeustCallback());
        }
    }

    private Callback<String> getReqeustCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR);
    }

    //各种请求
    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void postRaw() {
        request(HttpMethod.POST_RAW);
    }

}







