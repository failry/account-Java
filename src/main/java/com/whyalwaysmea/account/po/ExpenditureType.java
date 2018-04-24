package com.whyalwaysmea.account.po;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "expenditure_type")
@Data
public class ExpenditureType implements Serializable {

    @Transient
    private static final long serialVersionUID = -5409588016640623359L;

    /**
     * 分类id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类父id
     */
    private Long pid;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类创建者
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 分类icon url
     */
    @Column(name = "icon_url")
    private String iconUrl;

    /**
     * 排序id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Transient
    private List<ExpenditureType> childExpenditure;
}