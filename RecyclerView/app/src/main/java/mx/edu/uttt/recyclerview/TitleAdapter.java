package mx.edu.uttt.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by qas on 22/08/16.
 */
public class TitleAdapter
        extends RecyclerView.Adapter<TitleAdapter.TitlesViewHolder>
        implements View.OnClickListener{

    private View.OnClickListener listener;
    private ArrayList<Title> titles;

    public static class TitlesViewHolder extends  RecyclerView.ViewHolder{

        private TextView txvTitle;
        private TextView txvSubTitle;

        public TitlesViewHolder(View itemView) {
            super(itemView);
            txvTitle = (TextView)itemView.findViewById(R.id.txv_title);
            txvSubTitle = (TextView)itemView.findViewById(R.id.txv_subtitle);
        }

        public void bindTitle(Title title){
            txvTitle.setText(title.getTitle());
            txvSubTitle.setText(title.getSubtitle());
        }
    }

    public TitleAdapter(ArrayList<Title> titles) {
        this.titles = titles;
    }

    @Override
    public TitlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_layout, parent, false);
        itemView.setOnClickListener(this);

        TitlesViewHolder titlesViewHolder = new TitlesViewHolder(itemView);

        return titlesViewHolder;
    }


    @Override
    public void onBindViewHolder(TitlesViewHolder holder, int position) {
        Title title = titles.get(position);
        holder.bindTitle(title);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }
}
