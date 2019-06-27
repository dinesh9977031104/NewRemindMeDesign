package com.example.newremindmedesign.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.newremindmedesign.Adapter.RecyclerAdapterProvider;
import com.example.newremindmedesign.Adapter.ViewPagerAdapter;
import com.example.newremindmedesign.Fragment.HomeFragment;
import com.example.newremindmedesign.Fragment.OfficeFragment;
import com.example.newremindmedesign.Fragment.OtherFragment;
import com.example.newremindmedesign.Model.RecyclerModel;
import com.example.newremindmedesign.R;
import com.example.newremindmedesign.Utils.RecyclerViewClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R.id.list_view)
    RecyclerView listView;


    private ActionBar actionBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawer;
    private int getPosition;

    public MainActivity() {
    }

    private int[] categoryIconList = new int[]{R.drawable.category_pluse};

    private String[] categoryNameList = new String[]{"Add Custom Category"};

    private int[] paymentIconList = new int[]{
            R.drawable.payment_option_credit_card,
            R.drawable.payment_option_debit_card,
            R.drawable.payment_option_net_banking,
            R.drawable.payment_option_cash,
            R.drawable.payment_option_check};

    private String[] paymentTypeList = new String[]{
            "Credit Card",
            "Debit Card",
            "NetBanking",
            "Cash",
            "Check"};

    private int[] bankIcon = new int[]{
            R.drawable.bank_axis,
            R.drawable.bank_hdfc,
            R.drawable.bank_icici,
            R.drawable.bank_kotak,
            R.drawable.bank_sbi};

    private String[] bankName = new String[]{
            "Axis Bank",
            "HDFC Bank",
            "ICICI Bank",
            "Kotak Mahindra Bank",
            "SBI Bank"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Remind Me");

        bottomNavigationDrawar();

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        Menu menu = navigationView.getMenu();
        MenuItem tools = menu.findItem(R.id.nav_general_setting);
        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.color.colorAccent), 0, s.length(), 0);
        tools.setTitle(s);


        Menu menu2 = navigationView.getMenu();
        MenuItem tools2 = menu2.findItem(R.id.nav_communicate);
        SpannableString s2 = new SpannableString(tools2.getTitle());
        s2.setSpan(new TextAppearanceSpan(this, R.color.colorAccent), 0, s2.length(), 0);
        tools2.setTitle(s2);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new HomeFragment(), getResources().getString(R.string.fragment_home));
        adapter.AddFragment(new OfficeFragment(), getResources().getString(R.string.fragment_office));
        adapter.AddFragment(new OtherFragment(), getResources().getString(R.string.fragment_other));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setOnClickListener() {
        //ivFullScreenImage.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_favorite:
                break;
            case R.id.nav_setting:
                break;
            case R.id.nav_share:
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void bottomNavigationDrawar() {

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent intent = null;
                        switch (item.getItemId()) {
                            case R.id.add_reminder:
                                intent = new Intent(MainActivity.this, ActivityAddReminder.class);
                                startActivity(intent);
                                break;
                            case R.id.custom_category:
                                showCategoryLayout();
                                break;
                            case R.id.add_payment_option:
                                showPaymentOptionsLayout();
                                break;
                        }
                        return true;
                    }
                });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
        }
        return super.onOptionsItemSelected(item);
    }

    private void showCategoryLayout() {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        ArrayList<RecyclerModel> categoryList = null;
        categoryList = addCategoryItems();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, categoryList);
        recyclerView.setAdapter(rvAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener.RecyclerTouchListener(this, recyclerView, new RecyclerViewClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                switch (position) {
                    case 0:
                        showCustomCategoryDialog();
                        break;

                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        dialog.show();
    }

    private ArrayList<RecyclerModel> addCategoryItems() {

        ArrayList<RecyclerModel> list = new ArrayList<>();

        for (int i = 0; i < categoryNameList.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(categoryNameList[i]);
            recyclerModel.setImage(categoryIconList[i]);
            list.add(recyclerModel);
        }

        return list;
    }

    private void showCustomCategoryDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_custom_category_layout);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuccessDialog();
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFaildDialog();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showPaymentOptionsLayout() {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        ArrayList<RecyclerModel> categoryList = null;
        categoryList = addPaymentOptionItems();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, categoryList);
        recyclerView.setAdapter(rvAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener.RecyclerTouchListener(this, recyclerView, new RecyclerViewClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 0:
                        showCreditCardList();
                        break;
                    case 1:
                        showDebitCardList();
                        break;
                    case 2:
                        showNetBankingList();
                        break;
                    case 3:
                        showCasePaymentList();
                        break;
                    case 4:
                        showChequeList();
                        break;

                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        dialog.show();
    }

    private ArrayList<RecyclerModel> addPaymentOptionItems() {

        ArrayList<RecyclerModel> list = new ArrayList<>();

        for (int i = 0; i < paymentTypeList.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(paymentTypeList[i]);
            recyclerModel.setImage(paymentIconList[i]);
            list.add(recyclerModel);
        }

        return list;
    }

    private void showCreditCardList() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_credit_card_list);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btnAddNew = (Button) dialog.findViewById(R.id.btn_add_new);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreditCardDialog();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showDebitCardList() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_debit_card_list);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btnAddNew = (Button) dialog.findViewById(R.id.btn_add_new);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreditCardDialog();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showNetBankingList() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_netbanking_list);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btnAddNew = (Button) dialog.findViewById(R.id.btn_add_new);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  showCreditCardDialog();
                showNetbankingDialog();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void showCasePaymentList() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_cash_list);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btnAddNew = (Button) dialog.findViewById(R.id.btn_add_new);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  showCreditCardDialog();
                showCashDialog();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showChequeList() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_cheque_list);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        Button btnAddNew = (Button) dialog.findViewById(R.id.btn_add_new);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // showCreditCardDialog();
                showCashDialog();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showCreditCardDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.row_credit_card_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuccessDialog();
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFaildDialog();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showNetbankingDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.row_netbanking_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final TextView tvSelectBank = dialog.findViewById(R.id.tv_select_bank);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);

        tvSelectBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBankList();

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuccessDialog();
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFaildDialog();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showCashDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_cash_layout);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuccessDialog();
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFaildDialog();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showBankList() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        ArrayList<RecyclerModel> bankList = null;
        bankList = addBankList();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, bankList);
        recyclerView.setAdapter(rvAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener.RecyclerTouchListener(this, recyclerView, new RecyclerViewClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                dialog.dismiss();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        dialog.show();
    }

    private ArrayList<RecyclerModel> addBankList() {
        ArrayList<RecyclerModel> list = new ArrayList<>();
        for (int i = 0; i < bankName.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(bankName[i]);
            recyclerModel.setImage(bankIcon[i]);
            list.add(recyclerModel);
        }

        return list;
    }

    private void showSuccessDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_success);
        Button btnOK = (Button) dialog.findViewById(R.id.btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showFaildDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_failed);
        Button btnOK = (Button) dialog.findViewById(R.id.btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View v) {

    }
}
