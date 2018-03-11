package com.livecoinalert.lca.data.model;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreampany.framework.data.adapter.SmartAdapter;
import com.dreampany.framework.data.model.BaseItem;
import com.dreampany.framework.data.util.ViewUtil;
import com.google.common.base.Objects;
import com.livecoinalert.lca.R;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by air on 10/18/17.
 */

public class DemoItem extends BaseItem<DemoItem.ViewHolder> implements IFlexible<DemoItem.ViewHolder>, Comparable<DemoItem> {

    private Demo demo;
    private int layoutId;

    public DemoItem(Demo demo) {
        this.demo = demo;
        this.layoutId = R.layout.item_demo;
    }

    @Override
    public boolean equals(Object inObject) {
        if (DemoItem.class.isInstance(inObject)) {
            DemoItem item = (DemoItem) inObject;
            return demo.equals(item.demo);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(demo);
    }

    public Demo getDemo() {
        return demo;
    }

    @Override
    public int getLayoutRes() {
        return layoutId;
    }

    @Override
    public boolean filter(String constraint) {
/*        if (demo.getWord().toLowerCase().startsWith(constraint.toLowerCase())) {
            return true;
        }*/
        return false;
    }

    @Override
    public int compareTo(@NonNull DemoItem item) {
        //return demo.getWord().compareToIgnoreCase(item.demo.getWord());
        return 0;
    }

    @Override
    public ViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new ViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ViewHolder holder, int position, List payloads) {

/*        if (adapter.hasSearchText()) {
            FlexibleUtils.highlightText(holder.demo, demo.getWord(), adapter.getSearchText(), ColorUtil.getColor(holder.itemView.getContext(), R.color.colorAmber900));
        } else {
            holder.demo.setText(demo.getWord());
        }

        holder.partOfSpeech.setText(demo.getPartOfSpeech());
        ViewUtil.setVisibility(holder.selection, adapter.isSelected(position) ? View.VISIBLE : View.GONE);*/

/*        if (demo.hasExamples()) {
            holder.viewExample.setText(DataUtil.toFirstString(demo.getExamples()));
            holder.viewExample.setVisibility(View.VISIBLE);
        } else {
            holder.viewExample.setVisibility(View.GONE);
        }*/

/*        Drawable drawable = DrawableUtils.getSelectableBackgroundCompat(
                ColorUtil.getColor(context, color.getColorPrimaryId()),
                ColorUtil.getColor(context, color.getColorAccentId()),
                ColorUtil.getColor(context, R.color.colorWhite));
        DrawableUtils.setBackgroundCompat(holder.itemView, drawable);*/
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
