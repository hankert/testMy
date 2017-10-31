package com.hanker.test.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hanker.core.base.delegate.LatteDelegate;
import com.hanker.core.net.retrorit.RestClient;
import com.hanker.core.net.retrorit.callback.IFailure;
import com.hanker.core.net.retrorit.callback.ISuccess;
import com.hanker.core.utils.MatChaLogger;
import com.hanker.test.R;

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
        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        ToastUtils.showShort(response);
                        MatChaLogger.d("test", response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        ToastUtils.showShort("连接失败");
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
