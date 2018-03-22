package tif.eurekalabs.com;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tif.eurekalabs.com.fragment.restaurantsDetails.BreakfastFragment;
import tif.eurekalabs.com.fragment.restaurantsDetails.DinnerFragment;
import tif.eurekalabs.com.fragment.restaurantsDetails.LunchFragment;

public class RestaurantsDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    private ViewPager viewPager;

    private AppBarLayout mAppBarLayout;

    private TextView mTitle;

    private Toolbar toolbar;

    private TabLayout tabLayout;

    private FrameLayout flCart;

    private TextView tvPrice;
    private TextView tvQunatity;

    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.4f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private int quantity = 0;
    private int price = 0;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        mTitle = (TextView) findViewById(R.id.tv_toolbar_title);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        flCart = (FrameLayout) findViewById(R.id.fl_view_cart);

        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvQunatity = (TextView) findViewById(R.id.tv_items);

        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);

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

        mAppBarLayout.addOnOffsetChangedListener(this);

        flCart.setOnClickListener(this);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new BreakfastFragment(), "Breakfast");
        adapter.addFragment(new LunchFragment(), "Lunch");
        adapter.addFragment(new DinnerFragment(), "Dinner");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
               /* startAlphaAnimation(cvToolbar, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;*/
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
              /*  startAlphaAnimation(cvToolbar, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;*/
            }
        }
    }

    private static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_view_cart:
                Intent i=new Intent(this,CartActivity.class);
                startActivity(i);
                break;
        }
    }

    public void addToCart(int price) {
        if (quantity==0)
        {
            flCart.setVisibility(View.VISIBLE);
        }
        quantity++;
        this.price = this.price + price;
        tvQunatity.setText(""+quantity+" items");
        tvPrice.setText(""+this.price);
    }

    public void removeFromCart(int price) {
        if (quantity > 0) {
            quantity--;
            this.price = this.price - price;
            tvQunatity.setText(""+quantity+" items");
            tvPrice.setText(""+this.price);
        }
        if (quantity==0)
        {
            flCart.setVisibility(View.GONE);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
