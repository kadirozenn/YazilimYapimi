package com.example.yazilimyapimiproje.Activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yazilimyapimiproje.Alagan.Alagan;
import com.example.yazilimyapimiproje.Alagan.Class.AlaganStringDatabase;
import com.example.yazilimyapimiproje.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActivityQuestion extends AppCompatActivity {


    Button btnAnswerA,btnAnswerB,btnAnswerC,btnAnswerD;
    ProgressBar progressBar;
    TextView txtQuestion,txtSentence;
    String id;
    String rightAnswer,date;
    boolean boolThread=true;
    List<Button> btnList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        id=getIntent().getExtras().getString("id");

        Init();

        Time();

        Alagan.Instance.dbString.put("command","word").put("wordID",id).read(new AlaganStringDatabase.AlaganListener() {
            @Override
            public void onResponse(String response) {
                String word[]=response.split(">");
                txtQuestion.setText(word[0]);
                txtSentence.setText(word[2]);
                Random rand=new Random();
                int r=rand.nextInt(4);
                Log.e("asdasdasdasdasd",""+r);
                Button btnRightAnswer = btnList.get(r);

                btnRightAnswer.setText(word[1]);
                rightAnswer=word[1];
                btnList.remove(r);
                Toast.makeText(getApplicationContext(),""+word[1],Toast.LENGTH_SHORT).show();

                Alagan.Instance.dbString.put("command","randAnswer").put("type","name").read(new AlaganStringDatabase.AlaganListener() {
                    @Override
                    public void onResponse(String response) {
                        String randomWord[]=response.split(">");
                        Button randomAnswer1=btnList.get(0);
                        Button randomAnswer2=btnList.get(1);
                        Button randomAnswer3=btnList.get(2);

                        randomAnswer1.setText(randomWord[0]);
                        randomAnswer2.setText(randomWord[1]);
                        randomAnswer3.setText(randomWord[2]);
                    }
                });
            }
        });







    }
    Thread t;
    private void Time() {
            int a = 0;
            final int totalTime = 100;
            t = new Thread() {
                @Override
                public void run() {
                    int jumpTime = 0;


                    while (jumpTime < totalTime && boolThread) {
                        try {
                            sleep(150);
                            jumpTime += 1;
                            progressBar.setProgress(jumpTime);
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }

                    }


                }
            };


            t.start();

        }


        private void Init() {
            btnAnswerA=findViewById(R.id.Activity_Question_Answer_A);
            btnAnswerB=findViewById(R.id.Activity_Question_Answer_B);
            btnAnswerC=findViewById(R.id.Activity_Question_Answer_C);
            btnAnswerD=findViewById(R.id.Activity_Question_Answer_D);
            txtQuestion=findViewById(R.id.Activity_Question_Question);
            txtSentence=findViewById(R.id.Activity_Question_Sentence);
            progressBar=findViewById(R.id.Activity_Question_Progresbar);
            btnList=new ArrayList<>();
            btnList.add(btnAnswerA);
            btnList.add(btnAnswerB);
            btnList.add(btnAnswerC);
            btnList.add(btnAnswerD);

        }

        public void ActvityQuestionBtn(View view) {
            switch (view.getId()){
                case R.id.Activity_Question_Answer_A:
                    if(btnAnswerA.getText().toString().equals(rightAnswer)){
                        RightAnswer();
                    }else{
                        WrongAnswer();
                    }
                    break;
                case R.id.Activity_Question_Answer_B:
                    if(btnAnswerB.getText().toString().equals(rightAnswer)){
                        RightAnswer();
                    }else{
                        WrongAnswer();
                    }
                    break;
                case R.id.Activity_Question_Answer_C:
                    if(btnAnswerC.getText().toString().equals(rightAnswer)){
                        RightAnswer();
                    }else{
                        WrongAnswer();
                    }
                    break;
                case R.id.Activity_Question_Answer_D:
                    if(btnAnswerD.getText().toString().equals(rightAnswer)){
                        RightAnswer();
                    }else{
                        WrongAnswer();

                    }
                    break;

            }

        }

        private void WrongAnswer(){
            Toast.makeText(getApplicationContext(),"Yalnış Cevap",Toast.LENGTH_SHORT).show();
            boolThread=false;
            t.interrupt();
            Log.e("aaaaaaaaaaaaaa",t.getName());
            Alagan.Instance.dbString.put("command","wordUpdate").put("wordID",id)
                    .put("status","0").read(new AlaganStringDatabase.AlaganListener() {
                @Override
                public void onResponse(String response) {
                    startActivity(new Intent(getApplicationContext(),ActivityMain.class));
                    finish();
                }
            });

        }

        private void RightAnswer(){
            Toast.makeText(getApplicationContext(),"Doğru Cevap",Toast.LENGTH_SHORT).show();
            boolThread=false;
            t.interrupt();
            Alagan.Instance.dbString.put("command","wordUpdate").put("wordID",id)
                    .put("status","1").read(new AlaganStringDatabase.AlaganListener() {
                @Override
                public void onResponse(String response) {
                    startActivity(new Intent(getApplicationContext(),ActivityMain.class));
                    finish();
                }
            });



        }

        @Override
        public void onBackPressed() {
            ActivityQuestion.this.finish();
            super.onBackPressed();
        }
    }
