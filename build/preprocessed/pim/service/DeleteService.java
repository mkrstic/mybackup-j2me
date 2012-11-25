/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pim.service;

import java.util.Vector;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Displayable;
import pim.ui.PimMidlet;
import pim.beans.User;
import pim.util.GuiUtils;
import pim.util.MessageManager;
import pim.util.XmlRpc;

/**
 *
 * @author mladen
 */
public class DeleteService extends Service implements Runnable {

    public DeleteService(PimMidlet midlet, User user) {
        super(midlet, user);
    }

    public void run() {
        Vector params = new Vector();

        midlet.getProgressMessageField().setText("Checking database state...");
        params.addElement(user.getId());
        Integer count = null;
        try {
            count = (Integer) XmlRpc.get().getRpc().execute("PimManager.countContacts", params);
        } catch (Exception ex) {
            midlet.getProgressMessageField().setText("");
            GuiUtils.alertUser("Error", "Oops! Communication with server resulting error. Cannot count your contacts.", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
            return;
        }
        if (count.intValue() == MessageManager.MESSAGE_ERROR) {
            midlet.getProgressMessageField().setText("");
            if (isCanceled()) {
                cancelOperation();
                return;
            }
            GuiUtils.alertUser("Error", "There was an error in communication with server. Cannot check your contacts on the remote database. Please try again.", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
            return;
        }
        boolean isDbEmpty = (count.intValue() == 0);
        if (isDbEmpty) {
            midlet.getProgressMessageField().setText("");
            GuiUtils.alertUser("Info", "You have no saved contacts on server. Please, upload your phone book.", AlertType.INFO, midlet.getDisplay(), midlet.getMainForm());
            return;
        }
        try {
            Integer deletionResult = (Integer) XmlRpc.get().getRpc().execute("PimManager.deleteAllContacts", params);
            if (deletionResult.intValue() == MessageManager.MESSAGE_FAILURE || deletionResult.intValue() == MessageManager.MESSAGE_ERROR) {
                midlet.getProgressMessageField().setText("");
                GuiUtils.alertUser("Error", "Oops! Cannot delete your contacts. Please, try again.", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
                return;
            }
        } catch (Exception ex) {
            midlet.getProgressMessageField().setText("");
            GuiUtils.alertUser("Error", "There was an error in communication with server. Please try again.", AlertType.ERROR, midlet.getDisplay(), midlet.getMainForm());
            return;
        }
        midlet.getProgressMessageField().setText("");
        String progressMsg = "Operation completed.";
        GuiUtils.alertUser("", progressMsg, AlertType.INFO, midlet.getDisplay(), parentDisplayable);
    }

    protected void cancelOperation() {
        midlet.getProgressMessageField().setText("");
        GuiUtils.alertUser("Info", "Operation canceled", AlertType.INFO, midlet.getDisplay(), midlet.getMainForm());
    }
}
