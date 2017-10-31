package com.hanker.test.ui.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.hanker.core.base.delegate.LatteDelegate;
import com.hanker.core.utils.timer.BaseTimerTask;
import com.hanker.core.utils.timer.ITimerListener;
import com.hanker.test.R;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/10/27.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener{


    @BindView(R.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }

    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            ToastUtils.showShort("倒计时完毕！");
//                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
