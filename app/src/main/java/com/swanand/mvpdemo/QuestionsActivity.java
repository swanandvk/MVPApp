package com.swanand.mvpdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.swanand.mvpdemo.adapters.QuestionsAdapter;
import com.swanand.mvpdemo.model.Item;
import com.swanand.mvpdemo.presenter.QuestionsPresenter;
import com.swanand.mvpdemo.view.QuestionsView;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity implements QuestionsView {
    QuestionsPresenter questionsPresenter;
    ProgressDialog progressDialog;
    QuestionsAdapter questionsAdapter;
    RecyclerView questionsRecyclerview;
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initRecyclerView();
        questionsPresenter=new QuestionsPresenter(this);
        questionsPresenter.presentQuestions();
        test= (TextView) findViewById(R.id.test);
    }

    public void initRecyclerView(){
        questionsRecyclerview= (RecyclerView) findViewById(R.id.recycler_view);
        questionsRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        questionsRecyclerview.setItemAnimator(new DefaultItemAnimator());
        questionsAdapter=new QuestionsAdapter(this);
        questionsRecyclerview.setAdapter(questionsAdapter);
    }

    @Override
    public void renderData(List<Item> items) {
        Log.d("data",items.toString());
        questionsAdapter.addQuestions(items);
        questionsAdapter.notifyDataSetChanged();

    }

    @Override
    public void showProgressDialog() {
         progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }
}