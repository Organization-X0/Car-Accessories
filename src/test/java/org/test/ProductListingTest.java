package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.AddCategoryState;
import org.sates.ProductCatalogState;
import org.sates.ProductListingState;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ProductListingTest {
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
            when(myApp.getCli()).thenReturn(cli);
            when(cli.displayCustomerProducts(any())).thenReturn("f");
            when(myApp.getError()).thenReturn(error);
            when(error.getLocation()).thenReturn("null");
            when(myApp.searchAccount(any())).thenReturn(user);
            when(user.getPhone()).thenReturn("1234567890");
            // Create an instance of your class under test, passing the mock as a parameter
            ProductListingState state = spy(new ProductListingState(myApp));
            doNothing().when(state).handleInput(any());
            // Set productAvailable field to true using reflection
            try {
                Field field = ProductListingState.class.getDeclaredField("productAvailable");
                field.setAccessible(true);
                field.set(state, true);
            }catch (Exception e){
                //Error
            }

            // When
            state.handle();

            // Then
            verify(cli).displayCustomerProducts(any());
            verify(cli).displayAfterPurchase(any(), any());
        }

}
