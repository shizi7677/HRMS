package dao.admin;


import bean.admin.Rule_social;
import database.*;

import java.sql.Connection;

public class Rule_socialDao {

    /**
     * 获取社保列表
     *
     * @param conn  数据库连接
     * @param param 查询参数
     * @return 检索结果，格式："{success:true,msg:"",effects:1}"
     */
    public DaoQueryListResult getRule_socialList(Connection conn, QueryParameter param) {
        return DbUtil.getList(conn, "rule_social", param, Rule_social.class);
    }

    public DaoQueryResult getSocial(Connection conn, long id) {
        QueryConditions conditions = new QueryConditions();
        conditions.add("id", "=", id);
        return DbUtil.get(conn, "rule_social", conditions, Rule_social.class);

    }

    public DaoUpdateResult updateSocial(Connection conn, Rule_social s) {
        String sql = "update rule_social set city = ?,start = ?,base=?,per1=?,per2=?,per3=?,extra=?,per4=?,per5=? where id=?";
        Object[] param = {s.getCity(), s.getStart(), s.getBase(), s.getPer1(), s.getPer2(), s.getPer3(), s.getExtra(),s.getPer4(),s.getPer5(), s.getId()};
        return DbUtil.update(conn, sql, param);
    }

    public DaoUpdateResult insertSocial(Connection conn, Rule_social s) {
        String sql = "insert rule_social(city ,start ,base,per1,per2,per3,extra,per4,per5) values (?,?,?,?,?,?,?,?,?)";
        Object[] param = {s.getCity(), s.getStart(), s.getBase(), s.getPer1(), s.getPer2(), s.getPer3(), s.getExtra(),s.getPer4(),s.getPer5()};
        return DbUtil.insert(conn, sql, param);
    }
}
