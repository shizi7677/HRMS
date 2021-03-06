package bean.admin;

//账号表
public class Account {
    private long id;//编号
    private String nickname;//昵称
    private String username;//账号
    private String password;//密码
    private byte role;//账号角色 0_平台管理员 1_派遣单位 2_合作单位 3_员工
    private long rid;//所属公司id 平台默认为0 用role和rid可以确定账户属于那个派遣单位或者合作单位
    private  int permission;//权限使用位运算 第0位账号管理 第1位客户管理 第2位工资审核 第3位财务管理

    public Account() {
    }

    public Account(long id, String nickname, String username, String password, byte role, long rid, int permission) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.role = role;
        this.rid = rid;
        this.permission = permission;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", rid=" + rid +
                ", permission=" + permission +
                '}';
    }
}
