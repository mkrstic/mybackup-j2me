/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pim.util;

public class MessageManager {


    private static MessageManager instance;
    public static int MESSAGE_SUCCESS = 0;
    public static int MESSAGE_FAILURE = 1;
    public static int MESSAGE_ERROR = -1;


    private MessageManager() {

    }

    public static MessageManager get() {
        if (instance == null) {
            instance = new MessageManager();
        }
        return instance;
    }

}
