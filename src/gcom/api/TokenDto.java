package gcom.api;

import java.io.Serializable;

public class TokenDto implements Serializable {
	
	private static final long serialVersionUID = 886708942907859654L;

	private String access_token;
	
	private String token_type;
	
	private String expires_in;
	
	public TokenDto() {}
	
	public TokenDto(String access_token, String token_type, String expires_in) {
		this.access_token = access_token;
		this.token_type = token_type;
		this.expires_in = expires_in;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	
	
}
