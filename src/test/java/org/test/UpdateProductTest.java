package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.UpdateAppointmentState;
import org.sates.UpdateProductState;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UpdateProductTest {

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
        updateData.put("productId", "123");
        updateData.put("newPrice", "99.99");
        when(myApp.getCli().displayUpdateProduct()).thenReturn(updateData);
        // Create an instance of your class under test, passing the mock as a parameter
        UpdateProductState state = spy(new UpdateProductState(myApp));
        doNothing().when(state).handleInput(any());

        // When
        state.handle();

        // Then
        verify(myApp.getError()).checkAndShow(anyString(), any());
        verify(myApp.getCli()).displayUpdateProduct();
    }
}