package com.hanker.core.base.delegate;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/10/26.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate{

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
