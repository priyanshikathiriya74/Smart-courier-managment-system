package com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO;

import java.util.List;

public class PkgAssignmentRequestDTO {
    private long managerId;
    private long agentId;
    private List<Long> packageIds;

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public List<Long> getPackageIds() {
        return packageIds;
    }

    public void setPackageIds(List<Long> packageIds) {
        this.packageIds = packageIds;
    }
}
