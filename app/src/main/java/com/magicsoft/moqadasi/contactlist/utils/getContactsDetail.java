package com.magicsoft.moqadasi.contactlist.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.magicsoft.moqadasi.contactlist.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class GetContactsDetail {

    public static List<Contact> lstAllContacts;

    private static String main_number;

    private static Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
    private static String _ID = ContactsContract.Contacts._ID;
    private static String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    private static String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
    private static Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private static String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
    private static String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
    private static Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
    private static String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
    private static String DATA = ContactsContract.CommonDataKinds.Email.DATA;

    public static List<Contact> getAllContacts(Context context) {
        lstAllContacts = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Contact contactToAdd = new Contact();
//        String phoneNumber = getContactNumbers(contentResolver, contactToAdd.id);
//        StringBuffer output = new StringBuffer();
        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, null);
        // Loop for every contact in the phone
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                contactToAdd = new Contact();
                contactToAdd.id = cursor.getString(cursor.getColumnIndex(_ID));
                contactToAdd.name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    // Query and loop for every phone number of the contact
                    getContactNumbers(contentResolver, contactToAdd.id);
                    contactToAdd.main_number = main_number;
                    // Query and loop for every email of the contact

                    //get image
                    contactToAdd.pro_uri = getContactUri(contentResolver, contactToAdd.id);
                } else
                    continue;
                lstAllContacts.add(contactToAdd);
            }
        }
        return lstAllContacts;
    }

    public static String getContactEmails(ContentResolver contentResolver, String contact_id) {
        Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);
        String email;
        String emailsOutput = "";
        int cnt = 1;
        while (emailCursor.moveToNext()) {
            email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
            emailsOutput += (cnt + " :    " + email + "\n");
            cnt++;
        }
        emailCursor.close();
        if (emailsOutput.equals(""))
            emailsOutput = "درج نشده است\n";
        return emailsOutput;
    }

    public static String getContactNumbers(ContentResolver contentResolver, String contact_id) {
        String numbersOutput = "";
        String number;
        Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);
        int cnt = 1;
        while (phoneCursor.moveToNext()) {
            number = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
            numbersOutput += (cnt + " :    " + number + "\n");
            if (cnt == 1) {
                main_number = number;
            }
            cnt++;
        }
        phoneCursor.close();
        return numbersOutput;
    }

    public static Uri getContactUri(ContentResolver contentResolver, String contact_id) {
//        Cursor ImageCursor = contentResolver.query(
//                ContactsContract.Data.CONTENT_URI,
//                null,
//                ContactsContract.Data.CONTACT_ID + "=" + contact_id + " AND "
//                        + ContactsContract.Data.MIMETYPE + "='"
//                        + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE + "'", null,
//                null);
        return Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long
                .parseLong(String.valueOf(contact_id))), ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

    }
}
