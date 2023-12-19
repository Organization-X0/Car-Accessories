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
import org.sates.ManageAccountsState;
import org.sates.UpdateProductState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ManageAccountsTest {

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
        when(myApp.getError()).thenReturn(error);
        when(myApp.getDatabase()).thenReturn(myDatabase);
        Map<String, String> signUpData = new HashMap<>();
        when(myApp.getCli()).thenReturn(cli);
        ArrayList<User> customerList = new ArrayList<>();
        // Add mock customers to the list if necessary
        when(myApp.getDatabase().getCustomerList()).thenReturn(customerList);
        when(myApp.getCli().displayManageAccounts(customerList)).thenReturn("d");
        // Create an instance of your class under test, passing the mock as a parameter
        ManageAccountsState state = spy(new ManageAccountsState(myApp));
        doNothing().when(state).handleInput(any());

        // When
        state.handle();

        // Then
        verify(myApp.getError()).checkAndShow(anyString(), any());
        verify(myApp.getCli()).displayManageAccounts(customerList);
    }
}