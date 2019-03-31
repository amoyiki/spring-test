package com.amoyiki.springtest.entry;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`schedule_job`")
public class ScheduleJob {
    /**
     * 任务id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    private Integer id;

    /**
     * 任务名称
     */
    @Column(name = "`job_name`")
    private String jobName;

    /**
     * cron表达式
     */
    @Column(name = "`cron_expression`")
    private String cronExpression;

    /**
     * 服务名称
     */
    @Column(name = "`bean_name`")
    private String beanName;

    /**
     * 方法名称
     */
    @Column(name = "`method_name`")
    private String methodName;

    /**
     * 状态 1.启动 2.暂停
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 是否删除 0.否 1.是
     */
    @Column(name = "`deleted`")
    private Boolean deleted;

    /**
     * 创建人id
     */
    @Column(name = "`creator_id`")
    private Integer creatorId;

    /**
     * 创建人
     */
    @Column(name = "`creator_name`")
    private String creatorName;

    /**
     * 创建时间
     */
    @Column(name = "`created`")
    private Date created;

    /**
     * 更新时间
     */
    @Column(name = "`updated`")
    private Date updated;

    /**
     * 获取任务id
     *
     * @return id - 任务id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置任务id
     *
     * @param id 任务id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取任务名称
     *
     * @return job_name - 任务名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置任务名称
     *
     * @param jobName 任务名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 获取cron表达式
     *
     * @return cron_expression - cron表达式
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * 设置cron表达式
     *
     * @param cronExpression cron表达式
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * 获取服务名称
     *
     * @return bean_name - 服务名称
     */
    public String getBeanName() {
        return beanName;
    }

    /**
     * 设置服务名称
     *
     * @param beanName 服务名称
     */
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 获取方法名称
     *
     * @return method_name - 方法名称
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 设置方法名称
     *
     * @param methodName 方法名称
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 获取状态 1.启动 2.暂停
     *
     * @return status - 状态 1.启动 2.暂停
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 1.启动 2.暂停
     *
     * @param status 状态 1.启动 2.暂停
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取是否删除 0.否 1.是
     *
     * @return deleted - 是否删除 0.否 1.是
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除 0.否 1.是
     *
     * @param deleted 是否删除 0.否 1.是
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取创建人id
     *
     * @return creator_id - 创建人id
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建人id
     *
     * @param creatorId 创建人id
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取创建人
     *
     * @return creator_name - 创建人
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 设置创建人
     *
     * @param creatorName 创建人
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取更新时间
     *
     * @return updated - 更新时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 设置更新时间
     *
     * @param updated 更新时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}