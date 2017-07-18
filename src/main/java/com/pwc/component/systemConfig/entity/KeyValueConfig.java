package com.pwc.component.systemConfig.entity;

import com.pwc.common.base.entity.BaseEntity;
import com.pwc.component.workflow.entity.FlowEvent;
import com.pwc.component.workflow.entity.FlowPoint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name="KeyValueConfig")
public class KeyValueConfig extends BaseEntity {


	private static final long serialVersionUID = 1L;


    @Column(name="KEY")
    private String key;

    @Column(name="VALUE")
    private String value;

    public KeyValueConfig(String key, String value) {
        super();
        this.key=key;
        this.value=value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
