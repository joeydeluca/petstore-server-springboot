package com.petservice.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Joe Deluca on 9/21/2016.
 */
@MappedSuperclass
public abstract class DomainEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    private Date modifiedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    private Date createdDate;

    @Version
    private int version = 0;

    @PrePersist
    public void prePersist(){
        this.createdDate = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.modifiedDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainEntity that = (DomainEntity) o;

        if(id == null || that.id == null) return false;

        return id.equals(that.id);

    }
}
