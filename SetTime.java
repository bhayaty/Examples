package com.bhayat.resttest;

import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.Time;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * Creates an OnFocusChangeListener for an EditText control that displays
 * a TimePicker Control letting the user select the time from a 24 Hr
 * clock.  The resultant time is formatted into a string showing HH:mm
 * as per the picker control and populated as the value of the EditText.
 *
 * The class implements the View.OnFocusChangeListener for determining
 * when the EditText has gained focus, and the TimePickerDialog.OnTimeSetListener
 * to get the Time selected by the user input.
 *
 * @author Yusuf Bhayat
 * @version 1.0
 * @since 2016-02-27
 * @see android.view.View.OnFocusChangeListener
 * @see android.app.TimePickerDialog.OnTimeSetListener
 * @see android.text.format.Time
 */
public class SetTime implements View.OnFocusChangeListener, TimePickerDialog.OnTimeSetListener {

    private EditText editText;
    private Context ctx;
    private Time time;  //android.text.format.Time object used to store time

    /**
     * SetTime Constructor which sets the EditText control that will be manipulated
     * as well as the Context of the EditText control.  It also initialises the Time
     * object that will get and hold the time as well as setting the OnFocusChangeListener.
     *
     * @param editText  The EditText control to which we want to bind out listener
     * @param context   The context of the EditTextControl, which will be used to the launch
     *                  the TimePicker control
     */
    public SetTime(EditText editText, Context context) {
        this.editText = editText;
        this.ctx = context;
        this.editText.setOnFocusChangeListener(this);
        this.time = new Time();

        time.setToNow();
    }

    /**
     * Implementation of the Abstract method onTimeSet for a TimePickerDialog
     *
     * Gets the Time set by user using the TimerPicker control and adds the values to the
     * Time object.  It then sets the text of the control to that of the selected time.
     *
     * @param view      The TimePicker view that is returning the value
     * @param hour      The Hour value chosen by the user
     * @param minute    The Mainute value as chosen by the user
     * @see android.app.TimePickerDialog.OnTimeSetListener
     */
    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        time.hour = hour;
        time.minute = minute;
        time.second = 0;
        //time.timezone = "GMT";

        //String timeStr = time.format("%Y:%m:%d %H:%M:%S");
        //Formats time output into Hour and Minutes for display
        editText.setText(time.format("%H:%M"));
    }

    /**
     * Displays the Time Picker dialog when the focus is set to the EditText control
     *
     * @param view  The view that raised the event
     * @param hasFocus  Determines if the control currently has focus
     */
    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        //If we currently have focus on the EditText then display the
        //TimePickerDialog, setting the Hour and Minute to match what is
        //currently in the EditText (or time when activity was started)
        if (hasFocus) {
            new TimePickerDialog(ctx, this,
                    time.hour,
                    time.minute,
                        true).show();
        }
    }
}
