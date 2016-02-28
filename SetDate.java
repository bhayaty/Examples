package com.bhayat.resttest;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Creates an OnFocusChangeListener for an EditText control that displays
 * a DatePicker Control letting the user select the date to set.
 * The resultant date is formatted into a string showing dd/MM/yyyy
 * and populated as the value of the EditText.
 *
 * The class implements the View.OnFocusChangeListener for determining
 * when the EditText has gained focus, and the DatePickerDialog.OnDateSetListener
 * to get the Date selected by the user input.
 *
 * @author Yusuf Bhayat
 * @version 1.0
 * @since 2016-02-27
 * @see android.view.View.OnFocusChangeListener
 * @see android.app.TimePickerDialog.OnTimeSetListener
 * @see java.util.Calendar
 */
class SetDate implements View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener {

    private EditText editText;
    private Calendar myCalendar;    //java.util.Calendar Object used to store date
    private Context ctx;

    /**
     * SetDate Constructor which sets the EditText control that will be manipulated
     * as well as the Context of the EditText control.  It also initialises the Date
     * object that will get and hold the time as well as setting the OnFocusChangeListener.
     *
     * @param editText  The EditText control to which we want to bind out listener
     * @param context   The context of the EditTextControl, which will be used to the launch
     *                  the TimePicker control
     */
    public SetDate(EditText editText, Context context){
        this.editText = editText;
        this.ctx = context;
        this.editText.setOnFocusChangeListener(this);
        myCalendar = Calendar.getInstance();    //Creates new instance of Calendar with current date
    }

    /**
     * Implementation of the Abstract method onDateSet for a DatePickerDialog
     *
     * Gets the Date set by user using the DatePicker dialog and adds the values to the
     * myCalendar object.  It then sets the text of the control to that of the selected date.
     *
     * @param view          The DatePicker view that is returning the value
     * @param year          The year value chosen by the user
     * @param monthOfYear   The month value as chosen by the user
     * @param dayOfMonth    The day value as chosen by the user
     * @see android.app.DatePickerDialog.OnDateSetListener
     */
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)     {

        String myFormat = "dd/MM/yyyy"; //Format of date to display in EditText
        SimpleDateFormat sdformat = new SimpleDateFormat(myFormat, Locale.UK);
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        editText.setText(sdformat.format(myCalendar.getTime()));
    }

    /**
     * Displays the Date Picker dialog when the focus is set to the EditText control
     *
     * @param view  The view that raised the event
     * @param hasFocus  Determines if the control currently has focus
     */
    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        //If we currently have focus on the EditText then display the
        //DatePickerDialog, setting the Year, Mnth, and Day to match what is
        //currently in the EditText (or today's date)
        if(hasFocus){
            new DatePickerDialog(ctx, this, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }
}
