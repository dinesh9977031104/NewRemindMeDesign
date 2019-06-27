package com.example.newremindmedesign.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.newremindmedesign.Adapter.RecyclerAdapterProvider;
import com.example.newremindmedesign.Adapter.SpinnerManager;
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


   /* @BindView(R.id.ivFullScreenImage)
    ImageView ivFullScreenImage;*/

    private int mYearForDueDate;
    private int mMonthForDueDate;
    private int mDayForDueDate;


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


    private int[] categoryIconList = new int[]{
            R.drawable.category_electricity,
            R.drawable.category_insurance,
            R.drawable.category_landline,
            R.drawable.category_gas,
            R.drawable.category_water,
            R.drawable.category_pluse};

    private String[] categoryNameList = new String[]{
            "Electricity",
            "Insurance",
            "Landline",
            "Gas",
            "Water",
            "Add Custom Category"};


    private int[] electricityProviderIcon = new int[]{
            R.drawable.electricity_tp_ajmer,
            R.drawable.electricity_mp_rural,
            R.drawable.electricity_chamundeshwari,
            R.drawable.electricity_adani,
            R.drawable.electricity_india_power,
            R.drawable.electricity_bikaner,
            R.drawable.electricity_new_delhi,
            R.drawable.electricity_punjab_state};

    private String[] electricityProviderName = new String[]{
            "Ajmer Vidhyut Vitaran Nigam LTD",
            "MP Madhya Kshetra(Rural)",
            "Chamundeshwari Electricity",
            "Adani Electricity",
            "India Power Electricity",
            "Bikaner Electricity Provider",
            "New Delhi Electricity",
            "Punjab Electricity"};


    private int[] landlineProviderIcon = new int[]{
            R.drawable.landline_airtel,
            R.drawable.landline_bsnl,
            R.drawable.landline_docomo,
            R.drawable.landline_mtnl,
            R.drawable.landline_mtnl,
            R.drawable.landline_reliance};

    private String[] landlineProviderName = new String[]{
            "Airtel",
            "BSNL",
            "TATA Docomo",
            "MTNL delhi",
            "MTNL Mumbai",
            "Reliance"};


    private int[] gasProviderIcon = new int[]{
            R.drawable.gas_aavantika,
            R.drawable.gas_adani,
            R.drawable.gas_centralup,
            R.drawable.gas_charotargas,
            R.drawable.gas_gail,
            R.drawable.gas_gujaratgas,
            R.drawable.gas_haryanacity,
            R.drawable.gas_indianoil};

    private String[] gasProviderName = new String[]{
            "Aavantika Gas",
            "Adani Gas",
            "Central UP Gas",
            "Charottar Gas",
            "GAIL Gas",
            "Gujarat Gas",
            "Haryana City Gas",
            "Indian Oil Gas"};

    private int[] waterProviderIcon = new int[]{
            R.drawable.water_bangalore,
            R.drawable.water_bhopal,
            R.drawable.water_delhi,
            R.drawable.water_gwalior,
            R.drawable.water_haryana,
            R.drawable.water_indore,
            R.drawable.water_jabalpur,
            R.drawable.water_ujjain};

    private String[] waterProviderName = new String[]{
            "Bangalore Water Provider",
            "Bhopal Nagar Nigam",
            "Delhi Water Providers",
            "Gwalior Nagar Nigam",
            "Haryana Jal Board",
            "Indore Nagar Nigam",
            "Jabalpur Nagar Nigam",
            "Ujjain Nagar Nigam"};


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

    private int[] lifeInsuranceIcon = new int[]{
            R.drawable.health_bajaj,
            R.drawable.health_bharti,
            R.drawable.health_cholamandalam,
            R.drawable.health_cigna,
            R.drawable.health_future};

    private String[] lifeInusranceName = new String[]{
            "Bajaj Life Insurance",
            "Bharti AXA Life Insurance",
            "Cholamandalam Life Insurance",
            "Cigna Life Insurance",
            "Future India Life Insurance"};

    private int[] healthInsuranceIcon = new int[]{
            R.drawable.health_bajaj,
            R.drawable.health_bharti,
            R.drawable.health_cholamandalam,
            R.drawable.health_cigna,
            R.drawable.health_future};

    private String[] healthInsuranceName = new String[]{
            "Bajaj Health Insurance",
            "Bharti AXA Health Insurance",
            "Cholamandalam Health Insurance",
            "Cigna Health Insurance",
            "Future India Health Insurance"};

    private int[] bikeInsuranceIcon = new int[]{
            R.drawable.bike_bajaj,
            R.drawable.bike_bharti,
            R.drawable.bike_hdfc,
            R.drawable.bike_iffco,
            R.drawable.bike_liberty};

    private String[] bikeInsuranceName = new String[]{
            "Bajaj Alias Bike Insurance",
            "Bharti AXA Bike Insurance",
            "HDFC Bike Insurance",
            "Iffco Tokio Bike Insurance",
            "Liberty Bike Insurance"};

    private int[] carInsuranceIcon = new int[]{
            R.drawable.car_bajaj,
            R.drawable.car_bharti,
            R.drawable.car_cholamandalam,
            R.drawable.car_future,
            R.drawable.car_hdfc};

    private String[] carInsuranceName = new String[]{
            "Bajaj Car Insurance",
            "Bharti AXA Car Insurance",
            "Cholamandalam Car Insurance",
            "Future India Car Insurance",
            "HDFC Car Insurance"};


    private int[] termInsuranceIcon = new int[]{
            R.drawable.term_aegon,
            R.drawable.term_aviva,
            R.drawable.term_bajaj,
            R.drawable.term_bharti,
            R.drawable.term_birla};

    private String[] termInsuranceName = new String[]{
            "Aegon Term Insurance",
            "Aviva Term Insurance",
            "Bajaj Term Insurance",
            "Bharti Axa Term Insurance",
            "Birla Sun Life Term Insurance"};

    boolean clicked = false;
    String[] listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        ButterKnife.bind(this);
        listItems = getResources().getStringArray(R.array.notification_type);

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

       // ivFullScreenImage.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.relative_layout_view_category:
                showCategoryLayout();
                break;

            case R.id.relative_layout_view_payment:
                showPaymentOptionsLayout();
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

           /* case R.id.ivFullScreenImage:
                final Dialog dialog = new Dialog(ActivityAddReminder.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.row_full_screen_image);
                dialog.setTitle("My OTP");
                Drawable drawable = ((ImageView) v).getDrawable();
                ((ImageView) dialog.findViewById(R.id.iv_full_image)).setImageDrawable(drawable);
                ImageView ivProfileDialogCancle = (ImageView) dialog.findViewById(R.id.iv_profile_dialog_cancel);
                ivProfileDialogCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
                break;*/
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                showReminderTypeLayout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showNoteDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_note_layout);

        final Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        final Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etNote = (EditText) dialog.findViewById(R.id.et_note);
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

    private void showElectricityDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_electricity_bill_layout);
        final TextView textView = (TextView) dialog.findViewById(R.id.tv_provider);
        final Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
        final Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);

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

    private void showInsuranceDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_insurance_layout);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);

        String insurance;
        String[] insuranceList;
        Spinner spInsurance = (Spinner) dialog.findViewById(R.id.sp_insurance);

        insuranceList = getResources().getStringArray(R.array.insurance_list);
        spInsurance.setAdapter(SpinnerManager.setSpinner(this, insuranceList));

        spInsurance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        showLifeInsuranceList();
                        break;
                    case 2:
                        showHealthInsuranceProviderList();
                        break;
                    case 3:
                        showBikeInsuranceList();
                        break;
                    case 4:
                        showCarInsuranceList();
                        break;
                    case 5:
                        showTermInsuranceList();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    private void showReminderTypeLayout() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_reminder_type);
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


    private void showLandlineDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_landline_layout);
        TextView tvLandlineProvider = (TextView) dialog.findViewById(R.id.tv_landline_provider);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);

        tvLandlineProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLandlineProviderDialog();
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

    private void showGasDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_gas_layout);
        TextView tvGasProvider = (TextView) dialog.findViewById(R.id.tv_gas_provider);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);

        tvGasProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGasProviderDialog();
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

    private void showWaterBillDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_water_bill_layout);
        TextView tvWaterProvider = (TextView) dialog.findViewById(R.id.tv_water_provide);
        Button btnAdd = (Button) dialog.findViewById(R.id.btn_add);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);

        tvWaterProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWaterProviderDialog();
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


    private void showElectricityProviderDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        ArrayList<RecyclerModel> electricityProviderList = null;
        electricityProviderList = addElectricityProviders();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, electricityProviderList);
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

    private ArrayList<RecyclerModel> addElectricityProviders() {
        ArrayList<RecyclerModel> list = new ArrayList<>();
        for (int i = 0; i < electricityProviderName.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(electricityProviderName[i]);
            recyclerModel.setImage(electricityProviderIcon[i]);
            list.add(recyclerModel);
        }

        return list;
    }


    private void showLandlineProviderDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        ArrayList<RecyclerModel> landlineProviderList = null;
        landlineProviderList = addLandlindeProvider();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, landlineProviderList);
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

    private ArrayList<RecyclerModel> addLandlindeProvider() {
        ArrayList<RecyclerModel> list = new ArrayList<>();
        for (int i = 0; i < landlineProviderName.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(landlineProviderName[i]);
            recyclerModel.setImage(landlineProviderIcon[i]);
            list.add(recyclerModel);
        }

        return list;
    }


    private void showGasProviderDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        ArrayList<RecyclerModel> gasProviderList = null;
        gasProviderList = addGasProvider();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, gasProviderList);
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

    private ArrayList<RecyclerModel> addGasProvider() {
        ArrayList<RecyclerModel> list = new ArrayList<>();
        for (int i = 0; i < gasProviderName.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(gasProviderName[i]);
            recyclerModel.setImage(gasProviderIcon[i]);
            list.add(recyclerModel);
        }

        return list;
    }


    private void showWaterProviderDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        ArrayList<RecyclerModel> waterProviderList = null;
        waterProviderList = addWaterProviders();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, waterProviderList);
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

    private ArrayList<RecyclerModel> addWaterProviders() {
        ArrayList<RecyclerModel> list = new ArrayList<>();
        for (int i = 0; i < waterProviderName.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(waterProviderName[i]);
            recyclerModel.setImage(waterProviderIcon[i]);
            list.add(recyclerModel);
        }

        return list;
    }


    //----------------------------------payment options dialog-------------------------------------
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

    private void showLifeInsuranceList() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        ArrayList<RecyclerModel> lifeInsuranceProviderList = null;
        lifeInsuranceProviderList = addLifeInsuranceList();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, lifeInsuranceProviderList);
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

    private ArrayList<RecyclerModel> addLifeInsuranceList() {
        ArrayList<RecyclerModel> list = new ArrayList<>();
        for (int i = 0; i < lifeInusranceName.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(lifeInusranceName[i]);
            recyclerModel.setImage(lifeInsuranceIcon[i]);
            list.add(recyclerModel);
        }

        return list;
    }

    private void showHealthInsuranceProviderList() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        ArrayList<RecyclerModel> healthInsuranceList = null;
        healthInsuranceList = addHealthInsuranceList();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, healthInsuranceList);
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

    private ArrayList<RecyclerModel> addHealthInsuranceList() {
        ArrayList<RecyclerModel> list = new ArrayList<>();
        for (int i = 0; i < healthInsuranceName.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(healthInsuranceName[i]);
            recyclerModel.setImage(healthInsuranceIcon[i]);
            list.add(recyclerModel);
        }

        return list;
    }

    private void showBikeInsuranceList() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        ArrayList<RecyclerModel> bikeInsuranceList = null;
        bikeInsuranceList = addBikeInsuranceList();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, bikeInsuranceList);
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

    private ArrayList<RecyclerModel> addBikeInsuranceList() {
        ArrayList<RecyclerModel> list = new ArrayList<>();
        for (int i = 0; i < bikeInsuranceName.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(bikeInsuranceName[i]);
            recyclerModel.setImage(bikeInsuranceIcon[i]);
            list.add(recyclerModel);
        }

        return list;
    }

    private void showCarInsuranceList() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        ArrayList<RecyclerModel> carInsuranceList = null;
        carInsuranceList = addCarInsuranceList();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, carInsuranceList);
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

    private ArrayList<RecyclerModel> addCarInsuranceList() {
        ArrayList<RecyclerModel> list = new ArrayList<>();
        for (int i = 0; i < carInsuranceName.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(carInsuranceName[i]);
            recyclerModel.setImage(carInsuranceIcon[i]);
            list.add(recyclerModel);
        }

        return list;
    }

    private void showTermInsuranceList() {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.row_recycler_view_layout);
        ArrayList<RecyclerModel> termInsuranceList = null;
        termInsuranceList = addTermInsuranceList();
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view_provider);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapterProvider rvAdapter = new RecyclerAdapterProvider(this, termInsuranceList);
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

    private ArrayList<RecyclerModel> addTermInsuranceList() {
        ArrayList<RecyclerModel> list = new ArrayList<>();
        for (int i = 0; i < termInsuranceName.length; i++) {
            RecyclerModel recyclerModel = new RecyclerModel();
            recyclerModel.setName(termInsuranceName[i]);
            recyclerModel.setImage(termInsuranceIcon[i]);
            list.add(recyclerModel);
        }

        return list;
    }
}


