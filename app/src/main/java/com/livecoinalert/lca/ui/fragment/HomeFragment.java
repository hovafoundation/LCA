package com.livecoinalert.lca.ui.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.livecoinalert.lca.R;
import com.livecoinalert.lca.data.adapter.DemoAdapter;
import com.livecoinalert.lca.data.enums.ItemState;
import com.livecoinalert.lca.data.enums.ItemSubtype;
import com.livecoinalert.lca.data.enums.ItemType;
import com.livecoinalert.lca.data.manager.TaskManager;
import com.livecoinalert.lca.data.model.Demo;
import com.livecoinalert.lca.data.model.DemoItem;
import com.livecoinalert.lca.databinding.FragmentDemosBinding;
import com.livecoinalert.lca.ui.activity.ToolsActivity;
import com.dreampany.framework.data.animators.LandingItemAnimator;
import com.dreampany.framework.data.enums.UiState;
import com.dreampany.framework.data.model.Color;
import com.dreampany.framework.data.model.UiTask;
import com.dreampany.framework.data.util.AndroidUtil;
import com.dreampany.framework.data.util.LogKit;
import com.dreampany.framework.data.util.ViewUtil;
import com.dreampany.framework.ui.fragment.BaseMenuFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import eu.davidea.flexibleadapter.common.FlexibleItemDecoration;
import eu.davidea.flexibleadapter.common.SmoothScrollLinearLayoutManager;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by nuc on 2/21/2017.
 */

public class HomeFragment extends BaseMenuFragment implements SingleObserver<List<DemoItem>> {

    private FragmentDemosBinding binding;
    private UiState state = UiState.CONTENT;
    private Color color;
    private final int offset = 6;
    private final int limit = 1000;
    private Disposable disposable;
    private int lastPosition = -1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_demos;
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_search;
    }

    @Override
    protected void startUi(Bundle state) {
        super.startUi(state);
        setTitle(getString(R.string.title_home));
        binding = getBinding();
        color = getColor();
        initView();
        initRecycler();
        updateState(UiState.CONTENT);
        AndroidUtil.post(this::produceItems);
    }

    @Override
    protected void onCreatedMenu(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        if (searchView != null) {
            searchView.setInputType(InputType.TYPE_TEXT_VARIATION_FILTER);
            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_FULLSCREEN);
            searchView.setQueryHint(getString(R.string.action_search));
            SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getParent().getComponentName()));
            searchView.setOnQueryTextListener(this);
        }
    }

    @Override
    protected void stopUi() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.stopUi();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLoadMore:
                break;
            case R.id.buttonOffline:
            case R.id.buttonEmpty:
                AndroidUtil.post(this::produceItems);
                break;
            case R.id.fab:
                //AndroidUtil.post(this::addWords);
                break;
            case R.id.buttonNewItems:
                hideNewItems();
                binding.recyclerView.scrollToPosition(lastPosition);
                lastPosition = -1;
                break;
        }
    }

    @Override
    public boolean onItemClick(int position) {
        if (position != RecyclerView.NO_POSITION) {
            DemoAdapter adapter = getAdapter();
            DemoItem item = adapter.getItem(position);
            showDemo(item);
            return true;
        }
        return false;
    }

    @Override
    public void noMoreLoad(int newItemsSize) {

    }

    @Override
    public void onLoadMore(int lastPosition, int currentPage) {

    }

    @Override
    public void onSubscribe(Disposable d) {
        this.disposable = d;
    }

    @Override
    public void onSuccess(List<DemoItem> items) {
        DemoAdapter adapter = getAdapter();
        adapter.clear();
        updateState(UiState.CONTENT);
        adapter.addItems(0, items);
    }

    @Override
    public void onError(Throwable e) {
        if (!getAdapter().isEmpty()) {
            return;
        }
        if (!TaskManager.onInstance(getContext()).hasInternet()) {
            updateOfflineState();
            return;
        }
        updateEmptyState();
        LogKit.verbose("error: " + e.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(final DemoItem item) {
        DemoAdapter adapter = getAdapter();
        updateState(UiState.CONTENT);
        boolean contains = adapter.contains(item);
        adapter.addItem(item);
        if (!contains) {
            lastPosition = adapter.getPosition(item);
            showNewItems();
        }
    }

    private void initView() {
        ViewUtil.setClickListener(this, R.id.fab);
        ViewUtil.setClickListener(this, R.id.buttonNewItems);
        //ViewUtil.setBackground(this, R.id.layoutNewItems, color.getColorPrimaryDarkId());
    }

    private void initRecycler() {
        binding.setItems(new ObservableArrayList<>());
        DemoAdapter adapter = new DemoAdapter(this);

        ViewUtil.setRecycler(
                binding.recyclerView,
                adapter,
                new SmoothScrollLinearLayoutManager(getContext()),
                new LandingItemAnimator(),
                new FlexibleItemDecoration(getContext())
                        .addItemViewType(R.layout.item_demo, offset)
                        .withEdge(true)
        );
    }

    private void produceItems() {
        if (getAdapter().isEmpty()) {
            //updateState(UiState.PROGRESS);
        }
/*        if (this.keyword != null) {
            TaskManager.onInstance(getContext()).produceWords(limit, this);
            return;
        }
        TaskManager.onInstance(getContext()).produceWords(task.getKeyword(), this);*/
    }

    private DemoAdapter getAdapter() {
        return ViewUtil.getAdapter(binding.recyclerView);
    }

    private void showDemo(DemoItem item) {
        UiTask<Demo, ItemType, ItemSubtype, ItemState> task = new UiTask<>();
        task.setItem(item.getDemo());
        task.setType(ItemType.DEMO);
        task.setSubtype(ItemSubtype.ORIGINAL);
        AndroidUtil.openActivity(getParent(), ToolsActivity.class, task);
    }

    private void showNewItems() {
        ViewUtil.setVisibility(this, R.id.layoutNewItems, View.VISIBLE);
    }

    private void hideNewItems() {
        ViewUtil.setVisibility(this, R.id.layoutNewItems, View.GONE);
    }

    private void updateOfflineState() {
        ViewUtil.setClickListener(this, R.id.buttonOffline, this);
        updateState(UiState.OFFLINE);
    }

    private void updateEmptyState() {
        ViewUtil.setClickListener(this, R.id.buttonEmpty, this);
        updateState(UiState.EMPTY);
    }

    private void updateState(UiState state) {

        if (this.state == state) {
            return;
        }
        this.state = state;

        switch (state) {
            case CONTENT:
                binding.stateful.showContent();
                break;
            case PROGRESS:
                binding.stateful.showProgress();
                break;
            case OFFLINE:
                binding.stateful.showOffline();
                break;
            case EMPTY:
                binding.stateful.showEmpty();
                break;
            case ERROR:
                binding.stateful.showEmpty();
                break;
        }
    }

}
