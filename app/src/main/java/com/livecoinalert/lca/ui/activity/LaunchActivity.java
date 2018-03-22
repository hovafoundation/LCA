package com.livecoinalert.lca.ui.activity;

import android.os.Bundle;

import com.livecoinalert.lca.R;
import com.livecoinalert.lca.databinding.ActivityLaunchBinding;
import com.dreampany.framework.data.util.AndroidUtil;
import com.dreampany.framework.ui.activity.BaseActivity;
import com.dynamitechetan.flowinggradient.FlowingGradientClass;


/**
 * Created by air on 9/17/17.
 */

public class LaunchActivity extends BaseActivity {

    private ActivityLaunchBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected boolean enableFullScreen() {
        return true;
    }

    @Override
    protected void startUi(Bundle state) {
        super.startUi(state);
        binding = getBinding();

        FlowingGradientClass grad = new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.translate)
                .onRelativeLayout(binding.layout)
                .setTransitionDuration(2000)
                .start();

        binding.shimmer.startShimmerAnimation();

        AndroidUtil.post(() -> {
            binding.shimmer.stopShimmerAnimation();
            openActivity(NavigationActivity.class);
            finish();
        }, 2000L);
    }



   /* @Override
    public void onStart() {
        super.onStart();
        Branch branch = Branch.getInstance();

        branch.initSession((branchUniversalObject, linkProperties, error) -> {
            if (error == null) {
                // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
                // params will be empty if no data found
                // ... insert custom logic here ...
            } else {
                Log.i("MyApp", error.getMessage());
            }
        }, this.getIntent().getData(), this);
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }*/
}
