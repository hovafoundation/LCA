package com.livecoinalert.lca.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreampany.framework.data.util.AndroidUtil;
import com.dreampany.framework.data.util.TextUtil;
import com.dreampany.framework.ui.fragment.BaseFragment;
import com.livecoinalert.lca.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * Created by nuc on 10/30/2017.
 */

public class AboutFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = inflater.getContext();

        setTitle("About");

        AboutPage page = new AboutPage(context)
                .isRTL(false)
                .setImage(R.mipmap.ic_launcher)
                .setDescription(TextUtil.getString(context, R.string.app_name))
                .addItem(getVersion(context))
                .addItem(getPrivacyPolicy(context))
                .addGroup("Connect with us")
                .addEmail(TextUtil.getString(context, R.string.email)/*, TextUtil.getString(context, R.string.title_email)*/)
                .addWebsite(TextUtil.getString(context, R.string.website)/*, TextUtil.getString(context, R.string.title_website)*/)
                .addPlayStore(AndroidUtil.getApplicationId(context)/*, TextUtil.getString(context, R.string.title_play_store)*/)
                .addGitHub(TextUtil.getString(context, R.string.id_github)/*, TextUtil.getString(context, R.string.title_github)*/)
                .addItem(getYandexTranslation(context));

        return page.create();
    }

    private Element getVersion(Context context) {
        String version = AndroidUtil.getVersionName(context);
        Element element = new Element()
                .setTitle(TextUtil.getString(context, R.string.summary_app_version, version));
        return element;
    }

    private Element getPrivacyPolicy(Context context) {
        Element element = new Element()
                .setTitle(TextUtil.getString(context, R.string.title_privacy_policy))
                .setOnClickListener(view -> {
                    String url = TextUtil.getString(context, R.string.url_privacy);
                    Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                    urlIntent.setData(Uri.parse(url));
                    startActivity(urlIntent);
                });
        return element;
    }

    private Element getYandexTranslation(Context context) {
        Element element = new Element()
                .setTitle(TextUtil.getString(context, R.string.title_yandex_translation))
                .setOnClickListener(view -> {
                    String url = TextUtil.getString(context, R.string.url_yandex_translation);
                    Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                    urlIntent.setData(Uri.parse(url));
                    startActivity(urlIntent);
                });
        return element;
    }
}
