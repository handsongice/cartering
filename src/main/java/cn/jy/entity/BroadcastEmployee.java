package cn.jy.entity;

public class BroadcastEmployee {
    private Long id;

    private Long employeeId;

    private Long broadcastId;

    private String broads;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(Long broadcastId) {
        this.broadcastId = broadcastId;
    }

    public String getBroads() {
        return broads;
    }

    public void setBroads(String broads) {
        this.broads = broads;
    }
}