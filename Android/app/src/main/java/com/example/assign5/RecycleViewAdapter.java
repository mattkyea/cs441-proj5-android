package com.example.assign5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    //list of entries
    private ArrayList<RowEntry> entries = new ArrayList<>();
    private RecyclerView recyclerView;


    //constructor - the RowEntries and the view
    public RecycleViewAdapter(ArrayList<RowEntry> items, RecyclerView recyclerView){
        this.entries = items;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this needed to set up the view holder
        Context c = parent.getContext();
        LayoutInflater li = LayoutInflater.from(c);
        View v = li.inflate(R.layout.recyclerview_row, parent, false);
        //this also needed for view holder setup
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    //this sets each element of the list using the nested viewholder
    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        RowEntry entry = entries.get(position);
        TextView text = holder.text;
        TextView num = holder.num;
        text.setText(entry.getTweetContent());//text content of the tweet
        num.setText(entry.getNetGainLoss().toString()); //rise or fall of price
        if(entry.isGain()) num.setTextColor(0xFF00FF00);//green for gain (positive)
        else num.setTextColor(0xFFFF0000);//red for fall (negative)
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //my xml that's displayed in a list has 2 textviews
        public TextView text;
        public TextView num;

        public ViewHolder(@NonNull View itemView) {
            //just get the 2 TextViews - we'll set the texts in the Adapter
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tweetText);
            num = (TextView) itemView.findViewById(R.id.netGain);
        }

        //don't need, just has to be implemented as parent is abstract
        @Override
        public void onClick(View v) {
        }
    }

}
