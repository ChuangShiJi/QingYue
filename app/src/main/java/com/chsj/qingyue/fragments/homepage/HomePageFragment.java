package com.chsj.qingyue.fragments.homepage;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chsj.qingyue.R;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {


    private RecyclerView recyclerView;
    private List<HpEntity> datas;
    private MyRecyclerAdapter adapter;

    public HomePageFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        datas = new LinkedList<HpEntity>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_homepage_recycler_view);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(manager);
        adapter = new MyRecyclerAdapter(datas);
        recyclerView.setAdapter(adapter);

        return view;
    }


    private class MyRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

        private List<HpEntity> datas;

        public MyRecyclerAdapter(List<HpEntity> datas) {
            this.datas = datas;
        }


        @Override
        public int getItemCount() {

            int ret = 0;
            if (datas != null) {
                ret = datas.size();
            }
            return ret;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtAuthor;
        private TextView txtContent;
        private TextView txtPn;

        public ViewHolder(View itemView) {
            super(itemView);


        }
    }
}
