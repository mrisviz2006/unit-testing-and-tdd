package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class SavingAccountTest {
    public static final int ID_STUB = 1;

    @Test
    public void shouldStorePropertiesWhenCreated() throws IllegalAccessException {
        //region when
        Client client = new Client(ID_STUB, "dummy client name");
        SavingAccount sut = new SavingAccount(ID_STUB, client, 1000.00);
        //endregion

        //region then
        assertThat(sut,
                allOf(
                        hasProperty("id", equalTo(ID_STUB)),
                        hasProperty("client", notNullValue()),
                        hasProperty("amount", equalTo(1000.00))
                ));
        //endregion
    }

    @Test
    public void GetErrorNullIdWhenCreated() {
        final Client DUMMY_CLIENT = new Client(ID_STUB, "dummy client name");
        final double DUMMY_AMOUNT = 1000.00;

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(0, DUMMY_CLIENT, DUMMY_AMOUNT));
    }

    @Test
    public void GetErrorNullClientWhenCreated() {
        final double DUMMY_AMOUNT = 1000.00;
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(ID_STUB, null, DUMMY_AMOUNT) );
    }

    @Test
    public void GetErrorLessThanMaxValueAmountWhenCreated() {
        final Client DUMMY_CLIENT = new Client(ID_STUB, "dummy client name");
        final double DUMMY_AMOUNT = Double.POSITIVE_INFINITY;
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->  new SavingAccount(ID_STUB, DUMMY_CLIENT, DUMMY_AMOUNT));
    }

    @Test
    public void GetErrorMoreThanMinMaxValueAmountWhenCreated() {
        final Client DUMMY_CLIENT = new Client(ID_STUB, "dummy client name");
        final double DUMMY_AMOUNT = Double.NEGATIVE_INFINITY;
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->  new SavingAccount(ID_STUB, DUMMY_CLIENT, DUMMY_AMOUNT));
    }

    @Test
    public void GetErrorThanMaxValueAmountWhenCreated() {
        final Client DUMMY_CLIENT = new Client(ID_STUB, "dummy client name");
        final double DUMMY_AMOUNT = Double.MAX_VALUE;
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->  new SavingAccount(ID_STUB, DUMMY_CLIENT, DUMMY_AMOUNT));
    }

    @Test
    public void GetErrorThanMinValueAmountWhenCreated() {
        final Client DUMMY_CLIENT = new Client(ID_STUB, "dummy client name");
        final double DUMMY_AMOUNT = -Double.MAX_VALUE;
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->  new SavingAccount(ID_STUB, DUMMY_CLIENT, DUMMY_AMOUNT));
    }
}
