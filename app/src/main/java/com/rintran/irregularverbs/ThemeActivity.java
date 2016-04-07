package com.rintran.irregularverbs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ThemeActivity extends AppCompatActivity {

    private String itemName[] = {"Default","Cute girl", "Forest", "King pirate","Leaf","Love","Puzzle","Vector shapes"};
    private int itemImage[] = {R.mipmap.background_default,R.mipmap.background_girl,R.mipmap.background_forest,
            R.mipmap.background_king_pirate,R.mipmap.background_leaf,R.mipmap.background_love,R.mipmap.background_puzzle,
            R.mipmap.background_vector_shapes};
 //private int itemImage[]={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,
 // R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};

    ListView listViewTheme;
    TextView textView;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        ArrayList<Item> theme = new ArrayList<>();

        for (int i = 0; i<itemName.length;i++){
            Item item = new Item();
            item.setImage(itemImage[i]);
            item.setName(itemName[i]);
            theme.add(item);
        }

        textView = (TextView)findViewById(R.id.txtvThemeTitle);
        test = (TextView) findViewById(R.id.test);
//        final View content = findViewById(R.id.theme);

        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonts/timesbi.ttf");
        textView.setTypeface(typeface);

        ITAdapter adapter = new ITAdapter(ThemeActivity.this, R.layout.theme_list_style, theme);
        listViewTheme = (ListView) findViewById(R.id.theme_list_content);
        listViewTheme.setAdapter(adapter);

        listViewTheme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Intent intent = new Intent(ThemeActivity.this,MainActivity.class);
                intent.putExtra("pos",position);
                startActivity(intent);
                //content.setBackgroundResource(R.drawable.background_default);
            }

        });
    }
}
