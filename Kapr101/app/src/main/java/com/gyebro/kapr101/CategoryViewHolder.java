package com.gyebro.kapr101;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    // each data item is just a string in this case
    public TextView mCategoryName;
    public TextView mPagesText;
    public OnItemClick mListener;

    public CategoryViewHolder(View layoutView, OnItemClick listener) {
        super(layoutView);
        mListener = listener;
        mCategoryName = (TextView)layoutView.findViewById(R.id.categoryText);
        mPagesText = (TextView)layoutView.findViewById(R.id.pagesText);
        layoutView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mListener.onItemClick(v);
    }

    public static interface OnItemClick {
        public void onItemClick(View caller);
    }
}
