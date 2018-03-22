package tif.eurekalabs.com.fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tif.eurekalabs.com.AddAddressActivity;
import tif.eurekalabs.com.R;
import tif.eurekalabs.com.adapter.AddressBookListItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressBookFragment extends Fragment {


    View root;

    RecyclerView rvAddress;

    List<Drawable> bannerList = new ArrayList<>();

    AddressBookListItemAdapter adapter;

    FloatingActionButton btnAdd;

    public AddressBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_address_book, container, false);

        rvAddress=(RecyclerView) root.findViewById(R.id.rv_address);

        btnAdd=(FloatingActionButton) root.findViewById(R.id.btn_add);

        bannerList.add(ContextCompat.getDrawable(getContext(),R.drawable.img_ad_1));
        bannerList.add(ContextCompat.getDrawable(getContext(),R.drawable.img_ad_1));
        bannerList.add(ContextCompat.getDrawable(getContext(),R.drawable.img_ad_1));
        bannerList.add(ContextCompat.getDrawable(getContext(),R.drawable.img_ad_1));

        adapter = new AddressBookListItemAdapter(bannerList,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvAddress.setLayoutManager(mLayoutManager);
        rvAddress.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(), AddAddressActivity.class);
                getContext().startActivity(i);
            }
        });

        return root;
    }

}
