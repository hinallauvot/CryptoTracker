package com.example.hinal.cryptotrackerapp;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

import java.util.ArrayList;
import java.util.List;


public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.myViewHolder> implements Filterable
{

    Context my_context;
    private List<AllCardClass> my_data;
    private List<AllCardClass> ListFiltered;
    Dialog myDialog;
    RequestOptions options;
    SharedPreference sharedPreference;
    int priceColor1;
    int priceColor2;
    int priceColor3;

    public recyclerViewAdapter(Context my_context, List<AllCardClass> my_data)
    {
        this.my_context = my_context;
        this.my_data = my_data;
        sharedPreference = new SharedPreference();
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round).error(R.mipmap.ic_launcher_round).diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position)
    {
        final View view;

        LayoutInflater my_inflater = LayoutInflater.from(my_context);
        view = my_inflater.inflate(R.layout.all_fragment_cardview,viewGroup,false);
        final myViewHolder vHolder = new myViewHolder(view);
        myDialog = new Dialog(my_context);
        myDialog.setContentView(R.layout.desc_dialog);
        myDialog.setCanceledOnTouchOutside(true);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));




        vHolder.allCoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView dialog_name =(TextView)myDialog.findViewById(R.id.dialog_name);
                TextView dialog_symbol =(TextView)myDialog.findViewById(R.id.dialog_symbol);
                TextView dialog_price =(TextView)myDialog.findViewById(R.id.dialog_price);
                TextView dialog_1h =(TextView)myDialog.findViewById(R.id.dialog_chg_1h);
                TextView dialog_24h =(TextView)myDialog.findViewById(R.id.dialog_chg_24h);
                TextView dialog_7d =(TextView) myDialog.findViewById(R.id.dialog_chg_7d);
                TextView maximumSupply =(TextView)myDialog.findViewById(R.id.dialog_max_supply);
                TextView marketCap = (TextView)myDialog.findViewById(R.id.dialog_mkt_cap);
                TextView avlSupply =(TextView)myDialog.findViewById(R.id.dialog_avl_supply);
                ImageView dialog_img =(ImageView)myDialog.findViewById(R.id.dialog_icon);
                dialog_name.setText(my_data.get(vHolder.getAdapterPosition()).getName());
                dialog_symbol.setText(my_data.get(vHolder.getAdapterPosition()).getSymbol());
                dialog_price.setText(my_data.get(vHolder.getAdapterPosition()).getPrice());
                dialog_1h.setText(my_data.get(vHolder.getAdapterPosition()).getChange_hr());
                dialog_24h.setText(my_data.get(vHolder.getAdapterPosition()).getChange_24hr());
                dialog_7d.setText(my_data.get(vHolder.getAdapterPosition()).getChange_7d());
                Glide.with(my_context).load(my_data.get(vHolder.getAdapterPosition()).getImage()).apply(options).into(dialog_img);
                maximumSupply.setText(my_data.get(vHolder.getAdapterPosition()).getMaxSupply());
                avlSupply.setText(my_data.get(vHolder.getAdapterPosition()).getAvlSupply());
                marketCap.setText(my_data.get(vHolder.getAdapterPosition()).getMktCap());



                myDialog.show();

            }
        });
        return vHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder myViewHolder, int position) {

        myViewHolder.name.setText(my_data.get(position).getName());
        myViewHolder.symbol.setText(my_data.get(position).getSymbol());
        myViewHolder.price.setText(my_data.get(position).getPrice());
        myViewHolder.price_1h.setText(my_data.get(position).getChange_hr());
        myViewHolder.price_24h.setText(my_data.get(position).getChange_24hr());
        myViewHolder.price_7d.setText(my_data.get(position).getChange_7d());
        Glide.with(my_context).load(my_data.get(position).getImage()).apply(options).into(myViewHolder.imageView);

        if (my_data.get(position).getChange_hr().contains("-"))
            priceColor1 = Color.parseColor("#FF0000");
        else
            priceColor1 =  Color.parseColor("#32CD32");

        if (my_data.get(position).getChange_24hr().contains("-"))
            priceColor2 = Color.parseColor("#FF0000");
        else
            priceColor2 =  Color.parseColor("#32CD32");

        if (my_data.get(position).getChange_7d().contains("-"))
            priceColor3 = Color.parseColor("#FF0000");
        else
            priceColor3 =  Color.parseColor("#32CD32");

        myViewHolder.price_1h.setTextColor(priceColor1);
        myViewHolder.price_24h.setTextColor(priceColor2);
        myViewHolder.price_7d.setTextColor(priceColor3);


    }

    @Override
    public int getItemCount()
    {
        return my_data.size();
    }

   @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();


                    for (AllCardClass row : my_data) {
                        List<AllCardClass
                                > filteredList = new ArrayList<>();
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }


                    ListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = ListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                ListFiltered = (ArrayList<AllCardClass>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class myViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout allCoins;
        TextView name;
        TextView symbol;
        TextView price;
        TextView price_1h;
        TextView price_24h;
        TextView price_7d;
        ImageView imageView;
        MaterialFavoriteButton materialFavoriteButton;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name_tv);

            symbol = (TextView) itemView.findViewById(R.id.symbol_tv);

            price = (TextView) itemView.findViewById(R.id.price_tv);

            price_1h = (TextView) itemView.findViewById(R.id.change_1hr_tv);

            price_24h = (TextView) itemView.findViewById(R.id.change_24hr_tv);

            price_7d = (TextView) itemView.findViewById(R.id.change_7d_tv);

            imageView = (ImageView)itemView.findViewById(R.id.icon_tv);

            allCoins = (LinearLayout)itemView.findViewById(R.id.linearCoin);
            materialFavoriteButton =(MaterialFavoriteButton)itemView.findViewById(R.id.fav_button);


        }
    }

}
