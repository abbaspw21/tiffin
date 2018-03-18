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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import tif.eurekalabs.com.BookPackageActivity;
import tif.eurekalabs.com.R;
import tif.eurekalabs.com.RestaurantsDetailActivity;
import tif.eurekalabs.com.SelectPackageActivity;


/**
 * Created by coderap on 4/4/2017.
 */

public class NearbyRestuarantsListItemAdapter extends RecyclerView.Adapter<NearbyRestuarantsListItemAdapter.MyViewHolder> {

    public List<Drawable> list;

    RelativeLayout parent;

    static String TAG = "NearbyRestuarantsListItemAdapter";

    Context context;

    Activity activity;

    int choice;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rlHolder;

        TextView tvViewDetails;

        public MyViewHolder(View view) {
            super(view);

            tvViewDetails=(TextView) view.findViewById(R.id.tv_view_details);


            rlHolder=(RelativeLayout) view.findViewById(R.id.rl_holder);


        }
    }


    public NearbyRestuarantsListItemAdapter(List<Drawable> feedsList, Context c) {
        this.list = feedsList;
        this.choice=0;
        context = c;
    }

    public NearbyRestuarantsListItemAdapter(List<Drawable> feedsList, Context c,Activity activity) {
        this.list = feedsList;
        this.choice=1;
        context = c;
        this.activity=activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_nearby_restaurants, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if(choice==0)
        {
            holder.tvViewDetails.setVisibility(View.GONE);
        }

        holder.tvViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RestaurantsDetailActivity.class);
                i.putExtra("SHOW_BUY_ID",1 );
                context.startActivity(i);
            }
        });

        holder.rlHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choice==0) {
                    Intent i = new Intent(context, RestaurantsDetailActivity.class);
                    i.putExtra("SHOW_BUY_ID",0 );
                    context.startActivity(i);
                }
                else if(choice==1) {
                    Intent i = new Intent(context, SelectPackageActivity.class);
                    i.putExtra("title","name");
                    activity.setResult(Activity.RESULT_OK,i);
                    activity.finish();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}

