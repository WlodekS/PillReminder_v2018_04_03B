package com.pum2018.pillreminder_v2018_04_03.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.provider.CalendarContract;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pum2018.pillreminder_v2018_04_03.R;

import java.util.ArrayList;

import static com.pum2018.pillreminder_v2018_04_03.Utility.MyUtil.padLZero;


/**
 * Created by Wlodek on 2018-04-10.
 */

public class TakingsPlanAdapter extends BaseAdapter {

    Context mContex;
    ArrayList<TakingsPlanViewForAdapter> mTakingsPlanViewForAdapters;
    LayoutInflater mInflater;

    //Constructor:
    public TakingsPlanAdapter(Context c, ArrayList<TakingsPlanViewForAdapter> takingsPlanViewForAdapters) {
        this.mContex = c;
        this.mTakingsPlanViewForAdapters = takingsPlanViewForAdapters;
        mInflater = (LayoutInflater) mContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    public int getCount() {
        return mTakingsPlanViewForAdapters.size();
    }

    public Object getItem(int position) {
        return mTakingsPlanViewForAdapters.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    /**
     * Clears all items from the data set.
     */
    public void clear() {
        if (this.mTakingsPlanViewForAdapters != null) {
            this.mTakingsPlanViewForAdapters.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * Adds all of the items to the data set.
     * @param items The item list to be added.
     */
    public void addAll(ArrayList<TakingsPlanViewForAdapter> items){
        if(this.mTakingsPlanViewForAdapters == null){
            this.mTakingsPlanViewForAdapters = new ArrayList<>();
        }
        this.mTakingsPlanViewForAdapters.addAll(items);
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_schedule, null);
        //UWAGA!!! - Zacznijmy od przemyslenia ID - jak to pokazać żeby było niewidoczne.
        //Ustalam zmienną która przechowuje -id, ale nie będzie wyświetlana:
        Integer currTakingsPlan_id;

        //Zmienne do dostępu do obiektów ekranowych:

        //TextView textView_id = (TextView)view.findViewById(R.id.txt_medicine_id_check);
        TextView textView_id = (TextView)view.findViewById(R.id.textView_current_ID);

        //Wartości tekstowe:
        //Hour:
        TextView textViewHour = (TextView)view.findViewById(R.id.textView_Schedule_Hour);
        //Dwukropek(Colon):
        TextView textViewColon = (TextView)view.findViewById(R.id.textView_Schedule_Colon);
        //Minute:
        TextView textViewMinute = (TextView)view.findViewById(R.id.textView_Schedule_Minute);
        //Name:
        TextView textViewName = (TextView)view.findViewById(R.id.textView_Schedule_MediceName);
        //Dose:
        TextView textViewDose = (TextView)view.findViewById(R.id.textView_Schedule_Dose);
        //Form:
        TextView textViewForm = (TextView)view.findViewById(R.id.textView_Form);
        //DoseOption:
        TextView textViewDoseOption = (TextView)view.findViewById(R.id.textView_Dose_Option);

        // Aktywne dni tygodnia (Zmienne dostępu):
        TextView textView_Sun = (TextView)view.findViewById(R.id.textView_Sun);
        TextView textView_Mon = (TextView)view.findViewById(R.id.textView_Mon);
        TextView textView_Tue = (TextView)view.findViewById(R.id.textView_Tue);
        TextView textView_Wed = (TextView)view.findViewById(R.id.textView_Wed);
        TextView textView_Thu = (TextView)view.findViewById(R.id.textView_Thu);
        TextView textView_Fri = (TextView)view.findViewById(R.id.textView_Fri);
        TextView textView_Sat = (TextView)view.findViewById(R.id.textView_Sat);




        TakingsPlanViewForAdapter currentTakingsPlanViewForAdapter = mTakingsPlanViewForAdapters.get(position);


        //Przypisanie wartośi do elementu niewidocznego (zachowanie _bieżącego ID:
        //currTakingsPlan_id = currentTakingsPlanViewForAdapter.get_id();
        //currentRecord_ID = currTakingsPlan_id;
        //Przypisanie wartośi niewyświetlanej (bieżące _id rekordu):
        textView_id.setText(currentTakingsPlanViewForAdapter.get_id().toString());
        //Log.i("DB","TakingsPlanAdapter-getView: zapisano ID: " + currentTakingsPlanViewForAdapter.get_id().toString() );

        //Przypisanie wartośi do wyświetlenia:
        //Hour:
        textViewHour.setText(currentTakingsPlanViewForAdapter.getHour().toString());
        //Dwukropek(Colon):
        textViewColon.setText(":");
        //Minute:
        //textViewMinute.setText(currentTakingsPlanViewForAdapter.getMinute().toString());
        textViewMinute.setText(padLZero(currentTakingsPlanViewForAdapter.getMinute().toString()));
        //Name:
        textViewName.setText(currentTakingsPlanViewForAdapter.getMedicineName());
        //Dose:
        textViewDose.setText(currentTakingsPlanViewForAdapter.getDose().toString());
        //Form:
        textViewForm.setText(currentTakingsPlanViewForAdapter.getMedKeyfForm().toString());
        //DoseOption:
        textViewDoseOption.setText(currentTakingsPlanViewForAdapter.getDoseType().toString());
        //textViewDoseOption.setText("AA");

        //Kolorowanie aktywnych dni tygodnia w wierszu ListView:
        //Sunday:
        if (currentTakingsPlanViewForAdapter.getDay_sunday() == 1) {
            textView_Sun.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayActive));
            textView_Sun.setTypeface(textView_Sun.getTypeface(), Typeface.BOLD);

        }else{
            textView_Sun.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayNOActive));
            textView_Sun.setTypeface(textView_Sun.getTypeface(), Typeface.NORMAL);
        }
        //Monday:
        if (currentTakingsPlanViewForAdapter.getDay_monday() == 1) {
            textView_Mon.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayActive));
            textView_Mon.setTypeface(textView_Sun.getTypeface(), Typeface.BOLD);
        }else{
            textView_Mon.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayNOActive));
            textView_Mon.setTypeface(textView_Sun.getTypeface(), Typeface.NORMAL);
        }
        //Tuesday:
        if (currentTakingsPlanViewForAdapter.getDay_tuesday() == 1) {
            textView_Tue.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayActive));
            textView_Tue.setTypeface(textView_Sun.getTypeface(), Typeface.BOLD);
        }else{
            textView_Tue.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayNOActive));
            textView_Tue.setTypeface(textView_Sun.getTypeface(), Typeface.NORMAL);
        }
        //Wednesday:
        if (currentTakingsPlanViewForAdapter.getDay_wednesday() == 1) {
            textView_Wed.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayActive));
            textView_Wed.setTypeface(textView_Sun.getTypeface(), Typeface.BOLD);
        }else{
            textView_Wed.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayNOActive));
            textView_Wed.setTypeface(textView_Sun.getTypeface(), Typeface.NORMAL);
        }
        //Thursday:
        if (currentTakingsPlanViewForAdapter.getDay_thursday() == 1) {
            textView_Thu.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayActive));
            textView_Thu.setTypeface(textView_Sun.getTypeface(), Typeface.BOLD);
        }else{
            textView_Thu.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayNOActive));
            textView_Thu.setTypeface(textView_Sun.getTypeface(), Typeface.NORMAL);
        }
        //Friday:
        if (currentTakingsPlanViewForAdapter.getDay_friday() == 1) {
            textView_Fri.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayActive));
            textView_Fri.setTypeface(textView_Sun.getTypeface(), Typeface.BOLD);
        }else{
            textView_Fri.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayNOActive));
            textView_Fri.setTypeface(textView_Sun.getTypeface(), Typeface.NORMAL);
        }
        //Saturday:
        if (currentTakingsPlanViewForAdapter.getDay_saturday() == 1) {
            textView_Sat.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayActive));
        }else{
            textView_Sat.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayNOActive));
        }


        //zwrócenie nowego - innego widoku:
        return view;

    }
}
