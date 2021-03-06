package service.settlement;

import bean.client.MapSalary;
import bean.employee.Deduct;
import bean.employee.Employee;
import bean.employee.EnsureSetting;
import bean.rule.RuleMedicare;
import bean.rule.RuleSocial;
import bean.settlement.Detail1;
import bean.settlement.Detail2;
import bean.settlement.ViewDetail1;
import bean.settlement.ViewDetail2;
import dao.client.MapSalaryDao;
import dao.employee.DeductDao;
import dao.employee.EmployeeDao;
import dao.employee.SettingDao;
import dao.rule.RuleMedicareDao;
import dao.rule.RuleSocialDao;
import dao.settlement.Detail1Dao;
import dao.settlement.Detail2Dao;
import database.DaoQueryListResult;
import database.DaoUpdateResult;
import database.QueryConditions;
import database.QueryParameter;
import utills.Calculate;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Detail1Service {
    public static DaoQueryListResult getList(Connection conn, QueryParameter param, long id){
        param.conditions.add("sid","=",id);
        return Detail1Dao.getList(conn,param);
    }
    public static DaoUpdateResult update(Connection conn, List<Detail1> details ){
        return  Detail1Dao.update(conn,details);
    }
    public static DaoUpdateResult importDetails(Connection conn, long sid, List<ViewDetail1> viewDetail1s, long did){
        DaoUpdateResult result = new DaoUpdateResult();
        List<Detail1> detail1s =new ArrayList<>();

        for(ViewDetail1 v :viewDetail1s){
            QueryConditions conditions = new QueryConditions();
            conditions.add("cardId","=",v.getCardId());
            conditions.add("did","=",did);
            if(!EmployeeDao.exist(conn,conditions).exist){
                result.msg = "用户"+v.getName()+"不存在，或者身份证id不正确，请核对";
                return  result;
            }
            Employee employee = (Employee) EmployeeDao.get(conn,conditions).data; //根据员工身份证获取员工id
            Detail1 detail1 = new Detail1(0,sid,employee.getId(),v.getBase(),v.getPension1(),v.getMedicare1(),v.getUnemployment1(),v.getDisease1(),v.getFund1(),v.getPension2(),v.getMedicare2()
            ,v.getUnemployment2(),v.getInjury(),v.getDisease2(),v.getBirth(),v.getFund2(),v.getTax(),v.getPayable(),v.getPaid(),v.getF1(),v.getF2(),v.getF3(),v.getF4(),v.getF5(),v.getF6(),v.getF7()
            ,v.getF8(),v.getF9(),v.getF10(),v.getF11(),v.getF12(),v.getF13(),v.getF14(),v.getF15(),v.getF16(),v.getF17(),v.getF18(),v.getF19(),v.getF20(),v.getStatus());
            detail1s.add(detail1);
        }
        result = Detail1Dao.importDetails(conn,detail1s);
        return  result;
    }
    public static String backup(Connection conn,Long id,String month){
       return null;
    }
    public static String makeup(Connection conn,Long id,String month){
        return null;
    }


    //计算结算单并修改
    public static DaoUpdateResult saveDetail(Connection conn,long sid, long cid) {

        QueryParameter param = new QueryParameter();
        param.conditions.add("sid","=",sid);
        DaoQueryListResult result1 = Detail1Dao.getList(conn,param);//查询数据库中属于该结算单的所有明细
        List<Detail1> detail1s = (List<Detail1>) result1.rows;//所有明细

        List<Detail1> detail1List  = new ArrayList<>();//新建一个集合用于存放计算好后的明细
        for(Detail1 d:detail1s){

            EnsureSetting setting = (EnsureSetting) SettingDao.get(conn,d.getEid()).data;//员工设置
            String city = setting.getCity();//员工所处地市
            RuleMedicare medicare= (RuleMedicare) RuleMedicareDao.getLast(conn,city).data;//获取该地市的最新医保
            RuleSocial social = (RuleSocial) RuleSocialDao.getLast(conn,city).data;//获取该地市的最新社保

            MapSalary mapSalary = (MapSalary) MapSalaryDao.getLast(cid,conn).data;//获取当月结算单
            Deduct deduct = (Deduct) DeductDao.get(conn,d.getEid()).data;//获取员工个税专项扣除

            Detail1 detail1 = Calculate.calculateDetail1(d,medicare,social,setting,mapSalary,deduct);//计算结算单明细

            detail1List.add(detail1);

        }
        return  Detail1Dao.update(conn,detail1List);
    }
}
