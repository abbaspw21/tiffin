package tif.eurekalabs.com;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import tif.eurekalabs.com.adapter.PackageWeeklyMenuListItemAdapter;

public class PackageWeeklyMenuActivity extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView rvMenu;

    List<Drawable> bannerList = new ArrayList<>();

    PackageWeeklyMenuListItemAdapter adapter;

    AppCompatButton btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_weekly_menu);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        rvMenu=(RecyclerView) findViewById(R.id.rv_menu);

        btnBook=(AppCompatButton) findViewById(R.id.btn_book);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_back_white));

        bannerList.add(ContextCompat.getDrawable(this,R.drawable.img_ad_1));
        bannerList.add(ContextCompat.getDrawable(this,R.drawable.img_ad_2));
        bannerList.add(ContextCompat.getDrawable(this,R.drawable.img_ad_3));
        bannerList.add(ContextCompat.getDrawable(this,R.drawable.img_ad_1));
        bannerList.add(ContextCompat.getDrawable(this,R.drawable.img_ad_2));
        bannerList.add(ContextCompat.getDrawable(this,R.drawable.img_ad_3));

        adapter = new PackageWeeklyMenuListItemAdapter(bannerList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvMenu.setLayoutManager(mLayoutManager);
        rvMenu.setAdapter(adapter);

    }

}
