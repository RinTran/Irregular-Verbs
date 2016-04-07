package com.rintran.irregularverbs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.DrawableWrapper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    // data
    SQLDataSource db;// slqite data
    List<Verbs> listVerb;//
    List<String> listFavorite;
    CustomListviewAdapter adapter;
    boolean status;


    // view
    ListView content;
    SearchView search;
    int pos = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting
        // - fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // - actionbar
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        // - navication
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // - background
        Intent intent = getIntent();
        pos = intent.getIntExtra("pos", 0);
        selectTheme(drawer,pos);
        // - status of list
        status = true;

        // - setting view
        TextView c1 = (TextView) findViewById(R.id.c1);
        TextView c2 = (TextView) findViewById(R.id.c2);
        TextView c3 = (TextView) findViewById(R.id.c3);
        TextView c4 = (TextView) findViewById(R.id.c4);
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonts/georgiab.ttf");
        c1.setTypeface(typeface);
        c2.setTypeface(typeface);
        c3.setTypeface(typeface);
        c4.setTypeface(typeface);


        // Method
        // - display list verb
        listVerb = new ArrayList<>();
        db = new SQLDataSource(this);
        listVerb = db.getListVerb();

        content = (ListView) findViewById(R.id.list_verb);
        adapter = new CustomListviewAdapter(this,R.layout.content_list_verb,listVerb);
        content.setAdapter(adapter);
        // - add to favorite
        content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                if (status) {
                    AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);


                    String title = "Notice:";
                    String message = "Do you want to add to Favorite?";
                    String btn_right = "Cancel";
                    String btn_left = "Add";
                    b.setTitle(title);
                    b.setMessage(message);
                    b.setIcon(R.drawable.signs);
                    b.setPositiveButton(btn_right, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    b.setNegativeButton(btn_left, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.cancel();
                        }
                    });
                    b.create().show();
                }
                else {
                    AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);

                    String title = "Notice:";
                    String message = "Do you want to remove from Favorite?";
                    String btn_right = "Cancel";
                    String btn_left = "Remove";
                    b.setTitle(title);
                    b.setMessage(message);
                    b.setIcon(R.drawable.signs);
                    b.setPositiveButton(btn_right, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    b.setNegativeButton(btn_left, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.cancel();
                        }
                    });
                    b.create().show();
                }

            }
        });
        // - search
        search = (SearchView) findViewById(R.id.search);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchVerb(newText);
                return true;
            }
        });
    }

    private void selectTheme(DrawerLayout drawer,int pos) {
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
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        if (id == R.id.action_settings2) {
//            return true;
//        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_verb) {
            listVerb = db.getListVerb();
            adapter = new CustomListviewAdapter(this,R.layout.content_list_verb,listVerb);
            content.setAdapter(adapter);
            status = true;
        }else if (id == R.id.nav_favorite) {
            listVerb = db.getListFavorite();
            adapter = new CustomListviewAdapter(this,R.layout.content_list_verb,listVerb);
            content.setAdapter(adapter);
            status = false;
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
    public boolean searchVerb(String key){
        listVerb = db.getListVerbByKey(key);
        CustomListviewAdapter adapter2 = new CustomListviewAdapter(this,R.layout.content_list_verb,listVerb);
        content.setAdapter(adapter2);
        return true;
    }
}
