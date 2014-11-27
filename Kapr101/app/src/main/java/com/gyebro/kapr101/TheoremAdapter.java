package com.gyebro.kapr101;

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Gergely on 2014.11.27..
 */
public class TheoremAdapter extends RecyclerView.Adapter<TheoremAdapter.TheoremViewHolder> {

    private static final String TAG = "TheoremAdapter";
    private String[] mDataset;
    private int mFirst;

    public TheoremAdapter(String[] dataset, int first) {
        mDataset = dataset;
        mFirst = first;
    }

    @Override
    public TheoremViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.theorem_item, parent, false);
        TheoremViewHolder vh = new TheoremViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TheoremViewHolder holder, int position) {
        holder.mTheoremNumber.setText(""+(mFirst+position));
        holder.mTheoremText.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public class TheoremViewHolder extends RecyclerView.ViewHolder {

        public TextView mTheoremNumber;
        public TextView mTheoremText;

        public TheoremViewHolder(View layoutView) {
            super(layoutView);
            mTheoremNumber = (TextView)layoutView.findViewById(R.id.theoremNumber);
            mTheoremText = (TextView)layoutView.findViewById(R.id.theoremText);
        }
    }
}
