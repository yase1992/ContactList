package com.magicsoft.moqadasi.contactlist.adp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.magicsoft.moqadasi.contactlist.R;
import com.magicsoft.moqadasi.contactlist.models.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.magicsoft.moqadasi.contactlist.R.id.lstContact_imgPro;

public class CustomArrayAdapterContacts extends ArrayAdapter<Contact> {
    List<Contact> allContacts = new ArrayList<>();
    List<Contact> showListContacts = new ArrayList<>();
    private Contact currentContact;

    public CustomArrayAdapterContacts(Context context, int resource, List<Contact> allContacts) {
        super(context, resource, allContacts);
        this.allContacts.addAll(allContacts);
        this.showListContacts.addAll(allContacts);
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, @NonNull final ViewGroup parent) {
        View row = convertView;
        currentContact = getItem(position);
        if (row == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            row = layoutInflater.inflate(R.layout.contacts_list_item, null);
        }
        ImageView mLstContact_imgPro = (ImageView) row.findViewById(lstContact_imgPro);
        TextView mLstContact_txtName = (TextView) row.findViewById(R.id.lstContact_txtName);
        TextView mLstContact_txtMainNumber = (TextView) row.findViewById(R.id.lstContact_txtNumber);
        if ((position % 2) == 0)
            mLstContact_imgPro.setBackground(getContext().getResources().getDrawable(R.mipmap.man));
        else
            mLstContact_imgPro.setBackground(getContext().getResources().getDrawable(R.mipmap.woman));
        mLstContact_txtName.setText(currentContact.name);
        mLstContact_txtMainNumber.setText(currentContact.main_number);
        mLstContact_imgPro.setImageURI(currentContact.pro_uri);

        return row;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        showListContacts.clear();
        if (charText.length() == 0) {
            showListContacts.addAll(allContacts);
        } else {
            for (Contact contact : allContacts) {
                if (charText.length() != 0 && contact.name.toLowerCase(Locale.getDefault()).contains(charText)) {
                    showListContacts.add(contact);
                } else if (charText.length() != 0 && contact.main_number.toLowerCase(Locale.getDefault()).contains(charText)) {
                    showListContacts.add(contact);
                }
            }
        }
        clear();
        addAll(showListContacts);
        notifyDataSetChanged();
    }
}