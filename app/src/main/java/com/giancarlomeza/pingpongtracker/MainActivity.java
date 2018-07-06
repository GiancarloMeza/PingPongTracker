package com.giancarlomeza.pingpongtracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String NAMES = "names";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerNameInputs = (RecyclerView) findViewById(R.id.recyclerNameInputs);

        final NameInputsAdapter adapter = new NameInputsAdapter(this, new NameInputsAdapter
                .TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence, int position) {
                final NameInputsAdapter adapter = (NameInputsAdapter) recyclerNameInputs.getAdapter();
                final List<String> names = adapter.getNames();
                names.remove(position);
                names.add(position, String.valueOf(charSequence));

                if (position + 1 == names.size()) {
                    names.add("");

                    new Handler(Looper.myLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    }, 100);
                }
                System.out.println(Arrays.asList(adapter.getNames()));
            }
        });

        recyclerNameInputs.setAdapter(adapter);
        recyclerNameInputs.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TournamentActivity.class);
                intent.putExtra(NAMES, adapter.getNames().toArray(new String[adapter.getNames().size()]));
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
}
