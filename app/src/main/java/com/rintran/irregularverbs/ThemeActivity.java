package com.rintran.irregularverbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

public class ThemeActivity extends AppCompatActivity {

    Item theme[] = {new Item("Default",R.drawable.background_default),
            new Item("Cute girl",R.drawable.background_cute_girl),
            new Item("Forest",R.drawable.background_forest),
            new Item("King pivate",R.drawable.background_king_pirate),
            new Item("Leaf",R.drawable.background_leaf),
            new Item("Love",R.drawable.background_love),
            new Item("Puzzle",R.drawable.background_puzzle),
            new Item("Vector shapes",R.drawable.background_vector_shapes)
    };


    ListView lvTheme;
    ThemeAdapter adapterMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        lvTheme = (ListView) findViewById(R.id.list_theme);

        /*
        *  Cài giao diện cho app
        *  Thiết lập các thuộc tính
         */
        // Kiểu hiển thị của listview là simple_list_item_1(mỗi phần tử là 1 thẻ đơn thuần)
        // Kiểu hiển thị menu_item là do custom định nghĩa bằng 1 file .xml
        adapterMenu = new ThemeAdapter(this,R.layout.theme_list,theme);
        lvTheme.setAdapter(adapterMenu);

        /*
        * Cài tác vụ cho menu
         */

        lvTheme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:

                        break;
                    case 3:
                        finish();
                        break;
                }
            }
        });
    }
}
