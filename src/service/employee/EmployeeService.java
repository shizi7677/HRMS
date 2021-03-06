package service.employee;


import bean.client.Cooperation;
import bean.employee.Employee;
import bean.employee.EmployeeExtra;
import bean.employee.ViewEmployee;
import bean.settlement.Detail3;
import dao.client.CooperationDao;
import dao.employee.EmployeeDao;
import dao.employee.ExtraDao;
import database.*;

import javax.lang.model.element.VariableElement;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class EmployeeService {
    //获取列表
    public static DaoQueryListResult getList(Connection conn, QueryParameter param){
        return EmployeeDao.getList(conn,param);
    }

    //获取详情
    public static DaoQueryResult get(Connection conn, long id) {
        QueryConditions conditions = new QueryConditions();
        conditions.add("id","=",id);
        return EmployeeDao.get(conn,conditions);
    }

    //修改
    public static DaoUpdateResult update(Connection conn, ViewEmployee employee) {
        return EmployeeDao.update(conn,employee);
    }

    //增加
    public static DaoUpdateResult insert(Connection conn, Employee employee) {
        return EmployeeDao.insert(conn,employee);
    }

    //删除
    public static DaoUpdateResult delete(Connection conn, long id, byte category) {
        DaoUpdateResult result = null;
        switch (category){
            case 0://移入人才库
                result = EmployeeDao.remove(conn,id);
                break;
            case 1://删除
                result = EmployeeDao.delete(conn,id);
                break;
        }
        return result;
    }

    //批量插入
    public static DaoUpdateResult insertBatch(Connection conn, List<ViewEmployee> viewEmployees, long did) {
        /**
         * 流程
         * 1、先批量插入员工信息
         * 2、返回插入后的员工id集合  eids[]
         * 3、员工补充信息添加对应eid
         * 4、批量插入员工补充信息
         */
        DaoUpdateResult result = new DaoUpdateResult();
        List<Employee> employees =new ArrayList<>();
        List<EmployeeExtra> extras =new ArrayList<>();
        for(ViewEmployee v : viewEmployees) {
            long cid = 0;
            if (v.getCname() != null) {//根据客户名称和派遣方id查找合作单位id
                QueryConditions conditions = new QueryConditions();
                conditions.add("name", "=", v.getCname());
                conditions.add("did", "=", did);
                if (!CooperationDao.exist(conn, conditions).exist) {
                    result.msg = "合作单位" + v.getCname() + "不存在，请核对";
                    return result;
                }
                Cooperation cooperation = (Cooperation) CooperationDao.get(conn, conditions).data;
                cid = cooperation.getId();
                //封装员工信息
                Employee employee = new Employee(0, did, cid, v.getCardId(), v.getName(), v.getPhone(), v.getDegree(), v.getType(), v.getEntry()
                        , v.getStatus(), v.getDepartment(), v.getPost(), v.getCategory(), v.getPrice());
                employees.add(employee);

                //封装员工补充信息
                EmployeeExtra extra = new EmployeeExtra(0, v.getRid(), v.getSchool(), v.getMajor(), v.getHousehold(), v.getAddress(), v.getDate1()
                        , v.getDate2(), v.getReason());
                extras.add(extra);
            }
        }

        result = EmployeeDao.insertBatch(conn,employees);//批量插入员工数据
        int[] eids = (int[])result.extra;
        for(int i = 0;i<eids.length;i++){//员工补充信息添加对应eid
            extras.get(i).setEid(eids[i]);
        }

        ExtraDao.insertBatch(conn,extras);//批量插入员工补充数据
        return  result;
    }

    //批量派遣
    public static DaoUpdateResult dispatch(Connection conn, String[] eids,long cid) {
        return  EmployeeDao.dispatch(conn,eids,cid);
    }

    //雇用
    public static DaoUpdateResult employ(Connection conn, long id,byte category) {
        return  EmployeeDao.employ(conn,id,category);
    }
}
