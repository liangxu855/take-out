package com.hasee.bh_takeout.dagger.module.fragment;


import com.hasee.bh_takeout.presenter.fragment.HomeFragmentPresenter;
import com.hasee.bh_takeout.ui.fragment.HomeFragment;

import dagger.Module;
import dagger.Provides;

/**
 * HomeFragment业务类创建
 */
@Module
public class HomeFragmentModule {
    private HomeFragment fragment;

    public HomeFragmentModule(HomeFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    HomeFragmentPresenter provideHomeFragmentPresenter(){
        return new HomeFragmentPresenter(fragment);
    }
}
