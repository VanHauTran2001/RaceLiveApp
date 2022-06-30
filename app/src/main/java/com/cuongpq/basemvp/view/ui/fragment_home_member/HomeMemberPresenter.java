package com.cuongpq.basemvp.view.ui.fragment_home_member;

import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

public class HomeMemberPresenter extends BasePresenter implements IHomeMemberPresenter {
    private IHomeMemberView view;

    public HomeMemberPresenter(IHomeMemberView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {
        view.onClickListener();
    }
}
