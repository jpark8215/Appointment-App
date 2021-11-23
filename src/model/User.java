package model;

import java.sql.Timestamp;

/**
 * User class
 */
public class User {

    public static int userId;
    private  String userName;
    private String userPassword;
    private Timestamp userCreateDate;
    private String userCreatedBy;
    private Timestamp userUpdateDate;
    private  String userUpdatedBy;

    /**
     * @param userId userId
     * @param userName userName
     * @param userPassword userPassword
     * @param userCreateDate userCreateDate
     * @param userCreatedBy userCreatedBy
     * @param userUpdateDate userUpdateDate
     * @param userUpdatedBy userUpdatedBy
     */
    public User (int userId, String userName, String userPassword, Timestamp userCreateDate, String userCreatedBy, Timestamp userUpdateDate, String userUpdatedBy) {
        User.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userCreateDate = userCreateDate;
        this.userCreatedBy = userCreatedBy;
        this.userUpdateDate = userUpdateDate;
        this.userUpdatedBy = userUpdatedBy;

    }

    public User(int currentUserId, String username, String password) {
    }


    /**
     * @return userId
     */
    public int getUserId() { return userId; }

    public void setUserId(int userId) { User.userId = userId; }

    /**
     * @return userName
     */
    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    /**
     * @return userPassword
     */
    public String getUserPassword() { return userPassword; }

    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

    /**
     * @return userCreateDate
     */
    public Timestamp getUserCreateDate() { return userCreateDate; }

    public void setUserCreateDate(Timestamp userCreateDate) { this.userCreateDate = userCreateDate; }

    /**
     * @return userCreatedBy
     */
    public String getUserCreatedBy() { return userCreatedBy; }

    public void setUserCreatedBy(String userCreatedBy) { this.userCreatedBy = userCreatedBy; }

    /**
     * @return userUpdateDate
     */
    public Timestamp getUserUpdateDate() { return userUpdateDate; }

    public void setUserUpdateDate(Timestamp userUpdateDate) { this.userUpdateDate = userUpdateDate; }

    /**
     * @return userUpdatedBy
     */
    public String getUserUpdatedBy() { return userUpdatedBy; }

    public void setUserUpdatedBy(String userUpdatedBy) { this.userUpdatedBy = userUpdatedBy; }

}
