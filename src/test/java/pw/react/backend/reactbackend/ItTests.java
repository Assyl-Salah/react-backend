package pw.react.backend.reactbackend;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pw.react.backend.reactbackend.respository.UserRepository;
import pw.react.backend.reactbackend.service.UserService;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles("it")
@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest
public class ItTests {
    @InjectMocks
    private UserService service;

    @MockBean
    UserRepository repository;

    @Autowired
    MockMvc mockMvc;
    @Test
    public void contextLoads() throws Exception {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/users/findbylogin/{login}")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        System.out.println(mvcResult.getResponse());

        verify(repository).findAll();
    }
}
