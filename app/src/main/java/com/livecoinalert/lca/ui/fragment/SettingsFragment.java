package com.livecoinalert.lca.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;

import com.dreampany.framework.data.model.Text;
import com.dreampany.framework.data.model.UiTask;
import com.dreampany.framework.data.util.AndroidUtil;
import com.dreampany.framework.ui.fragment.BaseFragment;
import com.livecoinalert.lca.R;
import com.livecoinalert.lca.data.enums.ItemState;
import com.livecoinalert.lca.data.enums.ItemSubtype;
import com.livecoinalert.lca.data.enums.ItemType;
import com.livecoinalert.lca.data.provider.pref.Pref;
import com.livecoinalert.lca.ui.activity.ToolsActivity;
import com.webianks.easy_feedback.EasyFeedback;


/**
 * Created by nuc on 6/11/2017.
 */

public class SettingsFragment extends BaseFragment {

    @Override
    protected int getPrefLayoutId() {
        return R.xml.settings;
    }

    @Override
    protected void startUi(Bundle state) {
        super.startUi(state);
        setTitle("Settings");

        Preference feedback = findPreference(getString(R.string.key_feedback));
        Preference rate = findPreference(getString(R.string.key_rate));
        Preference about = findPreference(getString(R.string.key_about));
        feedback.setOnPreferenceClickListener(this);
        rate.setOnPreferenceClickListener(this);
        about.setOnPreferenceClickListener(this);

        ListPreference language = (ListPreference) findPreference(getString(R.string.key_language));
        ListPreference country = (ListPreference) findPreference(getString(R.string.key_country));
        if (language != null) {
            if (language.getValue() == null) {
                String code = Pref.onInstance(getContext()).getLanguageCode();
                language.setValue(code);
            }
        }
        if (country != null) {
            if (country.getValue() == null) {
                String code = Pref.onInstance(getContext()).getCountryCode();
                country.setValue(code);
            }

        }
        //LanguageList.setStandardOptionLabel(getString(R.string.default_locale));

/*        String version = AndroidUtil.getVersionName(getContext());
        Preference versionPref = findPreference(getString(R.string.key_version));
        versionPref.setSummary(TextUtil.getString(getAppContext(), R.string.summary_app_version, version));*/

    }

    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
        super.onStop();
    }

    @Override
    public void onPause() {
        //Language.setFromPreference(this, getString(R.string.key_language));
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        super.onSharedPreferenceChanged(sharedPreferences, key);

        /*boolean activated = sharedPreferences.getBoolean(key, false);

        PrefEvent<Boolean> event = new PrefEvent<>();
        event.key = key;
        event.value = activated;

        EventManager.post(event);*/
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (getString(R.string.key_feedback).equals(preference.getKey())) {
            sendFeedback();
            return true;
        }
        if (getString(R.string.key_rate).equals(preference.getKey())) {
            rateUs();
            return true;
        }
        if (getString(R.string.key_about).equals(preference.getKey())) {
            showAbout();
            return true;
        }
        return super.onPreferenceClick(preference);
    }

    private void sendFeedback() {
        new EasyFeedback.Builder(getActivity())
                .withEmail(getString(R.string.email))
                .withSystemInfo()
                .build()
                .start();
    }


    private void rateUs() {
        AndroidUtil.rateUs(getActivity());
    }

    private void showAbout() {
        UiTask<Text, ItemType, ItemSubtype, ItemState> task = new UiTask<>();
        task.setType(ItemType.ABOUT);
        AndroidUtil.openActivity(getParent(), ToolsActivity.class, task);
    }
}
