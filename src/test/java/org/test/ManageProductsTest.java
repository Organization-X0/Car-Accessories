package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.Category;
import org.data.DataBase;
import org.data.Product;
import org.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.ManageProductsState;
import org.sates.ProductCatalogState;
import org.sates.ProductCrudState;

import javax.xml.crypto.Data;
import java.util.ArrayList;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ManageProductsTest {

    @Mock
    private App myApp;
    @Test
    public void testHandle() {
        // Given
        ManageProductsState state = new ManageProductsState(myApp); // replace YourStateClass with the actual class name
        // When
        state.handle();

        verify(myApp).handelProductCatalogAndManageProducts(state.getStateString());

    }

}