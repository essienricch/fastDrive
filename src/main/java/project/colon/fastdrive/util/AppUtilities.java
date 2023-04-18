package project.colon.fastdrive.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import project.colon.fastdrive.data.dto.request.Location;
import project.colon.fastdrive.exception.BusinessLogicException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;

public class AppUtilities {
    public static final int NUMBER_OF_ITEMS_PER_PAGE = 3;
    public static final String EMAIL_REGEX_STRING="^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String USER_VERIFICATION_BASE_URL="http:\\localhost:8080\\api\\v1\\user\\account\\verify";
    public static final String WELCOME_MAIL_TEMPLATE_LOCATION="C:\\Users\\New Owner\\IdeaProjects\\fastDrive\\src\\main\\resources\\welcome.txt";
    private static final String ADMIN_INVITE_MAIL_TEMPLATE_LOCATION = "C:\\Users\\New Owner\\IdeaProjects\\fastDrive\\src\\main\\resources\\adminMail.txt";
    public static String FAST_DRIVE_TEST_IMAGE="C:\\Users\\New Owner\\IdeaProjects\\fastDrive\\src\\main\\resources\\images\\toa-heftiba-MKYnSP2VwEg-unsplash.jpg";

    public  static final String JSON_CONSTANT="json";

    public static final String TRANSPORT_MODE="driving";


    public static String getMailTemplate(){
        try(BufferedReader reader = new BufferedReader(new FileReader(
            WELCOME_MAIL_TEMPLATE_LOCATION))){
            return reader.lines().collect(Collectors.joining());
        }catch (IOException e){
            throw new BusinessLogicException(e.getMessage());
        }
    }

    public static String generateVerificationLink(Long userId) {
        return USER_VERIFICATION_BASE_URL+"userId="+userId+"&token="+generateVerificationToken();
    }

    private static  String generateVerificationToken(){
        return Jwts.builder()
                .setIssuer("fast-drive")
                .signWith(SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E="))
                .setIssuedAt(new Date())
                .compact();
    }

    public static boolean isValidToken(String token){
        return Jwts.parser().isSigned(token);
    }

    public static String getAdminMailTemplate() {
        try(BufferedReader reader =
                new BufferedReader(new FileReader(ADMIN_INVITE_MAIL_TEMPLATE_LOCATION))){
            return reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new BusinessLogicException(e.getMessage());
        }
    }

    public static String buildLocation(Location location){
        return location.getHouseNumber()+ ", " + location.getStreet() + ", " + location.getCity() +" " +location.getState();
    }

    public static BigDecimal calculateRideFare(String distance){
        return BigDecimal.valueOf(Double.parseDouble(distance.split("k")[0]))
                .multiply(BigDecimal.valueOf(1000));
    }
}
