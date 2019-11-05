package pw.react.backend.reactbackend;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pw.react.backend.reactbackend.controller.Controller;
import pw.react.backend.reactbackend.entity.User;
import pw.react.backend.reactbackend.error.ResourceNotFoundException;
import pw.react.backend.reactbackend.respository.UserRepository;
import pw.react.backend.reactbackend.service.UserService;

import java.util.Optional;

import static org.aspectj.bridge.MessageUtil.fail;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class ReactBackendApplicationdev {
    private Controller userController;

    private UserService service;

    @Mock
    private UserRepository repository;

    @Before
    public void setUp() throws Exception {
        service = spy(new UserService(repository));
    }

    @Test
    public void givenNotExistingUser_whenUpdateUser_thenThrowResourceNotFoundException() {
        User user = new User();
        user.setId(1);
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            service.updateUser(user);
            fail("Should throw ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
           assertThat(ex.getMessage(), is(equalTo("user not created : '1'")));
        }
        verify(repository, times(0)).save(any(User.class));
    }

    @Test
    public void givenExistingUser_whenUpdateUser_thenExecuteSaveMethod() {
        User user = new User();
        user.setId(1);
        when(repository.findById(anyLong())).thenReturn(Optional.of(user));

        service.updateUser(user);

        verify(repository, times(1)).save(eq(user));
    }







}
