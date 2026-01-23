package customTypes;

import domainObjects.BillingDetails;
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
}
