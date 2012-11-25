/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pim.service;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.lcdui.AlertType;
import javax.microedition.pim.Contact;
import javax.microedition.pim.ContactList;
import javax.microedition.pim.PIMException;
import org.kobjects.base64.Base64;
import pim.ui.PimMidlet;
import pim.beans.User;
import pim.util.GuiUtils;
import pim.util.MessageManager;
import pim.util.XmlRpc;

/**
 *
 * @author mladen
 */
public class SendService extends Service implements Runnable {

    private ContactList phoneBook;

    public SendService(PimMidlet midlet, User user, ContactList contactList) {
        super(midlet, user);
        this.phoneBook = contactList;
    }

    public void run() {
        Vector params = new Vector();
        Vector localContactUids = new Vector();

        int processedContacts = 0;
        int counter = 0;
        midlet.getProgressMessageField().setText("Checking database state...");

        params.addElement(user.getId());
        Integer numContacts = null;
        try {
            numContacts = (Integer) XmlRpc.get().getRpc().execute("PimManager.countContacts", params);
        } catch (Exception ex) {
            midlet.getProgressMessageField().setText("");
            GuiUtils.alertUser("Error", "Cannot count your contacts.", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
            return;
        }

        if (numContacts.intValue() == MessageManager.MESSAGE_ERROR) {
            midlet.getProgressMessageField().setText("");
            if (isCanceled()) {
                cancelOperation();
                return;
            }
            GuiUtils.alertUser("Error", "There was an error in communication with server. Cannot check your contacts on the remote database. Please try again.", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
            return;
        }
        Integer deletedContactsCount = new Integer(0);

        boolean isDbEmpty = (numContacts.intValue() == 0);
        boolean revisionSupported = phoneBook.isSupportedField(Contact.REVISION);

        Enumeration items = null;
        try {
            items = phoneBook.items();
        } catch (PIMException pe) {
            midlet.getProgressMessageField().setText("");
            GuiUtils.alertUser("Error", "Cannot read contacts", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
            return;
        }

        for (counter = 0; items.hasMoreElements(); counter++) {
            Contact contact = (Contact) items.nextElement();
            StringBuffer strBuffNumbers = new StringBuffer("");

            midlet.getProgressMessageField().setText("Processing " + (counter + 1) + ". contact");

            String strRevision = "";
            if (revisionSupported) {
                strRevision = String.valueOf(contact.getDate(Contact.REVISION, Contact.ATTR_NONE));
            }

            String uid = "";
            if (phoneBook.isSupportedField(Contact.UID)) {
                uid = contact.getString(Contact.UID, Contact.ATTR_NONE);
                localContactUids.addElement(uid);
            }

            if (!isDbEmpty && revisionSupported) { // send to check if modified
                if (!params.isEmpty()) {
                    params.removeAllElements();
                }
                params.addElement(user.getId());
                params.addElement(strRevision);
                params.addElement(uid);

                if (isCanceled()) {
                    cancelOperation();
                    return;
                }
                try {
                    Boolean isModified = (Boolean) XmlRpc.get().getRpc().execute("PimManager.isContactModified", params);
                    if (!isModified.booleanValue()) {
                        if (isCanceled()) {
                            cancelOperation();
                            return;
                        }
                        processedContacts++;
                        continue;
                    }
                } catch (Exception ex) {
                    if (isCanceled()) {
                        cancelOperation();
                        return;
                    }
                    midlet.getProgressMessageField().setText("");
                    GuiUtils.alertUser("Error", "There was an error in communication with server. Cannot sync contact. Please try again.", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
                    return;
                }
            }
            String email = "";
            if (phoneBook.isSupportedField(Contact.EMAIL)) {
                try {
                    email = contact.getString(Contact.EMAIL, Contact.ATTR_NONE);
                    if (email == null || email.trim().equals("")) {
                        email = contact.getString(Contact.EMAIL, Contact.ATTR_HOME);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    //nothing for now
                }


            }
            String url = "";
            if (phoneBook.isSupportedField(Contact.URL)) {
                try {
                    url = contact.getString(Contact.URL, Contact.ATTR_NONE);
                } catch (IndexOutOfBoundsException ex) {
                    //nothing
                }

            }
            String note = "";
            if (phoneBook.isSupportedField(Contact.NOTE)) {
                try {
                    email = contact.getString(Contact.NOTE, Contact.ATTR_NONE);
                } catch (IndexOutOfBoundsException ex) {
                    //nothing
                }

            }


            String address = "";
            if (phoneBook.isSupportedField(Contact.FORMATTED_ADDR)) {
                try {
                    address = contact.getString(Contact.FORMATTED_ADDR, Contact.ATTR_NONE);
                } catch (IndexOutOfBoundsException ex) {
                    //nothing
                }

            }

            String name = "";
            boolean defined = false;
            if (phoneBook.isSupportedField(Contact.FORMATTED_NAME) && contact.countValues(Contact.FORMATTED_NAME) != 0) {
                String s = contact.getString(Contact.FORMATTED_NAME, 0);
                if ((s != null) && (s.trim().length() > 0)) {
                    defined = true;
                    name = s;
                }
            }
            if (!defined) { // try to take from name array
                if (phoneBook.isSupportedField(Contact.NAME) && contact.countValues(Contact.NAME) != 0) {
                    String[] a = contact.getStringArray(Contact.NAME, 0);
                    if (a != null) {
                        StringBuffer sb = new StringBuffer();
                        if (a[Contact.NAME_GIVEN] != null) {
                            sb.append(a[Contact.NAME_GIVEN]);
                        }
                        if (a[Contact.NAME_FAMILY] != null) {
                            if (sb.length() > 0) {
                                sb.append(" ");
                            }
                            sb.append(a[Contact.NAME_FAMILY]);
                        }
                        String s = sb.toString().trim();
                        if (s.length() > 0) {
                            name = s;
                        } else {
                            name = "";
                        }
                    }
                } else {
                    name = "undefined";
                }
            }

            if (isCanceled()) {
                cancelOperation();
                return;
            }

            name = Base64.encode(name.getBytes());


            int telCount = contact.countValues(Contact.TEL);
            int preferredIndex = contact.getPreferredIndex(Contact.TEL);
            for (int i = 0; i < telCount; i++) {
                int telAttributes = contact.getAttributes(Contact.TEL, i);
                strBuffNumbers.append(contact.getString(Contact.TEL, i)); // get number
                if (phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_ASST)
                        && (telAttributes & Contact.ATTR_ASST) != 0) { // match
                    strBuffNumbers.append("~");
                    strBuffNumbers.append("Assistant");
                } else if (phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_AUTO)
                        && (telAttributes & Contact.ATTR_AUTO) != 0) { // match
                    strBuffNumbers.append("~");
                    strBuffNumbers.append("Auto");
                } else if (phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_FAX)
                        && (telAttributes & Contact.ATTR_FAX) != 0) { // match
                    strBuffNumbers.append("~");
                    strBuffNumbers.append("Fax");
                } else if (phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_HOME)
                        && (telAttributes & Contact.ATTR_HOME) != 0) { // match
                    strBuffNumbers.append("~");
                    strBuffNumbers.append("Home");
                } else if (phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_MOBILE)
                        && (telAttributes & Contact.ATTR_MOBILE) != 0) { // match
                    strBuffNumbers.append("~");
                    strBuffNumbers.append("Mobile");
                } else if (phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_OTHER)
                        && (telAttributes & Contact.ATTR_OTHER) != 0) { // match
                    strBuffNumbers.append("~");
                    strBuffNumbers.append("Other");
                } else if (phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_PAGER)
                        && (telAttributes & Contact.ATTR_PAGER) != 0) { // match
                    strBuffNumbers.append("~");
                    strBuffNumbers.append("Pager");
                } else if (phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_WORK)
                        && (telAttributes & Contact.ATTR_WORK) != 0) { // match
                    strBuffNumbers.append("~");
                    strBuffNumbers.append("Work");
                } else if (phoneBook.isSupportedAttribute(Contact.TEL, Contact.ATTR_SMS)
                        && (telAttributes & Contact.ATTR_SMS) != 0) { // match
                    strBuffNumbers.append("~");
                    strBuffNumbers.append("Sms");
                } else { // attribute none, type general
                    strBuffNumbers.append("~");
                    strBuffNumbers.append("General");
                }


                strBuffNumbers.append("~");
                if (i == preferredIndex) {
                    strBuffNumbers.append("1");
                } else {
                    strBuffNumbers.append("0");
                }

                if (i != (telCount - 1)) { // if not the last one
                    strBuffNumbers.append(":");
                }
            }
            String formattedTels = Base64.encode(strBuffNumbers.toString().getBytes());
            if (!params.isEmpty()) {
                params.removeAllElements();
            }

            params.addElement(user.getId());
            params.addElement(name);
            params.addElement(email);
            params.addElement(url);
            params.addElement(note);
            params.addElement(address);
            params.addElement(strRevision);
            params.addElement(uid);
            params.addElement(formattedTels);

            if (isCanceled()) {
                cancelOperation();
                return;
            }

            midlet.getProgressMessageField().setText("Sending " + (counter + 1) + ". contact...");
            Integer syncResult;
            try {
                syncResult = (Integer) XmlRpc.get().getRpc().execute("PimManager.syncContact", params);
            } catch (Exception ex) {
                midlet.getProgressMessageField().setText("Not processed. Ignoring.");
                continue;
            }

            if (syncResult == null || syncResult.intValue() == MessageManager.MESSAGE_FAILURE) {
                if (isCanceled()) {
                    cancelOperation();
                    return;
                }
                midlet.getProgressMessageField().setText("Not processed. Ignoring.");
            } else if (syncResult.intValue() == MessageManager.MESSAGE_SUCCESS) { // successfully processed
                if (isCanceled()) {
                    cancelOperation();
                    return;
                }
                processedContacts++;
                midlet.getProgressMessageField().setText("Completed");
            }
        }
        Vector remoteContactUids = null;
        if (!localContactUids.isEmpty()) {
            try {
                if (!params.isEmpty()) {
                    params.removeAllElements();
                }
                params.addElement(user.getId());
                remoteContactUids = (Vector) XmlRpc.get().getRpc().execute("PimManager.getContactUids", params);
                Vector deletionCandidateList = new Vector();
                int remoteContactUidsSize = remoteContactUids.size();
                for (int i = 0; i < remoteContactUidsSize; i++) {
                    String elem = (String) remoteContactUids.elementAt(i);
                    //if (!localContactUids.contains(elem)) {
                    if (!contain(localContactUids, elem)) {
                        deletionCandidateList.addElement(elem);
                    }
                }
                if (!deletionCandidateList.isEmpty()) {
                    if (!params.isEmpty()) {
                        params.removeAllElements();
                    }
                    params.addElement(user.getId());
                    params.addElement(deletionCandidateList);
                    try {
                        deletedContactsCount = (Integer) XmlRpc.get().getRpc().execute("PimManager.deleteContactsByUid", params);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }

                    if (deletedContactsCount == null || deletedContactsCount.intValue() == 0) {
                        throw new Exception();
                    }
                }
            } catch (Exception ex) {
                midlet.getProgressMessageField().setText("");
                GuiUtils.alertUser("Warning", "Cannot compare contacts with others in database. It might be duplicates.", AlertType.WARNING, midlet.getDisplay(), midlet.getWaitingForm());
            }
        }
        if (!params.isEmpty()) {
            params.removeAllElements();
        }
        params.addElement(user.getId());
        try {
            XmlRpc.get().getRpc().execute("PimManager.updateLastSynced", params);
        } catch (Exception ex) {
        }
        midlet.getProgressMessageField().setText("");
        String progressMsg = "Operation completed. Processed: " + counter + " contacts. Sent: " + processedContacts + " contacts.";
        GuiUtils.alertUser("", progressMsg, AlertType.INFO, midlet.getDisplay(), parentDisplayable);
        try {
            phoneBook.close();
        } catch (PIMException pe) {
            //nothing
        }
    }

    private boolean contain(Vector vector, String targetElem) {
        boolean isContain = false;
        int vectorSize = vector.size();
        for (int i = 0; i < vectorSize; i++) {
            String elem = (String) vector.elementAt(i);
            if (targetElem.compareTo(elem) == 0) {
                isContain = true;
                break;
            }
        }
        return isContain;
    }

    protected void cancelOperation() {
        midlet.getProgressMessageField().setText("");
        GuiUtils.alertUser("Info", "Operation canceled", AlertType.INFO, midlet.getDisplay(), midlet.getMainForm());
    }
}
