package com.hanker.test.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hanker.core.base.delegate.LatteDelegate;
import com.hanker.core.net.retrorit.ApiConfig;
import com.hanker.core.net.retrorit.RestClient;
import com.hanker.core.net.retrorit.callback.IError;
import com.hanker.core.net.retrorit.callback.IFailure;
import com.hanker.core.net.retrorit.callback.ISuccess;
import com.hanker.core.utils.AesUtil;
import com.hanker.core.utils.MatChaLogger;
import com.hanker.core.utils.UtilForRequest;
import com.hanker.test.R;

import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/10/26.
 */

public class MainDelegate extends LatteDelegate {

    @BindView(R.id.test)
    TextView mTest;


    @OnClick(R.id.test)
    void test(){

        final WeakHashMap<String, Object> map = new WeakHashMap<>();
        map.put(ApiConfig.FIELD_APPID, "102");
        map.put(ApiConfig.FIELD_PASSWORD, UtilForRequest.genMD5String("qwe123"));
        map.put(ApiConfig.FIELD_PHONENUM, AesUtil.doEncrypt("18500235644"));
        map.put(ApiConfig.FIELD_TYPE, 0);
        map.put(ApiConfig.FIELD_PLATFORM, UtilForRequest.getAndroidVer());
        map.put(ApiConfig.FIELD_PHONEINFO, UtilForRequest.getAndroidModel());
        map.put(ApiConfig.FIELD_DEVICEID, UtilForRequest.getDeviceId());
        map.put(ApiConfig.FIELD_SIGN, UtilForRequest.genSigntureString(map));

        RestClient.builder()
                .url("login")
                .params(map)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        MatChaLogger.d("flowbank", response);
                        ToastUtils.showShort(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        ToastUtils.showShort("连接失败");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        MatChaLogger.d("flowbank: ", "code:"+code+"msg:"+msg);
                    }
                })

                .build()
                .get();

    }

    @Override
    public Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
//        StatusBarCompat.setStatusBarColor(getActivity(), Color.YELLOW);
    }


}
