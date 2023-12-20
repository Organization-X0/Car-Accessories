package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.Category;
import org.data.DataBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.ManageCategoriesState;
import org.sates.UpdateAccountState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UpdateAccountStateTest {

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
        when(myApp.getError()).thenReturn(error);
        when(myApp.getCli()).thenReturn(cli);
        Map<String, String> updateData = new HashMap<>();
        updateData.put("FullName", "newUsername");
        when(myApp.getCli().displayUpdateAccount()).thenReturn(updateData);
        // Create an instance of your class under test, passing the mock as a parameter
        UpdateAccountState state = spy(new UpdateAccountState(myApp));

        // When
        state.handle();

        // Then
        verify(cli).displayUpdateAccount();
    }
}