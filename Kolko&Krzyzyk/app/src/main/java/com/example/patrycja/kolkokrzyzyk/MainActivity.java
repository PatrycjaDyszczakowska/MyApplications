package com.example.patrycja.kolkokrzyzyk;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {
    private EditText text, text1;
    private TextView msg, ranking;
    private TableLayout board;
    private String winner="";
    private String[] player = new String[2];
    private int[] winnings = new int[2];
    private int round;
    private Button b2,b3,b4,b5,b6,b7,b8,b9, b10;
    Random generator = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText)findViewById(R.id.player1);
        text1 = (EditText)findViewById(R.id.player2);
        msg = (TextView)findViewById(R.id.msg);
        ranking = (TextView)findViewById(R.id.ranking);
        board = (TableLayout)findViewById(R.id.board);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        b5 = (Button)findViewById(R.id.button5);
        b6 = (Button)findViewById(R.id.button6);
        b7 = (Button)findViewById(R.id.button7);
        b8 = (Button)findViewById(R.id.button8);
        b9 = (Button)findViewById(R.id.button9);
        b10 = (Button)findViewById(R.id.button10);
    }

    private void Resoults(){
        if(b2.getText().equals(b3.getText()) && b2.getText().equals(b4.getText())){
            if(b2.getText().equals("X")){
                winner = "Wygral gracz " + player[0];
            }
            if(b2.getText().equals("O")){
                winner = "Wygral gracz " + player[1];
            }
        }
        if(b2.getText().equals(b6.getText()) && b2.getText().equals(b10.getText())){
            if(b2.getText().equals("X")){
                winner = "Wygral gracz " + player[0];
            }
            if(b2.getText().equals("O")){
                winner = "Wygral gracz " + player[1];
            }
        }
        if(b2.getText().equals(b5.getText()) && b2.getText().equals(b8.getText())){
            if(b2.getText().equals("X")){
                winner = "Wygral gracz " + player[0];
            }
            if(b2.getText().equals("O")){
                winner = "Wygral gracz " + player[1];
            }
        }
        if(b3.getText().equals(b6.getText()) && b3.getText().equals(b9.getText())){
            if(b3.getText().equals("X")){
                winner = "Wygral gracz " + player[0];
            }
            if(b3.getText().equals("O")){
                winner = "Wygral gracz " + player[1];
            }
        }
        if(b4.getText().equals(b6.getText()) && b4.getText().equals(b8.getText())){
            if(b4.getText().equals("X")){
                winner = "Wygral gracz " + player[0];
            }
            if(b4.getText().equals("O")){
                winner = "Wygral gracz " + player[1];
            }
        }
        if(b4.getText().equals(b7.getText()) && b4.getText().equals(b10.getText())){
            if(b4.getText().equals("X")) {
                winner = "Wygral gracz " + player[0];
            }
            if(b4.getText().equals("O")){
                winner = "Wygral gracz " + player[1];
            }
        }
        if(b5.getText().equals(b6.getText()) && b5.getText().equals(b7.getText())){
            if(b5.getText().equals("X")){
                winner = "Wygral gracz " + player[0];
            }
            if(b5.getText().equals("O")){
                winner = "Wygral gracz " + player[1];
            }
        }
        if(b8.getText().equals(b9.getText()) && b8.getText().equals(b10.getText())){
            if(b8.getText().equals("X")){
                winner = "Wygral gracz " + player[0];
            }
            if(b8.getText().equals("O")){
                winner = "Wygral gracz " + player[1];
            }
        }
        if((b2.getText() != "") && (b3.getText() != "") && (b4.getText() != "") && (b5.getText() != "") && (b6.getText() != "") && (b7.getText() != "") && (b8.getText() != "") && (b9.getText() != "") && (b10.getText() != "")){
            Toast.makeText(getApplicationContext(), "NIkt nie wygral",Toast.LENGTH_SHORT).show();
            clean();
        }
        if(winner.equals("Wygral gracz " + player[0]) || winner.equals("Wygral gracz " + player[1])){
            Toast.makeText(getApplicationContext(), winner,Toast.LENGTH_SHORT).show();
            if(winner.equals("Wygral gracz " + player[0])){
                winnings[0]++;
            }
            else{
                winnings[1]++;
            }
            winner="";
            ranking.setText(player[0]+" "+winnings[0]+":"+winnings[1]+" "+player[1]);
            clean();
        }
    }
    private void clean(){
        b2.setEnabled(true);
        b2.setText("");
        b3.setEnabled(true);
        b3.setText("");
        b4.setEnabled(true);
        b4.setText("");
        b5.setEnabled(true);
        b5.setText("");
        b6.setEnabled(true);
        b6.setText("");
        b7.setEnabled(true);
        b7.setText("");
        b8.setEnabled(true);
        b8.setText("");
        b9.setEnabled(true);
        b9.setText("");
        b10.setEnabled(true);
        b10.setText("");
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (text1.getText().length() == 0 && text.getText().length()==0) {
                    Toast.makeText(this, "Please enter a valid number",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                player[0] = ""+text.getText();
                player[1] = ""+text1.getText();
                winnings[0]=0;
                winnings[1]=0;
                round = generator.nextInt(2);
                msg.setText("Teraz kolej gracza: " + player[round]);
                ranking.setText(player[0]+" "+winnings[0]+":"+winnings[1]+" "+player[1]);
                board.setVisibility(View.VISIBLE);
                clean();
                break;

            case R.id.button2:
                if(round==0){
                    b2.setText("X");
                    round++;
                }
                else{
                    b2.setText("O");
                    round--;
                }
                msg.setText("Teraz kolej gracza: "+player[round]);
                b2.setTextSize(b3.getHeight() / 4);
                b2.setEnabled(false);
                break;
            case R.id.button3:
                if(round==0){
                    b3.setText("X");
                    round++;
                }
                else{
                    b3.setText("O");
                    round--;
                }
                msg.setText("Teraz kolej gracza: "+player[round]);
                b3.setTextSize(b3.getHeight()/4);
                b3.setEnabled(false);
                break;
            case R.id.button4:
                if(round==0){
                    b4.setText("X");
                    round++;
                }
                else{
                    b4.setText("O");
                    round--;
                }
                msg.setText("Teraz kolej gracza: "+player[round]);
                b4.setTextSize(b3.getHeight() / 4);
                b4.setEnabled(false);
                break;
            case R.id.button5:
                if(round==0){
                    b5.setText("X");
                    round++;
                }
                else{
                    b5.setText("O");
                    round--;
                }
                msg.setText("Teraz kolej gracza: "+player[round]);
                b5.setTextSize(b3.getHeight() / 4);
                b5.setEnabled(false);
                break;
            case R.id.button6:
                if(round==0){
                    b6.setText("X");
                    round++;
                }
                else{
                    b6.setText("O");
                    round--;
                }
                msg.setText("Teraz kolej gracza: "+player[round]);
                b6.setTextSize(b3.getHeight() / 4);
                b6.setEnabled(false);
                break;
            case R.id.button7:
                if(round==0){
                    b7.setText("X");
                    round++;
                }
                else{
                    b7.setText("O");
                    round--;
                }
                msg.setText("Teraz kolej gracza: "+player[round]);
                b7.setTextSize(b3.getHeight() / 4);
                b7.setEnabled(false);
                break;
            case R.id.button8:
                if(round==0){
                    b8.setText("X");
                    round++;
                }
                else{
                    b8.setText("O");
                    round--;
                }
                msg.setText("Teraz kolej gracza: "+player[round]);
                b8.setTextSize(b3.getHeight() / 4);
                b8.setEnabled(false);
                break;
            case R.id.button9:
                if(round==0){
                    b9.setText("X");
                    round++;
                }
                else{
                    b9.setText("O");
                    round--;
                }
                msg.setText("Teraz kolej gracza: "+player[round]);
                b9.setTextSize(b3.getHeight() / 4);
                b9.setEnabled(false);
                break;
            case R.id.button10:
                if(round==0){
                    b10.setText("X");
                    round++;
                }
                else{
                    b10.setText("O");
                    round--;
                }
                msg.setText("Teraz kolej gracza: "+player[round]);
                b10.setTextSize(b3.getHeight()/4);
                b10.setEnabled(false);
                break;
        }
        Resoults();
    }
}