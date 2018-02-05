package tif.eurekalabs.com.fragment.packageWeeklyMenu;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tif.eurekalabs.com.R;
import tif.eurekalabs.com.adapter.PackageWeeklyMenuListItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class LunchFragment extends Fragment {


    View root;
    RecyclerView rvMenu;

    List<Drawable> bannerList = new ArrayList<>();

    PackageWeeklyMenuListItemAdapter adapter;

    public LunchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_package_weekly_menu_lunch, container, false);

        rvMenu=(RecyclerView) root.findViewById(R.id.rv_menu);
        bannerList.add(ContextCompat.getDrawable(getContext(),R.drawable.img_ad_1));
        bannerList.add(ContextCompat.getDrawable(getContext(),R.drawable.img_ad_2));
        bannerList.add(ContextCompat.getDrawable(getContext(),R.drawable.img_ad_3));
        bannerList.add(ContextCompat.getDrawable(getContext(),R.drawable.img_ad_1));
        bannerList.add(ContextCompat.getDrawable(getContext(),R.drawable.img_ad_2));
        bannerList.add(ContextCompat.getDrawable(getContext(),R.drawable.img_ad_3));

        adapter = new PackageWeeklyMenuListItemAdapter(bannerList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvMenu.setLayoutManager(mLayoutManager);
        rvMenu.setAdapter(adapter);

        return root;
    }

}
