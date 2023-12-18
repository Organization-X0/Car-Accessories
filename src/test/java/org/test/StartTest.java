package org.test;

import org.car.App;
import org.car.Cli;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.LoginState;
import org.sates.StartState;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StartTest {

    @Mock
    private App myApp;
    @Mock
    private Cli cli;

    @Mock
    private Error error;

    @Test
    public void testHandle() {
        // Given
        when(myApp.getCli()).thenReturn(cli);
        when(cli.displayStart()).thenReturn("1");
        // Create an instance of your class under test, passing the mock as a parameter
        StartState state = new StartState(myApp);

        // When
        state.handle();

        // Then
        verify(cli).displayStart();
//        verify(error).checkAndShow(anyString());
        verify(myApp).setState(any(LoginState.class));
    }
}