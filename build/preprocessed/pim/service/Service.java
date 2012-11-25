/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pim.service;

import javax.microedition.lcdui.Displayable;
import pim.beans.User;
import pim.ui.PimMidlet;

/**
 *
 * @author mladen
 */
public abstract class Service implements Runnable {

    protected PimMidlet midlet;
    protected User user;
    protected Displayable parentDisplayable;
    protected boolean canceled;

    public Service(PimMidlet midlet, User user) {
        this.midlet = midlet;
        this.user = user;
        this.canceled = false;
    }

    public abstract void run();

    protected abstract void cancelOperation();

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean isCanceled) {
        this.canceled = isCanceled;
    }

    public Displayable getParentDisplayable() {
        return parentDisplayable;
    }

    public void setParentDisplayable(Displayable parentDisplayable) {
        this.parentDisplayable = parentDisplayable;
    }

    public PimMidlet getMidlet() {
        return midlet;
    }

    public void setMidlet(PimMidlet midlet) {
        this.midlet = midlet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
