/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pim.ui;

import java.util.Date;
import javax.microedition.io.ConnectionNotFoundException;
import pim.beans.User;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.pim.ContactList;
import javax.microedition.pim.PIM;
import javax.microedition.pim.PIMException;
import org.kobjects.base64.Base64;

import pim.util.AppUtils;
import pim.service.DeleteService;

import pim.service.SendService;
import pim.util.GuiUtils;
import pim.service.FetchService;
import pim.service.Service;
import pim.util.MessageManager;
import pim.util.XmlRpc;

/**
 * @author mladen
 */
public class PimMidlet extends MIDlet implements CommandListener, ItemCommandListener {

    private boolean midletPaused = false;
    private Service activeService;
    private User user;
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private java.util.Hashtable __previousDisplayables = new java.util.Hashtable();
    private Command exitCommand;
    private Command signInCommand;
    private Command logoutCommand;
    private Command recoverPassInitCommand;
    private Command signUpInitCommand;
    private Command backCommand3;
    private Command recoverPassProcessCommand;
    private Command backCommand2;
    private Command backToSignInCommand3;
    private Command exitCommand1;
    private Command cancelOperationCommand;
    private Command aboutUsCommand;
    private Command aboutUsCommand2;
    private Command payLinkCommand;
    private Command exitCommand2;
    private Command backCommand1;
    private Command backCommand;
    private Command enterKeyCommand;
    private Command exitCommand3;
    private Command signUpSubmitCommand;
    private Command signUpNextCommand;
    private Command exitCommand4;
    private Command backCommand4;
    private Command processOperationCommand;
    private Command submitActivationCommand;
    private Form signInForm;
    private TextField passwordField;
    private TextField phoneField;
    private StringItem forgotPassField;
    private StringItem signUpField;
    private StringItem loginField;
    private Form mainForm;
    private StringItem stringItem;
    private ChoiceGroup operationsPopup;
    private Form recoverPassForm;
    private TextField recoverPhoneField;
    private StringItem stringItem2;
    private StringItem sendPassBtn;
    private Form signUpForm;
    private TextField signUpPhoneField;
    private TextField signUpPasswordField;
    private TextField signUpFirstNameField;
    private TextField signUpEmailField;
    private TextField signUpLastNameField;
    private StringItem signUpProcessField;
    private TextField signUpTownField;
    private Form signUpCompletedForm;
    private StringItem stringItem1;
    private Form waitingForm;
    private StringItem progressMessageField;
    private Gauge gauge;
    private Form aboutForm;
    private StringItem stringItem3;
    private Form paymentInfoForm;
    private StringItem stringItem4;
    private StringItem stringItem5;
    private StringItem stringItem6;
    private Form activationKeyForm;
    private TextField activationKeyField;
    private StringItem stringItem7;
    private Form confirmRegistrationForm;
    private StringItem stringItem8;
    private StringItem confirmEmailStringItem;
    private StringItem confirmPhoneStringItem;
    private StringItem confirmFirstNameStringItem;
    private StringItem confirmLastNameStringItem;
    private StringItem confirmPasswordStringItem;
    private StringItem confirmTownStringItem;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The HelloMIDlet constructor.
     */
    public PimMidlet() {
        activeService = null;
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    /**
     * Switches a display to previous displayable of the current displayable.
     * The <code>display</code> instance is obtain from the <code>getDisplay</code> method.
     */
    private void switchToPreviousDisplayable() {
        Displayable __currentDisplayable = getDisplay().getCurrent();
        if (__currentDisplayable != null) {
            Displayable __nextDisplayable = (Displayable) __previousDisplayables.get(__currentDisplayable);
            if (__nextDisplayable != null) {
                switchDisplayable(null, __nextDisplayable);
            }
        }
    }
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getSignInForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        Displayable __currentDisplayable = display.getCurrent();
        if (__currentDisplayable != null  &&  nextDisplayable != null) {
            __previousDisplayables.put(nextDisplayable, __currentDisplayable);
        }
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == aboutForm) {//GEN-BEGIN:|7-commandAction|1|127-preAction
            if (command == backCommand3) {//GEN-END:|7-commandAction|1|127-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|2|127-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|3|172-preAction
        } else if (displayable == activationKeyForm) {
            if (command == backCommand1) {//GEN-END:|7-commandAction|3|172-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainForm());//GEN-LINE:|7-commandAction|4|172-postAction
                // write post-action user code here
            } else if (command == exitCommand3) {//GEN-LINE:|7-commandAction|5|178-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|6|178-postAction
                // write post-action user code here
            } else if (command == submitActivationCommand) {//GEN-LINE:|7-commandAction|7|212-preAction
                String strKey = activationKeyField.getString();
                try {
                    Long.parseLong(strKey);
                } catch (NumberFormatException ex) {
                    GuiUtils.alertUser("Failure", "You entered invalid PIN code.", AlertType.ERROR, getDisplay(), getActivationKeyForm());
                    return;
                }
                Integer activationResult = null;
                try {
                    Vector params = new Vector();
                    params.addElement(user.getId());
                    params.addElement(strKey);
                    activationResult = (Integer) XmlRpc.get().getRpc().execute("PimManager.activateAccount", params);
                    if (activationResult == null || activationResult.intValue() == MessageManager.MESSAGE_ERROR) {
                        throw new Exception();
                    }
                    if (activationResult.intValue() == MessageManager.MESSAGE_SUCCESS) {
                        user.setPaid(Boolean.TRUE);
                        GuiUtils.alertUser("Success", "Your account has been activated.", AlertType.CONFIRMATION, getDisplay(), getMainForm());
                        return;
                    } else if (activationResult.intValue() == MessageManager.MESSAGE_FAILURE) {
                        user.setPaid(Boolean.FALSE);
                        GuiUtils.alertUser("Failure", "You entered invalid PIN code.", AlertType.ERROR, getDisplay(), getActivationKeyForm());
                        return;
                    }
                } catch (Exception ex) {
                    GuiUtils.alertUser("Failure", "Cannot activate your account right now. Please try later.", AlertType.ERROR, getDisplay(), getActivationKeyForm());
                    return;
                }
                switchDisplayable(null, getWaitingForm());//GEN-LINE:|7-commandAction|8|212-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|194-preAction
        } else if (displayable == confirmRegistrationForm) {
            if (command == backCommand4) {//GEN-END:|7-commandAction|9|194-preAction


                switchDisplayable(null, getSignUpForm());//GEN-LINE:|7-commandAction|10|194-postAction
                // write post-action user code here
            } else if (command == exitCommand4) {//GEN-LINE:|7-commandAction|11|197-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|12|197-postAction
                // write post-action user code here
            } else if (command == signUpSubmitCommand) {//GEN-LINE:|7-commandAction|13|206-preAction

                System.out.println("phone=" + getConfirmPhoneStringItem().getText());
                System.out.println("pass=" + getConfirmPasswordStringItem().getText());
                String phone = Base64.encode(getConfirmPhoneStringItem().getText().getBytes());
                String password = Base64.encode(getConfirmPasswordStringItem().getText().getBytes());
                Vector params = new Vector();
                params.addElement(phone);
                params.addElement(password);


                params.addElement(getConfirmFirstNameStringItem().getText().trim());
                params.addElement(getConfirmLastNameStringItem().getText().trim());
                params.addElement(getConfirmEmailStringItem().getText().trim());
                params.addElement(getConfirmTownStringItem().getText().trim());
                try {
                    Integer result = (Integer) XmlRpc.get().getRpc().execute("PimManager.register", params);
                    if (result.intValue() == MessageManager.MESSAGE_SUCCESS) {
                        GuiUtils.alertUser("Success", "Your account is successfully created.", AlertType.CONFIRMATION, getDisplay(), getSignUpCompletedForm());

                    } else if (result.intValue() == MessageManager.MESSAGE_FAILURE) {
                        GuiUtils.alertUser("Warning", "User with that phone number is already registered", AlertType.WARNING, getDisplay(), getSignUpForm());
                        return;
                    } else {
                        GuiUtils.alertUser("Error", "There was an error while creating your account. Try again.", AlertType.ERROR, getDisplay(), getSignUpForm());
                        return;
                    }
                } catch (Exception ex) {
                    GuiUtils.alertUser("Error", "There was an error while creating your account. Try again.", AlertType.ERROR, getDisplay(), getSignUpForm());
                    return;
                }
//GEN-LINE:|7-commandAction|14|206-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|133-preAction
        } else if (displayable == mainForm) {
            if (command == aboutUsCommand2) {//GEN-END:|7-commandAction|15|133-preAction
                // write pre-action user code here
                switchDisplayable(null, getAboutForm());//GEN-LINE:|7-commandAction|16|133-postAction
                // write post-action user code here
            } else if (command == enterKeyCommand) {//GEN-LINE:|7-commandAction|17|167-preAction
                // write pre-action user code here
                switchDisplayable(null, getActivationKeyForm());//GEN-LINE:|7-commandAction|18|167-postAction
                // write post-action user code here
            } else if (command == logoutCommand) {//GEN-LINE:|7-commandAction|19|37-preAction
                user = null;
                GuiUtils.alertUser("", "Logged out", AlertType.CONFIRMATION, getDisplay(), getSignInForm());
//GEN-LINE:|7-commandAction|20|37-postAction
                // write post-action user code here
            } else if (command == processOperationCommand) {//GEN-LINE:|7-commandAction|21|209-preAction
                if (!AppUtils.isPIMSupported()) {
                    GuiUtils.alertUser("Fatal error", "Your mobile phone do not supports phone book API, this application cannot work. We're sorry.", AlertType.ERROR, getDisplay(), getMainForm());
                    return;
                }
                if (user.getPaid() == Boolean.FALSE) {
                    GuiUtils.alertUser("Info", "This is paid service. Please, follow our instructions to activate your account.", AlertType.WARNING, getDisplay(), getPaymentInfoForm());
                    return;
                }
                ContactList contacts = null;
                try {
                    contacts = (ContactList) PIM.getInstance().openPIMList(PIM.CONTACT_LIST, PIM.READ_WRITE);
                } catch (PIMException pe) {
                    GuiUtils.alertUser("Fatal error", "Your mobile phone do not supports phone book API. We're sorry.", AlertType.ERROR, getDisplay(), getMainForm());
                    return;
                } catch (SecurityException se) {
                    GuiUtils.alertUser("Fatal error", "Your mobile phone do not supports phone book API. We're sorry.", AlertType.ERROR, getDisplay(), getMainForm());
                    return;
                } catch (IllegalArgumentException iae) {
                    GuiUtils.alertUser("Fatal error", "Your contacts structure is note valid. This application won't work. We're sorry.", AlertType.ERROR, getDisplay(), getMainForm());
                    iae.printStackTrace();
                    return;
                }

                int selectedOperation = operationsPopup.getSelectedIndex();
                Thread process = null;
                switch (selectedOperation) {
                    case 0: // sync contacts
                        activeService = new SendService(this, user, contacts);
                        break;
                    case 1: // retrieve contacts
                        activeService = new FetchService(this, user, contacts);
                        break;
                    case 2: // delete all contacts from DB
                        activeService = new DeleteService(this, user);
                        break;
                    default:
                        //error
                        return;
                }
                activeService.setParentDisplayable(mainForm);
                process = new Thread(activeService);
                process.start();
                switchDisplayable(null, getWaitingForm());//GEN-LINE:|7-commandAction|22|209-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|23|163-preAction
        } else if (displayable == paymentInfoForm) {
            if (command == backCommand) {//GEN-END:|7-commandAction|23|163-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainForm());//GEN-LINE:|7-commandAction|24|163-postAction
                // write post-action user code here
            } else if (command == exitCommand2) {//GEN-LINE:|7-commandAction|25|160-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|26|160-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|27|65-preAction
        } else if (displayable == recoverPassForm) {
            if (command == backCommand3) {//GEN-END:|7-commandAction|27|65-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|28|65-postAction
                // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|29|56-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|30|56-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|31|130-preAction
        } else if (displayable == signInForm) {
            if (command == aboutUsCommand) {//GEN-END:|7-commandAction|31|130-preAction
                // write pre-action user code here
                switchDisplayable(null, getAboutForm());//GEN-LINE:|7-commandAction|32|130-postAction
                // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|33|19-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|34|19-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|35|102-preAction
        } else if (displayable == signUpCompletedForm) {
            if (command == backToSignInCommand3) {//GEN-END:|7-commandAction|35|102-preAction
                // write pre-action user code here
                switchDisplayable(null, getSignInForm());//GEN-LINE:|7-commandAction|36|102-postAction
                // write post-action user code here
            } else if (command == exitCommand1) {//GEN-LINE:|7-commandAction|37|105-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|38|105-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|39|89-preAction
        } else if (displayable == signUpForm) {
            if (command == backCommand2) {//GEN-END:|7-commandAction|39|89-preAction
                // write pre-action user code here

                switchDisplayable(null, getSignInForm());//GEN-LINE:|7-commandAction|40|89-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|41|120-preAction
        } else if (displayable == waitingForm) {
            if (command == cancelOperationCommand) {//GEN-END:|7-commandAction|41|120-preAction
                if (activeService != null) {
                    activeService.setCanceled(true);
                }
//GEN-LINE:|7-commandAction|42|120-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|43|7-postCommandAction
        }//GEN-END:|7-commandAction|43|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|44|
    //</editor-fold>//GEN-END:|7-commandAction|44|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|18-getter|0|18-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|18-getter|0|18-preInit
            if (user != null) {
                user = null;
            }
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|18-getter|1|18-postInit
            // write post-init user code here
        }//GEN-BEGIN:|18-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|18-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signInForm ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of signInForm component.
     * @return the initialized component instance
     */
    public Form getSignInForm() {
        if (signInForm == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            signInForm = new Form("Sign in", new Item[] { getPhoneField(), getPasswordField(), getLoginField(), getForgotPassField(), getSignUpField() });//GEN-BEGIN:|14-getter|1|14-postInit
            signInForm.addCommand(getExitCommand());
            signInForm.addCommand(getAboutUsCommand());
            signInForm.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return signInForm;
    }
    //</editor-fold>//GEN-END:|14-getter|2|
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: phoneField ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of phoneField component.
     * @return the initialized component instance
     */
    public TextField getPhoneField() {
        if (phoneField == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            phoneField = new TextField("Your phone number:", null, 50, TextField.PHONENUMBER);//GEN-LINE:|22-getter|1|22-postInit
            // write post-init user code here
        }//GEN-BEGIN:|22-getter|2|
        return phoneField;
    }
    //</editor-fold>//GEN-END:|22-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: passwordField ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initiliazed instance of passwordField component.
     * @return the initialized component instance
     */
    public TextField getPasswordField() {
        if (passwordField == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            passwordField = new TextField("Password:", null, 20, TextField.ANY | TextField.PASSWORD | TextField.SENSITIVE);//GEN-LINE:|23-getter|1|23-postInit
            // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return passwordField;
    }
    //</editor-fold>//GEN-END:|23-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signInCommand ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initiliazed instance of signInCommand component.
     * @return the initialized component instance
     */
    public Command getSignInCommand() {
        if (signInCommand == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            signInCommand = new Command("Sign in", Command.ITEM, 0);//GEN-LINE:|24-getter|1|24-postInit
            // write post-init user code here
        }//GEN-BEGIN:|24-getter|2|
        return signInCommand;
    }
    //</editor-fold>//GEN-END:|24-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Items ">//GEN-BEGIN:|17-itemCommandAction|0|17-preItemCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular item.
     * @param command the Command that was invoked
     * @param displayable the Item where the command was invoked
     */
    public void commandAction(Command command, Item item) {//GEN-END:|17-itemCommandAction|0|17-preItemCommandAction
        // write pre-action user code here
        if (item == forgotPassField) {//GEN-BEGIN:|17-itemCommandAction|1|41-preAction
            if (command == recoverPassInitCommand) {//GEN-END:|17-itemCommandAction|1|41-preAction

                switchDisplayable(null, getRecoverPassForm());//GEN-LINE:|17-itemCommandAction|2|41-postAction
                // write post-action user code here
            }//GEN-BEGIN:|17-itemCommandAction|3|97-preAction
        } else if (item == loginField) {
            if (command == signInCommand) {//GEN-END:|17-itemCommandAction|3|97-preAction
                String phone = AppUtils.formatPhone(phoneField.getString().trim());
                String password = passwordField.getString().trim();
                if (!AppUtils.isValidPhone(phone) || !AppUtils.isValidPassword(password)) {
                    GuiUtils.alertUser("Warning", "Incorrect phone number or password", AlertType.WARNING, getDisplay(), getSignInForm());
                    return;
                } else {
                    try {
                        phone = Base64.encode(phone.getBytes());
                        password = Base64.encode(password.getBytes());
                    } catch (Exception npe) {
                        GuiUtils.alertUser("Warning", "Credentials problem. Cannot check your login.", AlertType.WARNING, getDisplay(), getSignInForm());
                        return;
                    }

                    Vector params = new Vector();
                    params.addElement(phone);
                    params.addElement(password);
                    Hashtable resultHash = null;

                    try {
                        resultHash = (Hashtable) XmlRpc.get().getRpc().execute("PimManager.login", params);

                    } catch (Exception ex) {
                        GuiUtils.alertUser("Error", "Error in communication with server", AlertType.ERROR, getDisplay(), getSignInForm());
                        ex.printStackTrace();
                        return;
                    }
                    String loginSuccess = null;
                    if (resultHash == null) {
                        GuiUtils.alertUser("Error", "Error in communication with web service", AlertType.ERROR, getDisplay(), getSignInForm());
                        return;
                    }
                    loginSuccess = (String) resultHash.get("loginSuccess");
                    if (loginSuccess.equals("true")) {
                        try {
                            Integer id = Integer.valueOf((String) resultHash.get("id"));
                            user = new User(id);

                            String firstName = (String) resultHash.get("firstName");
                            if (firstName == null) {
                                GuiUtils.alertUser("Error", "Login firstName = null", AlertType.ERROR, getDisplay(), getSignInForm());
                                return;
                            }

                            user.setFirstName(firstName);
                            Integer intPaid = Integer.valueOf((String) resultHash.get("paid"));
                            if (intPaid == null) {
                                GuiUtils.alertUser("Error", "Login paid = null", AlertType.ERROR, getDisplay(), getSignInForm());
                                return;
                            }
                            Boolean paid = (intPaid.intValue() == 1 ? Boolean.TRUE : Boolean.FALSE);
                            user.setPaid(paid);
                            String paymentURL = (String) resultHash.get("paymentURL");
                            user.setPaymentURL(paymentURL);
                            String strLastSyncedLong = (String) resultHash.get("lastSyncedLong");
                            long lastSyncedLong = Long.parseLong(strLastSyncedLong);
                            Date lastSynced = new Date(lastSyncedLong);
                            Date now = new Date(System.currentTimeMillis());
                            double timeSpanMinutes = AppUtils.getTimeSpanInMinutes(now, lastSynced);
                            double timeSpanInDays = timeSpanMinutes / 60 / 24;
                            if (lastSyncedLong == 0 || timeSpanInDays > 7) { // difference when last synced greater than 7 days (in minutes)
                                //GuiUtils.alertUser("Scheduler", "Last Synced: " + lastSynced + ". You haven't synced your contacts recently. Please upload your contacts", AlertType.INFO, getDisplay(), getMainForm());
                                final Command yesCommand = new Command("Yes", Command.SCREEN, 0);
                                final Command noCommand = new Command("No", Command.EXIT, 0);
                                String question = null;
                                if (lastSyncedLong == 0) {
                                    question = "You are using this service for first time. Do you want to upload all contacts now?";
                                } else {
                                    question = "You uploaded contacts before " + Math.floor(timeSpanInDays) + " days. Do you want to upload all contacts now?";
                                }
                                Alert a = new Alert("");
                                a.setString(question);
                                a.setTimeout(Alert.FOREVER);
                                a.setType(AlertType.CONFIRMATION);
                                a.addCommand(yesCommand);
                                a.addCommand(noCommand);
                                a.setCommandListener(new CommandListener() {

                                    public void commandAction(Command c, Displayable d) {
                                        if (c == yesCommand) {
                                            syncCommandFired();
                                        } else if (c == noCommand) {
                                            switchDisplayable(null, getMainForm());
                                        }
                                    }
                                });
                                getDisplay().setCurrent(a);

                            } else {
                                GuiUtils.alertUser("Info", "Login successful", AlertType.INFO, getDisplay(), getMainForm());
                            }
                        } catch (NullPointerException ex) {
                            GuiUtils.alertUser("Error", "Oops! Login failed. Please, try again.", AlertType.ERROR, getDisplay(), getSignInForm());
                            ex.printStackTrace();
                            return;
                        }

                    } else if (loginSuccess.equals("false")) {
                        String message = "Incorrect phone number or password";
                        GuiUtils.alertUser("Warning", message, AlertType.WARNING, getDisplay(), getSignInForm());
                        return;
                    }

                }
//GEN-LINE:|17-itemCommandAction|4|97-postAction
                // write post-action user code here
            }//GEN-BEGIN:|17-itemCommandAction|5|68-preAction
        } else if (item == sendPassBtn) {
            if (command == recoverPassProcessCommand) {//GEN-END:|17-itemCommandAction|5|68-preAction
                String phone = AppUtils.formatPhone(recoverPhoneField.getString().trim());
                if (!AppUtils.isValidPhone(phone)) {
                    GuiUtils.alertUser("Warning", "Phone number is not valid. Enter phone in international format (only ciphers allowed).", AlertType.WARNING, getDisplay(), getSignInForm());
                    return;
                }
                phone = Base64.encode(phone.getBytes());
                Vector params = new Vector();
                params.addElement(phone);
                try {
                    Integer result = (Integer) XmlRpc.get().getRpc().execute("PimManager.recoverPassword", params);
                    if (result.intValue() == MessageManager.MESSAGE_FAILURE) {
                        GuiUtils.alertUser("Warning", "There is no registered user with that phone number", AlertType.WARNING, getDisplay(), getRecoverPassForm());
                        return;
                    } else if (result.intValue() == MessageManager.MESSAGE_SUCCESS) {
                        GuiUtils.alertUser("Pasword sent", "Password is sent on your email address.", AlertType.CONFIRMATION, getDisplay(), getSignInForm());
                    } else {
                        GuiUtils.alertUser("Error", "Cannot send you a password. There was an error.Please, try again.", AlertType.ERROR, getDisplay(), getRecoverPassForm());
                        return;
                    }
                } catch (Exception ex) {
                    GuiUtils.alertUser("Error", "Cannot send you a password. There was an error.Please, try again.", AlertType.ERROR, getDisplay(), getRecoverPassForm());
                    return;
                }
//GEN-LINE:|17-itemCommandAction|6|68-postAction
                // write post-action user code here
            }//GEN-BEGIN:|17-itemCommandAction|7|72-preAction
        } else if (item == signUpField) {
            if (command == signUpInitCommand) {//GEN-END:|17-itemCommandAction|7|72-preAction
                // write pre-action user code here
                switchDisplayable(null, getSignUpForm());//GEN-LINE:|17-itemCommandAction|8|72-postAction
                // write post-action user code here
            }//GEN-BEGIN:|17-itemCommandAction|9|203-preAction
        } else if (item == signUpProcessField) {
            if (command == signUpNextCommand) {//GEN-END:|17-itemCommandAction|9|203-preAction
                String phone = AppUtils.formatPhone(signUpPhoneField.getString().trim().toLowerCase());
                String password = signUpPasswordField.getString().trim().toLowerCase();
                if (!AppUtils.isValidPhone(phone)) {
                    GuiUtils.alertUser("Warning", "Phone number is not valid. Enter phone in international format (only ciphers allowed).", AlertType.WARNING, getDisplay(), getSignInForm());
                    return;
                }
                if (!AppUtils.isValidPassword(password)) {
                    GuiUtils.alertUser("Warning", "Password must be long between 5 and 20 characters.", AlertType.WARNING, getDisplay(), getSignUpForm());
                    return;
                }
                if (!AppUtils.isValidEmail(signUpEmailField.getString().trim(), true)) {
                    GuiUtils.alertUser("Warning", "Email is incorrect.", AlertType.WARNING, getDisplay(), getSignUpForm());
                    return;
                } else {
                    getConfirmPhoneStringItem().setText(phone);
                    getConfirmPasswordStringItem().setText(password);
                    getConfirmFirstNameStringItem().setText(signUpFirstNameField.getString().trim());
                    getConfirmLastNameStringItem().setText(signUpLastNameField.getString().trim());
                    getConfirmEmailStringItem().setText(signUpEmailField.getString().trim());
                    getConfirmTownStringItem().setText(signUpTownField.getString().trim());

                    switchDisplayable(null, getConfirmRegistrationForm());
//GEN-LINE:|17-itemCommandAction|10|203-postAction
                    // write post-action user code here
                }//GEN-BEGIN:|17-itemCommandAction|11|33-preAction
            } else if (item == stringItem) {
                if (command == exitCommand) {//GEN-END:|17-itemCommandAction|11|33-preAction
                    // write pre-action user code here
                    exitMIDlet();//GEN-LINE:|17-itemCommandAction|12|33-postAction
                    // write post-action user code here
                }//GEN-BEGIN:|17-itemCommandAction|13|157-preAction
            } else if (item == stringItem5) {
                if (command == payLinkCommand) {//GEN-END:|17-itemCommandAction|13|157-preAction
                    try {
                        platformRequest(user.getPaymentURL());
                    } catch (ConnectionNotFoundException ex) {
                    }
//GEN-LINE:|17-itemCommandAction|14|157-postAction
                    // write post-action user code here
                }//GEN-BEGIN:|17-itemCommandAction|15|17-postItemCommandAction
            }//GEN-END:|17-itemCommandAction|15|17-postItemCommandAction
            // write post-action user code here
        }//GEN-BEGIN:|17-itemCommandAction|16|
        //</editor-fold>//GEN-END:|17-itemCommandAction|16|



    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: mainForm ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initiliazed instance of mainForm component.
     * @return the initialized component instance
     */
    public Form getMainForm() {
        if (mainForm == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            mainForm = new Form("MyBackup", new Item[] { getStringItem(), getOperationsPopup() });//GEN-BEGIN:|26-getter|1|26-postInit
            mainForm.addCommand(getLogoutCommand());
            mainForm.addCommand(getAboutUsCommand2());
            mainForm.addCommand(getEnterKeyCommand());
            mainForm.addCommand(getProcessOperationCommand());
            mainForm.setCommandListener(this);//GEN-END:|26-getter|1|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return mainForm;
    }
    //</editor-fold>//GEN-END:|26-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            stringItem = new StringItem("Welcome, " + user.getFirstName(), "Please, select operation.");//GEN-BEGIN:|27-getter|1|27-postInit
            stringItem.addCommand(getExitCommand());
            stringItem.setItemCommandListener(this);//GEN-END:|27-getter|1|27-postInit
            // write post-init user code here
        }//GEN-BEGIN:|27-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|27-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: operationsPopup ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initiliazed instance of operationsPopup component.
     * @return the initialized component instance
     */
    public ChoiceGroup getOperationsPopup() {
        if (operationsPopup == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            operationsPopup = new ChoiceGroup("Operations:", Choice.POPUP);//GEN-BEGIN:|28-getter|1|28-postInit
            operationsPopup.append("Sync/Backup contacts", null);
            operationsPopup.append("Retrieve contacts", null);
            operationsPopup.append("Delete contacts", null);
            operationsPopup.setFitPolicy(Choice.TEXT_WRAP_DEFAULT);
            operationsPopup.setSelectedFlags(new boolean[] { false, false, false });//GEN-END:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return operationsPopup;
    }
    //</editor-fold>//GEN-END:|28-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: logoutCommand ">//GEN-BEGIN:|36-getter|0|36-preInit
    /**
     * Returns an initiliazed instance of logoutCommand component.
     * @return the initialized component instance
     */
    public Command getLogoutCommand() {
        if (logoutCommand == null) {//GEN-END:|36-getter|0|36-preInit
            // write pre-init user code here
            logoutCommand = new Command("Logout", Command.ITEM, 0);//GEN-LINE:|36-getter|1|36-postInit
            // write post-init user code here
        }//GEN-BEGIN:|36-getter|2|
        return logoutCommand;
    }
    //</editor-fold>//GEN-END:|36-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: recoverPassInitCommand ">//GEN-BEGIN:|40-getter|0|40-preInit
    /**
     * Returns an initiliazed instance of recoverPassInitCommand component.
     * @return the initialized component instance
     */
    public Command getRecoverPassInitCommand() {
        if (recoverPassInitCommand == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
            recoverPassInitCommand = new Command("I forgot my pass", Command.ITEM, 0);//GEN-LINE:|40-getter|1|40-postInit
            // write post-init user code here
        }//GEN-BEGIN:|40-getter|2|
        return recoverPassInitCommand;
    }
    //</editor-fold>//GEN-END:|40-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: forgotPassField ">//GEN-BEGIN:|39-getter|0|39-preInit
    /**
     * Returns an initiliazed instance of forgotPassField component.
     * @return the initialized component instance
     */
    public StringItem getForgotPassField() {
        if (forgotPassField == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            forgotPassField = new StringItem("", "I forgot my password", Item.HYPERLINK);//GEN-BEGIN:|39-getter|1|39-postInit
            forgotPassField.addCommand(getRecoverPassInitCommand());
            forgotPassField.setItemCommandListener(this);
            forgotPassField.setDefaultCommand(getRecoverPassInitCommand());//GEN-END:|39-getter|1|39-postInit
            // write post-init user code here
        }//GEN-BEGIN:|39-getter|2|
        return forgotPassField;
    }
    //</editor-fold>//GEN-END:|39-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: recoverPassForm ">//GEN-BEGIN:|45-getter|0|45-preInit
    /**
     * Returns an initiliazed instance of recoverPassForm component.
     * @return the initialized component instance
     */
    public Form getRecoverPassForm() {
        if (recoverPassForm == null) {//GEN-END:|45-getter|0|45-preInit
            // write pre-init user code here
            recoverPassForm = new Form("Recovering password", new Item[] { getStringItem2(), getRecoverPhoneField(), getSendPassBtn() });//GEN-BEGIN:|45-getter|1|45-postInit
            recoverPassForm.addCommand(getExitCommand());
            recoverPassForm.addCommand(getBackCommand3());
            recoverPassForm.setCommandListener(this);//GEN-END:|45-getter|1|45-postInit
            // write post-init user code here
        }//GEN-BEGIN:|45-getter|2|
        return recoverPassForm;
    }
    //</editor-fold>//GEN-END:|45-getter|2|
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: recoverPhoneField ">//GEN-BEGIN:|46-getter|0|46-preInit
    /**
     * Returns an initiliazed instance of recoverPhoneField component.
     * @return the initialized component instance
     */
    public TextField getRecoverPhoneField() {
        if (recoverPhoneField == null) {//GEN-END:|46-getter|0|46-preInit
            // write pre-init user code here
            recoverPhoneField = new TextField("Number:", null, 50, TextField.PHONENUMBER);//GEN-LINE:|46-getter|1|46-postInit
            // write post-init user code here
        }//GEN-BEGIN:|46-getter|2|
        return recoverPhoneField;
    }
    //</editor-fold>//GEN-END:|46-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem2 ">//GEN-BEGIN:|47-getter|0|47-preInit
    /**
     * Returns an initiliazed instance of stringItem2 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem2() {
        if (stringItem2 == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            stringItem2 = new StringItem("", "For this service, you must have email address linked with your account. ");//GEN-LINE:|47-getter|1|47-postInit
            // write post-init user code here
        }//GEN-BEGIN:|47-getter|2|
        return stringItem2;
    }
    //</editor-fold>//GEN-END:|47-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: sendPassBtn ">//GEN-BEGIN:|48-getter|0|48-preInit
    /**
     * Returns an initiliazed instance of sendPassBtn component.
     * @return the initialized component instance
     */
    public StringItem getSendPassBtn() {
        if (sendPassBtn == null) {//GEN-END:|48-getter|0|48-preInit
            // write pre-init user code here
            sendPassBtn = new StringItem("", "Send me my pass", Item.BUTTON);//GEN-BEGIN:|48-getter|1|48-postInit
            sendPassBtn.addCommand(getRecoverPassProcessCommand());
            sendPassBtn.setItemCommandListener(this);
            sendPassBtn.setDefaultCommand(getRecoverPassProcessCommand());//GEN-END:|48-getter|1|48-postInit
            // write post-init user code here
        }//GEN-BEGIN:|48-getter|2|
        return sendPassBtn;
    }
    //</editor-fold>//GEN-END:|48-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand3 ">//GEN-BEGIN:|64-getter|0|64-preInit
    /**
     * Returns an initiliazed instance of backCommand3 component.
     * @return the initialized component instance
     */
    public Command getBackCommand3() {
        if (backCommand3 == null) {//GEN-END:|64-getter|0|64-preInit
            // write pre-init user code here
            backCommand3 = new Command("Back", Command.BACK, 0);//GEN-LINE:|64-getter|1|64-postInit
            // write post-init user code here
        }//GEN-BEGIN:|64-getter|2|
        return backCommand3;
    }
    //</editor-fold>//GEN-END:|64-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: recoverPassProcessCommand ">//GEN-BEGIN:|67-getter|0|67-preInit
    /**
     * Returns an initiliazed instance of recoverPassProcessCommand component.
     * @return the initialized component instance
     */
    public Command getRecoverPassProcessCommand() {
        if (recoverPassProcessCommand == null) {//GEN-END:|67-getter|0|67-preInit
            // write pre-init user code here
            recoverPassProcessCommand = new Command("Send me my pass", Command.ITEM, 0);//GEN-LINE:|67-getter|1|67-postInit
            // write post-init user code here
        }//GEN-BEGIN:|67-getter|2|
        return recoverPassProcessCommand;
    }
    //</editor-fold>//GEN-END:|67-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpField ">//GEN-BEGIN:|70-getter|0|70-preInit
    /**
     * Returns an initiliazed instance of signUpField component.
     * @return the initialized component instance
     */
    public StringItem getSignUpField() {
        if (signUpField == null) {//GEN-END:|70-getter|0|70-preInit
            // write pre-init user code here
            signUpField = new StringItem("", "Not a member? Sign up", Item.HYPERLINK);//GEN-BEGIN:|70-getter|1|70-postInit
            signUpField.addCommand(getSignUpInitCommand());
            signUpField.setItemCommandListener(this);//GEN-END:|70-getter|1|70-postInit
            // write post-init user code here
        }//GEN-BEGIN:|70-getter|2|
        return signUpField;
    }
    //</editor-fold>//GEN-END:|70-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpInitCommand ">//GEN-BEGIN:|71-getter|0|71-preInit
    /**
     * Returns an initiliazed instance of signUpInitCommand component.
     * @return the initialized component instance
     */
    public Command getSignUpInitCommand() {
        if (signUpInitCommand == null) {//GEN-END:|71-getter|0|71-preInit
            // write pre-init user code here
            signUpInitCommand = new Command("Sign up", Command.ITEM, 0);//GEN-LINE:|71-getter|1|71-postInit
            // write post-init user code here
        }//GEN-BEGIN:|71-getter|2|
        return signUpInitCommand;
    }
    //</editor-fold>//GEN-END:|71-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpForm ">//GEN-BEGIN:|73-getter|0|73-preInit
    /**
     * Returns an initiliazed instance of signUpForm component.
     * @return the initialized component instance
     */
    public Form getSignUpForm() {
        if (signUpForm == null) {//GEN-END:|73-getter|0|73-preInit
            // write pre-init user code here
            signUpForm = new Form("Sign up", new Item[] { getSignUpPhoneField(), getSignUpPasswordField(), getSignUpFirstNameField(), getSignUpLastNameField(), getSignUpEmailField(), getSignUpTownField(), getSignUpProcessField() });//GEN-BEGIN:|73-getter|1|73-postInit
            signUpForm.addCommand(getBackCommand2());
            signUpForm.setCommandListener(this);//GEN-END:|73-getter|1|73-postInit
            // write post-init user code here
        }//GEN-BEGIN:|73-getter|2|
        return signUpForm;
    }
    //</editor-fold>//GEN-END:|73-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpPhoneField ">//GEN-BEGIN:|77-getter|0|77-preInit
    /**
     * Returns an initiliazed instance of signUpPhoneField component.
     * @return the initialized component instance
     */
    public TextField getSignUpPhoneField() {
        if (signUpPhoneField == null) {//GEN-END:|77-getter|0|77-preInit
            // write pre-init user code here
            signUpPhoneField = new TextField("Your phone number (international format, only ciphers): *", null, 50, TextField.PHONENUMBER);//GEN-LINE:|77-getter|1|77-postInit
            // write post-init user code here
        }//GEN-BEGIN:|77-getter|2|
        return signUpPhoneField;
    }
    //</editor-fold>//GEN-END:|77-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpPasswordField ">//GEN-BEGIN:|78-getter|0|78-preInit
    /**
     * Returns an initiliazed instance of signUpPasswordField component.
     * @return the initialized component instance
     */
    public TextField getSignUpPasswordField() {
        if (signUpPasswordField == null) {//GEN-END:|78-getter|0|78-preInit
            // write pre-init user code here
            signUpPasswordField = new TextField("Choose password: *", null, 20, TextField.ANY | TextField.PASSWORD | TextField.SENSITIVE);//GEN-LINE:|78-getter|1|78-postInit
            // write post-init user code here
        }//GEN-BEGIN:|78-getter|2|
        return signUpPasswordField;
    }
    //</editor-fold>//GEN-END:|78-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpFirstNameField ">//GEN-BEGIN:|79-getter|0|79-preInit
    /**
     * Returns an initiliazed instance of signUpFirstNameField component.
     * @return the initialized component instance
     */
    public TextField getSignUpFirstNameField() {
        if (signUpFirstNameField == null) {//GEN-END:|79-getter|0|79-preInit
            // write pre-init user code here
            signUpFirstNameField = new TextField("First name:", null, 45, TextField.ANY | TextField.INITIAL_CAPS_SENTENCE);//GEN-LINE:|79-getter|1|79-postInit
            // write post-init user code here
        }//GEN-BEGIN:|79-getter|2|
        return signUpFirstNameField;
    }
    //</editor-fold>//GEN-END:|79-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpLastNameField ">//GEN-BEGIN:|80-getter|0|80-preInit
    /**
     * Returns an initiliazed instance of signUpLastNameField component.
     * @return the initialized component instance
     */
    public TextField getSignUpLastNameField() {
        if (signUpLastNameField == null) {//GEN-END:|80-getter|0|80-preInit
            // write pre-init user code here
            signUpLastNameField = new TextField("Last name:", null, 45, TextField.ANY | TextField.INITIAL_CAPS_SENTENCE);//GEN-LINE:|80-getter|1|80-postInit
            // write post-init user code here
        }//GEN-BEGIN:|80-getter|2|
        return signUpLastNameField;
    }
    //</editor-fold>//GEN-END:|80-getter|2|
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpEmailField ">//GEN-BEGIN:|81-getter|0|81-preInit
    /**
     * Returns an initiliazed instance of signUpEmailField component.
     * @return the initialized component instance
     */
    public TextField getSignUpEmailField() {
        if (signUpEmailField == null) {//GEN-END:|81-getter|0|81-preInit
            // write pre-init user code here
            signUpEmailField = new TextField("Email:", null, 32, TextField.EMAILADDR);//GEN-LINE:|81-getter|1|81-postInit
            // write post-init user code here
        }//GEN-BEGIN:|81-getter|2|
        return signUpEmailField;
    }
    //</editor-fold>//GEN-END:|81-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpTownField ">//GEN-BEGIN:|82-getter|0|82-preInit
    /**
     * Returns an initiliazed instance of signUpTownField component.
     * @return the initialized component instance
     */
    public TextField getSignUpTownField() {
        if (signUpTownField == null) {//GEN-END:|82-getter|0|82-preInit
            // write pre-init user code here
            signUpTownField = new TextField("Town:", null, 45, TextField.ANY | TextField.INITIAL_CAPS_SENTENCE);//GEN-LINE:|82-getter|1|82-postInit
            // write post-init user code here
        }//GEN-BEGIN:|82-getter|2|
        return signUpTownField;
    }
    //</editor-fold>//GEN-END:|82-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpProcessField ">//GEN-BEGIN:|83-getter|0|83-preInit
    /**
     * Returns an initiliazed instance of signUpProcessField component.
     * @return the initialized component instance
     */
    public StringItem getSignUpProcessField() {
        if (signUpProcessField == null) {//GEN-END:|83-getter|0|83-preInit
            // write pre-init user code here
            signUpProcessField = new StringItem("", "Next", Item.BUTTON);//GEN-BEGIN:|83-getter|1|83-postInit
            signUpProcessField.addCommand(getSignUpNextCommand());
            signUpProcessField.setItemCommandListener(this);
            signUpProcessField.setDefaultCommand(getSignUpNextCommand());//GEN-END:|83-getter|1|83-postInit
            // write post-init user code here
        }//GEN-BEGIN:|83-getter|2|
        return signUpProcessField;
    }
    //</editor-fold>//GEN-END:|83-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand2 ">//GEN-BEGIN:|88-getter|0|88-preInit
    /**
     * Returns an initiliazed instance of backCommand2 component.
     * @return the initialized component instance
     */
    public Command getBackCommand2() {
        if (backCommand2 == null) {//GEN-END:|88-getter|0|88-preInit
            // write pre-init user code here
            backCommand2 = new Command("Back", Command.BACK, 0);//GEN-LINE:|88-getter|1|88-postInit
            // write post-init user code here
        }//GEN-BEGIN:|88-getter|2|
        return backCommand2;
    }
    //</editor-fold>//GEN-END:|88-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: loginField ">//GEN-BEGIN:|96-getter|0|96-preInit
    /**
     * Returns an initiliazed instance of loginField component.
     * @return the initialized component instance
     */
    public StringItem getLoginField() {
        if (loginField == null) {//GEN-END:|96-getter|0|96-preInit
            // write pre-init user code here
            loginField = new StringItem("", "Login", Item.BUTTON);//GEN-BEGIN:|96-getter|1|96-postInit
            loginField.addCommand(getSignInCommand());
            loginField.setItemCommandListener(this);
            loginField.setDefaultCommand(getSignInCommand());//GEN-END:|96-getter|1|96-postInit
            // write post-init user code here
        }//GEN-BEGIN:|96-getter|2|
        return loginField;
    }
    //</editor-fold>//GEN-END:|96-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpCompletedForm ">//GEN-BEGIN:|99-getter|0|99-preInit
    /**
     * Returns an initiliazed instance of signUpCompletedForm component.
     * @return the initialized component instance
     */
    public Form getSignUpCompletedForm() {
        if (signUpCompletedForm == null) {//GEN-END:|99-getter|0|99-preInit
            // write pre-init user code here
            signUpCompletedForm = new Form("Registration success", new Item[] { getStringItem1() });//GEN-BEGIN:|99-getter|1|99-postInit
            signUpCompletedForm.addCommand(getBackToSignInCommand3());
            signUpCompletedForm.addCommand(getExitCommand1());
            signUpCompletedForm.setCommandListener(this);//GEN-END:|99-getter|1|99-postInit
            // write post-init user code here
        }//GEN-BEGIN:|99-getter|2|
        return signUpCompletedForm;
    }
    //</editor-fold>//GEN-END:|99-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem1 ">//GEN-BEGIN:|100-getter|0|100-preInit
    /**
     * Returns an initiliazed instance of stringItem1 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem1() {
        if (stringItem1 == null) {//GEN-END:|100-getter|0|100-preInit
            // write pre-init user code here
            stringItem1 = new StringItem("Congratulations", "Your account was created. You can login now. Thank you for using MyBackup service.");//GEN-LINE:|100-getter|1|100-postInit
            // write post-init user code here
        }//GEN-BEGIN:|100-getter|2|
        return stringItem1;
    }
    //</editor-fold>//GEN-END:|100-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backToSignInCommand3 ">//GEN-BEGIN:|101-getter|0|101-preInit
    /**
     * Returns an initiliazed instance of backToSignInCommand3 component.
     * @return the initialized component instance
     */
    public Command getBackToSignInCommand3() {
        if (backToSignInCommand3 == null) {//GEN-END:|101-getter|0|101-preInit
            // write pre-init user code here
            backToSignInCommand3 = new Command("Go to Sign in", Command.BACK, 0);//GEN-LINE:|101-getter|1|101-postInit
            // write post-init user code here
        }//GEN-BEGIN:|101-getter|2|
        return backToSignInCommand3;
    }
    //</editor-fold>//GEN-END:|101-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|104-getter|0|104-preInit
    /**
     * Returns an initiliazed instance of exitCommand1 component.
     * @return the initialized component instance
     */
    public Command getExitCommand1() {
        if (exitCommand1 == null) {//GEN-END:|104-getter|0|104-preInit
            // write pre-init user code here
            exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|104-getter|1|104-postInit
            // write post-init user code here
        }//GEN-BEGIN:|104-getter|2|
        return exitCommand1;
    }
    //</editor-fold>//GEN-END:|104-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: waitingForm ">//GEN-BEGIN:|115-getter|0|115-preInit
    /**
     * Returns an initiliazed instance of waitingForm component.
     * @return the initialized component instance
     */
    public Form getWaitingForm() {
        if (waitingForm == null) {//GEN-END:|115-getter|0|115-preInit
            // write pre-init user code here
            waitingForm = new Form("", new Item[] { getGauge(), getProgressMessageField() });//GEN-BEGIN:|115-getter|1|115-postInit
            waitingForm.addCommand(getCancelOperationCommand());
            waitingForm.setCommandListener(this);//GEN-END:|115-getter|1|115-postInit
            // write post-init user code here
        }//GEN-BEGIN:|115-getter|2|
        return waitingForm;
    }
    //</editor-fold>//GEN-END:|115-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: gauge ">//GEN-BEGIN:|117-getter|0|117-preInit
    /**
     * Returns an initiliazed instance of gauge component.
     * @return the initialized component instance
     */
    public Gauge getGauge() {
        if (gauge == null) {//GEN-END:|117-getter|0|117-preInit
            // write pre-init user code here
            gauge = new Gauge("Processing your request", false, Gauge.INDEFINITE, Gauge.CONTINUOUS_IDLE);//GEN-BEGIN:|117-getter|1|117-postInit
            gauge.setLayout(ImageItem.LAYOUT_DEFAULT | Item.LAYOUT_TOP | Item.LAYOUT_BOTTOM | Item.LAYOUT_VCENTER);//GEN-END:|117-getter|1|117-postInit
            gauge.setValue(Gauge.CONTINUOUS_RUNNING);
        }//GEN-BEGIN:|117-getter|2|
        return gauge;
    }
    //</editor-fold>//GEN-END:|117-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelOperationCommand ">//GEN-BEGIN:|119-getter|0|119-preInit
    /**
     * Returns an initiliazed instance of cancelOperationCommand component.
     * @return the initialized component instance
     */
    public Command getCancelOperationCommand() {
        if (cancelOperationCommand == null) {//GEN-END:|119-getter|0|119-preInit
            // write pre-init user code here
            cancelOperationCommand = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|119-getter|1|119-postInit
            // write post-init user code here
        }//GEN-BEGIN:|119-getter|2|
        return cancelOperationCommand;
    }
    //</editor-fold>//GEN-END:|119-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: progressMessageField ">//GEN-BEGIN:|118-getter|0|118-preInit
    /**
     * Returns an initiliazed instance of progressMessageField component.
     * @return the initialized component instance
     */
    public StringItem getProgressMessageField() {
        if (progressMessageField == null) {//GEN-END:|118-getter|0|118-preInit
            // write pre-init user code here
            progressMessageField = new StringItem("", null, Item.PLAIN);//GEN-BEGIN:|118-getter|1|118-postInit
            progressMessageField.setLayout(ImageItem.LAYOUT_CENTER);//GEN-END:|118-getter|1|118-postInit
            // write post-init user code here
        }//GEN-BEGIN:|118-getter|2|
        return progressMessageField;
    }
    //</editor-fold>//GEN-END:|118-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: aboutForm ">//GEN-BEGIN:|121-getter|0|121-preInit
    /**
     * Returns an initiliazed instance of aboutForm component.
     * @return the initialized component instance
     */
    public Form getAboutForm() {
        if (aboutForm == null) {//GEN-END:|121-getter|0|121-preInit
            // write pre-init user code here
            aboutForm = new Form("About MyBackup", new Item[] { getStringItem3() });//GEN-BEGIN:|121-getter|1|121-postInit
            aboutForm.addCommand(getBackCommand3());
            aboutForm.setCommandListener(this);//GEN-END:|121-getter|1|121-postInit
            // write post-init user code here
        }//GEN-BEGIN:|121-getter|2|
        return aboutForm;
    }
    //</editor-fold>//GEN-END:|121-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem3 ">//GEN-BEGIN:|122-getter|0|122-preInit
    /**
     * Returns an initiliazed instance of stringItem3 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem3() {
        if (stringItem3 == null) {//GEN-END:|122-getter|0|122-preInit
            // write pre-init user code here
            stringItem3 = new StringItem("MyBackup v1.0", "MyBackup is cloud backup service for world-wide different mobile phones. It is based on JavaME technology and supports over a thousand phone models. Backup service is focused on phone book synchronization for now. Visit our site for more information.");//GEN-LINE:|122-getter|1|122-postInit
            // write post-init user code here
        }//GEN-BEGIN:|122-getter|2|
        return stringItem3;
    }
    //</editor-fold>//GEN-END:|122-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: aboutUsCommand ">//GEN-BEGIN:|129-getter|0|129-preInit
    /**
     * Returns an initiliazed instance of aboutUsCommand component.
     * @return the initialized component instance
     */
    public Command getAboutUsCommand() {
        if (aboutUsCommand == null) {//GEN-END:|129-getter|0|129-preInit
            // write pre-init user code here
            aboutUsCommand = new Command("About MyContacts", Command.SCREEN, 0);//GEN-LINE:|129-getter|1|129-postInit
            // write post-init user code here
        }//GEN-BEGIN:|129-getter|2|
        return aboutUsCommand;
    }
    //</editor-fold>//GEN-END:|129-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: aboutUsCommand2 ">//GEN-BEGIN:|132-getter|0|132-preInit
    /**
     * Returns an initiliazed instance of aboutUsCommand2 component.
     * @return the initialized component instance
     */
    public Command getAboutUsCommand2() {
        if (aboutUsCommand2 == null) {//GEN-END:|132-getter|0|132-preInit
            // write pre-init user code here
            aboutUsCommand2 = new Command("About MyBackup", Command.SCREEN, 0);//GEN-LINE:|132-getter|1|132-postInit
            // write post-init user code here
        }//GEN-BEGIN:|132-getter|2|
        return aboutUsCommand2;
    }
    //</editor-fold>//GEN-END:|132-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: payLinkCommand ">//GEN-BEGIN:|156-getter|0|156-preInit
    /**
     * Returns an initiliazed instance of payLinkCommand component.
     * @return the initialized component instance
     */
    public Command getPayLinkCommand() {
        if (payLinkCommand == null) {//GEN-END:|156-getter|0|156-preInit
            // write pre-init user code here
            payLinkCommand = new Command("Navigate", Command.ITEM, 0);//GEN-LINE:|156-getter|1|156-postInit
            // write post-init user code here
        }//GEN-BEGIN:|156-getter|2|
        return payLinkCommand;
    }
    //</editor-fold>//GEN-END:|156-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: paymentInfoForm ">//GEN-BEGIN:|151-getter|0|151-preInit
    /**
     * Returns an initiliazed instance of paymentInfoForm component.
     * @return the initialized component instance
     */
    public Form getPaymentInfoForm() {
        if (paymentInfoForm == null) {//GEN-END:|151-getter|0|151-preInit
            // write pre-init user code here
            paymentInfoForm = new Form("Activation", new Item[] { getStringItem4(), getStringItem5(), getStringItem6() });//GEN-BEGIN:|151-getter|1|151-postInit
            paymentInfoForm.addCommand(getExitCommand2());
            paymentInfoForm.addCommand(getBackCommand());
            paymentInfoForm.setCommandListener(this);//GEN-END:|151-getter|1|151-postInit
            // write post-init user code here
        }//GEN-BEGIN:|151-getter|2|
        return paymentInfoForm;
    }
    //</editor-fold>//GEN-END:|151-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem4 ">//GEN-BEGIN:|154-getter|0|154-preInit
    /**
     * Returns an initiliazed instance of stringItem4 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem4() {
        if (stringItem4 == null) {//GEN-END:|154-getter|0|154-preInit
            // write pre-init user code here
            stringItem4 = new StringItem("", "For more information about activation terms click on the link below or copy URL into your web browser.");//GEN-LINE:|154-getter|1|154-postInit
            // write post-init user code here
        }//GEN-BEGIN:|154-getter|2|
        return stringItem4;
    }
    //</editor-fold>//GEN-END:|154-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem5 ">//GEN-BEGIN:|155-getter|0|155-preInit
    /**
     * Returns an initiliazed instance of stringItem5 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem5() {
        if (stringItem5 == null) {//GEN-END:|155-getter|0|155-preInit
            // write pre-init user code here
            stringItem5 = new StringItem("", "Activation terms", Item.HYPERLINK);//GEN-BEGIN:|155-getter|1|155-postInit
            stringItem5.addCommand(getPayLinkCommand());
            stringItem5.setItemCommandListener(this);//GEN-END:|155-getter|1|155-postInit
            // write post-init user code here
        }//GEN-BEGIN:|155-getter|2|
        return stringItem5;
    }
    //</editor-fold>//GEN-END:|155-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem6 ">//GEN-BEGIN:|158-getter|0|158-preInit
    /**
     * Returns an initiliazed instance of stringItem6 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem6() {
        if (stringItem6 == null) {//GEN-END:|158-getter|0|158-preInit
            // write pre-init user code here
            stringItem6 = new StringItem("Activation URL:", user.getPaymentURL());//GEN-LINE:|158-getter|1|158-postInit
            // write post-init user code here
        }//GEN-BEGIN:|158-getter|2|
        return stringItem6;
    }
    //</editor-fold>//GEN-END:|158-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand2 ">//GEN-BEGIN:|159-getter|0|159-preInit
    /**
     * Returns an initiliazed instance of exitCommand2 component.
     * @return the initialized component instance
     */
    public Command getExitCommand2() {
        if (exitCommand2 == null) {//GEN-END:|159-getter|0|159-preInit
            // write pre-init user code here
            exitCommand2 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|159-getter|1|159-postInit
            // write post-init user code here
        }//GEN-BEGIN:|159-getter|2|
        return exitCommand2;
    }
    //</editor-fold>//GEN-END:|159-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|162-getter|0|162-preInit
    /**
     * Returns an initiliazed instance of backCommand component.
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {//GEN-END:|162-getter|0|162-preInit
            // write pre-init user code here
            backCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|162-getter|1|162-postInit
            // write post-init user code here
        }//GEN-BEGIN:|162-getter|2|
        return backCommand;
    }
    //</editor-fold>//GEN-END:|162-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: enterKeyCommand ">//GEN-BEGIN:|166-getter|0|166-preInit
    /**
     * Returns an initiliazed instance of enterKeyCommand component.
     * @return the initialized component instance
     */
    public Command getEnterKeyCommand() {
        if (enterKeyCommand == null) {//GEN-END:|166-getter|0|166-preInit
            // write pre-init user code here
            enterKeyCommand = new Command("Enter activation key", Command.SCREEN, 0);//GEN-LINE:|166-getter|1|166-postInit
            // write post-init user code here
        }//GEN-BEGIN:|166-getter|2|
        return enterKeyCommand;
    }
    //</editor-fold>//GEN-END:|166-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: activationKeyForm ">//GEN-BEGIN:|168-getter|0|168-preInit
    /**
     * Returns an initiliazed instance of activationKeyForm component.
     * @return the initialized component instance
     */
    public Form getActivationKeyForm() {
        if (activationKeyForm == null) {//GEN-END:|168-getter|0|168-preInit
            // write pre-init user code here
            activationKeyForm = new Form("Activation request", new Item[] { getStringItem7(), getActivationKeyField() });//GEN-BEGIN:|168-getter|1|168-postInit
            activationKeyForm.addCommand(getBackCommand1());
            activationKeyForm.addCommand(getExitCommand3());
            activationKeyForm.addCommand(getSubmitActivationCommand());
            activationKeyForm.setCommandListener(this);//GEN-END:|168-getter|1|168-postInit
            // write post-init user code here
        }//GEN-BEGIN:|168-getter|2|
        return activationKeyForm;
    }
    //</editor-fold>//GEN-END:|168-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|171-getter|0|171-preInit
    /**
     * Returns an initiliazed instance of backCommand1 component.
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {//GEN-END:|171-getter|0|171-preInit
            // write pre-init user code here
            backCommand1 = new Command("Back", Command.BACK, 0);//GEN-LINE:|171-getter|1|171-postInit
            // write post-init user code here
        }//GEN-BEGIN:|171-getter|2|
        return backCommand1;
    }
    //</editor-fold>//GEN-END:|171-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem7 ">//GEN-BEGIN:|169-getter|0|169-preInit
    /**
     * Returns an initiliazed instance of stringItem7 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem7() {
        if (stringItem7 == null) {//GEN-END:|169-getter|0|169-preInit
            // write pre-init user code here
            stringItem7 = new StringItem("", "Confirm your account activation by entering your PIN code.");//GEN-LINE:|169-getter|1|169-postInit
            // write post-init user code here
        }//GEN-BEGIN:|169-getter|2|
        return stringItem7;
    }
    //</editor-fold>//GEN-END:|169-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: activationKeyField ">//GEN-BEGIN:|170-getter|0|170-preInit
    /**
     * Returns an initiliazed instance of activationKeyField component.
     * @return the initialized component instance
     */
    public TextField getActivationKeyField() {
        if (activationKeyField == null) {//GEN-END:|170-getter|0|170-preInit
            // write pre-init user code here
            activationKeyField = new TextField("Activation PIN:", null, 32, TextField.ANY);//GEN-LINE:|170-getter|1|170-postInit
            // write post-init user code here
        }//GEN-BEGIN:|170-getter|2|
        return activationKeyField;
    }
    //</editor-fold>//GEN-END:|170-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand3 ">//GEN-BEGIN:|177-getter|0|177-preInit
    /**
     * Returns an initiliazed instance of exitCommand3 component.
     * @return the initialized component instance
     */
    public Command getExitCommand3() {
        if (exitCommand3 == null) {//GEN-END:|177-getter|0|177-preInit
            // write pre-init user code here
            exitCommand3 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|177-getter|1|177-postInit
            // write post-init user code here
        }//GEN-BEGIN:|177-getter|2|
        return exitCommand3;
    }
    //</editor-fold>//GEN-END:|177-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand4 ">//GEN-BEGIN:|193-getter|0|193-preInit
    /**
     * Returns an initiliazed instance of backCommand4 component.
     * @return the initialized component instance
     */
    public Command getBackCommand4() {
        if (backCommand4 == null) {//GEN-END:|193-getter|0|193-preInit
            // write pre-init user code here
            backCommand4 = new Command("Back", Command.BACK, 0);//GEN-LINE:|193-getter|1|193-postInit
            // write post-init user code here
        }//GEN-BEGIN:|193-getter|2|
        return backCommand4;
    }
    //</editor-fold>//GEN-END:|193-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand4 ">//GEN-BEGIN:|196-getter|0|196-preInit
    /**
     * Returns an initiliazed instance of exitCommand4 component.
     * @return the initialized component instance
     */
    public Command getExitCommand4() {
        if (exitCommand4 == null) {//GEN-END:|196-getter|0|196-preInit
            // write pre-init user code here
            exitCommand4 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|196-getter|1|196-postInit
            // write post-init user code here
        }//GEN-BEGIN:|196-getter|2|
        return exitCommand4;
    }
    //</editor-fold>//GEN-END:|196-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: confirmRegistrationForm ">//GEN-BEGIN:|185-getter|0|185-preInit
    /**
     * Returns an initiliazed instance of confirmRegistrationForm component.
     * @return the initialized component instance
     */
    public Form getConfirmRegistrationForm() {
        if (confirmRegistrationForm == null) {//GEN-END:|185-getter|0|185-preInit
            // write pre-init user code here
            confirmRegistrationForm = new Form("Confirm registration", new Item[] { getStringItem8(), getConfirmPhoneStringItem(), getConfirmPasswordStringItem(), getConfirmFirstNameStringItem(), getConfirmLastNameStringItem(), getConfirmEmailStringItem(), getConfirmTownStringItem() });//GEN-BEGIN:|185-getter|1|185-postInit
            confirmRegistrationForm.addCommand(getBackCommand4());
            confirmRegistrationForm.addCommand(getExitCommand4());
            confirmRegistrationForm.addCommand(getSignUpSubmitCommand());
            confirmRegistrationForm.setCommandListener(this);//GEN-END:|185-getter|1|185-postInit
            // write post-init user code here
        }//GEN-BEGIN:|185-getter|2|
        return confirmRegistrationForm;
    }
    //</editor-fold>//GEN-END:|185-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem8 ">//GEN-BEGIN:|187-getter|0|187-preInit
    /**
     * Returns an initiliazed instance of stringItem8 component.
     * @return the initialized component instance
     */
    public StringItem getStringItem8() {
        if (stringItem8 == null) {//GEN-END:|187-getter|0|187-preInit
            // write pre-init user code here
            stringItem8 = new StringItem("", "Please confirm that entered data is valid.");//GEN-LINE:|187-getter|1|187-postInit
            // write post-init user code here
        }//GEN-BEGIN:|187-getter|2|
        return stringItem8;
    }
    //</editor-fold>//GEN-END:|187-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: confirmPhoneStringItem ">//GEN-BEGIN:|188-getter|0|188-preInit
    /**
     * Returns an initiliazed instance of confirmPhoneStringItem component.
     * @return the initialized component instance
     */
    public StringItem getConfirmPhoneStringItem() {
        if (confirmPhoneStringItem == null) {//GEN-END:|188-getter|0|188-preInit
            // write pre-init user code here
            confirmPhoneStringItem = new StringItem("Your phone number:", null);//GEN-LINE:|188-getter|1|188-postInit
            // write post-init user code here
        }//GEN-BEGIN:|188-getter|2|
        return confirmPhoneStringItem;
    }
    //</editor-fold>//GEN-END:|188-getter|2|
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: confirmFirstNameStringItem ">//GEN-BEGIN:|189-getter|0|189-preInit
    /**
     * Returns an initiliazed instance of confirmFirstNameStringItem component.
     * @return the initialized component instance
     */
    public StringItem getConfirmFirstNameStringItem() {
        if (confirmFirstNameStringItem == null) {//GEN-END:|189-getter|0|189-preInit
            // write pre-init user code here
            confirmFirstNameStringItem = new StringItem("First Name:", null);//GEN-LINE:|189-getter|1|189-postInit
            // write post-init user code here
        }//GEN-BEGIN:|189-getter|2|
        return confirmFirstNameStringItem;
    }
    //</editor-fold>//GEN-END:|189-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: confirmEmailStringItem ">//GEN-BEGIN:|191-getter|0|191-preInit
    /**
     * Returns an initiliazed instance of confirmEmailStringItem component.
     * @return the initialized component instance
     */
    public StringItem getConfirmEmailStringItem() {
        if (confirmEmailStringItem == null) {//GEN-END:|191-getter|0|191-preInit
            // write pre-init user code here
            confirmEmailStringItem = new StringItem("Email:", null);//GEN-LINE:|191-getter|1|191-postInit
            // write post-init user code here
        }//GEN-BEGIN:|191-getter|2|
        return confirmEmailStringItem;
    }
    //</editor-fold>//GEN-END:|191-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: confirmTownStringItem ">//GEN-BEGIN:|192-getter|0|192-preInit
    /**
     * Returns an initiliazed instance of confirmTownStringItem component.
     * @return the initialized component instance
     */
    public StringItem getConfirmTownStringItem() {
        if (confirmTownStringItem == null) {//GEN-END:|192-getter|0|192-preInit
            // write pre-init user code here
            confirmTownStringItem = new StringItem("Town:", null);//GEN-LINE:|192-getter|1|192-postInit
            // write post-init user code here
        }//GEN-BEGIN:|192-getter|2|
        return confirmTownStringItem;
    }
    //</editor-fold>//GEN-END:|192-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: confirmPasswordStringItem ">//GEN-BEGIN:|199-getter|0|199-preInit
    /**
     * Returns an initiliazed instance of confirmPasswordStringItem component.
     * @return the initialized component instance
     */
    public StringItem getConfirmPasswordStringItem() {
        if (confirmPasswordStringItem == null) {//GEN-END:|199-getter|0|199-preInit
            // write pre-init user code here
            confirmPasswordStringItem = new StringItem("Password:", null);//GEN-LINE:|199-getter|1|199-postInit
            // write post-init user code here
        }//GEN-BEGIN:|199-getter|2|
        return confirmPasswordStringItem;
    }
    //</editor-fold>//GEN-END:|199-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpNextCommand ">//GEN-BEGIN:|202-getter|0|202-preInit
    /**
     * Returns an initiliazed instance of signUpNextCommand component.
     * @return the initialized component instance
     */
    public Command getSignUpNextCommand() {
        if (signUpNextCommand == null) {//GEN-END:|202-getter|0|202-preInit
            // write pre-init user code here
            signUpNextCommand = new Command("Next", Command.ITEM, 0);//GEN-LINE:|202-getter|1|202-postInit
            // write post-init user code here
        }//GEN-BEGIN:|202-getter|2|
        return signUpNextCommand;
    }
    //</editor-fold>//GEN-END:|202-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: signUpSubmitCommand ">//GEN-BEGIN:|205-getter|0|205-preInit
    /**
     * Returns an initiliazed instance of signUpSubmitCommand component.
     * @return the initialized component instance
     */
    public Command getSignUpSubmitCommand() {
        if (signUpSubmitCommand == null) {//GEN-END:|205-getter|0|205-preInit
            // write pre-init user code here
            signUpSubmitCommand = new Command("Submit", Command.ITEM, 0);//GEN-LINE:|205-getter|1|205-postInit
            // write post-init user code here
        }//GEN-BEGIN:|205-getter|2|
        return signUpSubmitCommand;
    }
    //</editor-fold>//GEN-END:|205-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: confirmLastNameStringItem ">//GEN-BEGIN:|207-getter|0|207-preInit
    /**
     * Returns an initiliazed instance of confirmLastNameStringItem component.
     * @return the initialized component instance
     */
    public StringItem getConfirmLastNameStringItem() {
        if (confirmLastNameStringItem == null) {//GEN-END:|207-getter|0|207-preInit
            // write pre-init user code here
            confirmLastNameStringItem = new StringItem("Last Name:", null);//GEN-LINE:|207-getter|1|207-postInit
            // write post-init user code here
        }//GEN-BEGIN:|207-getter|2|
        return confirmLastNameStringItem;
    }
    //</editor-fold>//GEN-END:|207-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: processOperationCommand ">//GEN-BEGIN:|208-getter|0|208-preInit
    /**
     * Returns an initiliazed instance of processOperationCommand component.
     * @return the initialized component instance
     */
    public Command getProcessOperationCommand() {
        if (processOperationCommand == null) {//GEN-END:|208-getter|0|208-preInit
            // write pre-init user code here
            processOperationCommand = new Command("Process", Command.SCREEN, 0);//GEN-LINE:|208-getter|1|208-postInit
            // write post-init user code here
        }//GEN-BEGIN:|208-getter|2|
        return processOperationCommand;
    }
    //</editor-fold>//GEN-END:|208-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: submitActivationCommand ">//GEN-BEGIN:|211-getter|0|211-preInit
    /**
     * Returns an initiliazed instance of submitActivationCommand component.
     * @return the initialized component instance
     */
    public Command getSubmitActivationCommand() {
        if (submitActivationCommand == null) {//GEN-END:|211-getter|0|211-preInit
            // write pre-init user code here
            submitActivationCommand = new Command("Submit PIN", Command.SCREEN, 0);//GEN-LINE:|211-getter|1|211-postInit
            // write post-init user code here
        }//GEN-BEGIN:|211-getter|2|
        return submitActivationCommand;
    }
    //</editor-fold>//GEN-END:|211-getter|2|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
        user = null;
    }


    private void syncCommandFired() {
        if (!AppUtils.isPIMSupported()) {
            GuiUtils.alertUser("Fatal error", "Your mobile phone do not supports phone book API, this application cannot work. We're sorry.", AlertType.ERROR, getDisplay(), getMainForm());
            return;
        }
        if (!user.getPaid().booleanValue()) {
            GuiUtils.alertUser("Info", "This is paid service. Please, follow our instructions to activate your account.", AlertType.WARNING, getDisplay(), getPaymentInfoForm());
            return;
        }
        ContactList contacts = null;
        try {
            contacts = (ContactList) PIM.getInstance().openPIMList(PIM.CONTACT_LIST, PIM.READ_WRITE);
        } catch (PIMException pe) {
            GuiUtils.alertUser("Fatal error", "Your mobile phone do not supports phone book API. We're sorry.", AlertType.ERROR, getDisplay(), getMainForm());
            return;
        } catch (SecurityException se) {
            GuiUtils.alertUser("Fatal error", "Your mobile phone do not supports phone book API. We're sorry.", AlertType.ERROR, getDisplay(), getMainForm());
            return;
        } catch (IllegalArgumentException iae) {
            GuiUtils.alertUser("Fatal error", "Your contacts structure is invalid. This application won't work. We're sorry.", AlertType.ERROR, getDisplay(), getMainForm());
            iae.printStackTrace();
            return;
        }

        Thread process = null;
        activeService = new SendService(this, user, contacts);
        activeService.setParentDisplayable(getMainForm());
        process = new Thread(activeService);
        process.start();
        switchDisplayable(null, getWaitingForm());
    }
}
