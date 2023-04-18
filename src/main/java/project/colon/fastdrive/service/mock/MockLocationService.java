package project.colon.fastdrive.service.mock;

import project.colon.fastdrive.data.dto.request.Location;
import project.colon.fastdrive.data.dto.response.GoogleDistanceResponse;

public interface MockLocationService {
    GoogleDistanceResponse getDistanceInformation(Location origin, Location destination);
}
