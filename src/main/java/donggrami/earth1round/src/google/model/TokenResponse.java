package donggrami.earth1round.src.google.model;

public class TokenResponse {

    private String accessToken;
    private String tokenType;

    public TokenResponse(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}