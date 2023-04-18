package project.colon.fastdrive.service.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.colon.fastdrive.data.dto.request.InviteAdminRequest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;

    private Set <InviteAdminRequest> inviteAdminRequests;

    @BeforeEach
    void setUp(){
        inviteAdminRequests = Set.of(new InviteAdminRequest("essienriicch98@outlook.com", "essien"));
    }

    @Test
    void sendInviteRequestsTest(){
        var response = adminService.sendInviteRequests(inviteAdminRequests);
        assertThat(response).isNotNull();
    }

}