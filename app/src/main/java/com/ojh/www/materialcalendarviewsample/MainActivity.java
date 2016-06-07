package com.ojh.www.materialcalendarviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    CalendarDay[] mCalendarDays = new CalendarDay[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btnStart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("start");
            }
        });

        btn = (Button) findViewById(R.id.btnEnd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("end");
            }
        });
    }

    private void showDialog(String tag) {

        DateDialog dialog = null;
        if(tag.equals("start")) {
            dialog = new DateDialog(mCalendarDays[0]);
        } else if(tag.equals("end")) {
            dialog = new DateDialog(mCalendarDays[1]);
        }

        dialog.setCancelable(false);
        dialog.show(getFragmentManager(), tag);
        dialog.setOnDialogClickListener(new DateDialog.OnDialogClickListener() {
            @Override
            public void onClickOk(String tag, CalendarDay calendarDay) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy년MM월dd일", Locale.KOREA);
                String date = formatter.format(calendarDay.getDate());

                if(tag.equals("start")) {
                    mCalendarDays[0]=calendarDay;
                    ((TextView)findViewById(R.id.tvStart)).setText(date);

                } else if(tag.equals("end")) {
                    mCalendarDays[1]=calendarDay;
                    ((TextView)findViewById(R.id.tvEnd)).setText(date);
                }
            }
        });
    }
}
