package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.LoginState;
import org.sates.SignUpState;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SignUpTest {

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
        Map<String, String> signUpData = new HashMap<>();
        when(myApp.getCli()).thenReturn(cli);
        signUpData.put("username", "testUser");
        signUpData.put("password", "testPassword");
        signUpData.put("email", "testEmail@test.com");
        when(myApp.getCli().displaySignUp()).thenReturn(signUpData);
        // Create an instance of your class under test, passing the mock as a parameter
        SignUpState state = spy(new SignUpState(myApp));
        doNothing().when(state).handleInput(any());

        // When
        state.handle();

        // Then
        verify(myApp.getError()).checkAndShow(anyString(), any());
        verify(myApp.getCli()).displaySignUp();
    }
}