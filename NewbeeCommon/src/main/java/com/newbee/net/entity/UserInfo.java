package com.newbee.net.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 登录人员表
 * </p>
 *
 * @author ${author}
 * @since 2018-11-21
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户编号
     */
    private String userNo;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 组织ID
     */
    private Integer unitId;
    /**
     * 组织名称
     */
    private String unitName;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别 性别 0:男 1:女
     */
    private Integer genger;
    /**
     * 公司
     */
    private String company;
    /**
     * 个性签名
     */
    private String personalizedSignature;
    /**
     * 是否面试者帐号 0：否 1：是
     */
    private Integer isInterview;
    /**
     * 员工照片
     */
    private String imgfile;
    /**
     * 状态:0 无效 1 有效
     */
    private Integer status;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 记录更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGenger() {
        return genger;
    }

    public void setGenger(Integer genger) {
        this.genger = genger;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    public Integer getIsInterview() {
        return isInterview;
    }

    public void setIsInterview(Integer isInterview) {
        this.isInterview = isInterview;
    }

    public String getImgfile() {
        return imgfile;
    }

    public void setImgfile(String imgfile) {
        this.imgfile = imgfile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
        ", id=" + id +
        ", userNo=" + userNo +
        ", userName=" + userName +
        ", nickName=" + nickName +
        ", unitId=" + unitId +
        ", unitName=" + unitName +
        ", password=" + password +
        ", genger=" + genger +
        ", company=" + company +
        ", personalizedSignature=" + personalizedSignature +
        ", isInterview=" + isInterview +
        ", imgfile=" + imgfile +
        ", status=" + status +
        ", lastLoginTime=" + lastLoginTime +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", remark=" + remark +
        "}";
    }
}
