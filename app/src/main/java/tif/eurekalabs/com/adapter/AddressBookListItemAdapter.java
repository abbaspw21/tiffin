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

import tif.eurekalabs.com.AddAddressActivity;
import tif.eurekalabs.com.R;


/**
 * Created by coderap on 4/4/2017.
 */

public class AddressBookListItemAdapter extends RecyclerView.Adapter<AddressBookListItemAdapter.MyViewHolder> {

    private List<Drawable> list;

    private RelativeLayout parent;

    private static String TAG = "AddressBookListItemAdapter";

    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvEdit;
        TextView tvDelete;

        public MyViewHolder(View view) {
            super(view);

            tvEdit=(TextView) view.findViewById(R.id.tv_edit);
            tvDelete=(TextView) view.findViewById(R.id.btn_delete);

        }
    }


    public AddressBookListItemAdapter(List<Drawable> feedsList, Context c) {
        this.list = feedsList;
        context = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_address_book, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, AddAddressActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}