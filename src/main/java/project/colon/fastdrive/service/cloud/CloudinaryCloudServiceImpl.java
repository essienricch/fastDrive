package project.colon.fastdrive.service.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.colon.fastdrive.exception.ImageUploadException;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
@Slf4j
@Service
public class CloudinaryCloudServiceImpl implements CloudService{

    private final Cloudinary cloudinary;
    @Override
    public String upload(MultipartFile image) {
        try{
            Map <?, ?> response =
                    cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            return response.get("url").toString();
        } catch (IOException e) {
            throw new ImageUploadException(e.getMessage());
        }
    }
}
