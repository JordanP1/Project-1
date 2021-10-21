package com.ex.tests;
import com.ex.models.Reimbursement;
import com.ex.models.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.*;

public class ReimbursementTests {
    private Reimbursement rb;
    private final int id = 99;
    private final int submitterId = 55;
    private final int resolverId = 22;
    private final String submitterEmail = "submitter";
    private final String resolverEmail = "resolver";
    private final double amount = 100;
    private final String status = "Status";

    @Before
    public void init() {
        rb = new Reimbursement();

        rb.setId(id);
        rb.setSubmitterId(submitterId);
        rb.setResolverId(resolverId);
        rb.setSubmitterEmail(submitterEmail);
        rb.setResolverEmail(resolverEmail);
        rb.setAmount(amount);
        rb.setStatus(status);
    }

    @Test
    public void setsId() {
        Reimbursement nrb = new Reimbursement();
        int id = 50;
        nrb.setId(id);

        Assert.assertEquals("Id was not set.", id, nrb.getId());
    }

    @Test
    public void setsSubmitterId() {
        Reimbursement nrb = new Reimbursement();
        int id = 212;
        nrb.setSubmitterId(id);

        Assert.assertEquals("Submitter Id was not set.", id, nrb.getSubmitterId());
    }

    @Test
    public void setsResolverId() {
        Reimbursement nrb = new Reimbursement();
        int id = 263;
        nrb.setResolverId(id);

        Assert.assertEquals("Resolver Id was not set.", id, nrb.getResolverId());
    }

    @Test
    public void setsSubmitterEmail() {
        Reimbursement nrb = new Reimbursement();
        String email = "testMail";
        nrb.setSubmitterEmail(email);

        Assert.assertEquals("Submitter Email was not set.", email, nrb.getSubmitterEmail());
    }

    @Test
    public void setsResolverEmail() {
        Reimbursement nrb = new Reimbursement();
        String email = "testResolverMail";
        nrb.setResolverEmail(email);

        Assert.assertEquals("Resolver Email was not set.", email, nrb.getResolverEmail());
    }

    @Test
    public void setsAmount() {
        Reimbursement nrb = new Reimbursement();
        double amount = 384;
        nrb.setAmount(amount);

        Assert.assertEquals("Amount was not set.", amount, nrb.getAmount(), 0);
    }

    @Test
    public void setsStatus() {
        Reimbursement nrb = new Reimbursement();
        String status = "A Status";
        nrb.setStatus(status);

        Assert.assertEquals("Status was not set.", status, nrb.getStatus());
    }

    @Test
    public void getsId() {
        Assert.assertEquals("Id was not retrieved.", id, rb.getId());
    }

    @Test
    public void getsSubmitterId() {
        Assert.assertEquals("Submitter Id was not retrieved.", submitterId, rb.getSubmitterId());
    }

    @Test
    public void getsResolverId() {
        Assert.assertEquals("Resolver Id was not retrieved.", resolverId, rb.getResolverId());
    }

    @Test
    public void getsSubmitterEmail() {
        Assert.assertEquals("Submitter Email was not retrieved.", submitterEmail, rb.getSubmitterEmail());
    }

    @Test
    public void getsResolverEmail() {
        Assert.assertEquals("Resolver Email was not retrieved.", resolverEmail, rb.getResolverEmail());
    }

    @Test
    public void getsAmount() {
        Assert.assertEquals("Amount was not retrieved.", amount, rb.getAmount(), 0);
    }

    @Test
    public void getsStatus() {
        Assert.assertEquals("Status was not retrieved.", status, rb.getStatus());
    }
}
