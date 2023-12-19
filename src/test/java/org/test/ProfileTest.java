package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.ProfileState;
import org.sates.ViewOrderHistoryState;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ProfileTest {

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
        when(myApp.getError()).thenReturn(error);
        when(myApp.getEmail()).thenReturn("test@test.com");
        when(myApp.getCli()).thenReturn(cli);
        when(myApp.searchAccount(myApp.getEmail())).thenReturn(user);
        when(user.getFullName()).thenReturn("Test User");
        when(user.getPhone()).thenReturn("1234567890");
        when(myApp.getCli().displayProfile(user.getFullName(), myApp.getEmail(), user.getPhone())).thenReturn("1");
        // Create an instance of your class under test, passing the mock as a parameter
        ProfileState state = spy(new ProfileState(myApp)); // replace YourStateClass with the actual class name

        // When
        state.handle();

        // Then
        verify(myApp.getError()).checkAndShow(anyString(), any());
        verify(myApp.getCli()).displayProfile(user.getFullName(), myApp.getEmail(), user.getPhone());

    }
}