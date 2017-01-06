package com.yapps.guessthemovie;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String[] db = {"dhruva","roy","ram"};
    String moviename;
    LinearLayout ll_holder;
    LinearLayout[] holder_row;
    Button[] iv_holder;
    int num_col;
    LinearLayout.LayoutParams lp1,lp2;
    int finish;
    TextView hint;

    int five;
    EditText editText;
    Button button;
    LinearLayout keyboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num_col=6;
         five=5;
        finish=0;

        //Random random = new Random();
        moviename = db[(int)(Math.random()*db.length)];

        hint = (TextView)findViewById(R.id.hint);
        hint.setText("");
        ll_holder= (LinearLayout) findViewById(R.id.Ll_holder);
        holder_row = new LinearLayout[(moviename.length()/num_col)+1];
        iv_holder = new Button[moviename.length()];
        lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        lp2 = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);

        for(int i=0; i<=(moviename.length()/num_col);i++){
            holder_row[i] = new LinearLayout(this);
            holder_row[i].setLayoutParams(lp1);
            ll_holder.addView(holder_row[i]);
            int r = (i==(moviename.length()/num_col))?(moviename.length()%num_col):num_col;
                for (int j=0; j<r;j++){
                    iv_holder[i*num_col+j]=new Button(this);
                    iv_holder[i*num_col+j].setLayoutParams(lp2);
                    Character c=moviename.charAt(i*num_col+j);
                    if(!(c.toString().equals(" "))){
                        iv_holder[i*num_col+j].setText("_");

                    }else finish++;
                    iv_holder[i*num_col+j].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    holder_row[i].addView(iv_holder[i*num_col+j]);
                }
        }

        //iv_holder[5].setText("k");

        CreateKeyBoard();


    }

    private void CreateKeyBoard() {
        String[][] str = new String[][]{
            {"0","1","2","3","4","5","6","7","8","9"},
            {"Q","W","E","R","T","Y","U","I","O","P"},
            {"A","S","D","F","G","H","J","K","L","-"},
            {"","Z","X","C","C","V","B","N","M",""},
        };
        keyboard = (LinearLayout)findViewById(R.id.keyboard);
        LinearLayout[] r = new LinearLayout[4];
        Button[] k = new Button[40];
        int width1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(width1, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp3.setMargins(0,0,0,0);

        for(int ind1=0; ind1<4;ind1++){
            r[ind1] = new LinearLayout(this);
            r[ind1].setLayoutParams(lp1);
            keyboard.addView(r[ind1]);
            for (int ind2=0;ind2<str[ind1].length;ind2++){
                k[ind1*10+ind2] = new Button(this);
                k[ind1*10+ind2].setTextSize(TypedValue.COMPLEX_UNIT_DIP,10);
                k[ind1*10+ind2].setLayoutParams(lp3);
                k[ind1*10+ind2].setText(str[ind1][ind2]);
                k[ind1*10+ind2].setId(ind1*100+ind2);
                k[ind1*10+ind2].setOnClickListener(this);
                r[ind1].addView(k[ind1*10+ind2]);
            }
        }

    }

    @Override
    public void onClick(View view) {

        Button key = (Button)view;
        Boolean b=false;
        for (int v=0;v<moviename.length();v++){

            if(Character.toString(moviename.charAt(v)).matches(key.getText().toString().toLowerCase())){

                iv_holder[v].setText(key.getText().toString());
                b=true;
                finish++;
                //Toast.makeText(getApplicationContext(),finish+"",Toast.LENGTH_SHORT).show();
            };
        }

        if(!b) {
            five--;
            hint.setText("wrong attempt "+ (5-five));
        }


        if(finish==moviename.length() || (five==0)){
            if (five==0){
                hint.setText("All lifes over");


            }else {
                hint.setText("Congratulations");
            }

            //this.recreate();
        }

    }



    private class LetterAdapeter extends BaseAdapter {
        @Override
        public int getCount() {
            return moviename.length();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {

            if(convertView==null){
                LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView=  li.inflate(R.layout.holder_child, null);
            }

            Button iv_child= (Button) convertView.findViewById(R.id.iv_child);
            Character c=moviename.charAt(i);
            if(!(c.toString().equals(" "))){
                iv_child.setText("_");
            }
            return convertView;
        }


    }
}




















 /*   Character c=editText.getText().toString().charAt(0);

    if(moviename.indexOf(c)<0) {
        for (int g = 0; g < moviename.length(); g++) {
            String c1 = c.toString();
            String s= Character.toString(moviename.charAt(g));
            if (c1.equals(s)) {
                iv_holder[g].setText(moviename.charAt(g));
            }
        }
    }else five--;

    Toast.makeText(getApplicationContext(),five+" "+c.toString(),Toast.LENGTH_LONG).show();
}*/