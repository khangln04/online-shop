/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author buima
 */
public class ChangeHistory {
    private int changeId;
    private int userId;
    private String changedColumn;
    private String oldValue;
    private String newValue;
    private Date changeDate;

    public ChangeHistory() {
    }

    public ChangeHistory(int changeId, int userId, String changedColumn, String oldValue, String newValue, Date changeDate) {
        this.changeId = changeId;
        this.userId = userId;
        this.changedColumn = changedColumn;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.changeDate = changeDate;
    }

    public int getChangeId() {
        return changeId;
    }

    public void setChangeId(int changeId) {
        this.changeId = changeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getChangedColumn() {
        return changedColumn;
    }

    public void setChangedColumn(String changedColumn) {
        this.changedColumn = changedColumn;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
    
}
