package donggrami.earth1round.src.custom;

import donggrami.earth1round.config.BaseException;
import donggrami.earth1round.config.BaseResponse;
import donggrami.earth1round.src.custom.model.GetCustomRes;
import donggrami.earth1round.src.custom.model.PatchCustomReq;
import donggrami.earth1round.utils.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static donggrami.earth1round.config.BaseResponseStatus.PATCH_CUSTOM_EMPTY_CUSTOM_NUM;

@RestController
@RequiredArgsConstructor
@RequestMapping("/custom")
public class CustomController {
    @Autowired
    private final CustomService customService;
    @Autowired
    private final JwtService jwtService;

    /**
     * Get Custom Number API
     * [GET] /custom
     * @return BaseResponse<GetCustomRes>
     */
    @GetMapping("")
    public BaseResponse<GetCustomRes> getCustom() {
        try {
            Long user_id = jwtService.getUserId();
            GetCustomRes getCustomRes = customService.retrieveCustom(user_id);

            return new BaseResponse<>(getCustomRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * Patch Custom Number API
     * [PATCH] /custom
     * @return BaseResponse<GetCustomRes>
     */
    @PatchMapping("")
    public BaseResponse<String> patchCustom(@Valid @RequestBody PatchCustomReq patchCustomReq) {
        Long user_id = jwtService.getUserId();
//        if (patchCustomReq.getCustom_num() == 0) {
//            throw new BaseException(PATCH_CUSTOM_EMPTY_CUSTOM_NUM, HttpStatus.BAD_REQUEST);
//        }
        customService.modifyCustom(user_id, patchCustomReq);
        String result = "커스텀 번호를 변경했습니다.";

        return new BaseResponse<>(result);
    }
}
