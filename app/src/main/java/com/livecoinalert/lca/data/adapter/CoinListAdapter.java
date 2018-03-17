package com.livecoinalert.lca.data.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreampany.framework.data.adapter.SmartAdapter;

/**
 * Created by suneel on 3/17/2018.
 */

public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.MyViewHolder> {

    @Override
    public CoinListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CoinListAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }
}
