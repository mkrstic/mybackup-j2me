/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pim.beans;

/**
 *
 * @author mladen
 */
public class Telephone {

    private String number;
    private String type;
    private Boolean preferred;

    public Telephone() {
    }

    public Telephone(String number, String type, Boolean preferred) {
        this.number = number;
        this.type = type;
        this.preferred = preferred;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the preferred
     */
    public Boolean isPreferred() {
        return preferred;
    }

    /**
     * @param preferred the preferred to set
     */
    public void setPreferred(Boolean preferred) {
        this.preferred = preferred;
    }
}
