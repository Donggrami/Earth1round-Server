package donggrami.earth1round.src.auth.google.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GoogleUserRes {
    private String access_token;
    private String refresh_token;
    private Long user_id;
}
