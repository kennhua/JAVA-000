package datasource.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Long id;

    private Long userId;

    private String userName;

    private Long orderNumber;

    private String receiver;

    private String receiverPhone;

    private String receiverAddress;

    private BigDecimal orderMount;

    private Byte state;

    private Date createTime;

    private Date updateTime;

    public Order(Long id, Long userId, String userName, Long orderNumber, String receiver, String receiverPhone, String receiverAddress, BigDecimal orderMount, Byte state, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.orderNumber = orderNumber;
        this.receiver = receiver;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
        this.orderMount = orderMount;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Order() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public BigDecimal getOrderMount() {
        return orderMount;
    }

    public void setOrderMount(BigDecimal orderMount) {
        this.orderMount = orderMount;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
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
}