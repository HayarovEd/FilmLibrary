package com.edurda77.filmlibrary.tmp;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.edurda77.filmlibrary.R;
import com.edurda77.filmlibrary.data.MovieData;
import com.edurda77.filmlibrary.domain.Movie;
import com.edurda77.filmlibrary.ui.FilmActivity;


import java.util.ArrayList;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder> {

    private ArrayList<MovieData> dataList;
    private Context mContext;
    private SectionListDataAdapter.OnStateClickListener onClickListener;

    public RecyclerViewDataAdapter(ArrayList<MovieData> dataList, Context mContext,
                                   SectionListDataAdapter.OnStateClickListener onClickListener) {
        this.dataList = dataList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_out_recycled_view, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        final String sectionName = dataList.get(i).getGanreTitle();

        ArrayList singleSectionItems = dataList.get(i).getAllMovieInSection();

        itemRowHolder.itemTitle.setText(sectionName);

        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(mContext, singleSectionItems, onClickListener);

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);
        itemRowHolder.recycler_view_list.setOnClickListener(view -> {

        });



        /*itemRowHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(v.getContext(), "click event on more, "+sectionName , Toast.LENGTH_SHORT).show();



            }
        });*/


       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle;

        protected RecyclerView recycler_view_list;

        //protected Button btnMore;



        public ItemRowHolder(View view) {
            super(view);

            this.itemTitle = view.findViewById(R.id.ganre_movie);
            this.recycler_view_list = view.findViewById(R.id.item_movie);
            //this.btnMore= (Button) view.findViewById(R.id.btnMore);


        }

    }

}
