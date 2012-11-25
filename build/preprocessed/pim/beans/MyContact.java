/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pim.beans;

/**
 *
 * @author mladen
 */
public class MyContact {

    private Integer id;
    private String name;
    private String url;
    private String email;
    private String address;
    private String note;
    private String uid;

    public MyContact() {
    }

    public MyContact(Integer id, String name, String uid) {
        this.id = id;
        this.name = name;
        this.uid = uid;
    }

    public MyContact(Integer contactId) {
        this.id = contactId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the description
     */
    public String getNote() {
        return note;
    }

    /**
     * @param description the description to set
     */
    public void setNote(String note) {
        this.note = note;
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

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }
}
