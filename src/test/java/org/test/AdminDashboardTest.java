package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.AdminDashboardState;
import org.sates.ManageProductsState;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AdminDashboardTest {
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
            when(cli.displayAdminDashboard()).thenReturn("1");
            when(myApp.getError()).thenReturn(error);
            // Create an instance of your class under test, passing the mock as a parameter
            AdminDashboardState state = new AdminDashboardState(myApp);

            // When
            state.handle();

            // Then
            verify(cli).displayAdminDashboard();
            verify(myApp).setState(any(ManageProductsState.class));
        }

}
