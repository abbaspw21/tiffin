package tif.eurekalabs.com.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

import tif.eurekalabs.com.R;


/**
 * Created by coderap on 4/4/2017.
 */

public class OrderSummaryListItemAdapter extends RecyclerView.Adapter<OrderSummaryListItemAdapter.MyViewHolder> {

    public List<Drawable> list;

    RelativeLayout parent;

    static String TAG = "OrderSummaryListItemAdapter";

    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View view) {
            super(view);
        }
    }


    public OrderSummaryListItemAdapter(List<Drawable> feedsList, Context c) {
        this.list = feedsList;
        context = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_order_summary, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}