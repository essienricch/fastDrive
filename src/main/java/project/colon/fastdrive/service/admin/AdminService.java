package project.colon.fastdrive.service.admin;

import project.colon.fastdrive.data.dto.request.InviteAdminRequest;
import project.colon.fastdrive.data.dto.response.ApiResponse;

import java.util.Set;

public interface AdminService {

    ApiResponse sendInviteRequests (Set <InviteAdminRequest> inviteAdminRequests);
}
