package dao.admin;

import bean.admin.Notice;
import database.*;

import java.sql.Connection;

public class NoticeDao {

    public DaoQueryListResult getList(Connection conn, QueryParameter param){
        return DbUtil.getList(conn,"notice",param, Notice.class);
    }

    public DaoQueryResult get(Connection conn, long id) {
        QueryConditions conditions = new QueryConditions();
        conditions.add("id","=",id);
        return DbUtil.get(conn,"notice",conditions,Notice.class);
    }

    public DaoUpdateResult update(Connection conn, Notice n) {
        String sql = "update notice set title=?,brief=?,content=?,publisher=?,date=? where id=?";
        Object []params = {n.getTitle(),n.getBrief(),n.getContent(),n.getPublisher(),n.getDate(),n.getId()};
        //调用DbUtil封装的update方法
        return DbUtil.update(conn,sql,params);
    }

    public DaoUpdateResult insert(Connection conn, Notice n) {
        String sql = "insert notice (title,brief,content,publisher,date) values (?,?,?,?,?)";
        Object []params = {n.getTitle(),n.getBrief(),n.getContent(),n.getPublisher(),n.getDate()};
        return  DbUtil.insert(conn,sql,params);
    }

    public DaoUpdateResult delete(Connection conn, long id) {
        QueryConditions conditions = new QueryConditions();
        conditions.add("id","=",id);
        return DbUtil.delete(conn,"notice",conditions);
    }
}
