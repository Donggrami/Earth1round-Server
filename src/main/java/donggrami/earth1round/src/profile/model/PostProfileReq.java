package donggrami.earth1round.src.profile.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostProfileReq {
    private String nickname;
    private String profileImg;
}
