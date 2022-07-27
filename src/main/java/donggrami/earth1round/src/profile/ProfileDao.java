package donggrami.earth1round.src.profile;

import donggrami.earth1round.src.domain.entity.Profile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ProfileDao {
    public static List<Profile> profiles;

    private Timestamp created_at = new Timestamp(new Date().getTime());
    private Timestamp updated_at = new Timestamp(new Date().getTime());

    //실제 데이터는 DB에서 가져오는게 맞으나 .... 아직 DB가 없으니 임시로 세팅해놓음
    static {
        profiles = new ArrayList<>();
        profiles.add(new Profile(1,1,"nickname1", "1234", 1, "'Active'", 2022-08-01, "2022-09-01"));
    }

    // Select one nickname by profileId
    public Profile getNicknameByProfileId(Long profileId) {
        return profiles.stream()
                .filter(user -> user.getProfileId().equals(profileId))
                .findFirst() // 찾은 첫 번째 것
                .orElse(new Profile(-1, ));
    }

    // Select one nickname by profileId
    public Profile getImgByProfileId(Long profileId) {
        return profiles
                .stream()
                .filter(user -> user.getProfileId().equals(profileId))
                .findAny()
                .orElse(new Profile(-1, ));
    }


}
