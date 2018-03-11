package com.livecoinalert.lca.ui.activity;

import android.os.Bundle;
import android.view.ViewGroup;

import com.dreampany.framework.data.event.AdEvent;
import com.dreampany.framework.data.event.ParticleEvent;
import com.dreampany.framework.data.manager.AdManager;
import com.dreampany.framework.data.manager.ParticleManager;
import com.dreampany.framework.data.model.UiTask;
import com.dreampany.framework.data.util.ViewUtil;
import com.dreampany.framework.ui.activity.BaseMenuActivity;
import com.dreampany.framework.ui.fragment.BaseFragment;
import com.livecoinalert.lca.R;
import com.livecoinalert.lca.data.enums.ItemSubtype;
import com.livecoinalert.lca.data.enums.ItemType;
import com.livecoinalert.lca.ui.fragment.AboutFragment;
import com.livecoinalert.lca.ui.fragment.WordsFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by nuc on 3/28/2017.
 */

public class ToolsActivity extends BaseMenuActivity {

    private boolean fullScreen;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tools;
    }

    @Override
    public int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected boolean enableFullScreen() {
        return false;
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    public void startUi(Bundle state) {
        UiTask task = (UiTask) getCurrentTask(true);
        if (task == null) {
            return;
        }
        fullScreen = task.isFullscreen();
        super.startUi(state);
        Class<? extends BaseFragment> fragmentClass = null;

        ItemType type = (ItemType) task.getType();
        ItemSubtype subtype = (ItemSubtype) task.getSubtype();

        switch (type) {
            case DEMO:
                fragmentClass = WordsFragment.class;
                break;
            case ABOUT:
                fragmentClass = AboutFragment.class;
                break;
        }

        if (fragmentClass != null) {
            commitFragment(fragmentClass, R.id.layout, task);
        }

        AdManager.onInstance(getContext()).loadBanner(ViewUtil.getAdView(this, R.id.adView));
        //AdManager.onInstance(getContext()).loadInterstitial(R.string.interstitial_ad_unit_id);
    }

    @Override
    protected void stopUi() {
        AdManager.onInstance(getContext()).closeBanner();
        super.stopUi();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AdEvent event) {
        ViewGroup layout = ViewUtil.getViewById(this, R.id.layout);
        ParticleManager.onInstance().onShot(layout);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ParticleEvent event) {
        ViewGroup layout = ViewUtil.getViewById(this, R.id.layout);
        ParticleManager.onInstance().onShot(layout);
    }
}
