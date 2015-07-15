package com.unaipme.expandablelisttest;

import android.content.Intent;
import android.graphics.Matrix;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {
    ExpandableListView listView;
    ArrayList<String> titles;
    HashMap<String, ArrayList<String>> content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ExpandableListView) findViewById(R.id.expListView);

        prepare();

        listView.setAdapter(new ExpandableListAdapter(this, titles, content));
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(MainActivity.this, "Expanded", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(MainActivity.this, "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                final String text = ((TextView) v.findViewById(R.id.itemText)).getText().toString();
                Intent intent = new Intent(MainActivity.this, HelloActivity.class);
                Bundle b = new Bundle();
                b.putString("text", text);
                intent.putExtras(b);
                startActivity(intent);
                return false;
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

    private void prepare() {
        content = new HashMap<>();
        titles = new ArrayList<>();
        titles.add("Titulo 1");
        titles.add("Titulo 2");
        titles.add("Titulo 3");

        for (int i=0; i<titles.size(); i++) {
            ArrayList<String> list = new ArrayList<>();
            list.add(i+": Elemento 1");
            list.add(i+": Elemento 2");
            list.add(i+": Elemento 3");
            content.put(titles.get(i), list);
        }
    }
}
