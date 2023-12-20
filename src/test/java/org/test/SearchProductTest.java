package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.AdminDashboardState;
import org.sates.ManageProductsState;
import org.sates.ProductCatalogState;
import org.sates.SearchProductState;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SearchProductTest {
    @Mock
    private App myApp;
    @Mock
    private Cli cli;
    @Mock
    private Error error;

    @Test
    public void testHandle() {
        // Given
        when(myApp.getCli()).thenReturn(cli);
        when(cli.displaySearchProduct()).thenReturn("item1");
        when(myApp.getError()).thenReturn(error);
        when(error.getLocation()).thenReturn("SearchProduct"); // Assuming "SearchProduct" is the state string
        when(myApp.whoLoggedIn()).thenReturn("admin"); // Mock the method to return "admin"
        // Create an instance of your class under test, passing the mock as a parameter
        SearchProductState state = spy(new SearchProductState(myApp));

        // Stub the handleInput method to do nothing
        doNothing().when(state).handleInput(any());

        // When
        state.handle();

        // Then
        verify(cli).displaySearchProduct();
        verify(myApp).setState(any(ManageProductsState.class)); // Verify that setState was called with an instance of ManageProductsState
    }

    @Test
    public void testHandleNonAdmin() {
        // Given
        when(myApp.getCli()).thenReturn(cli);
        when(cli.displaySearchProduct()).thenReturn("item1");
        when(myApp.getError()).thenReturn(error);
        when(error.getLocation()).thenReturn("null");
        // Create an instance of your class under test, passing the mock as a parameter
        SearchProductState state = spy(new SearchProductState(myApp));

        // Stub the handleInput method to do nothing
        doNothing().when(state).handleInput(any());


        // When
        state.handle();

        // Then
        verify(cli).displaySearchProduct();
    }

}
