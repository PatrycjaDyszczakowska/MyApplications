package com.example.patrycja.wisielec;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
/**
 * Aplikacja gdy Wisielect
 * @author  Patrycja Dyszczakowska
 * @version 1.0.4
*/
public class MainActivity extends Activity {
    /**
     * Zmienne używane globalnie w całej klasie
     */
    private TextView text;
    private ImageView board;
    private Button b;
    private TableLayout table;
    private Random generator;
    private String word, view;
    private String[] words, guess;
    private int error = 0;

    /**
     * Metoda onCreate ustawia Layout activity_main
     * oraz pod zmienne text, board, b i table
     * odpowiednie elementy
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generator = new Random();
        text = (TextView)findViewById(R.id.textView);
        board = (ImageView)findViewById(R.id.imageView);
        b = (Button)findViewById(R.id.button);
        table = (TableLayout)findViewById(R.id.table);
    }

    /**
     * Metoda onClick odpowiada co się będzie
     * działo po wciśnięciu danego przycisku na ekranie
     */
    public void onClick(View v){
        switch(v.getId()){
            /**
             * Przycisk losujący slowo z słownika
             */
            case R.id.button:
                view="";
                words = getResources().getStringArray(R.array.words);
                int a = generator.nextInt(words.length);
                word = words[a];
                guess = new String[word.length()];
                for(int i=0; i<word.length(); i++){
                    guess[i]=" _ ";
                    view+=guess[i];
                }
                text.setText(view);
                board.setImageResource(R.drawable.board0);
                b.setEnabled(false);
                table.setVisibility(View.VISIBLE);
                TableRow row1 = (TableRow) findViewById(R.id.row1);
                for ( int i = 0; i < row1.getChildCount();  i++ ){
                    View view1 = row1.getChildAt(i);
                    view1.setEnabled(true);
                }
                TableRow row2 = (TableRow) findViewById(R.id.row2);
                for ( int i = 0; i < row1.getChildCount();  i++ ){
                    View view2 = row2.getChildAt(i);
                    view2.setEnabled(true);
                }
                TableRow row3 = (TableRow) findViewById(R.id.row3);
                for ( int i = 0; i < row1.getChildCount();  i++ ){
                    View view3 = row3.getChildAt(i);
                    view3.setEnabled(true);
                }
                TableRow row4 = (TableRow) findViewById(R.id.row4);
                for ( int i = 0; i < row1.getChildCount();  i++ ){
                    View view4 = row4.getChildAt(i);
                    view4.setEnabled(true);
                }
                error=0;
                break;
            /**
             * Poniższe przyciski odpowiadają co się będzie
             * działo po przyciśnienciu danej litery
             */
            //Wiersz 1
            case R.id.a:
                (findViewById(R.id.a)).setEnabled(false);
                verification("A");
                break;
            case R.id.ą:
                (findViewById(R.id.ą)).setEnabled(false);
                verification("Ą");
                break;
            case R.id.b:
                (findViewById(R.id.b)).setEnabled(false);
                verification("B");
                break;
            case R.id.c:
                (findViewById(R.id.c)).setEnabled(false);
                verification("C");
                break;
            case R.id.ć:
                (findViewById(R.id.ć)).setEnabled(false);
                verification("Ć");
                break;
            case R.id.d:
                (findViewById(R.id.d)).setEnabled(false);
                verification("D");
                break;
            case R.id.e:
                (findViewById(R.id.e)).setEnabled(false);
                verification("E");
                break;
            case R.id.ę:
                (findViewById(R.id.ę)).setEnabled(false);
                verification("Ę");
                break;
            //Wiersz 2
            case R.id.f:
                (findViewById(R.id.f)).setEnabled(false);
                verification("F");
                break;
            case R.id.g:
                (findViewById(R.id.g)).setEnabled(false);
                verification("G");
                break;
            case R.id.h:
                (findViewById(R.id.h)).setEnabled(false);
                verification("H");
                break;
            case R.id.i:
                (findViewById(R.id.i)).setEnabled(false);
                verification("I");
                break;
            case R.id.j:
                (findViewById(R.id.j)).setEnabled(false);
                verification("J");
                break;
            case R.id.k:
                (findViewById(R.id.k)).setEnabled(false);
                verification("K");
                break;
            case R.id.l:
                (findViewById(R.id.l)).setEnabled(false);
                verification("L");
                break;
            case R.id.ł:
                (findViewById(R.id.ł)).setEnabled(false);
                verification("Ł");
                break;
            //Wiersz 3
            case R.id.m:
                (findViewById(R.id.m)).setEnabled(false);
                verification("M");
                break;
            case R.id.n:
                (findViewById(R.id.n)).setEnabled(false);
                verification("N");
                break;
            case R.id.ń:
                (findViewById(R.id.ń)).setEnabled(false);
                verification("Ń");
                break;
            case R.id.o:
                (findViewById(R.id.o)).setEnabled(false);
                verification("O");
                break;
            case R.id.ó:
                (findViewById(R.id.ó)).setEnabled(false);
                verification("Ó");
                break;
            case R.id.p:
                (findViewById(R.id.p)).setEnabled(false);
                verification("P");
                break;
            case R.id.r:
                (findViewById(R.id.r)).setEnabled(false);
                verification("R");
                break;
            case R.id.s:
                (findViewById(R.id.s)).setEnabled(false);
                verification("S");
                break;
            //Wiersz 4
            case R.id.ś:
                (findViewById(R.id.ś)).setEnabled(false);
                verification("Ś");
                break;
            case R.id.t:
                (findViewById(R.id.t)).setEnabled(false);
                verification("T");
                break;
            case R.id.u:
                (findViewById(R.id.u)).setEnabled(false);
                verification("U");
                break;
            case R.id.w:
                (findViewById(R.id.w)).setEnabled(false);
                verification("W");
                break;
            case R.id.y:
                (findViewById(R.id.y)).setEnabled(false);
                verification("Y");
                break;
            case R.id.z:
                (findViewById(R.id.z)).setEnabled(false);
                verification("Z");
                break;
            case R.id.ź:
                (findViewById(R.id.ź)).setEnabled(false);
                verification("Ź");
                break;
            case R.id.ż:
                (findViewById(R.id.ż)).setEnabled(false);
                verification("Ż");
                break;
        }
    }

    /**
     * Metoda sprawdzająca czy wysłana litera znajduje się w wylosowanym słowie
     * @param letter sprawdzana litera czy znajduje sie w wylosowoanym słowie
     */
    public void verification(String letter){
        String check = "neverguess";
        view="";
        /**
         * Sprawdzanie czy Litera znajduję sie przynajmniej raz
         * w wylosowanym słowie jak tak to zmienia sie zmienna
         * check na zgadnietą oraz do tablicy guess wpisuje się
         * w odpowiednim miejscu zgadnienta litere, następnie
         * obojętnie czy zgadnienta czy nie wpisywana jest
         * litera lub znak z tablicy guess do stringa view
         */
        for(int i = 0; i<guess.length; i++){
            if(letter.equals(""+word.charAt(i))){
                guess[i]=letter;
                check = "guess";
            }
            view += guess[i];
        }
        text.setText(view);

        /**
         * Sprawdzanie czy wylosowane słowo jest już zganiente
         * jak nie to sprawdza się czy zgadniento poprawna litere
         * jeśli jej nie ma w wylosowanym słowie to rysowane są kolejne części wisielca
         */
        if(view.equals(word)){
            b.setEnabled(true);
            table.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Udało ci sie zgadnąć :)",
                    Toast.LENGTH_LONG).show();
        }
        else if(error==0 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board1);
        }
        else if(error==1 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board2);
        }
        else if(error==2 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board3);
        }
        else if(error==3 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board4);
        }
        else if(error==4 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board5);
        }
        else if(error==5 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board6);
        }
        else if(error==6 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board7);
        }
        else if(error==7 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board8);
        }
        else if(error==8 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board9);
        }
        else if(error==9 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board10);
        }
        else if(error==10 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board11);
        }
        else if(error==11 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board12);
        }
        else if(error==12 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board13);
        }
        else if(error==13 && check.equals("neverguess")){
            board.setImageResource(R.drawable.board14);
        }
        /**
         * Gdy litera nie znajduje się w wylosowanym słowie sprawdza się czy
         * ilość błędów jest równa 13 jak tak to gracz przegrywa
         * jak nie to zwieksza się liczbe błędów o jeden
         */
        if(check.equals("neverguess")){
            if(error==13){
                b.setEnabled(true);
                table.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Nie udało ci sie zgadnąć :(",
                        Toast.LENGTH_LONG).show();
            }
            else{
                error++;
            }
        }
    }
}
