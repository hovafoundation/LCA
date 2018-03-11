package com.livecoinalert.lca.ui.fragment;

import android.databinding.ObservableArrayList;
import android.os.Bundle;

import com.livecoinalert.lca.R;
import com.livecoinalert.lca.data.adapter.MoreAdapter;
import com.dreampany.framework.data.util.ViewUtil;
import com.dreampany.framework.ui.fragment.BaseFragment;

import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;

/**
 * Created by air on 5/3/18.
 */

public class MoreFragment extends BaseFragment {

/*    private FragmentItemsBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_items;
    }*/

    @Override
    protected void startUi(Bundle state) {
        super.startUi(state);
        initRecycler();
    }

    private void initRecycler() {
/*        binding.setItems(new ObservableArrayList<>());
        ViewUtil.setRecycler(
                binding.recyclerView,
                new MoreAdapter(this),
                new SmoothScrollLinearLayoutManager(getContext()),
                null,
                null
        );*/
    }
}
