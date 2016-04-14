package com.rintran.irregularverbs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    // data
    SQLDataSource db;// slqite data
    List<Verbs> listVerb;//
    CustomListviewAdapter adapter;
    boolean status;
    DrawerLayout drawer;


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
        listVerb = new ArrayList<>();
        db = new SQLDataSource(this);
        content = (ListView) findViewById(R.id.list_verb);
        // - display list verb
        listVerb = db.getListVerb();
        setContentVerb(listVerb);
        // - status of list
        status = true;

        // - actionbar
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        // - navication
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // - background
        selectTheme(getBackgroundId());


        // - setting view
        final TextView c1 = (TextView) findViewById(R.id.c1);
        TextView c2 = (TextView) findViewById(R.id.c2);
        TextView c3 = (TextView) findViewById(R.id.c3);
        TextView c4 = (TextView) findViewById(R.id.c4);
        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonts/georgiab.ttf");
        c1.setTypeface(typeface);
        c2.setTypeface(typeface);
        c3.setTypeface(typeface);
        c4.setTypeface(typeface);


        // Method
        // - fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new CustomListviewAdapter(MainActivity.this, R.layout.content_list_verb, listVerb);
                if (status) {
                    listVerb = db.getListFavorite();
                    setContentVerb(listVerb);
                } else {
                    listVerb = db.getListVerb();
                    setContentVerb(listVerb);
                }
                content.setAdapter(adapter);
                status = !status;
            }
        });
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
                            TextView t1 = (TextView) view.findViewById(R.id.nguyenmau);
                            TextView t2 = (TextView) view.findViewById(R.id.quakhu);
                            TextView t3 = (TextView) view.findViewById(R.id.quakhuphantu);
                            TextView t4 = (TextView) view.findViewById(R.id.nghia);
                            db.removeFromFavorite(t1.getText().toString());
                            if (db.insertFavorite(new Verbs(t1.getText().toString(), t2.getText().toString(),
                                    t3.getText().toString(), t4.getText().toString()))) {
                                Toast.makeText(MainActivity.this,
                                        "Insert success", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this,
                                        "Insert fail", Toast.LENGTH_LONG).show();
                            }

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
                            TextView t1 = (TextView) view.findViewById(R.id.nguyenmau);
                            if (db.removeFromFavorite(t1.getText().toString())) {
                                listVerb = db.getListFavorite();
                                setContentVerb(listVerb);
                                Toast.makeText(MainActivity.this,
                                        "Remove success", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this,
                                        "Remove fail", Toast.LENGTH_LONG).show();
                            }
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
                listVerb = db.getListVerbByKey(newText);
                setContentVerb(listVerb);
                status = true;
                return true;
            }
        });
    }

    private void selectTheme(int pos) {
        switch (pos){
            case 0:
                drawer.setBackgroundResource(R.drawable.background_you_are_here);
                break;
            case 1:
                drawer.setBackgroundResource(R.drawable.background_bat);
                break;
            case 2:
                drawer.setBackgroundResource(R.drawable.background_beauty_autum);
                break;
            case 3:
                drawer.setBackgroundResource(R.drawable.background_hero);
                break;
            case 4:
                drawer.setBackgroundResource(R.drawable.background_hourglass);
                break;
            case 5:
                drawer.setBackgroundResource(R.drawable.background_panda);
                break;

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

    public void setContentVerb(List<Verbs> l) {
        adapter = new CustomListviewAdapter(this, R.layout.content_list_verb, l);
        content.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        pos = intent.getIntExtra("savePos", getBackgroundId());
        selectTheme(pos);
        updateBacground(pos);
    }

    public int getBackgroundId() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //int defaultValue = getResources().getInteger(R.string.saved_high_score_default);
        return sharedPref.getInt("pos", 0);
    }

    public void updateBacground(int newPos) {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("pos", newPos);
        editor.apply();
    }

}
