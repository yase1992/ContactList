package com.magicsoft.moqadasi.contactlist.adp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.magicsoft.moqadasi.contactlist.R;
import com.magicsoft.moqadasi.contactlist.models.Contact;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

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
        ImageView mLstContact_imgPro = (ImageView) row.findViewById(R.id.lstContact_imgPro);
        TextView mLstContact_txtName = (TextView) row.findViewById(R.id.lstContact_txtName);
//        lstContact_imgPro.setBackground();
        mLstContact_txtName.setText(currentContact.getName());
        return row;
    }
}