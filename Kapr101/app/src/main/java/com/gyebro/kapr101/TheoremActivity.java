package com.gyebro.kapr101;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;


public class TheoremActivity extends ActionBarActivity {

    private static final String TAG = "TheoremActivity";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theorem);
        String categoryName = getIntent().getStringExtra("category_name");
        int first = getIntent().getIntExtra("first",1);
        int last = getIntent().getIntExtra("last",1);

        //Log.d(TAG, "Started with category: "+categoryName);
        TextView categoryTextView = (TextView) findViewById(R.id.categoryText);
        categoryTextView.setText(categoryName);

        mRecyclerView = (RecyclerView) findViewById(R.id.theoremList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        String[] theorems = getResources().getStringArray(R.array.theorems);
        mAdapter = new TheoremAdapter(Arrays.copyOfRange(theorems, first-1, last), first);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_theorem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
