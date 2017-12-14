package com.pesteam.watchimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Screen4Activity extends AppCompatActivity {

    @BindView(R.id.bt_im_menu_screen4)
    ImageButton im_bt_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);
        ButterKnife.bind(this);
        creatMenu();
    }

    private void creatMenu() {
        im_bt_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(Screen4Activity.this, im_bt_menu);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_screen4, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.edit_screen4:
                                Toast.makeText(Screen4Activity.this,"sdsdsd",Toast.LENGTH_LONG).show();
                                break;
                            case R.id.frame_screen4:
                                Toast.makeText(Screen4Activity.this,"dddddd",Toast.LENGTH_LONG).show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

}
