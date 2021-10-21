package com.ex.daos;

import com.ex.models.Reimbursement;
import com.ex.models.User;

import java.util.List;

public interface Dao {
    /**
     * Retrieves all user data.
     * @return All user data in a list.
     */
    List getAllUsers();

    /**
     * Retrieve user by Id.
     * @param id User id.
     * @return Returns a user.
     */
    User getUser(int id);

    /**
     * Save and persist user data.
     * @param user User to save.
     */
    int saveUser(User user);

    /**
     * Retrieves all reimbursements.
     * @return All reimbursements in a list.
     */
    List getAllReimbursements();

    /**
     * Retrieve reimbursement by Id.
     * @param id Reimbursement id.
     * @return Returns a reimbursement.
     */
    Reimbursement getReimbursement(int id);

    /**
     *
     * @param reimbursement Reimbursement to save.
     * @return Returns a reimbursement.
     */
    int saveReimbursement(Reimbursement reimbursement);

    /**
     * Update reimbursement
     * @param reimbursement Reimbursement info.
     * @return
     */
    int updateReimbursement(Reimbursement reimbursement);
}
