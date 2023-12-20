package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.InstallerDashboardState;
import org.sates.UpdateAccountState;
import org.sates.ViewInstallationHistoryState;

import java.util.HashMap;
import java.util.Map;
import java.util.PrimitiveIterator;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class InstallerDashboardTest {

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
        when(cli.displayInstallerDashboard(user)).thenReturn("1");
        when(myApp.getEmail()).thenReturn("user1@gmail.com");
        when(myApp.searchAccount(myApp.getEmail())).thenReturn(user);
        when(myApp.getError()).thenReturn(error);
        // Create an instance of your class under test, passing the mock as a parameter
        InstallerDashboardState state = spy(new InstallerDashboardState(myApp)); // replace YourStateClass with the actual class name
        doNothing().when(state).handleInput(any());

        // When
        state.handle();

        // Then
        verify(myApp).searchAccount("user1@gmail.com");
        verify(cli).displayInstallerDashboard(user);
    }
}