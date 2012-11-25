/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pim.beans;

import org.kobjects.base64.Base64;

/**
 *
 * @author mladen
 */
public class User {

    private Integer id;
    private String firstName;
    private Boolean paid;
    private String paymentURL;

    public User(Integer id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the paid
     */
    public Boolean getPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    /**
     * @return the paymentURL
     */
    public String getPaymentURL() {
        return paymentURL;
    }

    /**
     * @param paymentURL the paymentURL to set
     */
    public void setPaymentURL(String paymentURL) {
        this.paymentURL = paymentURL;
    }
}
