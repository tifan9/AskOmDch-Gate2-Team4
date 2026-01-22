package customTypes;

import domainObjects.Credentials;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class CustomDataTableType {
    @DataTableType
    public Credentials loginCredentials(Map<String, String> entries){
        return new Credentials(
                entries.get("username"),
                entries.get("email"),
                entries.get("password"));
    }

}
