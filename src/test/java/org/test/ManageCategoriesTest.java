package org.test;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.Category;
import org.data.DataBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.sates.LoginState;
import org.sates.ManageAccountsState;
import org.sates.ManageCategoriesState;
import org.sates.StartState;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ManageCategoriesTest {

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
        ArrayList<Category> categoryList = new ArrayList<>();
        when(myApp.getDatabase()).thenReturn(myDatabase);
        when(myApp.getError()).thenReturn(error);
        when(myApp.getCli()).thenReturn(cli);
        when(myApp.getDatabase().getCategoryList()).thenReturn(categoryList);
        when(myApp.getCli().displayManageCategories(categoryList)).thenReturn("a");
        // Create an instance of your class under test, passing the mock as a parameter
        ManageCategoriesState state = spy(new ManageCategoriesState(myApp));

        // When
        state.handle();

        // Then
        verify(cli).displayManageCategories(categoryList);
    }
}