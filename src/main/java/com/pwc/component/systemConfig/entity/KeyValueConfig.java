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
    private String name;

    @Column(name="VALUE")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
