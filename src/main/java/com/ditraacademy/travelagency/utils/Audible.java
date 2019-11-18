package com.ditraacademy.travelagency.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
/*
* Give us the user from session
* get date
* EntityListeners : waiting for action ; ceation or modification
*
* */

public class Audible<U> {
    @CreatedBy
    private U createdBy ;

    @LastModifiedBy
    private U LastModifiedBy ;

    @CreatedDate
    private Date CreatedDate ;

    @LastModifiedDate
    private Date LastModifiedDate ;




}
