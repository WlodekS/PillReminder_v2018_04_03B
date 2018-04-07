package com.pum2018.pillreminder_v2018_04_03.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pum2018.pillreminder_v2018_04_03.DataModel.Medicine;
import com.pum2018.pillreminder_v2018_04_03.R;

import java.util.ArrayList;

/**
 * Created by Wlodek on 2018-04-07.
 * Adapter do ładowania danych do widoku Medicines
 */

/**
 * MedicineAdapter
 * Adapter for Get all Medicine rows from table MEDICINE_TABLE as ArrayList (for MedicineAdapter)
 * Params: no params
 * Return: list of medicine objects
 */
public class MedicineAdapter extends BaseAdapter {
    Context mContex;
    ArrayList<Medicine> mMedicines;
    LayoutInflater mInflater;

    public MedicineAdapter(Context c, ArrayList<Medicine> medicines) {
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
        View view = mInflater.inflate(R.layout.item_delete_medicine, null);

        TextView textView_id = (TextView)view.findViewById(R.id.txt_medicine_id_del);
        TextView textViewName = (TextView)view.findViewById(R.id.txt_medicine_name_del);
        TextView textViewForm = (TextView)view.findViewById(R.id.txt_medicine_form_del);
        TextView textViewQuantity = (TextView)view.findViewById(R.id.txt_medicine_quantity_del);

        Medicine currentMedicine = mMedicines.get(position);

        //Przypisanie wartośi do wyświetlenia:
        textView_id.setText(currentMedicine.get_id().toString());
        textViewName.setText(currentMedicine.getName());
        textViewForm.setText(currentMedicine.getFormMedicine().toString());
        textViewQuantity.setText(currentMedicine.getQuantity().toString());

        //zwrócenie nowego - innego widoku:
        return view;

    }
}