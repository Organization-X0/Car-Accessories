package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.CustomerDashboardState;
import org.sates.ProductCatalogState;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CustomerDashboardTest {

    @Mock
    private App myApp;
    @Mock
    private Cli cli;
    @Mock
    private User account;
    @Mock
    private Error error;

    @Test
    public void testHandle() {
        // Given
        when(myApp.getEmail()).thenReturn("user1@gmail.com");
        when(myApp.getCli()).thenReturn(cli);
        when(myApp.searchAccount(myApp.getEmail())).thenReturn(account);
        when(cli.displayCustomerDashboard(account)).thenReturn("1");
        when(myApp.getError()).thenReturn(error);
        // Create an instance of your class under test, passing the mock as a parameter
        CustomerDashboardState state = new CustomerDashboardState(myApp);

        // When
        state.handle();

        // Then
        verify(cli).displayCustomerDashboard(any(User.class));
        verify(myApp).setState(any(ProductCatalogState.class));
    }
}
