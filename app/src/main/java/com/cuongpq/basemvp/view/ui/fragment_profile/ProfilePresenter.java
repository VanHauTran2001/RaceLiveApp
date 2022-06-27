package com.cuongpq.basemvp.view.ui.fragment_profile;

import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

public class ProfilePresenter extends BasePresenter implements IProfilePresenter {
    private IProfileView view;

    public ProfilePresenter(IProfileView view) {
        this.view = view;
    }

    @Override
    public void oninitPresenter() {
        view.onClickListenner();
        view.setUserInformation();
        view.onUpdateUser();
    }
}
