package com.rintran.irregularverbs;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ThemeActivity extends AppCompatActivity {

    private String itemName[] = {"Default", "Bat", "Beauty autum", "Hero", "Hourglass", "Panda"};
    private int itemImage[] = {R.mipmap.background_you_are_here, R.mipmap.background_bat,
            R.mipmap.background_beauty_autum, R.mipmap.background_hero, R.mipmap.background_hourglass, R.mipmap.background_panda};

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
        //test = (TextView) findViewById(R.id.test);
//        final View content = findViewById(R.id.theme);

        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonts/timesbi.ttf");
        textView.setTypeface(typeface);

        ITAdapter adapter = new ITAdapter(ThemeActivity.this, R.layout.theme_list_style, theme);
        listViewTheme = (ListView) findViewById(R.id.theme_list_content);
        listViewTheme.setAdapter(adapter);

        listViewTheme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ThemeActivity.this, MainActivity.class);
                intent.putExtra("savePos", position);
                startActivity(intent);
                //content.setBackgroundResource(R.drawable.background_default);
            }

        });
    }
}
