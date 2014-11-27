package com.gyebro.kapr101;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gergely on 2014.11.25..
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>
implements CategoryViewHolder.OnItemClick {

    private static final String TAG = "CategoryAdapter";
    private String[] mDataset;
    private OnCategoryItemClick mListener;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CategoryAdapter(String[] myDataset, OnCategoryItemClick listener) {
        mListener = listener;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        CategoryViewHolder vh = new CategoryViewHolder(v, this);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mCategoryName.setText(mDataset[position]);
        Pair<Integer,Integer> p = categoryRanges.get(position);
        holder.first = p.first;
        holder.last = p.second;
        if (p.first.equals(p.second)) {
            holder.mPagesText.setText(""+p.first);
        } else {
            holder.mPagesText.setText(""+p.first+"-"+p.second);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    @Override
    public void onItemClick(View caller, Pair<Integer,Integer> pair) {
        // Forward the call to the listener
        mListener.onCategoryItemClick(caller, pair);
    }

    public static interface OnCategoryItemClick {
        public void onCategoryItemClick(View caller, Pair<Integer,Integer> pair);
    }

    public static final List<Pair<Integer, Integer>> categoryRanges;
    static {
        List<Pair<Integer, Integer>> l = new ArrayList<>();
        l.add(new Pair<>(1, 2));
        l.add(new Pair<>(3, 5));
        l.add(new Pair<>(6, 12));
        l.add(new Pair<>(13, 17));
        l.add(new Pair<>(18, 21));
        l.add(new Pair<>(22, 24));
        l.add(new Pair<>(25, 25));
        l.add(new Pair<>(26, 30));
        l.add(new Pair<>(31, 34));
        l.add(new Pair<>(35, 35));
        l.add(new Pair<>(36, 36));
        l.add(new Pair<>(37, 40));
        l.add(new Pair<>(41, 42));
        l.add(new Pair<>(43, 44));
        l.add(new Pair<>(45, 45));
        l.add(new Pair<>(46, 47));
        l.add(new Pair<>(48, 49));
        l.add(new Pair<>(50, 50));
        l.add(new Pair<>(51, 51));
        l.add(new Pair<>(52, 56));
        l.add(new Pair<>(57, 57));
        l.add(new Pair<>(58, 58));
        l.add(new Pair<>(59, 63));
        l.add(new Pair<>(64, 65));
        l.add(new Pair<>(66, 67));
        l.add(new Pair<>(68, 71));
        l.add(new Pair<>(72, 72));
        l.add(new Pair<>(73, 74));
        l.add(new Pair<>(75, 75));
        l.add(new Pair<>(76, 76));
        l.add(new Pair<>(77, 80));
        l.add(new Pair<>(81, 82));
        l.add(new Pair<>(83, 83));
        l.add(new Pair<>(84, 85));
        l.add(new Pair<>(86, 92));
        l.add(new Pair<>(93, 95));
        l.add(new Pair<>(96, 96));
        l.add(new Pair<>(97, 100));
        l.add(new Pair<>(101, 101));
        categoryRanges = Collections.unmodifiableList(l);
    }

}

