package com.example.patry.androidproject;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;

import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;
import com.example.patry.androidproject.Data.DataTree;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    HorizontalScrollMenuView menu;
    static TextView basket_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        menu = findViewById(R.id.menu);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        new DataTree();
        initMenu();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_layout,new MainFragment(), "MainFragment");
        transaction.commitAllowingStateLoss();
    }

    private void initMenu() {
        menu.addItem("Laptopy", R.drawable.ic_laptop);
        menu.addItem("Telefony", R.drawable.ic_phone);
        menu.addItem("Tablety", R.drawable.ic_tablet);
        menu.addItem("Akcesorie", R.drawable.ic_akcesorie);
        menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem menuItem, int position) {
                Random r = new Random();
                int n = r.nextInt(2);
                //Toast.makeText(MainActivity.this,""+menuItem.getText()+" "+position,Toast.LENGTH_SHORT ).show();
                if(getSupportFragmentManager().getBackStackEntryCount()>0) {
                    getSupportFragmentManager().popBackStack(getSupportFragmentManager().getBackStackEntryAt(0).getId(), getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                }
                if(n==0){
                    List1 lista1= new List1();
                    Bundle args = new Bundle();
                    args.putInt("category",position);
                    lista1.setArguments(args);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.addToBackStack("Lista1");
                    transaction.replace(R.id.fragment_layout,lista1, "Lista1");
                    transaction.commitAllowingStateLoss();
                }else{
                    List2 lista2 = new List2();
                    Bundle args = new Bundle();
                    args.putInt("category",position);
                    lista2.setArguments(args);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.addToBackStack("Lista2");
                    transaction.replace(R.id.fragment_layout,lista2, "Lista2");
                    transaction.commitAllowingStateLoss();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.basket);
        View actionView  = MenuItemCompat.getActionView(menuItem);
        basket_text = actionView.findViewById(R.id.basket_action);
        
        setupBadget();
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }

    public static void setupBadget() {
        if(DataTree.getBasket().size()==0){
            if(basket_text.getVisibility() != View.GONE){
                basket_text.setVisibility(View.GONE);
            }
        }else{
            basket_text.setText(String.valueOf(Math.min(DataTree.getBasket().size(),99)));
            if(basket_text.getVisibility() != View.VISIBLE){
                basket_text.setVisibility(View.VISIBLE);
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mail:
                        Mail mail = new Mail();
                        FragmentTransaction transactionMail = getSupportFragmentManager().beginTransaction();
                        transactionMail.addToBackStack("Mail");
                        transactionMail.replace(R.id.fragment_layout,mail, "Mail");
                        transactionMail.commitAllowingStateLoss();
                        return true;
                    case R.id.favorite:
                        Favorite favorite = new Favorite();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.addToBackStack("Ulubione");
                        transaction.replace(R.id.fragment_layout,favorite, "Ulubione");
                        transaction.commitAllowingStateLoss();
                        return true;
                    case R.id.home:
                        if(getSupportFragmentManager().getBackStackEntryCount()>0) {
                            getSupportFragmentManager().popBackStack(getSupportFragmentManager().getBackStackEntryAt(0).getId(), getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
                        }
                        return true;
                    case R.id.back:
                        onBackPressed();
                        //Toast.makeText(MainActivity.this, "Item back", Toast.LENGTH_SHORT).show();
                        return true;

                }
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Search search = new Search();
                FragmentTransaction transaction_search = getSupportFragmentManager().beginTransaction();
                transaction_search.addToBackStack("Search");
                transaction_search.replace(R.id.fragment_layout,search,"Search");
                transaction_search.commitAllowingStateLoss();
                return true;

            case R.id.search2:
                SearchName searchName = new SearchName();
                FragmentTransaction transaction_search_name = getSupportFragmentManager().beginTransaction();
                transaction_search_name.addToBackStack("Search Name");
                transaction_search_name.replace(R.id.fragment_layout,searchName,"Search Name");
                transaction_search_name.commitAllowingStateLoss();
                return true;

            case R.id.basket:
                Basket basket = new Basket();
                FragmentTransaction transaction_basket = getSupportFragmentManager().beginTransaction();
                transaction_basket.addToBackStack("Koszyk");
                transaction_basket.replace(R.id.fragment_layout,basket, "Koszyk");
                transaction_basket.commitAllowingStateLoss();
                //Toast.makeText(this, "Item basket", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }

    }
}