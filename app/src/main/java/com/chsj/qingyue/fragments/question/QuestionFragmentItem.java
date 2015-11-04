package com.chsj.qingyue.fragments.question;


import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chsj.qingyue.Constants;
import com.chsj.qingyue.QuestionEntity;
import com.chsj.qingyue.R;
import com.chsj.qingyue.tasks.QuestionAsyncTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragmentItem extends Fragment {

    private QuestionEntity questionEntity;

    private TextView time;
    private TextView questionTitle,questionDescrpt;
    private TextView anwserTitle,anwserDescript;
    private TextView sEdit;
    private TextView anwserComment;
    private View view;

    //获取实例的方法：
    public static QuestionFragmentItem getInstance(String url){
        QuestionFragmentItem fr = new QuestionFragmentItem();

        Bundle args = new Bundle();
        args.putString("url",url);
        fr.setArguments(args);

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

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        //初始化数据
        initData();

    }

    private void initView() {

        if (questionEntity!=null){

            //初始化视图：
            time.setText(questionEntity.getStrQuestionMarketTime());
            questionTitle.setText(questionEntity.getStrQuestionTitle());
            questionDescrpt.setText(questionEntity.getStrQuestionContent());
            anwserTitle.setText(questionEntity.getStrAnswerTitle());
            anwserDescript.setText(replaceStrBr(questionEntity.getStrAnswerContent())+"\n");
            sEdit.setText(questionEntity.getSEditor()+"\n");
            anwserComment.setText("当前评论:"+questionEntity.getStrPraiseNumber());

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

                            //初始化视图：
                            initView();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).execute(getArguments().getString("url"));
    }

    //字符穿替换方法：
    public String replaceStrBr(String str){
        return str.replace("<br>", "\n");
    }

}
