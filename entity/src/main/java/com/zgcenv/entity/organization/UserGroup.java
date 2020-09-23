package com.zgcenv.entity.organization;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName UserGroup
 * @Description
 * @Author Mr.Jangni
 * @Date 2020-9-17
 * @Version 1.0
 **/
@Entity
@Table(name = "user_group", schema = "cloud")
public class UserGroup {
    private String id;
    private String parentId;
    private String name;
    private String description;
    private String deleted;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private String createdBy;
    private String updatedBy;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "parent_id")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "deleted")
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "created_time")
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(name = "updated_time")
    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Basic
    @Column(name = "created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "updated_by")
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroup userGroup = (UserGroup) o;

        if (id != null ? !id.equals(userGroup.id) : userGroup.id != null) return false;
        if (parentId != null ? !parentId.equals(userGroup.parentId) : userGroup.parentId != null) return false;
        if (name != null ? !name.equals(userGroup.name) : userGroup.name != null) return false;
        if (description != null ? !description.equals(userGroup.description) : userGroup.description != null)
            return false;
        if (deleted != null ? !deleted.equals(userGroup.deleted) : userGroup.deleted != null) return false;
        if (createdTime != null ? !createdTime.equals(userGroup.createdTime) : userGroup.createdTime != null)
            return false;
        if (updatedTime != null ? !updatedTime.equals(userGroup.updatedTime) : userGroup.updatedTime != null)
            return false;
        if (createdBy != null ? !createdBy.equals(userGroup.createdBy) : userGroup.createdBy != null) return false;
        if (updatedBy != null ? !updatedBy.equals(userGroup.updatedBy) : userGroup.updatedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (updatedTime != null ? updatedTime.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (updatedBy != null ? updatedBy.hashCode() : 0);
        return result;
    }
}
