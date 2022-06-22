package com.cuongpq.basemvp.view.ui.fragment_home;

import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

public class HomePresenter extends BasePresenter implements IHomePresenter {
    private IHomeView view;

    public HomePresenter(IHomeView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {
        view.onClickListener();
    }
}
