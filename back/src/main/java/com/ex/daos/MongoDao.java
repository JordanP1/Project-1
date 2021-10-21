package com.ex.daos;
import com.ex.connections.DBConnector;
import com.ex.models.Reimbursement;
import com.ex.models.User;
import com.ex.utils.Logging;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class MongoDao implements Dao {
    private DBConnector connector;

    public MongoDao(DBConnector connector) {
        this.connector = connector;
    }

    /**
     * Retrieve a list of all users in the database.
     * @return Users in a list.
     */
    @Override
    public List getAllUsers() {
        try (Connection c = connector.newConnection()) {
            //Retrieve all users

            Logging.getLogger().info("Retrieving all users from database.");

            //SQL statement
            String sql = "SELECT id, is_employee, email, password, first_name, last_name, balance from project1_users";
            Statement s = c.createStatement();

            //Execute query statement
            ResultSet results = s.executeQuery(sql);
            List<User> users = new ArrayList<>();

            //Parse over the results
            while (results.next()) {
                User user = new User();

                user.setId(results.getInt("id"));
                user.setIsEmployee(results.getBoolean("is_employee"));
                user.setEmail(results.getString("email"));
                user.setPassword(results.getString("password"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setBalance(results.getDouble("balance"));

                users.add(user);
            }

            results.close();
            s.close();

            return users;

        } catch (Exception ex) {
            throw new RuntimeException("Could not retrieve data from database.", ex);
        }
    }

    /**
     * Get a user by Id
     * @param id User id.
     * @return A user
     */
    @Override
    public User getUser(int id) {
        try (Connection c = connector.newConnection()) {
            //Retrieve all users

            //SQL statement
            String sql = "SELECT id, is_employee, email, password, first_name, last_name, balance from project1_users where id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);

            //Execute query statement
            ResultSet results = ps.executeQuery();
            User user = new User();

            //Parse over the results
            while (results.next()) {
                user.setId(results.getInt("id"));
                user.setIsEmployee(results.getBoolean("is_employee"));
                user.setEmail(results.getString("email"));
                user.setPassword(results.getString("password"));
                user.setFirstName(results.getString("first_name"));
                user.setLastName(results.getString("last_name"));
                user.setBalance(results.getDouble("balance"));
            }

            results.close();
            ps.close();

            return user;

        } catch (Exception ex) {
            throw new RuntimeException("Could not retrieve data from database.", ex);
        }
    }

    /**
     * Save user to the database.
     * @param user User to save.
     */
    @Override
    public int saveUser(User user) {
        try (Connection c = this.connector.newConnection()) {
            //SQL statement
            String sql = "insert into project1_users (is_employee, email, password, first_name, last_name, balance) values (?, ?, ?, ?, ?, ?) on conflict do nothing";

            //Create statement
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, user.getIsEmployee());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getLastName());
            ps.setDouble(6, user.getBalance());

            //Database Meta
            DatabaseMetaData dbMeta = c.getMetaData();
            if (dbMeta.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE)) {
                c.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

                //Execute SQL Statement
                int insertedCount = ps.executeUpdate();

                if (insertedCount <= 0) {
                    System.out.println("User already exists in database.");
                    return -1;
                }

                int id = 0;

                ResultSet generatedKeys = ps.getGeneratedKeys();
                while(generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }

                user.setId((id));

                System.out.println("User \'" + user.getEmail() + "\' created successfully.");

                return id;
            } else {
                throw new RuntimeException("Can not complete transaction");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Could not save user data.", ex);
        }
    }

    /**
     * Retrieve a list of all reimbursements in the database.
     * @return Reimbursements in a list.
     */
    @Override
    public List getAllReimbursements() {
        try (Connection c = connector.newConnection()) {
            //Retrieve all reimbursements

            Logging.getLogger().info("Retrieving all reimbursements from database.");

            //SQL statement
            String sql = "SELECT id, submitter_id, resolver_id, submitter_email, resolver_email, amount, status from project1_reimbursements";
            Statement s = c.createStatement();

            //Execute query statement
            ResultSet results = s.executeQuery(sql);
            List<Reimbursement> reimbursements = new ArrayList<>();

            //Parse over the results
            while (results.next()) {
                Reimbursement reimbursement = new Reimbursement();

                reimbursement.setId(results.getInt("id"));
                reimbursement.setSubmitterId(results.getInt("submitter_id"));
                reimbursement.setResolverId(results.getInt("resolver_id"));
                reimbursement.setSubmitterEmail(results.getString("submitter_email"));
                reimbursement.setResolverEmail(results.getString("resolver_email"));
                reimbursement.setAmount(results.getDouble("amount"));
                reimbursement.setStatus(results.getString("status"));

                reimbursements.add(reimbursement);
            }

            results.close();
            s.close();

            return reimbursements;

        } catch (Exception ex) {
            throw new RuntimeException("Could not retrieve data from database.", ex);
        }
    }

    /**
     * Get a reimbursement by Id
     * @param id Reimbursement id.
     * @return A reimbursement
     */
    @Override
    public Reimbursement getReimbursement(int id) {
        try (Connection c = connector.newConnection()) {
            //Retrieve all reimbursements

            //SQL statement
            String sql = "SELECT id, submitter_id, resolver_id, submitter_email, resolver_email, amount, status from project1_reimbursements where id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);

            //Execute query statement
            ResultSet results = ps.executeQuery();
            Reimbursement reimbursement = new Reimbursement();

            //Parse over the results
            while (results.next()) {
                reimbursement.setId(results.getInt("id"));
                reimbursement.setSubmitterId(results.getInt("submitter_id"));
                reimbursement.setResolverId(results.getInt("resolver_id"));
                reimbursement.setSubmitterEmail(results.getString("submitter_email"));
                reimbursement.setResolverEmail(results.getString("resolver_email"));
                reimbursement.setAmount(results.getDouble("amount"));
                reimbursement.setStatus(results.getString("status"));
            }

            results.close();
            ps.close();

            return reimbursement;

        } catch (Exception ex) {
            throw new RuntimeException("Could not retrieve data from database.", ex);
        }
    }

    /**
     * Save reimbursement to the database.
     * @param reimbursement Reimbursement to save.
     */
    @Override
    public int saveReimbursement(Reimbursement reimbursement) {
        try (Connection c = this.connector.newConnection()) {
            //SQL statement
            String sql = "insert into project1_reimbursements (submitter_id, resolver_id, submitter_email, resolver_email, amount, status) values (?, ?, ?, ?, ?, ?) on conflict do nothing";

            //Create statement
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, reimbursement.getSubmitterId());
            ps.setInt(2, reimbursement.getResolverId());
            ps.setString(3, reimbursement.getSubmitterEmail());
            ps.setString(4, reimbursement.getResolverEmail());
            ps.setDouble(5, reimbursement.getAmount());
            ps.setString(6, reimbursement.getStatus());

            //Database Meta
            DatabaseMetaData dbMeta = c.getMetaData();
            if (dbMeta.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE)) {
                c.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

                //Execute SQL Statement
                int insertedCount = ps.executeUpdate();

                if (insertedCount <= 0) {
                    System.out.println("Failed to add reimbursement to database.");
                    return -1;
                }

                int id = 0;

                ResultSet generatedKeys = ps.getGeneratedKeys();
                while(generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }

                reimbursement.setId((id));

                System.out.println("Reimbursement for \'" + reimbursement.getSubmitterEmail() + "\' created successfully.");

                return id;
            } else {
                throw new RuntimeException("Can not complete transaction");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Could not save reimbursement data.", ex);
        }
    }

    /**
     * Update reimbursement
     * @param reimbursement Reimbursement info.
     * @return
     */
    @Override
    public int updateReimbursement(Reimbursement reimbursement) {
        try (Connection c = this.connector.newConnection()) {
            //SQL statement
            String sql = "update project1_reimbursements set resolver_id = ?, resolver_email = ?, status = ? where id = ?";

            //Create statement
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, reimbursement.getResolverId());
            ps.setString(2, reimbursement.getResolverEmail());
            ps.setString(3, reimbursement.getStatus());
            ps.setInt(4, reimbursement.getId());

            //Database Meta
            DatabaseMetaData dbMeta = c.getMetaData();
            if (dbMeta.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE)) {
                c.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

                //Execute SQL Statement
                int insertedCount = ps.executeUpdate();

                if (insertedCount <= 0) {
                    System.out.println("Failed to update reimbursement.");
                    return -1;
                }

                System.out.println("Reimbursement for id \'" + reimbursement.getId() + "\' updated successfully.");

                return reimbursement.getId();
            } else {
                throw new RuntimeException("Can not complete transaction");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Could not save reimbursement data.", ex);
        }
    }
}
