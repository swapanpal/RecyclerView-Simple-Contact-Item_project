package com.example.recyclerview;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.viewHolder> {

    ArrayList<Contact> myContacts;
    OnContactClickListener onContactClickListener;

    public ContactAdapter(ArrayList<Contact> myContacts, OnContactClickListener onContactClickListener) {
        this.myContacts = myContacts;
        this.onContactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);
        return new  viewHolder(view, onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Contact current = myContacts.get(position);

        holder.nameText.setText(current.getName());
        holder.numberText.setText(current.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        if (myContacts.isEmpty()){
            return 0;
        }else {
            return myContacts.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameText, numberText;
        OnContactClickListener onContactClickListener;

        public viewHolder(@NonNull View itemView, OnContactClickListener onContactClickListener) {
            super(itemView);
            nameText = itemView.findViewById(R.id.name);
            numberText = itemView.findViewById(R.id.phone);

            this.onContactClickListener = onContactClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactClickListener.onContactClick(getAdapterPosition());

        }
    }

    public interface OnContactClickListener{

        void onContactClick(int position);

    }
}
