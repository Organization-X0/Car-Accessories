package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.CustomerDashboardState;
import org.sates.LoginState;
import org.sates.StartState;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class LoginTest {

    @Mock
    private App myApp;
    @Mock
    private Cli cli;

    @Mock
    private Error error;

    @Test
    public void testHandle() {
        // Given
        when(myApp.getError()).thenReturn(error);
        Map<String, String> loginData = new HashMap<>();
        when(myApp.getCli()).thenReturn(cli);
        loginData.put("username", "user1@gmail.com");
        loginData.put("password", "u123");
        when(myApp.getCli().displayLogin()).thenReturn(loginData);
        // Create an instance of your class under test, passing the mock as a parameter
        LoginState state = spy(new LoginState(myApp));
        doNothing().when(state).handleInput(any());

        // When
        state.handle();

        // Then
        verify(myApp.getError()).checkAndShow(anyString(), any());
        verify(myApp.getCli()).displayLogin();
    }
}