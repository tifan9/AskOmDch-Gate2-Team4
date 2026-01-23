package customTypes;

import domainObjects.BillingDetails;
import domainObjects.Credentials;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class CustomDataTableType {
    @DataTableType
    public BillingDetails billingDetailsEntry(Map<String, String> entries){
        return new BillingDetails(
                entries.get("firstName"),
                entries.get("lastName"),
                entries.get("country"),
                entries.get("address"),
                entries.get("city"),
                entries.get("state"),
                entries.get("zipCode"),
                entries.get("email")
        );

    }

    @DataTableType
    public Credentials credentialsEntry(Map<String, String> entry){
        return new Credentials(
                entry.get("username"),
                entry.get("email"),
                entry.get("password")
        );
    }
}
