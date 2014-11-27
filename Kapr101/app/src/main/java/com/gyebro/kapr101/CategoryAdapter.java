package com.gyebro.kapr101;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        holder.mPagesText.setText("Pages: "+position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    @Override
    public void onItemClick(View caller) {
        Log.d(TAG, "CategoryItem clicked: "+
                ((TextView)caller.findViewById(R.id.categoryText)).getText().toString());
        // Forward the call to the listener
        mListener.onCategoryItemClick(caller);
    }

    public static interface OnCategoryItemClick {
        public void onCategoryItemClick(View caller);
    }

}

