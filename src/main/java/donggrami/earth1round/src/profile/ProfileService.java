package donggrami.earth1round.src.profile;

import donggrami.earth1round.config.BaseException;
import donggrami.earth1round.src.domain.entity.Profile;
import donggrami.earth1round.src.domain.entity.User;
import donggrami.earth1round.src.domain.repository.ProfileRepository;
import donggrami.earth1round.src.domain.repository.UserRepository;
import donggrami.earth1round.src.profile.model.GetProfileRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import static donggrami.earth1round.config.BaseResponseStatus.DATABASE_ERROR;
import static donggrami.earth1round.config.BaseResponseStatus.EMPTY_USER;

@Service
@RequiredArgsConstructor
public class ProfileService {
//    @Autowired
//    private final ProfileDao profileDao;

    @Autowired
    private final ProfileRepository profileRepository;

    @Autowired
    private final UserRepository userRepository;

    public GetProfileRes getMypageProfile(Long user_id) throws BaseException {
        try{
            Optional<User> user = userRepository.findById(user_id);
            GetProfileRes getProfileRes = null;
            if(user.isPresent()) {
                Profile profile = profileRepository.findByUser(user.get());
                getProfileRes = new GetProfileRes(profile.getName(), profile.getNickname(), profile.getProfile_img());
            }

            return getProfileRes;
        } catch (NoSuchElementException exception) {
            throw new BaseException(EMPTY_USER);
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}