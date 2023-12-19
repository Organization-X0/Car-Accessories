package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.LoginState;
import org.sates.StartState;
import org.sates.ViewInstallationHistoryState;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ViewInstallationHistoryTest {

    @Mock
    private App myApp;
    @Mock
    private Cli cli;

    @Mock
    private Error error;
    @Mock
    private User user;

    @Test
    public void testHandle() {
        // Given
        when(myApp.getCli()).thenReturn(cli);
        when(cli.displayInstallationHistory(any())).thenReturn("n");
        when(myApp.getEmail()).thenReturn("user1@gmail.com");
        when(myApp.searchAccount(myApp.getEmail())).thenReturn(user);
        when(myApp.getError()).thenReturn(error);
        // Create an instance of your class under test, passing the mock as a parameter
        ViewInstallationHistoryState state = spy(new ViewInstallationHistoryState(myApp)); // replace YourStateClass with the actual class name
        doNothing().when(state).handleInput(any());

        // When
        state.handle();

        // Then
        verify(cli).displayInstallationHistory(any());
    }
}