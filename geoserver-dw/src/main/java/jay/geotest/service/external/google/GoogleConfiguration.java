package jay.geotest.service.external.google;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoogleConfiguration {

    @JsonProperty
    @NotEmpty
    private String accountSid;

    @JsonProperty
    @NotEmpty
    private String authToken;

    @JsonProperty
    @NotEmpty
    private String number;

	public String getAccountSid() {
		return accountSid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public String getNumber() {
		return number;
	}
}
