package pw.react.backend.reactbackend;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pw.react.backend.reactbackend.controller.Controller;
import pw.react.backend.reactbackend.error.ResourceNotFoundException;
import pw.react.backend.reactbackend.respository.UserRepository;
import pw.react.backend.reactbackend.service.UserService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
//@RunWith(MockitoJUnitRunner.class)

public class devtests {
    private Controller controller;
    @InjectMocks
    private UserService service;

    @MockBean
    UserRepository repository;

 //   @Autowired
    //MockMvc mockMvc;
 @Test
 public void givenInvalidLogin_whenGetUsersWithLoginIsInvoked_thenThrowException() {
     // given
     String login = "abba";
     given(repository.findByLogin(login)).willReturn(null);

    // when(controller.findByLogin(login)).
           //  then Throw(ResourceNotFoundException.class);
 }
}
