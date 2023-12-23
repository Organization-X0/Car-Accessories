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
import org.sates.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AddAppointmentTest {

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
    public void testHandleAdmin() {
        // Given
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        when(myApp.getCli()).thenReturn(cli);
        when(myApp.getCli().displayAddAppointment(any())).thenReturn(data);
        when(myApp.getError()).thenReturn(error);
        when(myApp.whoLoggedIn()).thenReturn("admin");
        when(error.getLocation()).thenReturn("SomeOtherState");
        // Create an instance of your class under test, passing the mock as a parameter
        AddAppointmentState state = new AddAppointmentState(myApp); // replace YourStateClass with the actual class name

        // When
        state.handle();

        // Then
        verify(cli).displayAddAppointment(any());
        verify(cli).displayMsg(" Appointment added successfully! ", true);
        verify(myApp).setState(any(ManageInstallationAppointmentState.class));
    }

    @Test
    public void testHandleCustomer() {
        // Given
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        when(myApp.getCli()).thenReturn(cli);
        when(myApp.getCli().displayAddAppointmentCustomer(any())).thenReturn(data);
        when(myApp.getError()).thenReturn(error);
        when(myApp.whoLoggedIn()).thenReturn("customer");
        when(error.getLocation()).thenReturn("SomeOtherState");
        // Create an instance of your class under test, passing the mock as a parameter
        AddAppointmentState state = new AddAppointmentState(myApp); // replace YourStateClass with the actual class name

        // When
        state.handle();

        // Then
        verify(cli).displayAddAppointmentCustomer(any());
        verify(cli).displayMsg(" Appointment added successfully! ", true);
        verify(myApp).setState(any(CustomerDashboardState.class));
    }

}