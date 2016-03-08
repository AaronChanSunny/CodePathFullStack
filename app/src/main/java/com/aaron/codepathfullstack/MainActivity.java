package com.aaron.codepathfullstack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (id == R.id.action_simple) {
            startActivity(new Intent(this, SimpleActivity.class));
            return true;
        }
        if (id == R.id.action_animator_set) {
            startActivity(new Intent(this, AnimatorSetActivity.class));
            return true;
        }
        if (id == R.id.action_view_property_animator) {
            startActivity(new Intent(this, ViewPropertyAnimatorActivity.class));
            return true;
        }
        if (id == R.id.action_using_xml) {
            startActivity(new Intent(this, UsingXmlActivity.class));
            return true;
        }
        if (id == R.id.action_value_animator) {
            startActivity(new Intent(this, ValueAnimatorActivity.class));
            return true;
        }
        if (id == R.id.action_view_animator) {
            startActivity(new Intent(this, ViewAnimatorActivity.class));
            return true;
        }
        if (id == R.id.action_activity_transition) {
            startActivity(new Intent(this, SecondActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
