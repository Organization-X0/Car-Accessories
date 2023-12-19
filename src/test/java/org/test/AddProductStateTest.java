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
import org.sates.AddProductState;
import org.sates.ManageProductsState;
import org.sates.ScheduleOfAppointmentsState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AddProductStateTest {

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
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        when(myApp.getCli()).thenReturn(cli);
        when(cli.displayAddProduct(any())).thenReturn(data);
        when(myApp.getError()).thenReturn(error);
        when(error.getLocation()).thenReturn("null");
        when(myApp.getDatabase().getCategoryList()).thenReturn(new ArrayList<>());
        // Create an instance of your class under test, passing the mock as a parameter
        AddProductState state = new AddProductState(myApp); // replace YourStateClass with the actual class name

        // When
        state.handle();

        // Then
        verify(cli).displayAddProduct(any());
        verify(cli).displayMsg(" Product added successfully! ", true);
        verify(myApp).setState(any(ManageProductsState.class));
    }
}