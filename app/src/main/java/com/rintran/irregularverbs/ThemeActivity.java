package com.rintran.irregularverbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;


public class ThemeActivity extends AppCompatActivity {

    private String itemName[] = {"Default","Cute girl", "Forest", "King pirate","Leaf","Love","Puzzle","Vector shapes"};
//    private int itemImage[] = {R.drawable.background_default,R.drawable.background_cute_girl,R.drawable.background_forest,
//            R.drawable.background_king_pirate,R.drawable.background_leaf,R.drawable.background_love,R.drawable.background_puzzle,
//            R.drawable.background_vector_shapes};
 private int itemImage[]={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};

    ListView listViewTheme;

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

        ITAdapter adapter = new ITAdapter(ThemeActivity.this, R.layout.style_theme_list, theme);
        listViewTheme = (ListView) findViewById(R.id.them_list);
        listViewTheme.setAdapter(adapter);
    }
}
