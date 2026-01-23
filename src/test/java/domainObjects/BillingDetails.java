package domainObjects;

public class BillingDetails {

    private String firstname;
    private String lastname;
    private String country;
    private String street;
    private String town;
    private String state;
    private String zip;
    private String email;

    public BillingDetails(String firstname, String lastname,
                          String country, String street,
                          String town, String state,
                          String zip, String email) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.street = street;
        this.town = town;
        this.state = state;
        this.zip = zip;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
