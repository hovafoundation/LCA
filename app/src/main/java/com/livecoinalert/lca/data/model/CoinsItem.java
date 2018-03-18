package com.livecoinalert.lca.data.model;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreampany.framework.data.adapter.SmartAdapter;
import com.dreampany.framework.data.model.BaseItem;
import com.dreampany.framework.data.util.ViewUtil;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by suneel on 3/18/2018.
 */

public class CoinsItem extends BaseItem<CoinsItem.ViewHolder> implements IFlexible<CoinsItem.ViewHolder>, Comparable<CoinsItem>
{
    @Override
    public int getLayoutRes() {
        return 0;
    }

    @Override
    public CoinsItem.ViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return null;
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, CoinsItem.ViewHolder holder, int position, List<Object> payloads) {

    }

    @Override
    public boolean filter(String constraint) {
        return false;
    }

    @Override
    public int compareTo(@NonNull CoinsItem coinsItem) {
        return 0;
    }
    static final class ViewHolder extends FlexibleViewHolder {

        TextView word;
        TextView partOfSpeech;
        ImageView selection;
        Button load;

        ViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            word = view.findViewById(R.id.viewWord);
            partOfSpeech = view.findViewById(R.id.viewPartOfSpeech);
            selection = view.findViewById(R.id.imageSelection);
            load = view.findViewById(R.id.buttonLoadMore);

            if (adapter instanceof SmartAdapter) {
                View.OnClickListener listener = ((SmartAdapter) adapter).getClickListener();
                ViewUtil.setClickListener(load, listener);
                return;
            }
        }
    }
}
