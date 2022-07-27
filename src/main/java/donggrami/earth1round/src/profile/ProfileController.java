package donggrami.earth1round.src.profile;

import donggrami.earth1round.config.BaseException;
import donggrami.earth1round.config.BaseResponse;
import donggrami.earth1round.src.place.model.GetPlacesRes;
import donggrami.earth1round.src.place.model.PostPlaceReq;
import donggrami.earth1round.src.place.model.PostPlaceRes;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static donggrami.earth1round.config.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles") // /profiles로 들어오는 모든 요청 처리
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("")
    public Profile getAllProfiles() {
        return profileService.getProfiles();
    }

    @GetMapping("/nickname/{profileId}")
    public Profile getNicknameByProfileId(@PathVariable Long profileId) {
        return profileService.getNicknameByProfileId(profileId);
    }

    @GetMapping("/ProfileImg/{profileId}")
    public Profile getImgByProfileId(@PathVariable Long profileId) {
        return profileService.getImgByProfileId(profileId);
    }

    @GetMapping("/image")
    public Response getImage(@RequestParam("image_path") String imagePath) {
        Response response = new Response();
        try {
            response.setResponse("success");
            response.setMessage("이미지 조회를 성공적으로 완료했습니다.");
            response.setData(profileService.getImage(imagePath));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("이미지 조회를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }
        return response;
    }
}
