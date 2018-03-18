package tif.eurekalabs.com;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import tif.eurekalabs.com.adapter.BannerListItemAdapter;
import tif.eurekalabs.com.adapter.NearbyRestuarantsListItemAdapter;
import tif.eurekalabs.com.model.PackageItem;

public class SelectRestaurantActivity extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView rvRestaurants;

    NearbyRestuarantsListItemAdapter adapterRestaurantsList;

    List<Drawable> bannerList = new ArrayList<>();

    RecyclerView rvBanner;

    ImageView ivClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_restaurant);

        ivClose=(ImageView) findViewById(R.id.iv_close);

        rvRestaurants=(RecyclerView) findViewById(R.id.rv_resturants);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        bannerList.add(ContextCompat.getDrawable(this,R.drawable.img_ad_1));
        bannerList.add(ContextCompat.getDrawable(this,R.drawable.img_ad_2));
        bannerList.add(ContextCompat.getDrawable(this,R.drawable.img_ad_3));

        bannerList.add(ContextCompat.getDrawable(this,R.drawable.img_ad_1));
        bannerList.add(ContextCompat.getDrawable(this,R.drawable.img_ad_2));
        bannerList.add(ContextCompat.getDrawable(this,R.drawable.img_ad_3));



        adapterRestaurantsList=new NearbyRestuarantsListItemAdapter(bannerList,this,this);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        rvRestaurants.setLayoutManager(mLayoutManager2);
        rvRestaurants.setAdapter(adapterRestaurantsList);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
