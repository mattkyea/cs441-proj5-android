package com.example.assign5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    //this is the only tricky part

    private ArrayList<RowEntry> entries = new ArrayList<>();
    private RecyclerView recyclerView;


    //constructor - the items/words and the view
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

        //this does the click handling
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = recyclerView.getChildLayoutPosition(view);//find what item user clicked on
                CharSequence text = "";
                //couple of cases
                /*
                if(itemPosition == 0){//first element
                    text = words.get(itemPosition) + " is before all other words.";
                }else if(itemPosition == words.size()-1){//last element
                    text = words.get(itemPosition) + " is after all other words.";
                }else {//any other element
                    text = words.get(itemPosition) + " is after " + words.get(itemPosition-1) + " and before " + words.get(itemPosition+1) + ".";
                }
                */
                //display toast (popup) to user
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(c, text, duration);
                toast.show();
            }
        });

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
        text.setText(entry.getTweetContent());
        num.setText(Float.toString(entry.getNetGainLoss())); //fix this later
        if(entry.isGain()) num.setTextColor(0xFF00FF00);//green
        else num.setTextColor(0xFFFF0000);//red
        System.out.println("displaying " + entry.getTweetContent() + ", " + entry.getNetGainLoss());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //my xml that's displayed in a list only has 1 element, a TextView
        public TextView text;
        public TextView num;

        public ViewHolder(@NonNull View itemView) {
            //just get the TextView - we'll set the text in the Adapter
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
