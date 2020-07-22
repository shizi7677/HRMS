package dao.admin;




import bean.admin.Dispatch;
import database.*;
import java.sql.Connection;

public class DispatchDao {
    /**
     * 获取派遣单位客户列表
     * @param conn 数据库连接
     *@param param 查询参数
     * @return 检索结果，格式："{success:true,msg:"",effects:1}"
     */
    public  DaoQueryListResult getClientList(Connection conn, QueryParameter param){
        return DbUtil.getList(conn,"dispatch",param, Dispatch.class);
    }


    public DaoQueryResult getClient(Connection conn, long id) {
        QueryConditions conditions = new QueryConditions();
        conditions.add("id","=",id);
        return  DbUtil.get(conn,"dispatch",conditions, Dispatch.class);
    }


    /**
     * 修改客户信息
     * @param conn 数据库连接
     * @param c 求职者信息
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public  DaoUpdateResult updateClient(Connection conn, Dispatch c){
        String sql = "update dispatch set rid=?,name=?,nickname=?,address=?,contact=?,phone=?,wx=?,qq=?,intro=? where id=?";
        Object []params = {c.getRid(),c.getName(),c.getNickname(),c.getAddress(), c.getContact(), c.getPhone(),c.getWx(),c.getQq(),c.getIntro(),c.getId()};
        //调用DbUtil封装的update方法
        return DbUtil.update(conn,sql,params);
    }

    /**
     * 插入潜在客户
     * @param conn
     * @param c
     * @return
     */
    public DaoUpdateResult insertClient(Connection conn, Dispatch c) {
        String sql = "insert into dispatch (rid,name,nickname,address,contact,phone,wx,qq,intro,status) values (?,?,?,?,?,?,?,?,?,?)";
        Object []params = {c.getRid(),c.getName(),c.getNickname(),c.getAddress(), c.getContact(), c.getPhone(),c.getWx(),c.getQq(),c.getIntro(),c.getStatus()};
        return DbUtil.insert(conn,sql,params);
    }

    //删除潜在客户
    public DaoUpdateResult deleteClient1(Connection conn, long id) {
        QueryConditions conditions = new QueryConditions();
        conditions.add("id","=",id);
        return DbUtil.delete(conn,"dispatch",conditions);
    }


    //修改客户状态 合作或者潜在 0_潜在，1_合作
    public  DaoUpdateResult updateStatus(Connection conn, long id,int status){
        String sql = "update dispatch set status=? where id=?";
        Object []params = {status,id};
        //调用DbUtil封装的update方法
        return DbUtil.update(conn,sql,params);
    }

}