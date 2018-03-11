package com.livecoinalert.lca.data.provider.pref;

import android.content.Context;

import com.dreampany.framework.data.provider.pref.FramePref;


/**
 * Created by air on 9/2/17.
 */

public final class Pref extends FramePref {

    private static final String LOAD = "load";
    private static final String LOAD_CATEGORY = "load_category";
    private static final String RAW_STATE = "raw_state";

    private static Pref instance;

    private Pref(Context context) {
        super(context);
    }

    synchronized public static Pref onInstance(Context context) {
        if (instance == null) {
            instance = new Pref(context);
        }
        return instance;
    }

    public void commitRawStated() {
        privatePref.put(RAW_STATE, true);
    }

    public void commitCategoryLoaded() {
        privatePref.put(LOAD_CATEGORY, true);
    }

    public void commitLoaded() {
        privatePref.put(LOAD, true);
    }

    public boolean isLoaded() {
        return privatePref.get(LOAD, Boolean.class, false);
    }

    public boolean isRawStated() {
        return privatePref.get(RAW_STATE, Boolean.class, false);
    }

    public boolean isCategoryLoaded() {
        return privatePref.get(LOAD_CATEGORY, Boolean.class, false);
    }
}

