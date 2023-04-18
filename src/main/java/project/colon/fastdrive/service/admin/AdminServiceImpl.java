package project.colon.fastdrive.service.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.colon.fastdrive.data.dto.request.EmailNotificationRequest;
import project.colon.fastdrive.data.dto.request.InviteAdminRequest;
import project.colon.fastdrive.data.dto.request.Recipient;
import project.colon.fastdrive.data.dto.response.ApiResponse;
import project.colon.fastdrive.data.model.Admin;
import project.colon.fastdrive.data.model.AppUser;
import project.colon.fastdrive.data.repository.AdminRepository;
import project.colon.fastdrive.exception.BusinessLogicException;
import project.colon.fastdrive.service.notification.MailService;
import project.colon.fastdrive.util.AppUtilities;

import java.util.Set;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    private final MailService mailService;
    @Override
    public ApiResponse sendInviteRequests(Set<InviteAdminRequest> inviteAdminRequests) {
        EmailNotificationRequest request = new EmailNotificationRequest();
        var recipients = inviteAdminRequests.stream().map(inviteAdminRequest -> createAdminProfile(inviteAdminRequest))
                .map(inviteAdminRequest -> new Recipient(inviteAdminRequest.getUser().getName(), inviteAdminRequest.getUser().getEmail()))
                .toList();
        request.getTo().addAll(recipients);

        String adminMail = AppUtilities.getAdminMailTemplate();
        request.setHtmlContent(String.format(adminMail, "admin", AppUtilities.generateVerificationLink(0L)));
        var response = mailService.sendHtmlMail(request);
        if (response != null){
            return ApiResponse.builder()
                    .message("Invite request sent")
                    .build();
        }throw new BusinessLogicException("Invite request sending failed");
    }

    private Admin createAdminProfile(InviteAdminRequest inviteAdminRequest) {
        Admin admin = new Admin();
        admin.setUser(new AppUser());
        admin.getUser().setName(inviteAdminRequest.getName());
        admin.getUser().setEmail(inviteAdminRequest.getEmail());
       return adminRepository.save(admin);
    }
}
