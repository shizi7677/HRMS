package bean.client;

//客户服务型项目
public class Finance {
    //客户编号
    private long cid;
   //客户类型 0_派遣单位 1_合作客户
   private byte type;
    //统一社会信用代码
    private String code;
    //开户的银行
    private String bank;
    //银行卡号
    private String cardNo;
    //联系人
    private String contact;
    //电话
    private String phone;
    //公司地址
    private String address;
    //大额行号
    private String bankNo;
    //公司余额
    private float balance;
    //备注
    private String comments;

    public Finance() {
    }

    public Finance(long cid, byte type, String code, String bank, String cardNo, String contact, String phone, String address, String bankNo, float balance, String comments) {
        this.cid = cid;
        this.type = type;
        this.code = code;
        this.bank = bank;
        this.cardNo = cardNo;
        this.contact = contact;
        this.phone = phone;
        this.address = address;
        this.bankNo = bankNo;
        this.balance = balance;
        this.comments = comments;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Finance{" +
                "cid=" + cid +
                ", type=" + type +
                ", code='" + code + '\'' +
                ", bank='" + bank + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", balance=" + balance +
                ", comments='" + comments + '\'' +
                '}';
    }
}
