package dao.rule;


import bean.contract.Contract;
import bean.rule.RuleSocial;
import database.*;

import java.sql.Connection;

public class RuleSocialDao {

    /**
     * 获取社保规则集合
     * @param conn 连接数据库
     * @param param 查询参数
     * @return 检索结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoQueryListResult getList(Connection conn, QueryParameter param){
        if(param.conditions.extra!=null && !param.conditions.extra.isEmpty()) {
            //根据地市模糊查询
            param.addCondition("city","like",param.conditions.extra);
        }
        return DbUtil.getList(conn, "rule_social", param, RuleSocial.class);
    }

    public static DaoQueryResult get(Connection conn, long id) {
        QueryConditions conditions = new QueryConditions();
        conditions.add("id", "=", id);
        return DbUtil.get(conn, "rule_social", conditions, RuleSocial.class);
    }

    /**
     * 修改社保规则
     * @param conn 连接数据库
     * @param rule 社保规则对象
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult update(Connection conn, RuleSocial rule) {
        String sql = "update rule_social set city=?, start=?, base=?, per1=?, per2=?, per3=?, extra=?, per4=?, per5=? where id=?";
        Object[] params = {rule.getCity(), rule.getStart(), rule.getBase(), rule.getPer1(), rule.getPer2(), rule.getPer3(), rule.getExtra(), rule.getPer4(), rule.getPer5(), rule.getId()};
        return DbUtil.update(conn, sql, params);
    }

    /**
     * 添加医社保规则
     * @param conn 连接数据库
     * @param rule 社保规则对象
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, RuleSocial rule) {
        String sql = "insert into rule_social (city , start, base, per1, per2, per3, extra, per4, per5) values (?,?,?,?,?,?,?,?,?)";
        Object[] params = {rule.getCity(), rule.getStart(), rule.getBase(), rule.getPer1(), rule.getPer2(), rule.getPer3(), rule.getExtra(), rule.getPer4(), rule.getPer5()};
        return DbUtil.update(conn, sql, params);
    }

    /**
     *删除指定规则
     * @param conn
     * @param id
     * @return
     */
    public static DaoUpdateResult delete(Connection conn, long id) {
        QueryConditions conditions = new QueryConditions();
        conditions.add("id", "=", id);
        return DbUtil.delete(conn, "rule_social", conditions);
    }

    /**
     * 根据地市获取最新规则，按照生效时间排序
     * @param conn
     * @param city 地市代码
     * @return
     */
    public static DaoQueryResult getLast(Connection conn ,String city){
        QueryConditions conditions = new QueryConditions();
        conditions.add("city", "=", city);
        String order = " ORDER BY start DESC limit 1";
        return DbUtil.getLast(conn, "rule_social", conditions,RuleSocial.class,order);
    }
}
