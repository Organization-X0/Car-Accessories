package org.test;

import org.car.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.ManageProductsState;
import org.sates.ProductCatalogState;

import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class ProductCatalogTest {

    @Mock
    private App myApp;
    @Test
    public void testHandle() {
        // Given
        ProductCatalogState state = new ProductCatalogState(myApp); // replace YourStateClass with the actual class name
        // When
        state.handle();

        verify(myApp).handelProductCatalogAndManageProducts(state.getStateString());

    }

}