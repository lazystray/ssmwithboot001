package com.zyl.dao.impl.base;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("myBatisBaseDao")
public class MyBatisBaseDao<T> extends SqlSessionDaoSupport{

    private String nameSpace;//命名空间，可省略，namespace是为了防止不同MapperXml中statement重名的查询

    public MyBatisBaseDao() {
    }

    public MyBatisBaseDao(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    private Logger logger = LoggerFactory.getLogger(MyBatisBaseDao.class);

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    //不用在xml中配置sqlSessionTemplate，但是在此处需要配置sqlSessionTemplate，二者需要有一
    @Autowired
    public void setsqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
        super.setSqlSessionTemplate(sqlSessionTemplate);
    }

    protected SqlSessionTemplate sqlSessionTemplate;


    public static final String SQL_SELECT = "select";
    public static final String SQL_SELECT_BY = "selectBy";
    public static final String SQL_SELECT_PAGE = "selectPage";
    public static final String SQL_INSERT = "insert";
    public static final String SQL_DELETE = "delete";
    public static final String SQL_UPDATE = "update";
    public static final String SQL_BATCH_INSERT = "batchInsert";


    public T get(T entity) {
        if (entity == null) {
            return null;
        }
        Object result = this.getSqlSession().selectOne(this.createStatementByName(SQL_SELECT), entity);
        if (result == null) {
            return null;
        }
        return (T) result;
    }

    public List<T> queryForList(String statementName,Object params) {
        if (params == null) {
            return null;
        }
        return this.getSqlSession().selectList(this.createStatementByName(statementName), params);
    }

    public int insert(T entity) {
        if (entity == null) {
            throw new RuntimeException("T is null");
        }
        return this.sqlSessionTemplate.insert(this.createStatementByName(SQL_INSERT), entity);
    }

    public int insert(List list) {
        if (list == null || list.size() <= 0) {
            return 0;
        }
        return this.sqlSessionTemplate.insert(this.createStatementByName(SQL_BATCH_INSERT), list);
    }

    public int update(T entity) {
        if (entity == null) {
            throw new RuntimeException("");
        }
        return this.sqlSessionTemplate.update(this.createStatementByName(SQL_UPDATE), entity);
    }

    public int update(List list) {
        if (list == null || list.size() <= 0) {
            return 0;
        }
        return this.sqlSessionTemplate.update(this.createStatementByName(SQL_UPDATE), list);
    }

    public int delete(T entity) {
        if (entity == null) {
            throw new RuntimeException("");
        }
        return this.sqlSessionTemplate.delete(this.createStatementByName(SQL_UPDATE), entity);
    }

    protected String createStatementByName(String id) {
        return this.nameSpace + "." + id;
    }

   /* @Autowired(
            required = false
    )
    public final void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
    }*/

}
