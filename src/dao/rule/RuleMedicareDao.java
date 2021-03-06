package dao.rule;

import bean.rule.RuleMedicare;
import bean.rule.RuleSocial;
import database.*;

import java.sql.Connection;

public class RuleMedicareDao {

    /**
     * 获取医保规则集合
     * @param conn 连接数据库
     * @param param 查询参数
     * @return 检索结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoQueryListResult getList(Connection conn, QueryParameter param){
        if(param.conditions.extra!=null && !param.conditions.extra.isEmpty()) {
            //根据地市模糊查询
            param.addCondition("city","like",param.conditions.extra);
        }
        return DbUtil.getList(conn, "rule_medicare", param, RuleMedicare.class);
    }

    /**
     * 根据指定id获取规则
     * @param conn
     * @param id 指定id
     * @return
     */
    public static DaoQueryResult get(Connection conn, long id) {
        QueryConditions conditions = new QueryConditions();
        conditions.add("id", "=", id);
        return DbUtil.get(conn, "rule_medicare", conditions,RuleMedicare.class);
    }

    /**
     * 修改医保规则
     * @param conn 连接数据库
     * @param rule 医保规则对象
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult update(Connection conn, RuleMedicare rule) {
        String sql = "update rule_medicare set city=?, start=?, base=?, per1=?, per2=?, per3=?, fin1=?, fin2=? where id=?";
        Object[] params = {rule.getCity(), rule.getStart(), rule.getBase(), rule.getPer1(), rule.getPer2(), rule.getPer3(), rule.getFin1(), rule.getFin2(), rule.getId()};
        return DbUtil.update(conn, sql, params);
    }

    /**
     * 添加医保规则
     * @param conn 连接数据库
     * @param rule 医保规则对象
     * @return 更新结果，格式："{success:true,msg:"",effects:1}"
     */
    public static DaoUpdateResult insert(Connection conn, RuleMedicare rule) {
        String sql = "insert into rule_medicare (city , start, base, per1, per2, per3, fin1, fin2) values (?,?,?,?,?,?,?,?)";
        Object[] params = {rule.getCity(), rule.getStart(), rule.getBase(), rule.getPer1(), rule.getPer2(), rule.getPer3(), rule.getFin1(), rule.getFin2()};
        return DbUtil.insert(conn, sql, params);
    }


    /**
     * 删除指定规则
     * @param conn
     * @param id 指定规则id
     * @return
     */
    public static DaoUpdateResult delete(Connection conn, long id) {
        QueryConditions conditions = new QueryConditions();
        conditions.add("id", "=", id);
        return DbUtil.delete(conn, "rule_medicare", conditions);
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
        return DbUtil.getLast(conn, "rule_medicare", conditions,RuleMedicare.class,order);
    }
}
