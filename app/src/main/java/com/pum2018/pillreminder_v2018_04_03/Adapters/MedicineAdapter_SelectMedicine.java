package com.pum2018.pillreminder_v2018_04_03.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;
import com.pum2018.pillreminder_v2018_04_03.R;

import java.util.ArrayList;

public class MedicineAdapter_SelectMedicine extends BaseAdapter {
    Context mContex;
    ArrayList<Medicine> mMedicines;
    LayoutInflater mInflater;

    public MedicineAdapter_SelectMedicine(Context c, ArrayList<Medicine> medicines) {
        this.mContex = c;
        this.mMedicines = medicines;
        mInflater = (LayoutInflater)mContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount(){
        return mMedicines.size();
    }

    public Object getItem(int position) {
        return mMedicines.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    /**
     * Clears all items from the data set.
     */
    public void clear(){
        if(this.mMedicines != null){
            this.mMedicines.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * Adds all of the items to the data set.
     * @param items The item list to be added.
     */
    public void addAll(ArrayList<Medicine> items){
        if(this.mMedicines == null){
            this.mMedicines = new ArrayList<>();
        }
        this.mMedicines.addAll(items);
        notifyDataSetChanged();
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_select_medicine, null);

        TextView textView_id = (TextView)view.findViewById(R.id.tv_rs_ID);
        TextView textViewName = (TextView)view.findViewById(R.id.tv_rs_MedicineName);
        TextView textViewForm = (TextView)view.findViewById(R.id.tv_rs_MedicineForm);
        TextView textViewQuantity = (TextView)view.findViewById(R.id.tv_rs_Quantity);
        TextView textViewDoseOption = (TextView)view.findViewById(R.id.tv_rs_DoseOption);

        Medicine currentMedicine = mMedicines.get(position);

        //Przypisanie wartośi do wyświetlenia:
        textView_id.setText(currentMedicine.get_id().toString());
        textViewName.setText(currentMedicine.getName());
        textViewForm.setText(currentMedicine.getFormMedicine());
        textViewQuantity.setText(currentMedicine.getQuantity().toString());
        textViewDoseOption.setText(currentMedicine.getDose_option());

        //zwrócenie nowego - innego widoku:
        return view;

    }

}
