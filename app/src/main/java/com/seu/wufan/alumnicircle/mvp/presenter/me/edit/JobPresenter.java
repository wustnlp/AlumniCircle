package com.seu.wufan.alumnicircle.mvp.presenter.me.edit;

import android.content.Context;

import com.seu.wufan.alumnicircle.api.entity.UserInfoDetailRes;
import com.seu.wufan.alumnicircle.common.utils.NetUtils;
import com.seu.wufan.alumnicircle.common.utils.PreferenceUtils;
import com.seu.wufan.alumnicircle.injector.qualifier.ForApplication;
import com.seu.wufan.alumnicircle.mvp.model.UserModel;
import com.seu.wufan.alumnicircle.mvp.views.IView;
import com.seu.wufan.alumnicircle.mvp.views.activity.me.ICompanyView;
import com.seu.wufan.alumnicircle.mvp.views.activity.me.IJobView;

import javax.inject.Inject;

import retrofit2.HttpException;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author wufan
 * @date 2016/5/15
 */
public class JobPresenter implements IJobPresenter{

    private Subscription jobSubscription;
    private IJobView iJobView;

    private PreferenceUtils preferenceUtils;
    private UserModel userModel;
    private Context appContext;

    @Inject
    public JobPresenter(@ForApplication Context context, UserModel userModel, PreferenceUtils preferenceUtils) {
        this.userModel = userModel;
        this.appContext = context;
        this.preferenceUtils = preferenceUtils;
    }

    @Override
    public void updateJob(UserInfoDetailRes userInfoDetailRes) {
        if(NetUtils.isNetworkConnected(appContext)){
            jobSubscription = userModel.updateUserDetail(userInfoDetailRes)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Void>() {
                        @Override
                        public void call(Void aVoid) {
                            iJobView.destroy();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            if(throwable instanceof retrofit2.HttpException) {
                                HttpException exception = (HttpException) throwable;
                                iJobView.showToast(exception.getMessage());
                            }else{
                                iJobView.showNetError();
                            }
                        }
                    });
        }else {
            iJobView.showNetCantUse();
        }
    }

    @Override
    public void attachView(IView v) {
        iJobView = (IJobView) v;
    }

    @Override
    public void destroy() {
        if(jobSubscription!=null){
            jobSubscription.unsubscribe();
        }
    }
}
