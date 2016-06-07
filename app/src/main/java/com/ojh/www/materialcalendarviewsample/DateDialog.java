package com.ojh.www.materialcalendarviewsample;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

/**
 * Created by JaeHwan Oh on 2016-06-07.
 */
public class DateDialog extends DialogFragment {

    MaterialCalendarView mcv;

    CalendarDay mCalendarDay;

    DateDialog(CalendarDay calendarDay) {
        mCalendarDay = calendarDay;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.custom_dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        mcv = (MaterialCalendarView)view.findViewById(R.id.calendarView);
        mcv.state().edit()
                .setMinimumDate(CalendarDay.from(2016, 6, 5))
                .setMaximumDate(CalendarDay.from(2016, 12, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        mcv.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);

        mcv.setDateSelected(mCalendarDay, true);
        mcv.setCurrentDate(mCalendarDay);



        Button btn = (Button)view.findViewById(R.id.btnOk);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    mListener.onClickOk(getTag(), mcv.getSelectedDate());
                    dismiss();
                }
            }
        });
        btn = (Button)view.findViewById(R.id.btnCancel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    OnDialogClickListener mListener;

    interface OnDialogClickListener {
        void onClickOk(String tag, CalendarDay calendarDay);
    }

    public void setOnDialogClickListener(OnDialogClickListener listener) {
        mListener = listener;
    }
}
