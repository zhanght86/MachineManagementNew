//package MachineManagement.DataBaseHelper;
//
//import java.util.List;
//
//import org.hibernate.Query;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.procedure.ProcedureCall;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class Dao {
//	
//	@Autowired(required=true) 
//    private SessionFactory sessionFactory;
//
////    public void setSessionFactory(SessionFactory sessionFactory) {
////        this.sessionFactory = sessionFactory;
////    }
////    public SessionFactory getSessionFactory() {
////        return sessionFactory;
////    }
//    
//
//    /**
//     * 直接执行hql
//     * @param hql
//     * @return
//     */
//    @SuppressWarnings("rawtypes")
//	public List find(String hql){
//        Session session = sessionFactory.openSession();
//        Query query = session.createQuery(hql);
//        return query.list();
//    }
//    
//    
//    
//    /**
//     * 直接执行hql分页
//     * @param hql
//     * @param pageNumber
//     * @param pageAmount
//     * @return
//     */
//    @SuppressWarnings("rawtypes")
//	public List find(String hql,int pageNumber,int pageAmount){
//        Session session = sessionFactory.openSession();
//        Query query = session.createQuery(hql);
//        query.setFirstResult((pageAmount)*(pageNumber-1));
//        query.setMaxResults(pageAmount);
//        return query.list();
//    }
//    
//    
//    
//    /**
//     * 执行有参数数组的hql语句
//     * @param hql
//     * @param parameters
//     * @return
//     */
//    @SuppressWarnings("rawtypes")
//	public List find(String hql,Object[] parameters){
//        Session session = sessionFactory.openSession();
//        Query query = session.createQuery(hql);
//        for(int i=0;i<parameters.length;i++)
//        {
//            query.setParameter(i, parameters[i]);
//        }
//        return query.list();
//    }
//    
//    
//    
//    /**
//     * 分页，参数组查询
//     * @param hql
//     * @param parameters
//     * @param pageNumber
//     * @param pageAmount
//     * @return
//     */
//    @SuppressWarnings("rawtypes")
//    public List find(String hql,Object[] parameters,int pageNumber,int pageAmount){
//        Session session = sessionFactory.openSession();
//        Query query = session.createQuery(hql);
//        for(int i=0;i<parameters.length;i++)
//        {
//            query.setParameter(i, parameters[i]);
//        }
//        query.setFirstResult((pageAmount)*(pageNumber-1));
//        query.setMaxResults(pageAmount);
//        return query.list();
//    }
//
//    
//    /**
//     * 保存或者更新
//     * @param object
//     */
//    public void saveOrUpdate(Object object){
//        Session session = sessionFactory.openSession();
//        session.saveOrUpdate(object);
//    }
//    
//    
//    /**
//     * 删除
//     * @param object
//     */
//    public void delete(Object object){
//        Session session = sessionFactory.openSession();
//        session.delete(object);
//    }
//    
//    
//    
//    
//    
//    /**
//     * 非select sql的执行，返回影响的数据的行数
//     * @param hql
//     * @return
//     */
//    public int update(String hql){
//        Session session = sessionFactory.openSession();
//        Query query = session.createQuery(hql);
//        return query.executeUpdate();
//    }
//    
//    
//    /**
//     * 非select sql的执行,传递参数数组，返回影响的数据的行数
//     * @param hql
//     * @param parameters
//     * @return
//     */
//    public int update(String hql,Object[] parameters){
//        Session session = sessionFactory.openSession();
//        Query query = session.createQuery(hql);
//        for(int i=0;i<parameters.length;i++)
//        {
//            query.setParameter(i, parameters[i]);
//        }
//        return query.executeUpdate();
//    }
//    
//    
//    //***********************************************************************以下为原生sql操作*********************************************************************************
//    
//    /**
//     * 使用原生sql查询
//     * @param sql
//     * @return
//     */
//    @SuppressWarnings("rawtypes")
//	public List findBySQL(String sql){
//        Session session = sessionFactory.openSession();
//        SQLQuery  sqlquery  = session.createSQLQuery(sql);
//        return sqlquery.list();
//    }
//    
//    
//    /**
//     * 使用原生sql查询,分页
//     * @param sql
//     * @param pageNumber
//     * @param pageAmount
//     * @return
//     */
//    @SuppressWarnings("rawtypes")
//	public List findBySQL(String sql,int pageNumber,int pageAmount){
//        Session session = sessionFactory.openSession();
//        SQLQuery  sqlquery  = session.createSQLQuery(sql);
//        sqlquery.setFirstResult((pageAmount)*(pageNumber-1));
//        sqlquery.setMaxResults(pageAmount);
//        return sqlquery.list();
//    }
//    
//    
//    /**
//     * 使用原生的sql进行查询，带参数
//     * @param sql
//     * @param parameters
//     * @return
//     */
//    @SuppressWarnings("rawtypes")
//	public List findBySQL(String sql,Object[] parameters){
//        Session session = sessionFactory.openSession();
//        SQLQuery  sqlquery  = session.createSQLQuery(sql);
//        for(int i=0;i<parameters.length;i++)
//        {
//        	sqlquery.setParameter(i, parameters[i]);
//        }
//        return sqlquery.list();
//    }
//    
//    /**
//     * 使用原生的sql进行查询，带参数,分页
//     * @param sql
//     * @param parameters
//     * @param pageNumber
//     * @param pageAmount
//     * @return
//     */
//    @SuppressWarnings("rawtypes")
//	public List findBySQL(String sql,Object[] parameters,int pageNumber,int pageAmount){
//        Session session = sessionFactory.openSession();
//        SQLQuery  sqlquery  = session.createSQLQuery(sql);
//        for(int i=0;i<parameters.length;i++)
//        {
//        	sqlquery.setParameter(i, parameters[i]);
//        }
//        sqlquery.setFirstResult((pageAmount)*(pageNumber-1));
//        sqlquery.setMaxResults(pageAmount);
//        return sqlquery.list();
//    }
//    
//    /**
//     * 使用原生sql执行非select操作，返回影响的数据行数
//     * @param sql
//     * @param parameters
//     * @return
//     */
//    public int updateBySQL(String sql){
//        Session session = sessionFactory.openSession();
//        SQLQuery  sqlquery = session.createSQLQuery(sql);
//        return sqlquery.executeUpdate();
//    }
//    
//    
//    /**
//     * 使用原生sql执行非select操作，带参数，返回影响的数据行数
//     * @param sql
//     * @param parameters
//     * @return
//     */
//    public int updateBySQL(String sql,Object[] parameters){
//        Session session = sessionFactory.openSession();
//        SQLQuery  sqlquery = session.createSQLQuery(sql);
//        for(int i=0;i<parameters.length;i++)
//        {
//        	sqlquery.setParameter(i, parameters[i]);
//        }
//        return sqlquery.executeUpdate();
//    }
//    
//    
//}