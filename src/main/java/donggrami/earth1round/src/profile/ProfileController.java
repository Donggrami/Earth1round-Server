package donggrami.earth1round.src.profile;

import donggrami.earth1round.config.BaseException;
import donggrami.earth1round.config.BaseResponse;
import donggrami.earth1round.src.profile.model.GetProfileRes;
import donggrami.earth1round.utils.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles") // /profiles로 들어오는 모든 요청 처리
public class ProfileController {
    @Autowired
    private final ProfileService profileService;

    @Autowired
    private final JwtService jwtService;

    @GetMapping("/mypage")
    public BaseResponse<GetProfileRes> getProfile(){
        try {
            Long user_id = jwtService.getUserId();
            GetProfileRes getProfileRes = profileService.getMypageProfile(user_id);

            return new BaseResponse<>(getProfileRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}