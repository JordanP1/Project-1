package com.ex.services;

import com.ex.models.Reimbursement;
import com.ex.models.User;

import java.util.List;

public interface Service {
    /**
     * Retrieve all user data.
     * @return All user data in a list.
     */
    public List getAllUsers();

    /**
     * Get user by Id.
     * @param id User Id.
     * @return Returns user.
     */
    public User getUser(int id);

    /**
     * Save user persistence.
     * @param user User to save.
     */
    public int saveUser(User user);

    /**
     * Retrieves all reimbursements.
     * @return All reimbursements in a list.
     */
    List getAllReimbursements();

    /**
     * Get reimbursement by Id.
     * @param id Reimbursement Id.
     * @return Returns reimbursement.
     */
    public Reimbursement getReimbursement(int id);

    /**
     * Save reimbursement persistence.
     * @param reimbursement Reimbursement to save.
     */
    public int saveReimbursement(Reimbursement reimbursement);

    /**
     * Update reimbursement
     * @param reimbursement Reimbursement info.
     * @return
     */
    public int updateReimbursement(Reimbursement reimbursement);
}
