package com.seu.wufan.alumnicircle.ui.activity.circle;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.seu.wufan.alumnicircle.R;
import com.seu.wufan.alumnicircle.common.base.BaseSwipeActivity;
import com.seu.wufan.alumnicircle.ui.adapter.circle.ImageAdapter;
import com.seu.wufan.alumnicircle.ui.fragment.circle.DynamicTextItemAgreeFragment;
import com.seu.wufan.alumnicircle.ui.fragment.circle.DynamicTextItemCommentFragment;
import com.seu.wufan.alumnicircle.ui.fragment.circle.DynamicTextItemShareFragment;
import com.seu.wufan.alumnicircle.ui.widget.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author wufan
 * @date 2016/2/2
 */
public class DynamicTextActivity extends BaseSwipeActivity {

    @Bind(R.id.circle_dynamic_share_linear_layout)
    LinearLayout mShareLl;
    @Bind(R.id.circle_dynamic_agree_linear_layout)
    LinearLayout mAgreeLl;
    @Bind(R.id.circle_dynamic_comment_linear_layout)
    LinearLayout mCommentLl;
    @Bind(R.id.circle_dynamic_text_scroll_view)
    ScrollView mDynamicTextSv;
    @Bind(R.id.circle_dynamic_list_card_view_grid_view)
    MyGridView imageGridView;

    DynamicTextItemCommentFragment commentFragment;
    DynamicTextItemAgreeFragment agreeFragment;
    DynamicTextItemShareFragment shareFragment;

    ImageAdapter imageAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_dynamic_text;
    }

    @Override
    protected void prepareDatas() {

    }

    @Override
    protected void initViewsAndEvents() {
        mDynamicTextSv.smoothScrollTo(0,0);


        initDatas();
        mShareLl.setOnClickListener(listener);
        mAgreeLl.setOnClickListener(listener);
        mCommentLl.setOnClickListener(listener);
    }

    private void initDatas() {
        selectFragment(0);

        List<String> images = new ArrayList<>();
        images.add(0,"http://img4.imgtn.bdimg.com/it/u=2015527637,3623972403&fm=21&gp=0.jpg");
        imageAdapter = new ImageAdapter(this,images);
        imageGridView.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.circle_dynamic_comment_linear_layout:
                    selectFragment(0);
                    break;
                case R.id.circle_dynamic_agree_linear_layout:
                    selectFragment(1);
                    break;
                case R.id.circle_dynamic_share_linear_layout:
                    selectFragment(2);
                    break;
            }
        }
    };

    void selectFragment(int i) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (i) {
            case 0:
                if (commentFragment == null) {
                    commentFragment = new DynamicTextItemCommentFragment();
                    ft.add(R.id.circle_dynamic_text_container, commentFragment);
                } else {
                    ft.show(commentFragment);
                }
                break;
            case 1:
                if (agreeFragment == null) {
                    agreeFragment = new DynamicTextItemAgreeFragment();
                    ft.add(R.id.circle_dynamic_text_container, agreeFragment);
                } else {
                    ft.show(agreeFragment);
                }
                break;
            case 2:
                if (shareFragment == null) {
                    shareFragment = new DynamicTextItemShareFragment();
                    ft.add(R.id.circle_dynamic_text_container, shareFragment);
                } else {
                    ft.show(shareFragment);
                }
                break;
        }
        ft.commit();
    }

    void hideFragment(FragmentTransaction ft) {
        if (commentFragment != null) {
            ft.hide(commentFragment);
        }
        if (agreeFragment != null) {
            ft.hide(agreeFragment);
        }
        if (shareFragment != null) {
            ft.hide(shareFragment);
        }
    }

}
