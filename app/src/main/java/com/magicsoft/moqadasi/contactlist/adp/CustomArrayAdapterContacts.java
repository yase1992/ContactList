package com.magicsoft.moqadasi.contactlist.adp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.magicsoft.moqadasi.contactlist.R;
import com.magicsoft.moqadasi.contactlist.models.Contact;

import java.util.List;

import static com.magicsoft.moqadasi.contactlist.R.id.lstContact_imgPro;

public class CustomArrayAdapterContacts extends ArrayAdapter<Contact> {
    private int PICK_IMAGE = 8;
    private ImageView dlgAddPerson_imgPerson;
    private Uri profilePicUri = null;
    private Contact currentContact;

    public CustomArrayAdapterContacts(Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View row = convertView;
        profilePicUri = null;

        currentContact = getItem(position);
        if (row == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            row = layoutInflater.inflate(R.layout.contacts_list_item, null);
        }
        ImageView mLstContact_imgPro = (ImageView) row.findViewById(lstContact_imgPro);
        TextView mLstContact_txtName = (TextView) row.findViewById(R.id.lstContact_txtName);
        TextView mLstContact_txtNumber = (TextView) row.findViewById(R.id.lstContact_txtNumber);
//        lstContact_imgPro.setBackground();
        if ((position % 2) == 0)
            mLstContact_imgPro.setBackground(getContext().getResources().getDrawable(R.mipmap.man));
        else
            mLstContact_imgPro.setBackground(getContext().getResources().getDrawable(R.mipmap.woman));
        mLstContact_txtName.setText(currentContact.name);
        mLstContact_txtNumber.setText(currentContact.main_number);
        mLstContact_imgPro.setImageURI(currentContact.pro_uri);

        return row;
    }
}