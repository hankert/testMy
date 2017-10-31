package com.hanker.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.hanker.core.base.ProxyActivity;
import com.hanker.core.base.delegate.LatteDelegate;
import com.hanker.test.ui.MainDelegate;
import com.hanker.test.ui.launcher.ILauncherListener;
import com.hanker.test.ui.launcher.LauncherDelegate;
import com.hanker.test.ui.launcher.OnLauncherFinishTag;

public class MainActivity extends ProxyActivity implements ILauncherListener{


    @Override
    public LatteDelegate setRootDelegate() {
//        return new LauncherDelegate();
        return new MainDelegate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {



    }

    //    private TextView test;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        test =(TextView) findViewById(R.id.test);
//
//
//        test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                test();
//            }
//        });
//    }
//
//    private void test() {
//        RestClient.builder()
//                .url("http://news.baidu.com/")
//                .loader(this)
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        ToastUtils.showShort(response);
//                        MatChaLogger.d("test", response);
//                    }
//                })
//                .failure(new IFailure() {
//                    @Override
//                    public void onFailure() {
//                        ToastUtils.showShort("连接失败");
//                    }
//                })
//                .build()
//                .get();
//
//    }
}
