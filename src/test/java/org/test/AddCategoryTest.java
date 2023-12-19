package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.AddCategoryState;
import org.sates.AdminDashboardState;
import org.sates.ManageProductsState;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AddCategoryTest {
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
            when(cli.displayAddCategory()).thenReturn("new Category");
            when(myApp.getError()).thenReturn(error);
            when(error.getLocation()).thenReturn("null");
            // Create an instance of your class under test, passing the mock as a parameter
            AddCategoryState state = spy(new AddCategoryState(myApp)); // replace YourStateClass with the actual class name
            doNothing().when(state).handleInput(any());

            // When
            state.handle();

            // Then
            verify(cli).displayAddCategory();
            verify(cli).displayMsg(" Category added successfully! ", true);
        }

}
