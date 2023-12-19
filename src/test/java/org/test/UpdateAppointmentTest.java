package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.SignUpState;
import org.sates.UpdateAppointmentState;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UpdateAppointmentTest {

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
        Map<String, String> updateData = new HashMap<>();
        updateData.put("appointmentId", "123");
        updateData.put("newDate", "2023-12-20");
        when(myApp.getCli().displayUpdateAppointment()).thenReturn(updateData);
        // Create an instance of your class under test, passing the mock as a parameter
        UpdateAppointmentState state = spy(new UpdateAppointmentState(myApp));
        doNothing().when(state).handleInput(any());

        // When
        state.handle();

        // Then
        verify(myApp.getError()).checkAndShow(anyString(), any());
        verify(myApp.getCli()).displayUpdateAppointment();
    }
}