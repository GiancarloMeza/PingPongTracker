package com.giancarlomeza.pingpongtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String NAMES = "names";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final LinearLayout layoutNameInputs = (LinearLayout) findViewById(R.id.layoutNameInputs);
        final LinearLayout firstChild = (LinearLayout) layoutNameInputs.getChildAt(0);
        final EditText firstEditText = firstChild.findViewById(R.id.inputName);
        firstEditText.addTextChangedListener(new TextChangeListener(layoutNameInputs));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = layoutNameInputs.getChildCount() - 1;
                String[] names = new String[count];
                for (int i = 0; i < count; i++) {
                   names[i] = ((EditText) layoutNameInputs.getChildAt(i)
                           .findViewById(R.id.inputName)).getText().toString();
                }

                Intent intent = new Intent(MainActivity.this, TournamentActivity.class);
                intent.putExtra(NAMES, names);
                startActivity(intent);
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

        return super.onOptionsItemSelected(item);
    }

    class TextChangeListener implements TextWatcher {
        private final LinearLayout layoutNameInputs;

        TextChangeListener(LinearLayout layoutNameInputs) {
            this.layoutNameInputs = layoutNameInputs;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() == 0) {
                LinearLayout layout = (LinearLayout) LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.name_input_cell, layoutNameInputs, false);
                ((EditText) layout.findViewById(R.id.inputName))
                        .addTextChangedListener(new TextChangeListener(layoutNameInputs));
                layoutNameInputs.addView(layout);
            }
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 0) {
                layoutNameInputs.removeViewAt(layoutNameInputs.getChildCount() - 1);
            }
        }
    }
}
