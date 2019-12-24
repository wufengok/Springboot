package com.skonst.report.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author wufeng
 * @since 2019-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CofUserAccount implements Serializable {

    private static final long serialVersionUID=1L;

    private Long userAccountId;

    private Date createdTime;

    private Date modifiedTime;

    private Boolean deleted;

    private String tenantCode;

    private Date accountExpiredDate;

    private String email;

    private String name;

    private String password;

    private Date passwordExpiredDate;

    private Date startValidDate;

    private String status;

    private String username;

    private Long creator;

    private Long modifier;

    /**
     * 数据来源0为主数据，1为系统添加数据
     */
    private Integer dataSource;


}
