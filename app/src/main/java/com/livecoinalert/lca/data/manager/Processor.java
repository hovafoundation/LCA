package com.livecoinalert.lca.data.manager;

import android.content.Context;

import com.livecoinalert.lca.data.enums.ItemState;
import com.livecoinalert.lca.data.enums.ItemSubtype;
import com.livecoinalert.lca.data.enums.ItemType;
import com.livecoinalert.lca.data.provider.pref.Pref;
import com.dreampany.framework.data.api.network.data.manager.NetworkManager;
import com.dreampany.framework.data.manager.FrameManager;
import com.dreampany.framework.data.manager.Translator;
import com.dreampany.framework.data.model.Category;
import com.dreampany.framework.data.model.Flag;
import com.dreampany.framework.data.model.Translate;
import com.google.common.base.Objects;

import java.util.List;
import java.util.Locale;

/**
 * Created by air on 11/18/17.
 * this is for database, cloud etc
 */

class Processor {

    private final Context context;
    private final Pref pref;
    private final FrameManager frame;
    private final NetworkManager network;


    Processor(Context context, NetworkManager network) {
        if (context == null) {
            throw new NullPointerException();
        }
        this.context = context.getApplicationContext();
        pref = Pref.onInstance(context);
        frame = FrameManager.onInstance(context);
        this.network = network;
    }

    Pref pref() {
        return pref;
    }

    FrameManager frame() {
        return frame;
    }

    NetworkManager network() {
        return network;
    }

    private boolean hasFlag(Flag flag) {
        return frame.hasFlag(flag.getId(), flag.getType(), flag.getSubtype());
    }

    private boolean hasFlag(String id, ItemType type, ItemSubtype subtype) {
        return frame.hasFlag(id, type.value(), subtype.value());
    }

    private void insert(Flag flag) {
        frame.flagDao().insert(flag);
    }

    private void delete(Flag flag) {
        frame.flagDao().delete(flag);
    }

    boolean toggle(Flag flag) {
        if (hasFlag(flag)) {
            delete(flag);
            return false;
        } else {
            insert(flag);
            return true;
        }
    }

    void storeCategory(Category category) {
        frame.categoryDao().insert(category);
    }

    List<Category> getCategories() {
        return frame.categoryDao().getAll();
    }


    String resolveTranslate(String word) {
        String source = Locale.ENGLISH.getLanguage();
        String target = Pref.onInstance(context).getLanguageCode();

        if (source.equals(target)) {
            return null;
        }

        Translate translate = Translator.onInstance().getTranslate(context, source, target, word);

        if (translate != null) {
            return word.equals(translate.getSourceText()) ? translate.getTargetText() : translate.getSourceText();
        }
        return null;
    }

    boolean isViewed(String id, ItemType type, ItemSubtype subtype) {
        return FrameManager.onInstance(context).stateDao().count(id, type.value(), subtype.value(), ItemState.VIEWED.value()) > 0;
    }

    List<String> getStates(ItemType type, ItemSubtype subtype, ItemState state) {
        return FrameManager.onInstance(context).stateDao().getIds(type.value(), subtype.value(), state.value());
    }

    String getStateId(ItemType type, ItemSubtype subtype, ItemState state, ItemState noState) {
        return FrameManager.onInstance(context).stateDao().getIdWithNo(type.value(), subtype.value(), state.value(), noState.value());
    }

    private String getKey(ItemType type, ItemState state) {
        return String.valueOf(Objects.hashCode(type, state));
    }

    private String getKey(ItemType type, ItemSubtype subtype, ItemState state) {
        return String.valueOf(Objects.hashCode(type, subtype, state));
    }
}
