package com.ex.tests;

import com.ex.models.User;
import com.ex.utils.Logging;
import org.junit.Assert;
import org.junit.Before;
import org.junit.*;

import java.time.LocalDateTime;

public class UserTests {
    private User user;
    private final int id = 75;
    private final String email = "test@email.com";
    private final String password = "password123";
    private final double balance = 100.0;
    private final String firstName = "FirstName";
    private final String lastName = "LastName";

    @Before
    public void init() {
        user = new User();

        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setBalance(balance);
        user.setFirstName(firstName);
        user.setLastName(lastName);
    }

    @Test
    public void setsIsEmployee() {
        User newUser = new User();
        boolean isEmployee = true;
        newUser.setIsEmployee(isEmployee);

        Assert.assertEquals("Is Employee was not set.", isEmployee, newUser.getIsEmployee());
    }

    @Test
    public void setsId() {
        User newUser = new User();
        int id = 50;
        newUser.setId(id);

        Assert.assertEquals("Id was not set.", id, newUser.getId());
    }

    @Test
    public void setsEmail() {
        User newUser = new User();
        String email = "new@email.com";
        newUser.setEmail(email);

        Assert.assertEquals("Email was not set.", email, newUser.getEmail());
    }

    @Test
    public void setsPassword() {
        User newUser = new User();
        String password = "newpass";
        newUser.setPassword(password);

        Assert.assertEquals("Password was not set.", password, newUser.getPassword());
    }

    @Test
    public void setsBalance() {
        User newUser = new User();
        double balance = 10.0;
        newUser.setBalance(balance);

        Assert.assertEquals("Balance was not set.", balance, newUser.getBalance(), 0);
    }

    @Test
    public void setsFirstName() {
        User newUser = new User();
        String firstName = "First User";
        newUser.setFirstName(firstName);

        Assert.assertEquals("First Name was not set.", firstName, newUser.getFirstName());
    }

    @Test
    public void setsLastName() {
        User newUser = new User();
        String lastName = "Last User";
        newUser.setLastName(lastName);

        Assert.assertEquals("Last Name was not set.", lastName, newUser.getLastName());
    }

    @Test
    public void getsId() {
        Assert.assertEquals("Id was not retrieved.", id, user.getId());
    }

    @Test
    public void getsEmail() {
        Assert.assertEquals("Email was not retrieved.", email, user.getEmail());
    }

    @Test
    public void getsPassword() {
        Assert.assertEquals("Password was not retrieved.", password, user.getPassword());
    }

    @Test
    public void getsBalance() {
        Assert.assertEquals("Balance was not retrieved.", balance, user.getBalance(), 0);
    }

    @Test
    public void getsIsEmployee() {
        User customer = new User();
        User employee = new User();
        customer.setIsEmployee(false);
        employee.setIsEmployee(true);

        Assert.assertFalse("Customer should not be an employee.", customer.getIsEmployee());
        Assert.assertTrue("Employee should be an employee.", employee.getIsEmployee());
    }

    @Test
    public void getsFirstName() {
        Assert.assertEquals("First Name was not retrieved.", firstName, user.getFirstName());
    }

    @Test
    public void getsLastName() {
        Assert.assertEquals("Last Name was not retrieved.", lastName, user.getLastName());
    }

    @Test
    public void getsLogger() {
        Assert.assertNotNull("Logger is null.", Logging.getLogger());
    }
}
