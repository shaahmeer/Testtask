package com.example.testtask;


import static android.content.ContentValues.TAG;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BlankFragment2 extends Fragment {

    private ImageView black;
    private TextView somed;
    private ImageButton button2;
    private static TextView btc, boosttext,getBtctext;
    private TextView nachat;

    private TextView satoshii;
    private ImageButton pingal;
    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton getbtcc;
    ImageButton imageButton4;
    ImageButton boost;
    private ColorStateList defaultTextColor,defaultTextColor2;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private Runnable resetButtonRunnable;
    private Handler handler;
    DatabaseManager databaseManager;
    private String btcValueString,satoshiValueString;
    private static SharedPreferences sharedpreferences;


    


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank2, container, false);

        button2 = view.findViewById(R.id.subtract);
//       greeen = view.findViewById(R.id.rectangle);
        black = view.findViewById(R.id.green);
        somed = view.findViewById(R.id.somed);
        btc = view.findViewById(R.id.btccount);
        satoshii = view.findViewById(R.id.satoshibalance);
        imageButton1 = view.findViewById(R.id.imagebutton1);
        imageButton2 = view.findViewById(R.id.imagebutton2);
        imageButton3 = view.findViewById(R.id.imagebutton3);
        imageButton4 = view.findViewById(R.id.imagebutton4);
        boosttext = view.findViewById(R.id.bootext);
        boost = view.findViewById(R.id.boost);
        nachat = view.findViewById(R.id.startbuttoncircle);
        timerTextView = view.findViewById(R.id.timerTextView);
        getbtcc = view.findViewById(R.id.getbtc);
        getBtctext = view.findViewById(R.id.textgetbtc);




        buttoncolor();
        serverbutton();
        textchange();
        buttonsetting();
        sqlite();
        return view;



    }



    public void buttonsetting() {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation();


                int currentPercentage = Integer.parseInt(somed.getText().toString().replace("%", ""));
                int newPercentage = currentPercentage + 25;

                String btcValueString = btc.getText().toString().replace(",", ".");
                String satoshiValueString = satoshii.getText().toString().replace("%", "");

                double btcValue = Double.parseDouble(btcValueString);
                int satoshiValue = Integer.parseInt(satoshiValueString);

                btcValue += 0.0000025;
                satoshiValue += 25;


                String formattedBtcValue = String.format("%.8f", btcValue);
                String formattedsatoshiValue = String.format("%,d", satoshiValue);


                button2.setBackgroundColor(Color.parseColor("#A9E141"));


                GradientDrawable drawable = new GradientDrawable();
                drawable.setShape(GradientDrawable.OVAL);
                drawable.setColor(Color.RED);
                button2.setBackground(drawable);


                if (newPercentage <= 100) {
                    somed.setText(newPercentage + "%");
                    btc.setText(formattedBtcValue);
                    satoshii.setText(formattedsatoshiValue);
                    drawable.setColor(Color.GREEN);
                    button2.setBackground(drawable);

                    if (newPercentage == 100) {
                        nachat.setText("СТОП");
                        drawable.setShape(GradientDrawable.OVAL);
                        drawable.setColor(Color.RED);
                        button2.setBackground(drawable);
                        button2.setEnabled(false); // Disable the button

                        countDownTimer = new CountDownTimer(20 * 20 * 90, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                // Calculate remaining time
                                long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60;
                                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60;

                                // Format remaining time as a string
                                String remainingTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);

                                // Update TextView with remaining time
                                timerTextView.setText(remainingTime);

                            }

                            @Override
                            public void onFinish() {
                                button2.setEnabled(true); // Enable the button when the timer finishes
                                timerTextView.setText("00:00:00");
                                if (resetButtonRunnable != null) {
                                    handler.removeCallbacks(resetButtonRunnable); // Remove any previously scheduled reset
                                }

                                resetButtonRunnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        button2.setBackgroundColor(Color.BLUE); // Reset the button color
                                    }
                                };
                            }
                        };

                        countDownTimer.start(); // Start the countdown timer
                    }


                    ViewGroup.LayoutParams layoutParams = black.getLayoutParams();
                    layoutParams.height += 90;
                    black.setLayoutParams(layoutParams);
                } else {

                    somed.setText("100%");


                }
            }


        });


    }


    public void animation() {
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(button2, "scaleX", 1f, 0f);
        scaleXAnimator.setDuration(300);
        scaleXAnimator.setRepeatCount(1);
        scaleXAnimator.setRepeatMode(ValueAnimator.REVERSE);

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(button2, "scaleY", 1f, 0f);
        scaleYAnimator.setDuration(300);
        scaleYAnimator.setRepeatCount(1);
        scaleYAnimator.setRepeatMode(ValueAnimator.REVERSE);

        scaleXAnimator.start();
        scaleYAnimator.start();
    }


    public void buttoncolor() {
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the color of imageButton1

                imageButton1.setColorFilter(Color.parseColor("#A9E141"));

                // Reset the color of other ImageButtons if necessary
                imageButton2.setColorFilter(null);
                imageButton3.setColorFilter(null);
                imageButton4.setColorFilter(null);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the color of imageButton2
                imageButton2.setColorFilter(Color.parseColor("#A9E141"));

                // Reset the color of other ImageButtons if necessary
                imageButton1.setColorFilter(null);
                imageButton3.setColorFilter(null);
                imageButton4.setColorFilter(null);
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the color of imageButton3
                imageButton3.setColorFilter(Color.parseColor("#A9E141"));
                // Reset the color of other ImageButtons if necessary
                imageButton1.setColorFilter(null);
                imageButton2.setColorFilter(null);
                imageButton4.setColorFilter(null);
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change the color of imageButton4
                imageButton4.setColorFilter(Color.parseColor("#A9E141"));

                // Reset the color of other ImageButtons if necessary
                imageButton1.setColorFilter(null);
                imageButton2.setColorFilter(null);
                imageButton3.setColorFilter(null);
            }
        });
    }

    public void serverbutton() {
        boost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boosttext.setTextColor(Color.GREEN);

                GradientDrawable drawable = new GradientDrawable();

                drawable.setColor(Color.GREEN);

                // Array of image buttons
                ImageButton[] imageButtons = {imageButton1, imageButton2, imageButton3, imageButton4};

                // Generate a random index
                Random random = new Random();
                int randomIndex = random.nextInt(imageButtons.length);

                // Select the random image button
                ImageButton randomImageButton = imageButtons[randomIndex];

                // Perform any action on the selected image button
                randomImageButton.setColorFilter(Color.RED);
                // Reset the color of other image buttons if necessary
                for (ImageButton imageButton : imageButtons) {
                    if (imageButton != randomImageButton) {
                        imageButton.setColorFilter(null);
                    }
                }
            }


        });


    }

    public void textchange() {
        defaultTextColor = boosttext.getTextColors();
        defaultTextColor2 = getBtctext.getTextColors();
        boost.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Change text color to red when button is pressed
                    boosttext.setTextColor(Color.RED);


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Revert to default text color when button is released
                    boosttext.setTextColor(defaultTextColor);

                }
                return false;
            }
        });
        getbtcc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Change text color to red when button is pressed
                    getBtctext.setTextColor(Color.RED);


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Revert to default text color when button is released
                    getBtctext.setTextColor(defaultTextColor2);

                }
                return false;
            }
        });
    }



    public void sqlite(){
        getbtcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentmessage();
                try{

                    btcValueString = btc.getText().toString().replace(",", ".");
                    satoshiValueString = satoshii.getText().toString().replace("%", "");

                    Log.e(TAG, "Value BTC 22 : " + btcValueString);
                    Log.e(TAG, "Value of Satoch 22 : " +satoshiValueString);

                    databaseManager = new DatabaseManager(getActivity());

                    databaseManager.open();

                    databaseManager.insertIntoTable(btcValueString, satoshiValueString);



                } catch (Exception e) {

                    Log.e(TAG, "No data found");
                }


//                    LayoutInflater layoutInflater
//                            = (LayoutInflater)getContext()
//                            .getSystemService(LAYOUT_INFLATER_SERVICE);
//                    View popupView = layoutInflater.inflate(R.layout.activity_popup_window, null);
//                    final PopupWindow popupWindow = new PopupWindow(
//                            popupView,
//                            ViewGroup.LayoutParams.WRAP_CONTENT,
//                            ViewGroup.LayoutParams.WRAP_CONTENT);
//
//                    Button btnDismiss = (Button)popupView.findViewById(R.id.share);
//                    btnDismiss.setOnClickListener(new Button.OnClickListener(){
//
//                        @Override
//                        public void onClick(View v) {
//                            // TODO Auto-generated method stub
//                            popupWindow.dismiss();
//                        }});
//
//                    popupWindow.showAsDropDown(getbtcc, 0, -600);

                }});
         }

         public void intentmessage(){
             String btc1 = (String) btc.getText();
             String satoshi = (String) satoshii.getText();
             Intent intent = new Intent(getActivity(), PopupWindowActivity.class);
             intent.putExtra("btc", btc1);
             intent.putExtra("satoshi",satoshi);
             startActivity(intent);
         }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        sqlite();


    }







}




