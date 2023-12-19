package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.DataBase;
import org.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.InstallationRequestsState;
import org.sates.ViewOrderHistoryState;

import javax.xml.crypto.Data;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class InstallationRequestsTest {

    @Mock
    private App myApp;
    @Mock
    private Cli cli;

    @Mock
    private Error error;
    @Mock
    private User user;
    @Mock
    private DataBase myDatabase;

    @Test
    public void testHandle() {
        // Given
        when(myApp.getDatabase()).thenReturn(myDatabase);
        when(myApp.getCli()).thenReturn(cli);
        when(cli.displayInstallationRequests(any())).thenReturn("n");
        when(myApp.getError()).thenReturn(error);
        // Create an instance of your class under test, passing the mock as a parameter
        InstallationRequestsState state = spy(new InstallationRequestsState(myApp)); // replace YourStateClass with the actual class name
        doNothing().when(state).handleInput(any());

        // When
        state.handle();

        // Then
        verify(cli).displayInstallationRequests(any());
    }
}