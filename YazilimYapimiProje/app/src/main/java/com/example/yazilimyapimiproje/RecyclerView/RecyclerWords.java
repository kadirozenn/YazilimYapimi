package com.example.yazilimyapimiproje.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yazilimyapimiproje.Activity.ActivityQuestion;
import com.example.yazilimyapimiproje.Class.WordsClass;
import com.example.yazilimyapimiproje.R;

import java.util.ArrayList;


public class RecyclerWords extends RecyclerView.Adapter<RecyclerWords.WordsHolder> {

    private Context mContext;

    LayoutInflater mInflater;
    ArrayList<WordsClass> wordList;

    public RecyclerWords(Context context, ArrayList<WordsClass> words){
        mInflater=LayoutInflater.from(context);
        wordList=words;
        mContext=context;

    }

    @NonNull
    @Override
    public WordsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=mInflater.inflate(R.layout.recyclerview_item,parent,false);

        WordsHolder holder =new WordsHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder( WordsHolder wordsHolder, int position) {
        final WordsClass wordsClass=wordList.get(position);
        wordsHolder.txtWord.setText(wordsClass.getWord());
        wordsHolder.txtRemainingDay.setText(wordsClass.getRemainingDay());
        wordsHolder.txtCounter.setText(""+(position+1));
        int a=Integer.valueOf(wordsClass.getRemainingDay());
        if(a<0){
            wordsHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent= new Intent(mContext, ActivityQuestion.class);
                    intent.putExtra("id",wordsClass.getID());
                    mContext.startActivity(intent);



                }
            });

        }


    }



    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public static class WordsHolder extends RecyclerView.ViewHolder{

        TextView txtWord,txtRemainingDay,txtCounter;
        ConstraintLayout constraintLayout;

        public WordsHolder(View itemView) {
            super(itemView);
            constraintLayout=itemView.findViewById(R.id.Recycler_Const);
            txtWord=itemView.findViewById(R.id.Recycler_Word);
            txtCounter=itemView.findViewById(R.id.Recycler_Counter);
            txtRemainingDay=itemView.findViewById(R.id.Recycler_Day);
        }
    }
}
