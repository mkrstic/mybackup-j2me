/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pim.util;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

/**
 *
 * @author mladen
 */
public class GuiUtils {
     public static void alertUser(String title, String message, AlertType alertType, Display display, Displayable nextDisplayable) {
         Alert alert = new Alert(title, message, null, alertType);
         display.setCurrent(alert, nextDisplayable);
     }
     public static void alertUserForever(String title, String message, AlertType alertType, Display display, Displayable nextDisplayable) {
         Alert alert = new Alert(title, message, null, alertType);
         alert.setTimeout(Alert.FOREVER);
         display.setCurrent(alert, nextDisplayable);
     }

}
