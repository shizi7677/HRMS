package service.employee;

import bean.employee.Deduct;
import bean.employee.Employee;
import bean.employee.EnsureSetting;
import dao.employee.DeductDao;
import dao.employee.EmployeeDao;
import dao.employee.SettingDao;
import database.*;

import java.sql.Connection;
import java.util.List;

//个人专项扣除service层
public class DeductService {
    public static DaoQueryResult get(Connection conn, long id) {
        return DeductDao.get(conn,id);
    }

    //修改
    public static DaoUpdateResult update(Connection conn, Deduct deduct) {
        return DeductDao.update(conn,deduct);
    }

    //增加
    public static DaoUpdateResult insert(Connection conn, Deduct deduct) {
        DaoUpdateResult result = new DaoUpdateResult();
        //计算专项扣除总额
        float deducts = deduct.getDeduct1()+deduct.getDeduct2()+deduct.getDeduct3()+deduct.getDeduct4()+deduct.getDeduct5()+deduct.getDeduct6();
        deduct.setDeduct(deducts);
        if(!DeductDao.exist(conn,deduct.getEid()).exist){
           result = DeductDao.insert(conn,deduct);
        }else {//已存在则修改
            result = DeductDao.update(conn,deduct);
        }
        return result;
    }

    //列表查询
    public static DaoQueryListResult getList(Connection conn, QueryParameter param){
        return  DeductDao.getList(conn,param);
    }

    //删除
    public static DaoUpdateResult delete(Connection conn,long id){
        return DeductDao.delete(conn,id);
    }

    public static DaoUpdateResult importDeducts(Connection conn, List<Deduct> deducts) {
        DaoUpdateResult result = new DaoUpdateResult();
        for (Deduct deduct:deducts){
            if(!DeductDao.exist(conn,deduct.getEid()).exist){
                result = DeductDao.importDeducts(conn,deducts);
            }else {
                QueryConditions conditions = new QueryConditions();
                conditions.add("id","=",deduct.getEid());
                Employee employee = (Employee) EmployeeDao.get(conn,conditions).data;
                result.msg = "员工"+employee.getName()+"个税已存在，请勿重复添加";
            }
        }
        return result;
    }
}
