package donggrami.earth1round.src.course;

import donggrami.earth1round.config.BaseException;
import donggrami.earth1round.config.BaseResponse;
import donggrami.earth1round.src.course.model.*;
import donggrami.earth1round.utils.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static donggrami.earth1round.config.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
public class CourseController {
    @Autowired
    private final CourseService courseService;
    @Autowired
    private final JwtService jwtService;

    /**
     * Post Course API
     * [POST] /courses
     * @return BaseResponse<PostCourseRes>
     */
    @ResponseBody
    @PostMapping("/courses")
    public BaseResponse<PostCourseRes> createCourse(@RequestBody PostCourseReq postCourseReq) {
        if(postCourseReq.start_place_id == null){
            return new BaseResponse<>(POST_COURSES_EMPTY_STARTPLACE);
        }
        if(postCourseReq.end_place_id == null){
            return new BaseResponse<>(POST_COURSES_EMPTY_ENDPLACE);
        }
        if(postCourseReq.distance < 0){
            return new BaseResponse<>(POST_COURSES_WRONG_DISTANCE);
        }

        try{
            Long userIdByJwt = jwtService.getUserId();
            PostCourseRes postCourseRes = courseService.createCourse(userIdByJwt, postCourseReq);
            return new BaseResponse<>(postCourseRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * Get Course API
     * [GET] /course
     * @return BaseResponse<GetCourseRes>
     */
    @ResponseBody
    @GetMapping("/course")
    public BaseResponse<GetCourseRes> showCourse() {
        try{
            Long userIdByJwt = jwtService.getUserId();
            GetCourseRes getCourseRes = courseService.getCourse(userIdByJwt);
            return new BaseResponse<>(getCourseRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * Patch Course API
     * [PATCH] /course
     * @return BaseResponse<PatchCourseRes>
     */
    @ResponseBody
    @PatchMapping("/course")
    public BaseResponse<PatchCourseRes> completeCourse() {
        try{
            Long userIdByJwt = jwtService.getUserId();
            PatchCourseRes patchCourseRes = courseService.patchCourse(userIdByJwt);
            return new BaseResponse<>(patchCourseRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
