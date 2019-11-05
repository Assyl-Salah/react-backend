package pw.react.backend.reactbackend;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pw.react.backend.reactbackend.controller.Controller;
import pw.react.backend.reactbackend.respository.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(profiles = {"it"})
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc


public class ReactBackendApplicationIT {
    private MockMvc mockMvc;
    @Autowired
    private UserRepository repository;
    @Autowired
    public Controller controller;
    @Before
    public void setup(){this.mockMvc= MockMvcBuilders.standaloneSetup(this.controller).build();}

    @Test
    public void givenNothing_whenGetForUserIsRequested_thenReturnErrorStatus() throws Exception {
        this.mockMvc.perform(get("/users/findbyLogin/sa")).andExpect(status().isNotFound());
    }

    @Test
    public void givenNotPresentLogin_expectNotFoundStatus() throws Exception{
        MockHttpServletRequestBuilder getUser=get("/users/findByLogin/sszw");
        mockMvc.perform(getUser).andExpect(status().isNotFound());
    }

    @Test
    public void givenNotPresentId_expectNotFoundStatus() throws Exception{
        MockHttpServletRequestBuilder getUser=get("/users/findById/9");
        mockMvc.perform(getUser).andExpect(status().isNotFound());
    }
}
