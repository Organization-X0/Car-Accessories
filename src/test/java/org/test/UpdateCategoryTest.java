package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.UpdateCategoryState;
import org.sates.UpdateProductState;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UpdateCategoryTest {

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
        when(myApp.getCli().displayUpdateCategory()).thenReturn("new name");
        // Create an instance of your class under test, passing the mock as a parameter
        UpdateCategoryState state = spy(new UpdateCategoryState(myApp));
        doNothing().when(state).handleInput(any());

        // When
        state.handle();

        // Then
        verify(myApp.getCli()).displayUpdateCategory();
    }
}