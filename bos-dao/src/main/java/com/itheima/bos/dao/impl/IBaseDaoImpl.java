package com.itheima.bos.dao.impl;

import com.itheima.bos.dao.IBaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class IBaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

    private Class<T> entityClass;

    //在父类的构造方法中动态获得entityClass
    public IBaseDaoImpl() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        entityClass = (Class<T>) actualTypeArguments[0];
    }

    @Resource//根据类型注入spring工厂对象 sessionFactory
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass,id);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) this.getHibernateTemplate().find("FROM " + entityClass.getSimpleName());
    }

    @Override
    public void executeUpdate(String queryName, Object... Objects) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(queryName);
        int i = 0;
        for (Object obj:Objects) {
            query.setParameter(i++,obj);
        }
        //执行更新
        query.executeUpdate();
    }
}
