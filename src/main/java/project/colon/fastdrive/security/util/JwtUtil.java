package project.colon.fastdrive.security.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtUtil {

    private final String jwtSecret;
}
