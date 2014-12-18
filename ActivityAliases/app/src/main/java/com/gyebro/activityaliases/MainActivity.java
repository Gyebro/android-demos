package com.gyebro.activityaliases;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener {

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find out how the activity was started
        String label = "Started with icon: ";
        try {
            ActivityInfo ai = getPackageManager().getActivityInfo(getComponentName(), 0);
            label += ai.loadLabel(getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        ((TextView) findViewById(R.id.text)).setText(label);

        // Set up checkboxes
        setupCheckBox(R.id.checkBox0,0);
        setupCheckBox(R.id.checkBox1,1);
        setupCheckBox(R.id.checkBox2,2);
        setupCheckBox(R.id.checkBox3,3);
    }

    private void setupCheckBox(int checkBoxId, int aliasId) {
        CheckBox checkBox = (CheckBox) findViewById(checkBoxId);
        checkBox.setChecked(isAliasEnabled(aliasId));
        checkBox.setOnCheckedChangeListener(this);
    }

    private boolean isAliasEnabled(int id) {
        String aliasname = "com.gyebro.activityaliases.Alias" + Integer.toString(id);
        boolean enabled = getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE).getBoolean(aliasname,true); // All alias was enabled by default in this demo
        Log.d(TAG, aliasname + " is currently " + (enabled?"enabled":"disabled"));
        return enabled;
    }

    private void enableAlias(int id, boolean enabled) {
        String aliasname = "com.gyebro.activityaliases.Alias" + Integer.toString(id);
        Log.d(TAG, (enabled ? "Enabling":"Disabling")+" "+aliasname);
        // Enable / disable alias
        getPackageManager().setComponentEnabledSetting(
                new ComponentName("com.gyebro.activityaliases", aliasname),
                enabled ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
        // Save its state
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE).edit();
        editor.putBoolean(aliasname, enabled);
        editor.commit();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkBox0:
                enableAlias(0,isChecked);
                break;
            case R.id.checkBox1:
                enableAlias(1,isChecked);
                break;
            case R.id.checkBox2:
                enableAlias(2,isChecked);
                break;
            case R.id.checkBox3:
                enableAlias(3,isChecked);
                break;
        }
    }
}
