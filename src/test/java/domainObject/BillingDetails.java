package domainObject;

public class BillingDetails {
    private String firstName;
    private String lastName;
    private String billingAddress;
    private String billingCity;
    private String billingState;
    private String billingZipCode;
    private String billingCountry;
    private String billingPhone;
    private String billingEmail;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getBillingAddress() {
        return billingAddress;
    }
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
    public String getBillingCity() {
        return billingCity;
    }
    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }
    public String getBillingState() {
        return billingState;
    }
    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }
    public String getBillingZipCode() {
        return billingZipCode;
    }
    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }
    public String getBillingCountry() {
        return billingCountry;
    }
    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }
    public String getBillingPhone() {
        return billingPhone;
    }
    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }
    public String getBillingEmail() {
        return billingEmail;
    }
    public void setBillingEmail(String billingEmail) {
        this.billingEmail = billingEmail;
    }
    public BillingDetails(String firstName, String lastName, String billingAddress, String billingCity,
                          String billingState, String billingZipCode, String billingCountry, String billingPhone, String billingEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.billingAddress = billingAddress;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingZipCode = billingZipCode;
        this.billingCountry = billingCountry;
        this.billingPhone = billingPhone;
        this.billingEmail = billingEmail;
    }
}
