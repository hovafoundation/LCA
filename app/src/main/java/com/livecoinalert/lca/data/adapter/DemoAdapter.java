package com.livecoinalert.lca.data.adapter;

import android.support.annotation.NonNull;

import com.livecoinalert.lca.data.model.DemoItem;
import com.dreampany.framework.data.adapter.SmartAdapter;

import java.util.Comparator;

import eu.davidea.flexibleadapter.items.IFlexible;

/**
 * Created by air on 10/20/17.
 */

public class DemoAdapter extends SmartAdapter<DemoItem> {

    private Comparator<IFlexible> comparator;

    public DemoAdapter(Object listener) {
        super(listener);
        comparator = new ItemComparator();
    }

/*    @Override
    public String onCreateBubbleText(int position) {
        DemoItem item = getItem(position);
        return item.getWord().getWord().substring(0, 1).toUpperCase();
    }*/

    @Override
    public boolean addItem(@NonNull DemoItem item) {
        return super.addItem(item, comparator);
    }

/*    public void addSelection(DemoItem item) {
        super.addSelection(item, item.isFlag());
    }*/

    private class ItemComparator implements Comparator<IFlexible> {

        @Override
        public int compare(IFlexible t1, IFlexible t2) {
/*            if (t1 instanceof DemoItem && t2 instanceof DemoItem) {
                String word1 = ((DemoItem) t1).getWord().getWord();
                String word2 = ((DemoItem) t2).getWord().getWord();
                return word1.compareToIgnoreCase(word2);
            }*/
            return 0;
        }
    }
}
