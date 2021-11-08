package model;

import java.sql.Timestamp;

/**
 * User class
 */
public class User {

    private  int userId;
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
    this.userId = userId;
    this.userName = userName;
    this.userPassword = userPassword;
    this.userCreateDate = userCreateDate;
    this.userCreatedBy = userCreatedBy;
    this.userUpdateDate = userUpdateDate;
    this.userUpdatedBy = userUpdatedBy;

    }


    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserPassword() { return userPassword; }

    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }

    public Timestamp getUserCreateDate() { return userCreateDate; }

    public void setUserCreateDate(Timestamp userCreateDate) { this.userCreateDate = userCreateDate; }

    public String getUserCreatedBy() { return userCreatedBy; }

    public void setUserCreatedBy(String userCreatedBy) { this.userCreatedBy = userCreatedBy; }

    public Timestamp getUserUpdateDate() { return userUpdateDate; }

    public void setUserUpdateDate(Timestamp userUpdateDate) { this.userUpdateDate = userUpdateDate; }

    public String getUserUpdatedBy() { return userUpdatedBy; }

    public void setUserUpdatedBy(String userUpdatedBy) { this.userUpdatedBy = userUpdatedBy; }
}
