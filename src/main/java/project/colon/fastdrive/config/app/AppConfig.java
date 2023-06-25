package project.colon.fastdrive.config.app;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.colon.fastdrive.config.distance.DistanceConfig;
import project.colon.fastdrive.config.mail.MailConfig;
import project.colon.fastdrive.security.util.JwtUtil;


@Configuration
public class AppConfig {

    @Value("${sendinblue.mail.url}")
    private String mailUrl;

    @Value("${mail.api.key}")
    private String mailApiKey;

    @Value("${jwt.secret.key}")
    private String jwtSecret;

    @Value("${cloudinary.cloud.name}")
    private String cloudName;

    @Value("${cloudinary.api.key}")
    private String apiKey;

    @Value("${cloudinary.api.secret}")
    private String apiSecret;

    @Value("${google.api.key}")
    private String googleApiKey;
    @Value("${google.distance.url}")
    private String googleMailUrl;



    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(
                ObjectUtils.asMap("cloud_name", cloudName,
                                    "api_key",apiKey,
                                    "api_secret",apiSecret));
    }

    @Bean
    public MailConfig mailConfig(){
        return new MailConfig(mailUrl, mailApiKey);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil(jwtSecret);
    }

    @Bean
    public DistanceConfig distanceConfig(){
        return new DistanceConfig(googleMailUrl, googleApiKey);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
