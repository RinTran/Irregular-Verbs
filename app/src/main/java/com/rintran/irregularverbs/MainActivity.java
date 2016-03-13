package com.rintran.irregularverbs;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SQLDataSource db;
    List<Verbs> list;
    ListView content;



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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView c1 = (TextView) findViewById(R.id.c1);
        TextView c2 = (TextView) findViewById(R.id.c2);
        TextView c3 = (TextView) findViewById(R.id.c3);
        TextView c4 = (TextView) findViewById(R.id.c4);
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonts/georgiab.ttf");
        c1.setTypeface(typeface);
        c2.setTypeface(typeface);
        c3.setTypeface(typeface);
        c4.setTypeface(typeface);

        //
        list = new ArrayList<>();
        db = new SQLDataSource(this);
        list = db.getListVerb();

        content = (ListView) findViewById(R.id.list_verb);
        CustomListviewAdapter adapter = new CustomListviewAdapter(this,R.layout.content_list_verb,list);
        content.setAdapter(adapter);

        content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        //
        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos", 0);
        switch (pos){
            case 0:
                drawer.setBackgroundResource(R.drawable.background_default);
                break;
            case 1:
                drawer.setBackgroundResource(R.mipmap.background_girl);
                break;
            case 2:
                drawer.setBackgroundResource(R.mipmap.background_forest);
                break;
            case 3:
                drawer.setBackgroundResource(R.mipmap.background_king_pirate);
                break;
            case 4:
                drawer.setBackgroundResource(R.mipmap.background_leaf);
                break;
            case 5:
                drawer.setBackgroundResource(R.mipmap.background_love);
                break;
            case 6:
                drawer.setBackgroundResource(R.mipmap.background_puzzle);
                break;
            case 7:
                drawer.setBackgroundResource(R.mipmap.background_vector_shapes);
                break;
            default:
                drawer.setBackgroundResource(R.mipmap.background_default);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_theme) {
            startActivity(new Intent(MainActivity.this,ThemeActivity.class));
        } else if (id == R.id.nav_quit) {
            finish();
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
