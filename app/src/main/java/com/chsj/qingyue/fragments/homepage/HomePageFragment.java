package com.chsj.qingyue.fragments.homepage;


import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chsj.qingyue.Constants;
import com.chsj.qingyue.R;
import com.chsj.qingyue.tools.ImageUtils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {


    //图片网址
    private static String imgUrl = null;

    //第几条数据
    private static int index = 0;

    private String jsonStr;

    private RecyclerView recyclerView;
    private List<HpEntity> datas;
    private MyRecyclerAdapter adapter;


    //点击某张图片后出来的预览图
    private static ImageView imgShow;
    //占位的帧布局
    private static FrameLayout frameLayout;


    public HomePageFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        datas = new LinkedList<HpEntity>();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_homepage_recycler_view);
        imgShow = (ImageView) view.findViewById(R.id.fragment_home_page_showbig_img);
        frameLayout = (FrameLayout) view.findViewById(R.id.fragment_home_page_framelayout);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(
                getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(manager);
        adapter = new MyRecyclerAdapter(datas);
        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }


            /**
             * 监听滑动事件，实现类似Viewager的切换效果，
             * @param recyclerView
             * @param dx
             * @param dy  dy>0代表上拉  dy<0代表下拉
             */

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                if (dy >= 0 && recyclerView.getChildAt(0).getTop() < 0 && Math.abs(recyclerView.getChildAt(0).getTop()) > recyclerView.getHeight() / 2) {
                    recyclerView.scrollToPosition(++index);
                } else if (dy < 0 && recyclerView.getChildAt(0).getTop() < 0 && Math.abs(recyclerView.getChildAt(0).getTop()) < recyclerView.getHeight() / 2) {
                    recyclerView.scrollToPosition(--index);
                }

            }
        });


        imgShow.setOnClickListener(this);
        imgShow.setOnLongClickListener(this);


        return view;
    }


    @Override
    public void onResume() {

        super.onResume();
        datas.clear();

        for (int i = 1; i <= 10; i++) {

            new AsyTask(new AsyTask.CallBack() {
                @Override
                public void setJsonStr(String str) {
                    jsonStr = str;
                    Log.d("str", jsonStr + "====");
                    HpEntity hpEntity = ParseTool.parse(jsonStr);
                    adapter.notifyDataSetChanged();

                    datas.add(hpEntity);
                }
            }).execute(String.format(Constants.URL_HOME_PAGE, i));

        }
        adapter.notifyDataSetChanged();


    }

    /**
     * 点击预览图，预览图消失
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        frameLayout.setVisibility(View.INVISIBLE);

    }

    /**
     * 长按预览图，进行保存操作
     *
     * @param v
     * @return
     */
    @Override
    public boolean onLongClick(View v) {

        View view = LayoutInflater.from(getActivity().getApplicationContext())
                .inflate(R.layout.dialog_view, null);

        TextView txtLoad = (TextView) view.findViewById(R.id.dialog_view_load_img);
        TextView txtCancle = (TextView) view.findViewById(R.id.dialog_view_cancle);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final AlertDialog dialog = builder.setTitle("更多操作")
                .setView(view)
                .create();

        dialog.show();


        /**
         * 取消长按操作
         */
        txtCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        /**
         * 保存图片到本地
         */
        txtLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = imgShow.getDrawable();


                //TODO 保存图片出问题
                int width = drawable.getIntrinsicWidth();
                int height = drawable.getIntrinsicHeight();
                Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

                try {
                    ImageUtils.saveImg(imgUrl, bitmap);
                    Toast.makeText(getActivity(), "保存成功!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        return true;
    }

    /**
     * RecyclerView的适配器
     */
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

            ViewHolder ret = null;

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity().getApplicationContext());
            View view = layoutInflater.inflate(R.layout.fragment_homepage_recyclerview_item, parent, false);
            ret = new ViewHolder(view);
            return ret;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            HpEntity hpEntity = datas.get(position);

            imgUrl = hpEntity.getStrThumbnaiUrl();


            Bitmap bitmap = ImageUtils.getImg(imgUrl);
//            if (bitmap != null) {
//                holder.imageView.setImageBitmap(bitmap);
//            } else {

            Picasso.with(getActivity().getApplicationContext()).load(hpEntity.getStrThumbnaiUrl()).into(holder.imageView);
//            }


            String str = hpEntity.getAuthor();
            holder.txtTitle.setText(str.substring(0, str.indexOf('&')));
            holder.txtAuthor.setText(str.substring(str.indexOf('&') + 1));
            holder.txtContent.setText(hpEntity.getStrContent());
            holder.txtPn.setText(hpEntity.getStrPn());

        }

    }


    /**
     * VuewHolder
     */
    private static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView txtTitle;
        private TextView txtAuthor;
        private TextView txtContent;
        private TextView txtPn;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.fragment_homepage_item_icon);
            txtTitle = (TextView) itemView.findViewById(R.id.fragment_homepage_item_title);
            txtAuthor = (TextView) itemView.findViewById(R.id.fragment_homepage_item_author);
            txtContent = (TextView) itemView.findViewById(R.id.fragment_homepage_item_content);
            txtPn = (TextView) itemView.findViewById(R.id.fragment_homepage_item_pn);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            frameLayout.setVisibility(View.VISIBLE);
            imgShow.setImageDrawable(imageView.getDrawable());
            Animation mAnimation = AnimationUtils.loadAnimation(itemView.getContext(), R.anim.imgshowscal);
            mAnimation.setFillAfter(true);
            imgShow.startAnimation(mAnimation);

        }
    }


}
