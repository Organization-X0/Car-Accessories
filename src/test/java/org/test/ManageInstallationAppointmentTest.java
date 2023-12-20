package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.Appointment;
import org.data.Category;
import org.data.DataBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.ManageCategoriesState;
import org.sates.ManageInstallationAppointmentState;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ManageInstallationAppointmentTest {

    @Mock
    private App myApp;
    @Mock
    private Cli cli;

    @Mock
    private Error error;
    @Mock
    private DataBase myDatabase;

    @Test
    public void testHandle() {
        // Given
        ArrayList<Appointment> appointmentsList = new ArrayList<>();
        when(myApp.getDatabase()).thenReturn(myDatabase);
        when(myApp.getError()).thenReturn(error);
        when(myApp.getCli()).thenReturn(cli);
        when(myApp.getDatabase().getRequestedAppointmentsList()).thenReturn(appointmentsList);
        when(myApp.getCli().displayInstallationAppointments(appointmentsList)).thenReturn("a");
        // Create an instance of your class under test, passing the mock as a parameter
        ManageInstallationAppointmentState state = spy(new ManageInstallationAppointmentState(myApp));

        // When
        state.handle();

        // Then
        verify(cli).displayInstallationAppointments(appointmentsList);
    }
}