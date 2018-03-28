package com.livecoinalert.lca.ui.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;

import com.livecoinalert.lca.R;
import com.livecoinalert.lca.ui.fragment.ChartFragment;
import com.livecoinalert.lca.ui.fragment.MoreFragment;
import com.dreampany.framework.data.manager.AdManager;
import com.dreampany.framework.data.util.ViewUtil;
import com.dreampany.framework.ui.activity.BaseBottomNavigationActivity;
import com.livecoinalert.lca.ui.fragment.HomeFragment;
import com.lapism.searchview.SearchView;
import com.livecoinalert.lca.ui.fragment.SettingsFragment;


public class NavigationActivity extends BaseBottomNavigationActivity {

    public static final int REQUEST_CODE_TOUR = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_navigation;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }


    @Override
    protected boolean enabledHomeUp() {
        return false;
    }

    @Override
    protected int getNavigationViewId() {
        return R.id.navigationView;
    }

    @Override
    protected void startUi(Bundle state) {
        super.startUi(state);
        boolean touring = checkTour();
        if (!touring) {
            setSelectedItem(R.id.item_home);
            //AdManager.onInstance(getContext()).loadBanner(ViewUtil.getViewById(this, R.id.adView));
        }
    }

    @Override
    protected void stopUi() {
        AdManager.onInstance(getContext()).closeBanner();
        super.stopUi();
    }

    @Override
    public void onNavigationItem(int navItemId) {
        switch (navItemId) {
            case R.id.item_home:
                commitFragment(HomeFragment.class, R.id.layout);
                break;
            case R.id.item_status:
                commitFragment(ChartFragment.class, R.id.layout);
                break;
/*            case R.id.item_coins:
                commitFragment(CoinsFragment.class, R.id.layout);
                break;*/
/*            case R.id.item_nearby:
                commitFragment(NearbyFragment.class, R.id.layout);
                break;*/
/*            case R.id.item_library:
                //commitFragment(LibraryFragment.class, R.id.layout);
                break;
            case R.id.item_flag:
                //commitFragment(FlagFragment.class, R.id.layout);
                break;*/
            case R.id.item_more:
                commitFragment(SettingsFragment.class, R.id.layout);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        if (getCurrentFragment().beBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SearchView.SPEECH_REQUEST_CODE:
                getCurrentFragment().onActivityResult(requestCode, resultCode, data);
                break;
            case REQUEST_CODE_TOUR:
                if (resultCode == RESULT_OK) {
                    //Pref.onInstance(getContext()).commitTour();
                    setSelectedItem(R.id.item_home);
                } else {
                    finish();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public boolean checkTour() {
/*        if (!Pref.onInstance(getContext()).hasTour()) {
            AndroidUtil.openActivityForResult(this, TourActivity.class, REQUEST_CODE_TOUR);
            return true;
        }*/
        return false;
    }

    public void goLibrary() {
        //setSelectedItem(R.id.item_library);
    }

    // Kushal ---

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar tool= findViewById(getToolbarId());
        BottomNavigationView nav= findViewById(R.id.navigationView);
        nav.setBackground(tool.getBackground());
        int[][] states = new int[][] {
                /*new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_enabled},*/ // disabled
                new int[] {android.R.attr.state_checked}, // unchecked
                new int[] {-android.R.attr.state_checked}  // pressed
        };

        int[] colors = new int[] {
            /*    getWindow().getStatusBarColor(),
                Color.WHITE*/
                Color.WHITE,
                getWindow().getStatusBarColor()
        };
        ColorStateList myList = new ColorStateList(states, colors);
        nav.setItemIconTintList(myList);
        nav.setItemTextColor(myList);
        Drawable background = tool.getBackground();
        int color = ((ColorDrawable) background).getColor();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(color);
        }
    }
}
