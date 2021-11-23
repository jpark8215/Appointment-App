package model;

/**
 * Contact class
 */
public class Contact {

    private int contactId;
    private String contactName;
    private String email;

    /**
     * @param contactId contactId
     * @param contactName contactName
     * @param email email
     */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }


    public Contact(int contactId, String contactName) {
    }


    /**
     * @return contactId
     */
    public int getContactId() { return contactId; }

    public void setContactId(int contactId) { this.contactId = contactId; }

    /**
     * @return contactName
     */
    public String getContactName() { return contactName; }

    public void setContactName(String contactName) { this.contactName = contactName; }

    /**
     * @return email
     */
    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }


    /**
     * @return customerId and customerId in string
     */
    @Override
    public String toString() {return (contactId + " " + contactName);}

}
