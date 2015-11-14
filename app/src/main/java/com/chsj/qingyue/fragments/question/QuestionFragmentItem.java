package com.chsj.qingyue.fragments.question;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chsj.qingyue.Constants;
import com.chsj.qingyue.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragmentItem extends Fragment implements View.OnClickListener {

    private QuestionEntity questionEntity;

    private TextView time;
    private TextView questionTitle,questionDescrpt;
    private TextView anwserTitle,anwserDescript;
    private TextView sEdit;
    private TextView anwserComment;
    private View view;
    private int well;
    private ScrollView scrollView;

    private ImageView imageView;
    private AnimationDrawable anim;

    //    使用本地广播管理器：
    private LocalBroadcastManager localBroadcastManager;

    //获取实例的方法：
    public static QuestionFragmentItem getInstance(String url){
        QuestionFragmentItem fr = null;
        if (url != null) {
             fr = new QuestionFragmentItem();
            Bundle args = new Bundle();
            args.putString("url",url);
            fr.setArguments(args);
        }

        return fr;
    }

    public QuestionFragmentItem() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_question_fragment_item, container, false);

        time = (TextView) view.findViewById(R.id.question_fragment_date);
        questionTitle = (TextView) view.findViewById(R.id.question_fragment_title);
        questionDescrpt = (TextView) view.findViewById(R.id.question_problem_describe);
        anwserDescript = (TextView) view.findViewById(R.id.question_anwser_describe);
        anwserTitle = (TextView) view.findViewById(R.id.question_answer_title);
        sEdit = (TextView) view.findViewById(R.id.question_anwser_edit);
        anwserComment = (TextView) view.findViewById(R.id.question_comment);

        imageView = (ImageView) view.findViewById(R.id.queation_progress_loading);
        scrollView = (ScrollView) view.findViewById(R.id.question_scrollView);

        anwserComment.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        //获取本地广播管理器：
        localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        anim = (AnimationDrawable) imageView.getBackground();
        anim.start();
        //初始化数据
        initData();

    }

    //失去焦点
    @Override
    public void onPause() {
        super.onPause();
    }

    private void initView() {

        if (questionEntity!=null){

            String date = questionEntity.getStrQuestionMarketTime();

            String dates[] = date.split("-");
            if (dates != null) {
                int month = Integer.parseInt(dates[1]);
                String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
                date = months[month - 1] + " " + dates[2] + "," + dates[0];
            }
            //初始化视图：
            //获取时间：
            time.setText(date);
            questionTitle.setText(questionEntity.getStrQuestionTitle());
            questionDescrpt.setText(questionEntity.getStrQuestionContent());
            anwserTitle.setText(questionEntity.getStrAnswerTitle());
            anwserDescript.setText(replaceStrBr(questionEntity.getStrAnswerContent())+"\n");
            sEdit.setText(questionEntity.getSEditor()+"\n");
            anwserComment.setText(getResources().getString(R.string.question_now_comments) + questionEntity.getStrPraiseNumber());

            //获取点赞数
            well = Integer.parseInt(questionEntity.getStrPraiseNumber());
        }
    }

    private void initData() {
        //获取数据：
        new QuestionAsyncTask(new QuestionAsyncTask.CallbackInterface() {
            @Override
            public void callResultJson(JSONObject jsonObject) {
                if (jsonObject!=null){
                    //解析参数：
                    try {
                        String result = jsonObject.getString("result");
                        if (result.equals("SUCCESS")){
                            JSONObject quesJsonbject = jsonObject.getJSONObject("questionAdEntity");
                            //解析数据：
                            TypeToken<QuestionEntity> token = new TypeToken<QuestionEntity>(){};
                            Gson gson = new Gson();
                            questionEntity = gson.fromJson(quesJsonbject.toString(), token.getType());

                            //        //发送广播
                            if (questionEntity!=null){
                                //该页面备选中后   发送广播数据：通过本地广播管理器  来发送广播：
                                Intent intent = new Intent(Constants.GET_DATA_TO_SHARE);
                                Bundle bundle = new Bundle();

                                bundle.putSerializable(Constants.DATA_TO_EXTRA,questionEntity);//将网络数据传入
                                intent.putExtras(bundle);

                                localBroadcastManager.sendBroadcast(intent);

                            }

                            //初始化视图：
                            initView();

                            anim.stop();
                            imageView.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).execute(getArguments().getString("url"));
    }

    //字符串替换方法：
    public String replaceStrBr(String str){
        return str.replace("<br>", "\n");
    }

    private boolean isClick = true; //初始为  可以点击的

    @Override
    public void onClick(View v) {

        if (isClick){
            well = well+1;
            anwserComment.setText(getString(R.string.question_now_comments)+ well);
            isClick = false;
        }else{
            well = well-1;
            anwserComment.setText(getString(R.string.question_now_comments)+ well);
            isClick = true;
        }
    }
}
