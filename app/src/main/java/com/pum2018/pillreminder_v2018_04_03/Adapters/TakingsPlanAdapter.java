package com.pum2018.pillreminder_v2018_04_03.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pum2018.pillreminder_v2018_04_03.DataModel.TakingsPlan;
import com.pum2018.pillreminder_v2018_04_03.R;
import com.pum2018.pillreminder_v2018_04_03.ScheduleActivity;

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

        TakingsPlanViewForAdapter currentTakingsPlanViewForAdapter = mTakingsPlanViewForAdapters.get(position);


        //Przypisanie wartośi NIE do wyświetlenia:
        currTakingsPlan_id = currentTakingsPlanViewForAdapter.get_id();

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

        //zwrócenie nowego - innego widoku:
        return view;

    }
}
