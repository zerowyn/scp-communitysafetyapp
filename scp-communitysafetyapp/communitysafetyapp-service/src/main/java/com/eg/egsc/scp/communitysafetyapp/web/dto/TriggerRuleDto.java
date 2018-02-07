/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.communitysafetyapp.web.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * @author xinghai
 * @since 2017/12/14
 */
public class TriggerRuleDto implements Serializable {
    private static final long serialVersionUID = -8062919324838944808L;
    /**
     * 事件类型
     **/
    @NotNull(message = "communitysafetyapp.trigger.save.dto.eventType.null")
    private Integer eventType;
    /**
     * 联动规则Id
     **/
    private String triggerRuleId;
    /**
     * 事件源
     **/
    @NotBlank(message = "communitysafetyapp.trigger.save.dto.eventSourceCode.null")
    private String eventSourceCode;
    /**
     * 联动规则列表
     **/
    private List<TriggersDto> triggers;
    /**
     * 创建人
     **/
    private String creator;
    /**
     * 是否具备时序性
     **/
    private Boolean isSequential = false;

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getEventSourceCode() {
        return eventSourceCode;
    }

    public void setEventSourceCode(String eventSourceCode) {
        this.eventSourceCode = eventSourceCode;
    }

    public String getTriggerRuleId() {
        return triggerRuleId;
    }

    public void setTriggerRuleId(String triggerRuleId) {
        this.triggerRuleId = triggerRuleId;
    }

    public List<TriggersDto> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<TriggersDto> triggers) {
        this.triggers = triggers;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getIsSequential() {
        return isSequential;
    }

    public void setIsSequential(Boolean isSequential) {
        this.isSequential = isSequential;
    }
}
