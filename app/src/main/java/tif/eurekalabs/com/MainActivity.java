package tif.eurekalabs.com;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tif.eurekalabs.com.adapter.DrawerListItemAdapter;
import tif.eurekalabs.com.adapter.FragmentAdapter;
import tif.eurekalabs.com.fragment.AddressBookFragment;
import tif.eurekalabs.com.fragment.CartFragment;
import tif.eurekalabs.com.fragment.HomeFragment;
import tif.eurekalabs.com.fragment.OrdersFragment;
import tif.eurekalabs.com.model.DrawerItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] mNavigationDrawerItemTitles;

    private DrawerLayout mDrawerLayout;
    private ListView lvDrawer;

    RelativeLayout rlDrawer;

    ActionBarDrawerToggle mDrawerToggle;

    Toolbar toolbar;

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);

        rlDrawer=(RelativeLayout) findViewById(R.id.left_drawer);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        lvDrawer = (ListView) findViewById(R.id.lv_drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItem[] drawerItem = new DrawerItem[7];
        drawerItem[0] = new DrawerItem(R.drawable.ic_home_white, "Home");
        drawerItem[1] = new DrawerItem(R.drawable.ic_cart_white, "Cart");
        drawerItem[2] = new DrawerItem(R.drawable.ic_orders_white, "Orders");
        drawerItem[3] = new DrawerItem(R.drawable.ic_address_book_white, "Address Book");
        drawerItem[4] = new DrawerItem(R.drawable.ic_offers_white, "Offers");
        drawerItem[5] = new DrawerItem(R.drawable.ic_contact_us_white, "Contact Us");
        drawerItem[6] = new DrawerItem(R.drawable.ic_help_white, "Help");

        DrawerListItemAdapter adapter = new DrawerListItemAdapter(this, R.layout.list_item_drawer_menu, drawerItem);
        lvDrawer.setAdapter(adapter);
        lvDrawer.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);

        mDrawerToggle.syncState();

        selectItem(0);

    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new CartFragment();
                break;
            case 2:
                fragment = new OrdersFragment();
                break;
            case 3:
                fragment = new AddressBookFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            lvDrawer.setItemChecked(position, true);
            lvDrawer.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(rlDrawer); } else {
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
}
