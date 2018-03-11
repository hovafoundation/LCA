package com.livecoinalert.lca.app;

import android.app.Activity;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.dreampany.framework.app.BaseApp;
import com.dreampany.framework.data.api.network.data.model.NetworkEvent;
import com.dreampany.framework.data.event.NotifyEvent;
import com.dreampany.framework.data.model.Color;
import com.dreampany.framework.data.util.AndroidUtil;
import com.dreampany.framework.data.util.ColorUtil;
import com.dreampany.framework.data.util.TextUtil;
import com.dreampany.framework.ui.activity.BaseActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;
import com.livecoinalert.lca.BuildConfig;
import com.livecoinalert.lca.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.fabric.sdk.android.Fabric;


/**
 * Created by nuc on 2/21/2017.
 */

public class App extends BaseApp {

    private Color color;

    @Override
    protected void onOpen() {
        //setupLeakCanary();

        FirebaseApp.initializeApp(context);
        MobileAds.initialize(context, TextUtil.getString(context, R.string.admob_app_id));

        Crashlytics crashlyticsKit = new Crashlytics.Builder()
                .core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build();

        Fabric.with(context, crashlyticsKit);

        // Initialize the Branch object
        // Branch.getAutoInstance(this);
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context).setDownsampleEnabled(true).build();
        Fresco.initialize(context, config);
        // Colorful.init(this);
        //TypefaceProvider.registerDefaultIconSets();
/*        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/CrimsonText-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );*/

        //      AndroidUtil.initTextToSpeech(this);
        //  TaskManager.onInstance(context).start();
//        EventManager.register(this);

        // openPowerService(ItemService.class, 60);
        //openService(NotifyService.class, (int) TimeUtil.hourToSeconds(1));
    }

    @Override
    protected void onClose() {

    }

    @Override
    protected void onActivityOpen(Activity activity) {

    }

    @Override
    protected void onActivityClose(Activity activity) {

    }

    @Override
    protected boolean enableAppIndex() {
        return true;
    }

    @Override
    protected boolean enableAppUpdate() {
        return true;
    }

    @Override
    protected boolean enableRate() {
        return true;
    }

    @Override
    protected String getName() {
        return TextUtil.getString(context, R.string.app_name);
    }

    @Override
    protected String getDescription() {
        return TextUtil.getString(context, R.string.app_description);
    }

    @Override
    protected String getUrl() {
        return TextUtil.getString(context, R.string.app_url, AndroidUtil.getApplicationId(context));
    }

    @Override
    public boolean enableTheme() {
        return false;
    }

    @Override
    public boolean enableColor() {
        return true;
    }

    @Override
    public boolean enableCalligraphy() {
        return false;
    }

    @Override
    protected String getFontPath() {
        return "fonts/Lato-Regular.ttf";
    }

    @Override
    public Color getColor() {
        return color;
    }

    /*    @Override
    public void trackMe(String screenName) {
        if (AndroidUtil.isDebug()) return;
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, screenName);
        FirebaseAnalytics.getContext(context).logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }*/


    @Override
    protected <T extends BaseActivity> Class<T> getJumpingClass() {
        return null;
    }

    @Override
    protected boolean needForceJump() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(final NetworkEvent event) {
        if (!event.isConnected()) {
            //showToast("No Internet!");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(final NotifyEvent event) {
        /*if (event.isAlert()) {
            BaseActivity activity = getParent();

            if (activity == null) {
                return;
            }
            activity.showAlert(
                    TextUtil.getString(getAppContext(), R.string.build_alert_title),
                    TextUtil.getString(getAppContext(), R.string.build_alert_comment),
                    R.color.colorGreen900, TimeUtil.secondToMilli(5L));
        }*/
    }

    private void showToast(String text) {
        StyleableToast st = new StyleableToast
                .Builder(this)
                .text(text)
                .textColor(android.graphics.Color.WHITE)
                .backgroundColor(ColorUtil.getColor(this, R.color.red))
                .duration(Toast.LENGTH_LONG)
                .build();
        st.show();
    }

}
