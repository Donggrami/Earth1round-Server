package donggrami.earth1round.src.profile;

import donggrami.earth1round.config.BaseException;
import donggrami.earth1round.src.domain.repository.ProfileRepository;
import donggrami.earth1round.src.profile.model.GetProfileRes;
import donggrami.earth1round.src.profile.model.PostProfileReq;
import donggrami.earth1round.src.profile.model.PostProfileRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;

public class ProfileService {
    @Autowired
    ProfileDao profileDao;

    public Map<String, String> getMessage(){
        Map<String, String> map = profileDao.getMessage();
        return map;
    }

    public Profile getNicknameByProfileId(Long profileId) {
//        return (Profile) profileDao.getNicknameByProfileId(profileId);
        return profileDao.getNicknameByProfileId(profileId);
    }

    public Profile getImgByProfileId(Long profileId) {
        return profileDao.getImgByProfileId(profileId);
    }

    public byte[] getImage(String imagePath) throws Exception {
        System.out.println(imagePath);
        FileInputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        String absolutePath = new File("").getAbsolutePath() + "\\";
        try {
            inputStream = new FileInputStream(absolutePath + imagePath);
        }
        catch (FileNotFoundException e) {
            throw new Exception("해당 파일을 찾을 수 없습니다.");
        }
        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try {
            while((readCount = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, readCount);
            }
            fileArray = outputStream.toByteArray();
            inputStream.close();
            outputStream.close();
        }
        catch (IOException e) {
            throw new Exception("파일을 변환하는데 문제가 발생했습니다.");
        }
        return fileArray;
    }
}
