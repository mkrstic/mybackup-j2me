/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pim.service;

import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Displayable;
import javax.microedition.pim.Contact;
import javax.microedition.pim.ContactList;
import javax.microedition.pim.PIMException;
import org.kobjects.base64.Base64;
import pim.beans.MyContact;
import pim.ui.PimMidlet;
import pim.beans.Telephone;
import pim.beans.User;
import pim.util.GuiUtils;
import pim.util.MessageManager;
import pim.util.XmlRpc;

/**
 *
 * @author mladen
 */
public class FetchService extends Service implements Runnable {

    private ContactList phoneBook;

    public FetchService(PimMidlet midlet, User user, ContactList contactList) {
        super(midlet, user);
        this.phoneBook = contactList;
    }

    public void run() {
        Vector params = new Vector();

        int processedContacts = 0;
        midlet.getProgressMessageField().setText("Checking database state...");

        params.addElement(user.getId());
        Integer count = null;
        try {
            count = (Integer) XmlRpc.get().getRpc().execute("PimManager.countContacts", params);
        } catch (Exception ex) {
            midlet.getProgressMessageField().setText("");
            GuiUtils.alertUser("Error", "Cannot count your contacts.", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
            return;
        }

        if (count.intValue() == MessageManager.MESSAGE_ERROR) {
            midlet.getProgressMessageField().setText("");
            GuiUtils.alertUser("Error", "There was an error in communication with server. Cannot check your contacts on the remote database. Please try again.", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
            return;
        }

        midlet.getProgressMessageField().setText("Fetching number of stored contacts...");


        boolean isDbEmpty = (count.intValue() == 0);

        if (isDbEmpty) {
            midlet.getProgressMessageField().setText("");
            GuiUtils.alertUser("Info", "You have no saved contacts in the remote database. There is nothing to retrieve.", AlertType.INFO, midlet.getDisplay(), midlet.getMainForm());
            return;
        }

        Vector contactIds = null;

        if (!params.isEmpty()) {
            params.removeAllElements();
        }
        params.addElement(user.getId());
        try {
            contactIds = (Vector) XmlRpc.get().getRpc().execute("PimManager.getContactIds", params);
        } catch (Exception ex) {
            midlet.getProgressMessageField().setText("");
            GuiUtils.alertUser("Error", "Cannot read your contacts from the server. Please, try later.", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
            return;
        }

        midlet.getProgressMessageField().setText("Found " + contactIds.size() + " contacts");

        for (int i = 0; i < contactIds.size(); i++) {
            Integer contactId = (Integer) contactIds.elementAt(i);
            MyContact myContact = new MyContact(contactId);
            if (!params.isEmpty()) {
                params.removeAllElements();
            }
            params.addElement(user.getId());
            params.addElement(myContact.getId());

            Hashtable contactMap = null;

            midlet.getProgressMessageField().setText("Reading " + (processedContacts + 1) + ". contact");
            try {
                contactMap = (Hashtable) XmlRpc.get().getRpc().execute("PimManager.getContactById", params);
            } catch (Exception ex) {
                midlet.getProgressMessageField().setText("");
                GuiUtils.alertUser("Error", "Cannot fetch contact with id " + myContact.getId() + ". Ignoring.", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
                continue;
            }
            midlet.getProgressMessageField().setText("Successful");

            String name = new String(Base64.decode((String) contactMap.get("name")));
            String email = (String) contactMap.get("email");
            String url = (String) contactMap.get("url");
            String uid = (String) contactMap.get("uid");
            String address = (String) contactMap.get("address");
            String note = (String) contactMap.get("note");

            myContact.setName(name);
            if (name.equals("~")) {
                myContact.setName("");
            }

            myContact.setAddress(address);
            if (address.equals("~")) {
                myContact.setAddress("");
            }

            myContact.setEmail(email);
            if (email.equals("~")) {
                myContact.setEmail("");
            }

            myContact.setNote(note);
            if (note.equals("~")) {
                myContact.setNote("");
            }

            myContact.setUid(uid);
            if (uid.equals("~")) {
                myContact.setUid("");
            }

            myContact.setUrl(url);
            if (url.equals("~")) {
                myContact.setUrl("");
            }

            Contact c = phoneBook.createContact();

            if (phoneBook.isSupportedField(Contact.FORMATTED_NAME)) {
                c.addString(Contact.FORMATTED_NAME, Contact.ATTR_NONE, myContact.getName());
            }

            if (phoneBook.isSupportedField(Contact.NAME)) {
                String[] name_struct = new String[phoneBook.stringArraySize(Contact.NAME)];
                String firstName = "";
                String lastName = "";
                int spaceIndex = myContact.getName().trim().indexOf(" ");
                if (spaceIndex > 0) {
                    firstName = myContact.getName().substring(0, spaceIndex);
                    lastName = myContact.getName().substring(spaceIndex).trim();
                    name_struct[Contact.NAME_GIVEN] = firstName;
                    name_struct[Contact.NAME_FAMILY] = lastName;
                } else {
                    name_struct[Contact.NAME_GIVEN] = myContact.getName();
                }
                c.addStringArray(Contact.NAME, Contact.ATTR_NONE, name_struct);
            }
            if (phoneBook.isSupportedField(Contact.EMAIL)) {
                c.addString(Contact.EMAIL, Contact.ATTR_NONE, myContact.getEmail());
            }
            if (phoneBook.isSupportedField(Contact.NOTE)) {
                c.addString(Contact.NOTE, Contact.ATTR_NONE, myContact.getNote());
            }
            if (phoneBook.isSupportedField(Contact.URL)) {
                c.addString(Contact.URL, Contact.ATTR_NONE, myContact.getUrl());
            }
            if (phoneBook.isSupportedField(Contact.FORMATTED_ADDR)) {
                c.addString(Contact.FORMATTED_ADDR, Contact.ATTR_NONE, myContact.getAddress());
            }

            midlet.getProgressMessageField().setText("Reading telephone numbers...");
            Vector formattedTels = null;
            try {
                if (!params.isEmpty()) {
                    params.removeAllElements();
                }
                params.addElement(myContact.getId());
                formattedTels = (Vector) XmlRpc.get().getRpc().execute("PimManager.getTelsById", params);
                if (formattedTels == null) {
                    throw new Exception("Tels list is null");
                }
            } catch (Exception ex) {
                midlet.getProgressMessageField().setText("");
                GuiUtils.alertUser("Error", "Cannot fetch telephone numbers of contact with id " + myContact.getId() + ". Ignoring.", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
                continue;
            }

            midlet.getProgressMessageField().setText("Success");
            Vector tels = new Vector();
            for (int j = 0; j < formattedTels.size(); j++) {
                String telFormatted = new String(Base64.decode((String) formattedTels.elementAt(j)));
                int start = 0;
                int end = telFormatted.indexOf("~", start);
                String number = telFormatted.substring(start, end);
                start = end + 1;
                end = telFormatted.indexOf("~", start);
                String type = telFormatted.substring(start, end);
                start = end + 1;
                String strPref = telFormatted.substring(start);
                Boolean preferred;
                if (strPref.equals("1")) {
                    preferred = new Boolean(true);
                } else {
                    preferred = new Boolean(false);
                }
                Telephone t = new Telephone(number, type, preferred);
                tels.addElement(t);
            }
            for (int j = 0; j < tels.size(); j++) {
                Telephone t = (Telephone) tels.elementAt(j);
                if (t.getType().equals("Assistant") && phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_ASST)) {
                    c.addString(Contact.TEL, Contact.ATTR_ASST | isPrefBit(t), t.getNumber());
                } else if (t.getType().equals("Auto") && phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_AUTO)) {
                    c.addString(Contact.TEL, Contact.ATTR_AUTO | isPrefBit(t), t.getNumber());
                } else if (t.getType().equals("Fax") && phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_FAX)) {
                    c.addString(Contact.TEL, Contact.ATTR_FAX | isPrefBit(t), t.getNumber());
                } else if (t.getType().equals("Home") && phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_HOME)) {
                    c.addString(Contact.TEL, Contact.ATTR_HOME | isPrefBit(t), t.getNumber());
                } else if (t.getType().equals("Mobile") && phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_MOBILE)) {
                    c.addString(Contact.TEL, Contact.ATTR_MOBILE | isPrefBit(t), t.getNumber());
                } else if (t.getType().equals("Other") && phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_OTHER)) {
                    c.addString(Contact.TEL, Contact.ATTR_OTHER | isPrefBit(t), t.getNumber());
                } else if (t.getType().equals("Pager") && phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_PAGER)) {
                    c.addString(Contact.TEL, Contact.ATTR_PAGER | isPrefBit(t), t.getNumber());
                } else if (t.getType().equals("Sms") && phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_SMS)) {
                    c.addString(Contact.TEL, Contact.ATTR_SMS | isPrefBit(t), t.getNumber());
                } else if (t.getType().equals("Work") && phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_WORK)) {
                    c.addString(Contact.TEL, Contact.ATTR_WORK | isPrefBit(t), t.getNumber());
                } else { // if (t.getType().equals("General") && phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_FAX)) {
                    c.addString(Contact.TEL, Contact.ATTR_NONE, t.getNumber()); // general
                }
            }
            if (isCanceled()) {
                cancelOperation();
                return;
            }
            try {
                c.commit(); // make changes
            } catch (PIMException ex) {
                // nothing...
            }
            processedContacts = i;

        }

        midlet.getProgressMessageField().setText("");

        if (processedContacts > 0) {
            String progressMsg = "Operation completed. Processed: " + processedContacts + " contacts. Retrieved: " + processedContacts + " contacts.";
            GuiUtils.alertUser("", progressMsg, AlertType.INFO, midlet.getDisplay(), parentDisplayable);
        } else {
            String progressMsg = "Processed 0 contacts. Operation failed. Please, try again.";
            GuiUtils.alertUser("", progressMsg, AlertType.WARNING, midlet.getDisplay(), parentDisplayable);
        }

        try {
            phoneBook.close();
        } catch (PIMException ex) {
            // nothing
        }
    }

    protected void cancelOperation() {
        midlet.getProgressMessageField().setText("");
        GuiUtils.alertUser("Info", "Operation canceled", AlertType.INFO, midlet.getDisplay(), midlet.getMainForm());
    }

    private int isPrefBit(Telephone t) {
        if ((t.isPreferred().booleanValue() == true) && phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_PREFERRED)) {
            return Contact.ATTR_PREFERRED;
        } else {
            return 1; // neutral value in OR logical operation
        }
    }
}
