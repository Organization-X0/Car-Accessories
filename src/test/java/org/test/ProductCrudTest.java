package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.Product;
import org.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.InstallerDashboardState;
import org.sates.ProductCrudState;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ProductCrudTest {

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
        // Given
        ArrayList<Product> mockProductList = new ArrayList<>();
        when(myApp.getProductArrayListBetweenState()).thenReturn(mockProductList);
        when(myApp.getCli()).thenReturn(cli);
        when(myApp.getError()).thenReturn(error);
        when(myApp.getCli().displayProducts(mockProductList)).thenReturn("u");
        // Create an instance of your class under test, passing the mock as a parameter
        ProductCrudState state = spy(new ProductCrudState(myApp)); // replace YourStateClass with the actual class name
        doNothing().when(state).handleInput(any());

        // When
        state.handle();

        // Then
        verify(myApp.getError()).checkAndShow(anyString(), any());
        verify(cli).displayProducts(mockProductList);
    }
}