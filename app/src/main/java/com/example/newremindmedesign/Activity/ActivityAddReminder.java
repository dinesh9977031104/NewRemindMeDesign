package com.example.newremindmedesign.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.newremindmedesign.Adapter.RecyclerAdapterCategory;
import com.example.newremindmedesign.Adapter.RecyclerAdapterPaymentOptions;
import com.example.newremindmedesign.Adapter.RecyclerAdapterProvider;
import com.example.newremindmedesign.Model.RecyclerModel;
import com.example.newremindmedesign.R;
import com.example.newremindmedesign.Utils.RecyclerViewClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityAddReminder extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_layout_show)
    ImageView ivLayoutShow;
    @BindView(R.id.iv_layout_hide)
    ImageView ivLayoutHide;
    @BindView(R.id.relative_layout_view_category)
    RelativeLayout relativeLayoutViewCategory;
    @BindView(R.id.relative_layout_view_payment)
    RelativeLayout relativeLayoutViewPayment;
    @BindView(R.id.layout_categories)
    RelativeLayout layoutCategories;
    @BindView(R.id.iv_layout_show2)
    ImageView ivLayoutShow2;
    @BindView(R.id.iv_layout_hide2)
    ImageView ivLayoutHide2;
    @BindView(R.id.layout_payment_options)
    RelativeLayout layoutPaymentOptions;
    @BindView(R.id.tv_view_category)
    TextView tvViewCategory;
    @BindView(R.id.tv_select_category)
    TextView tvSelectCategory;
    @BindView(R.id.tv_view_payment_option)
    TextView tvViewPaymentOption;
    @BindView(R.id.tv_select_payment_option)
    TextView tvSelectPaymentOption;
    @BindView(R.id.tv_view_all)
    TextView tvViewAll;
    @BindView(R.id.layout_view_all)
    RelativeLayout layoutViewAll;
    @BindView(R.id.tv_notifi_in_adv)
    TextView tvNotifyInAdv;
    @BindView(R.id.tv_repeat)
    TextView tvRepeat;
    @BindView(R.id.linear_layout_notify)
    LinearLayout linearLayoutNotify;
    @BindView(R.id.linear_layout_repeat)
    LinearLayout linearLayoutRepeat;
    @BindView(R.id.relative_layout_date)
    RelativeLayout relativeLayoutDate;
    @BindView(R.id.relative_layout_time)
    RelativeLayout relativeLayoutTime;
    @BindView(R.id.tv_alarm_date)
    TextView tvAlarmDate;
    @BindView(R.id.tv_alarm_time)
    TextView tvAlarmTime;
    @BindView(R.id.tv_note)
    TextView tvNote;
    @BindView(R.id.iv_added_category)
    ImageView ivAddedCategory;
    @BindView(R.id.card_view_added_category)
    CardView cardViewAddedCategory;
    @BindView(R.id.iv_added_payment_options)
    ImageView ivAddedPaymentOptions;
    @BindView(R.id.card_view_added_payment_options)
    CardView cardViewAddedPaymentOptions;
    @BindView(R.id.relative_layout_view_all)
    RelativeLayout relativeLayoutViewAll;
    @BindView(R.id.iv_view_all_one)
    ImageView ivViewAllOne;
    @BindView(R.id.iv_view_all_two)
    ImageView ivViewAllTwo;
    @BindView(R.id.tv_view_all_hide)
    TextView tvViewAllHide;
    @BindView(R.id.linear_layout_note)
    LinearLayout linearLayoutNote;
    @BindView(R.id.tv_note_set)
    TextView tvNoteSet;


    private int mYearForDueDate;
    private int mMonthForDueDate;
    private int mDayForDueDate;

    private RecyclerView recyclerView, recyclerViewCategory;
    private ArrayList<RecyclerModel> paymentOptionList, categoryList;
    private RecyclerAdapterPaymentOptions adapter;
    private RecyclerAdapterCategory adapterCategory;

    private int[] paymentIconList = new int[]{R.drawable.payment_option_credit_card, R.drawable.payment_option_debit_card, R.drawable.payment_option_net_banking,
            R.drawable.payment_option_cash, R.drawable.payment_option_check};

    private String[] paymentTypeList = new String[]{"Credit Card", "Debit Card", "NetBanking", "Cash", "Check"};


    private int[] categoryIconList = new int[]{R.drawable.category_electricity, R.drawable.category_insurance, R.drawable.category_landline,
            R.drawable.category_gas, R.drawable.category_water, R.drawable.category_custom};

    private String[] categoryNameList = new String[]{"Electricity", "Insurance", "Landline", "Gas", "Water", "Custom"};


    boolean clicked = false;
    String[] listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        ButterKnife.bind(this);


        listItems = getResources().getStringArray(R.array.notification_type);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerViewCategory = (RecyclerView) findViewById(R.id.recycler_category);

        setRecyclerItemList();
        setOnClickListener();
        setToolbar(toolbar, getResources().getString(R.string.title_add_reminder));
    }

    protected void setToolbar(Toolbar toolBar, String title) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setTitle(title);
    }

    private void setOnClickListener() {
        relativeLayoutViewAll.setOnClickListener(this);
        linearLayoutNotify.setOnClickListener(this);
        linearLayoutRepeat.setOnClickListener(this);
        relativeLayoutDate.setOnClickListener(this);
        relativeLayoutTime.setOnClickListener(this);
        linearLayoutNote.setOnClickListener(this);
        relativeLayoutViewCategory.setOnClickListener(this);
        relativeLayoutViewPayment.setOnClickListener(this);
        ivAddedCategory.setOnClickListener(this);
        ivAddedPaymentOptions.setOnClickListener(this);

    }


    private void setRecyclerItemList() {
        //------------------------------------payment options----------------
        paymentOptionList = addItem();
        adapter = new RecyclerAdapterPaymentOptions(this, paymentOptionList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener.RecyclerTouchListener(this, recyclerView, new RecyclerViewClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 0:
                        showCreditCardDialog();
                        break;
                    case 1:
                        showCreditCardDialog();
                        break;
                    case 2:
                        showNetbankingDialog();
                        break;
                    case 3:
                        showCashDialog();
                        break;
                    case 4:
                        showCashDialog();
                        break;

                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //--------------------category-------------------
        categoryList = addCategoryItem();
        adapterCategory = new RecyclerAdapterCategory(this, categoryList);
        recyclerViewCategory.setAdapter(adapterCategory);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        recyclerViewCategory.addOnItemTouchListener(new RecyclerViewClickListener.RecyclerTouchListener(this, recyclerViewCategory, new RecyclerViewClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                switch (position) {
                    case 0:
                        showElectricityDialog();
                        break;
                    case 1:
                        showInsuranceDialog();
                        break;
                    case 2:
                        showLandlineDialog();
                        break;
                    case 3:
                        showGasDialog();
                        break;
                    case 4:
                        showWaterBillDialog();
                        break;
                    case 5:
                        showCustomCategoryDialog();
                        break;

                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private ArrayList<RecyclerModel> addItem() {

        ArrayList<RecyclerModel> list = new ArrayList<>();

        for (int i = 0; i < paymentTypeList.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(paymentTypeList[i]);
            recyclerModel.setImage_drawable(paymentIconList[i]);
            list.add(recyclerModel);
        }

        return list;
    }


    private ArrayList<RecyclerModel> addCategoryItem() {
        ArrayList<RecyclerModel> list = new ArrayList<>();
        for (int i = 0; i < categoryNameList.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(categoryNameList[i]);
            recyclerModel.setImage_drawable(categoryIconList[i]);
            list.add(recyclerModel);
        }

        return list;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.relative_layout_view_category:
                if (clicked) {
                    layoutCategories.setVisibility(View.GONE);
                    ivLayoutHide.setVisibility(View.GONE);
                    ivLayoutShow.setVisibility(View.VISIBLE);
                    tvSelectCategory.setVisibility(View.GONE);
                    tvViewCategory.setVisibility(View.VISIBLE);
                    clicked = false;
                } else {
                    layoutCategories.setVisibility(View.VISIBLE);
                    ivLayoutHide.setVisibility(View.VISIBLE);
                    ivLayoutShow.setVisibility(View.GONE);
                    tvSelectCategory.setVisibility(View.VISIBLE);
                    tvViewCategory.setVisibility(View.GONE);
                    clicked = true;
                }
                break;

            case R.id.relative_layout_view_payment:
                if (clicked) {
                    layoutPaymentOptions.setVisibility(View.GONE);
                    ivLayoutHide2.setVisibility(View.GONE);
                    ivLayoutShow2.setVisibility(View.VISIBLE);
                    tvSelectPaymentOption.setVisibility(View.GONE);
                    tvViewPaymentOption.setVisibility(View.VISIBLE);
                    clicked = false;
                } else {
                    layoutPaymentOptions.setVisibility(View.VISIBLE);
                    ivLayoutHide2.setVisibility(View.VISIBLE);
                    ivLayoutShow2.setVisibility(View.GONE);
                    tvSelectPaymentOption.setVisibility(View.VISIBLE);
                    tvViewPaymentOption.setVisibility(View.GONE);
                    clicked = true;
                }
                break;

            case R.id.relative_layout_view_all:
                if (clicked) {
                    layoutViewAll.setVisibility(View.GONE);
                    ivViewAllOne.setVisibility(View.VISIBLE);
                    ivViewAllTwo.setVisibility(View.GONE);
                    tvViewAll.setVisibility(View.VISIBLE);
                    tvViewAllHide.setVisibility(View.GONE);
                    clicked = false;
                } else {
                    layoutViewAll.setVisibility(View.VISIBLE);
                    ivViewAllOne.setVisibility(View.GONE);
                    ivViewAllTwo.setVisibility(View.VISIBLE);
                    tvViewAll.setVisibility(View.GONE);
                    tvViewAllHide.setVisibility(View.VISIBLE);
                    clicked = true;
                }
                break;


            case R.id.linear_layout_notify:
                showNotifiInAdvDialog();
                break;

            case R.id.linear_layout_repeat:
                showRepeatType();
                break;

            case R.id.relative_layout_date:
                setAlarmDate();
                break;

            case R.id.relative_layout_time:
                setAlarmTime();
                break;

            case R.id.linear_layout_note:
                showNoteDialog();
                break;

            case R.id.iv_added_category:
                if (clicked) {
                    cardViewAddedCategory.setVisibility(View.GONE);
                    clicked = false;
                } else {
                    cardViewAddedCategory.setVisibility(View.VISIBLE);
                    clicked = true;
                }
                break;

            case R.id.iv_added_payment_options:
                if (clicked) {
                    cardViewAddedPaymentOptions.setVisibility(View.GONE);
                    clicked = false;
                } else {
                    cardViewAddedPaymentOptions.setVisibility(View.VISIBLE);
                    clicked = true;
                }
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_reminders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            case R.id.action_check:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showNoteDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_note_layout);

        final Button btnCancel = (Button)dialog.findViewById(R.id.btn_cancel);
        final Button btnAdd = (Button)dialog.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             EditText etNote = (EditText)dialog.findViewById(R.id.et_note);
             String note = etNote.getText().toString();
             dialog.dismiss();
             tvNoteSet.setText(note);
            }
        });

        dialog.show();
    }

    private void showNotifiInAdvDialog() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(ActivityAddReminder.this);
        mBuilder.setTitle("Choose an item");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvNotifyInAdv.setText(listItems[i]);
                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void showRepeatType() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_repeat_type_layout);
        final int TAB_DAILY = 0;
        final int TAB_WEEKLY = 1;
        final int TAB_MONTHLY = 2;
        final int TAB_YEARLY = 3;
        final int SELECTED_TAB = 0;
        final TabLayout tabLayout = (TabLayout) dialog.findViewById(R.id.tablayout);
        final LinearLayout layoutDays = (LinearLayout) dialog.findViewById(R.id.layout_days);
        final TextView tvRepeatInterval = (TextView) dialog.findViewById(R.id.tv_repeat_interval);
        final Spinner spinList = (Spinner) dialog.findViewById(R.id.spin_list);
        final TextView tvEndDate = (TextView) dialog.findViewById(R.id.tv_end_date);
        final LinearLayout layoutRepeatCount = (LinearLayout) dialog.findViewById(R.id.layout_repeat_count);
        final TextView tvRepeatCount = (TextView) dialog.findViewById(R.id.tv_repeat_count);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int repeatInterval = 1;
                String INTERVAL_TYPE = "Day";
                layoutDays.setVisibility(View.GONE);
                repeatInterval = 1;
                tvRepeatInterval.setText(getResources().getString(R.string.repeat_every) + repeatInterval + " " + INTERVAL_TYPE);
                spinList.setSelection(0);
                tvEndDate.setVisibility(View.GONE);
                layoutRepeatCount.setVisibility(View.GONE);
                tvRepeatCount.setText(getResources().getString(R.string.one_times));
                switch (tab.getPosition()) {
                    case TAB_DAILY:
                        INTERVAL_TYPE = getResources().getString(R.string.day);
                        break;
                    case TAB_WEEKLY:
                        layoutDays.setVisibility(View.VISIBLE);
                        INTERVAL_TYPE = getResources().getString(R.string.week);
                        break;
                    case TAB_MONTHLY:
                        INTERVAL_TYPE = getResources().getString(R.string.month);
                        break;
                    case TAB_YEARLY:
                        INTERVAL_TYPE = getResources().getString(R.string.year);
                        break;
                }
                tvRepeatInterval.setText(getResources().getString(R.string.repeat_every) + repeatInterval + " " + INTERVAL_TYPE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        dialog.show();
    }


    public void setAlarmDate() {
        if (mYearForDueDate == 0) {
            final Calendar c = Calendar.getInstance();
            mYearForDueDate = c.get(Calendar.YEAR);
            mMonthForDueDate = c.get(Calendar.MONTH);
            mDayForDueDate = c.get(Calendar.DAY_OF_MONTH);
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year,
                                          final int monthOfYear, final int dayOfMonth) {
                        mYearForDueDate = year;
                        mMonthForDueDate = monthOfYear;
                        mDayForDueDate = dayOfMonth;
                        String strDate = mYearForDueDate + "-" + (mMonthForDueDate + 1) + "-" + mDayForDueDate;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date date = format.parse(strDate);
                            tvAlarmDate.setText(format.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, mYearForDueDate, mMonthForDueDate, mDayForDueDate);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        datePickerDialog.setTitle("");
        datePickerDialog.show();

    }

    private void setAlarmTime() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(ActivityAddReminder.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvAlarmTime.setText(selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void showElectricityDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_electricity_bill_layout);
        final TextView textView = (TextView) dialog.findViewById(R.id.tv_provider);
        final Button btnCancel = (Button)dialog.findViewById(R.id.btn_cancel);
        final Button btnAdd = (Button)dialog.findViewById(R.id.btn_add);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showElectricityProviderDialog();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuccessDialog();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFaildDialog();
            }
        });

        dialog.show();
    }

    private void showInsuranceDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_insurance_layout);
        dialog.show();
    }


    private void showLandlineDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_landline_layout);
        dialog.show();
    }

    private void showGasDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_gas_layout);
        dialog.show();
    }

    private void showWaterBillDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_water_bill_layout);
        dialog.show();
    }

    private void showCustomCategoryDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_custom_category_layout);
        dialog.show();
    }


    private void showElectricityProviderDialog() {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, categoryList);
        recyclerView.setAdapter(rvAdapter);
        dialog.show();
    }

    //----------------------------------payment options dialog-------------------------------------
    private void showCreditCardDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.row_credit_card_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }

    private void showNetbankingDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.row_netbanking_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        final TextView tvSelectBank = dialog.findViewById(R.id.tv_select_bank);
        tvSelectBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBankList();
            }
        });
        dialog.show();
    }


    private void showCashDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_cash_layout);
        dialog.show();
    }

    private void showBankList() {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, paymentOptionList);
        recyclerView.setAdapter(rvAdapter);
        dialog.show();
    }

    private void showSuccessDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_success);
        dialog.show();
    }

    private void showFaildDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_failed);
        dialog.show();
    }
}


