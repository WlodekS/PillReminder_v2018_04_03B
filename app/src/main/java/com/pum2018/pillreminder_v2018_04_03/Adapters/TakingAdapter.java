package com.pum2018.pillreminder_v2018_04_03.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pum2018.pillreminder_v2018_04_03.DataModel.Taking;
import com.pum2018.pillreminder_v2018_04_03.R;

import java.util.ArrayList;

import static com.pum2018.pillreminder_v2018_04_03.Utility.MyUtil.get_DistanceTime;
import static com.pum2018.pillreminder_v2018_04_03.Utility.MyUtil.padLZero;

/**
 * Created by Wlodek on 2018-04-18.
 */

public class TakingAdapter extends BaseAdapter {

    Context mContex;
    ArrayList<Taking> mTakings;
    LayoutInflater mInflater;

    //Constructor:
    public TakingAdapter(Context c, ArrayList<Taking> takings) {
        this.mContex = c;
        this.mTakings = takings;
        mInflater = (LayoutInflater) mContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mTakings.size();
    }

    @Override
    public Object getItem(int position) {
        return mTakings.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    /**
     * Clears all items from the data set.
     */
    public void clear() {
        if (this.mTakings != null) {
            this.mTakings.clear();
            notifyDataSetChanged();
        }
    }


    /**
     * Adds all of the items to the data set.
     * @param items The item list to be added.
     */
    public void addAll(ArrayList<Taking> items){
        if(this.mTakings == null){
            this.mTakings = new ArrayList<>();
        }
        this.mTakings.addAll(items);
        notifyDataSetChanged();
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_taking_report, null);

        //Zmienne do dostępu do obiektów ekranowych:

        //pole niewidoczne, malutkie w lewym rogu ekranu
        TextView textView_id = (TextView)view.findViewById(R.id.textView_ID);

        //Wartości tekstowe:
        //Data:
        TextView textViewData = (TextView)view.findViewById(R.id.textView_Data);
        //Nazwa medykamentu:
        TextView textViewMedName = (TextView)view.findViewById(R.id.textView_MedName);
        //Planowany czas zażycia:
        TextView textViewPlannedTime = (TextView)view.findViewById(R.id.textView_PlannedTime);
        //Czas faktycznego zażycia:
        TextView textViewActualTime = (TextView)view.findViewById(R.id.textView_ActualTime);
        //Komentarz:
        ImageView imageViewComment = (ImageView)view.findViewById(R.id.imageView7);


        Taking currentTaking = mTakings.get(position);


        //Przypisanie wartośi do elementu niewidocznego (zachowanie _bieżącego ID:
        //currTakingsPlan_id = currentTakingsPlanViewForAdapter.get_id();
        //currentRecord_ID = currTakingsPlan_id;
        //Przypisanie wartośi niewyświetlanej (bieżące _id rekordu):
        textView_id.setText(currentTaking.get_id().toString());

        //Przypisanie wartośi do wyświetlenia:
        //Data:
        textViewData.setText(currentTaking.getDate().toString());
        //Nazwa medykamentu:
        textViewMedName.setText(currentTaking.getMedicine_name().toString());
        //Planowany czas zażycia:
        textViewPlannedTime.setText(currentTaking.getPlannedtime().toString());
        //Czas faktycznego zażycia:
        textViewActualTime.setText(currentTaking.getTakingtime().toString());
        //Komentarz:
        //imageViewComment.setBackgroundResource(R.drawable.thumbs_down);

        //Roznica czasu planowego i faktycznego czasu zazycia
        Integer nDistanceTime;
        nDistanceTime = get_DistanceTime(currentTaking.getPlannedtime(), currentTaking.getTakingtime() );

        if (nDistanceTime>30) {
            //imageViewComment.setBackgroundResource(R.drawable.ic_rap_czerwony_wykrzyknik);
            imageViewComment.setImageResource(R.drawable.ic_rap_czerwony_wykrzyknik);
        }else{
            //imageViewComment.setBackgroundResource(R.drawable.ic_rap_zielona_fajka);
            imageViewComment.setImageResource(R.drawable.ic_rap_zielona_fajka);
        }
/*        //Kolorowanie aktywnych dni tygodnia w wierszu ListView:
        //Sunday:
        if (currentTakingsPlanViewForAdapter.getDay_sunday() == 1) {
            textView_Sun.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayActive));
            textView_Sun.setTypeface(textView_Sun.getTypeface(), Typeface.BOLD);

        }else{
            textView_Sun.setTextColor(ContextCompat.getColor(mContex, R.color.colorListView_DayNOActive));
            textView_Sun.setTypeface(textView_Sun.getTypeface(), Typeface.NORMAL);
        }*/



        //zwrócenie nowego - innego widoku:
        return view;

    }
}
