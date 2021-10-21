package com.ex.services;

import com.ex.daos.Dao;
import com.ex.models.Reimbursement;
import com.ex.models.User;
import com.ex.utils.Logging;

import java.util.ArrayList;
import java.util.List;

public class DataService implements Service {
    private Dao dao;
    //private List<User> users = null;
    //private List<Reimbursement> reimbursements = null;

    public  DataService() {}
    public  DataService(Dao dao) {
        this.dao = dao;
    }

    /**
     * Retrieve all users.
     * @return Users contained in a list.
     */
//    public List<User> getUsers() {
//        return users;
//    }

    /**
     * Retrieve all reimbursements.
     * @return Reimbursement contained in a list.
     */
//    public List<Reimbursement> getReimbursements() {
//        return reimbursements;
//    }

    /**
     * Load all data.
     * @return
     */
    @Override
    public List getAllUsers() {
        Logging.getLogger().info("Retrieving all users from the Data Service.");
//        if (users == null) {
//            users = dao.getAllUsers();
//        }
//
//        return users;

        return dao.getAllUsers();
    }

    /**
     * Get user by id
     * @param id User Id.
     * @return A user
     */
    @Override
    public User getUser(int id) {
        Logging.getLogger().info("Retrieving user Id " + id + " from Data Service.");
        User user = dao.getUser(id);

//        if (users == null) {
//            users = new ArrayList<>();
//            users.add(user);
//        } else {
//            boolean found = false;
//            for (User u : users) {
//                if (u.getId() == id) {
//                    found = true;
//                    break;
//                }
//            }
//
//            if (!found) {
//                users.add(user);
//            }
//        }

        return user;
    }

    /**
     * Save and persist user.
     * @param user User to save.
     */
    @Override
    public int saveUser(User user) {
        Logging.getLogger().info("Saving user from Data Service.");

//        if (users == null) {
//            users = new ArrayList<>();
//        }
//
//        users.add(user);
        return dao.saveUser(user);
    }

    /**
     * Load all data.
     * @return
     */
    @Override
    public List getAllReimbursements() {
        Logging.getLogger().info("Retrieving all reimbursements from the Data Service.");
//        if (reimbursements == null) {
//            reimbursements = dao.getAllReimbursements();
//        }
//
//        return reimbursements;

        return dao.getAllReimbursements();
    }

    /**
     * Get reimbursement by id
     * @param id Reimbursement Id.
     * @return A reimbursement
     */
    @Override
    public Reimbursement getReimbursement(int id) {
        Logging.getLogger().info("Retrieving reimbursement Id " + id + " from Data Service.");
        Reimbursement reimbursement = dao.getReimbursement(id);

//        if (reimbursements == null) {
//            reimbursements = new ArrayList<>();
//            reimbursements.add(reimbursement);
//        } else {
//            boolean found = false;
//            for (Reimbursement r : reimbursements) {
//                if (r.getId() == id) {
//                    found = true;
//                    break;
//                }
//            }
//
//            if (!found) {
//                reimbursements.add(reimbursement);
//            }
//        }

        return reimbursement;
    }

    /**
     * Save and persist reimbursement.
     * @param reimbursement Reimbursement to save.
     */
    @Override
    public int saveReimbursement(Reimbursement reimbursement) {
        Logging.getLogger().info("Saving reimbursement from Data Service.");

//        if (reimbursements == null) {
//            reimbursements = new ArrayList<>();
//        }
//
//        reimbursements.add(reimbursement);
//        return dao.saveReimbursement(reimbursement);

        return dao.saveReimbursement(reimbursement);
    }

    /**
     * Update reimbursement
     * @param reimbursement Reimbursement info.
     * @return
     */
    @Override
    public int updateReimbursement(Reimbursement reimbursement) {
        Logging.getLogger().info("Updating reimbursement from Data Service.");
        return dao.updateReimbursement(reimbursement);
    }
}
