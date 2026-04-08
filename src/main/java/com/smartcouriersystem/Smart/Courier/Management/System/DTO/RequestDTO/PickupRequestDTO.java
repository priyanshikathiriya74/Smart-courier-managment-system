package com.smartcouriersystem.Smart.Courier.Management.System.DTO.RequestDTO;

public class PickupRequestDTO {
    private long agentId;
    private long packageId;

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }

    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }
}
