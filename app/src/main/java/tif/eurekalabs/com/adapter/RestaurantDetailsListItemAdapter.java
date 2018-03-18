package tif.eurekalabs.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import tif.eurekalabs.com.PackageWeeklyMenuActivity;
import tif.eurekalabs.com.R;
import tif.eurekalabs.com.RestaurantsDetailActivity;


/**
 * Created by coderap on 4/4/2017.
 */

public class RestaurantDetailsListItemAdapter extends RecyclerView.Adapter<RestaurantDetailsListItemAdapter.MyViewHolder> {

    private List<Drawable> list;

    private RelativeLayout parent;

    private static String TAG = "RestaurantDetailsListItemAdapter";

    private Context context;

    private int qunatity;
    private int choice;

    private Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tvViewMenu;
        TextView tvQuantity;

        ImageView ivAdd;
        ImageView ivRemove;

        LinearLayout llQuantity;

        public MyViewHolder(View view) {
            super(view);
            llQuantity = (LinearLayout) view.findViewById(R.id.ll_quantity);

            tvViewMenu = (TextView) view.findViewById(R.id.tv_view_menu);
            tvQuantity = (TextView) view.findViewById(R.id.tv_quantity);

            ivAdd = (ImageView) view.findViewById(R.id.iv_add);
            ivRemove = (ImageView) view.findViewById(R.id.iv_remove);
        }
    }


    public RestaurantDetailsListItemAdapter(List<Drawable> feedsList, Context c, Activity activity) {
        this.list = feedsList;
        context = c;
        choice = 0;
        qunatity = 0;
        this.activity = activity;
    }

    public RestaurantDetailsListItemAdapter(List<Drawable> feedsList, Context c, int choice, Activity activity) {
        this.list = feedsList;
        context = c;
        qunatity = 0;
        this.activity = activity;
        this.choice = choice;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_restaurant_details, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (choice == 1) {
            holder.llQuantity.setVisibility(View.GONE);
        }

        holder.tvQuantity.setText("" + qunatity);

        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qunatity++;
                holder.tvQuantity.setText("" + qunatity);
                RestaurantsDetailActivity restaurantsDetailActivity = (RestaurantsDetailActivity) activity;
                restaurantsDetailActivity.addToCart(200);
            }
        });

        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qunatity > 0) {
                    qunatity--;
                    holder.tvQuantity.setText("" + qunatity);
                    RestaurantsDetailActivity restaurantsDetailActivity = (RestaurantsDetailActivity) activity;
                    restaurantsDetailActivity.removeFromCart(200);
                }
            }
        });

        holder.tvViewMenu.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PackageWeeklyMenuActivity.class);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}