package donggrami.earth1round.src.place;

import donggrami.earth1round.config.BaseException;
import donggrami.earth1round.config.BaseResponse;
import donggrami.earth1round.src.place.model.GetPlacesRes;
import donggrami.earth1round.src.place.model.PostPlaceReq;
import donggrami.earth1round.src.place.model.PostPlaceRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static donggrami.earth1round.config.BaseResponseStatus.POST_PLACE_EMPTY_PLACE_NAME;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {
    @Autowired
    private final PlaceService placeService;

    @GetMapping("")
    public BaseResponse<List<GetPlacesRes>> getPlaces() {
        try {
            List<GetPlacesRes> getPlacesRes = placeService.retrievePlaces();
            return new BaseResponse<>(getPlacesRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

//    @ResponseBody
//    @PostMapping("")
//    public BaseResponse<PostPlaceRes> createPlace(@RequestBody PostPlaceReq postPlaceReq) {
//        try {
//            if(postPlaceReq.getPlace_name().length() == 0) {
//                return new BaseResponse<>(POST_PLACE_EMPTY_PLACE_NAME);
//            }
////            if("".equals(postPlaceReq.getLatitude()) || )
//            PostPlaceRes postPlaceRes = placeService.createPlace(postPlaceReq);
//            return new BaseResponse<>(postPlaceRes);
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//    }
}