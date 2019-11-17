package com.example.patrycja.praca_inzynierska;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Klasa odpowiadająca za stworzenie nietypowego Adaptera
 * CustomAdapter został stworzony na przykładzie nastepującego
 * tutoriala/przykładu: https://androidexample.com/How_To_Create_A_Custom_Listview_-_Android_Example/index.php?view=article_discription&aid=67
 */
public class CustomAdapter extends BaseAdapter {
    /**
     * Tablice przechowujące dane elementy: Ocene kursu, numer użytkownika,
     * imie i nazwisko prowadzącego oraz opinie
     */
    private ArrayList<Integer> rate;
    private ArrayList<String> numer_ind, prow, opi;

    /**
     *
     */
    private Context con;
    private static LayoutInflater inflater=null;

    /**
     * Konstruktor klasy zapisując wysłane dane
     * @param context
     * @param rate tablica ocen kursu
     * @param numer tablica numerów uzytkowników
     * @param prowadzacy tablica prowadzących
     * @param opinie tablica opinii
     */
    public CustomAdapter(Context context, ArrayList<Integer> rate, ArrayList<String> numer, ArrayList<String> prowadzacy, ArrayList<String> opinie) {
        this.rate = rate;
        numer_ind = numer;
        prow = prowadzacy;
        opi = opinie;
        con = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Klasa odpowiadająca za TextView
     */
    public class Holder{
        TextView ra;
        TextView num;
        TextView pro;
        TextView op;
    }

    /**
     * Metoda odpowiadająca za długość tablicy z numerami użytkowników
     * @return Długość tablicy
     */
    @Override
    public int getCount() {
        return numer_ind.size();
    }

    /**
     * Metoda zwracająca podaną zmienna
     * @param i zmienna
     * @return zwraca i
     */
    @Override
    public Object getItem(int i) {
        return i;
    }

    /**
     * Metoda zwracająca podany identyfikator
     * @param i zmienna
     * @return zwraca i
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Metoda ustawiająca w odpowiednim rzędzie podane dane
     * @param position numer rzedu gdzie mają być zapisane elementy
     * @param converView widok
     * @param parent rodzic
     * @return Zwraca widok podanego rzędu
     */
    @Override
    public View getView(final int position, View converView, ViewGroup parent){
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.row, null);
        holder.ra = (TextView) rowView.findViewById(R.id.Rate_Review);
        holder.num = (TextView) rowView.findViewById(R.id.Numer_inde);
        holder.pro = (TextView) rowView.findViewById(R.id.Prowadzacy);
        holder.op = (TextView) rowView.findViewById(R.id.Opinia);
        holder.ra.setText("Ocena: " + rate.get(position));
        holder.num.setText("Numer Indeksu: " + numer_ind.get(position));
        holder.pro.setText("Prowadzący: " + prow.get(position));
        holder.op.setText("Opinia: \n" + opi.get(position));

        return rowView;
    }
}
