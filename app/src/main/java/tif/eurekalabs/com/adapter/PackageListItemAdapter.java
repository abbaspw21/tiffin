package tif.eurekalabs.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import tif.eurekalabs.com.BookPackageActivity;
import tif.eurekalabs.com.R;
import tif.eurekalabs.com.model.PackageItem;


/**
 * Created by coderap on 4/4/2017.
 */

public class PackageListItemAdapter extends RecyclerView.Adapter<PackageListItemAdapter.MyViewHolder> {

    public List<PackageItem> list;

    RelativeLayout parent;

    static String TAG = "PackageListItemAdapter";

    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvPrice;
        TextView tvDescription;
        TextView tvName;

        RelativeLayout rlHolder;

        public MyViewHolder(View view) {
            super(view);

            rlHolder=(RelativeLayout) view.findViewById(R.id.rl_holder);

            tvDescription=(TextView) view.findViewById(R.id.tv_description);
            tvPrice=(TextView) view.findViewById(R.id.tv_price);
            tvName=(TextView) view.findViewById(R.id.tv_name);


        }
    }


    public PackageListItemAdapter(List<PackageItem> feedsList, Context c) {
        this.list = feedsList;
        context = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_package, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvName.setText(list.get(position).getName());
        holder.tvDescription.setText(list.get(position).getDecription());
        holder.tvPrice.setText(list.get(position).getPrice());

        holder.rlHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, BookPackageActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}

