package com.springframework.orm;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository
@EnableTransactionManagement
public class ManchineManagementDao extends HibernateDaoSupport {

	 @Resource(name="sessionFactoryMachineManagement")
	 public void init(SessionFactory sessionFactoryMachineManagement) {
	     setSessionFactory(sessionFactoryMachineManagement);
	 }

	/**
	 * 直接执行hql
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public List find(String hql) {
		List list = null;
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = session.createQuery(hql);
			
			list = query.list();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * 直接执行hql分页
	 * 
	 * @param hql
	 * @param pageNumber
	 * @param pageAmount
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public List find(String hql, int pageNumber, int pageAmount) {
		List list = null;
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = session.createQuery(hql);
			query.setFirstResult((pageAmount) * (pageNumber - 1));
			query.setMaxResults(pageAmount);
			list = query.list();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * 执行有参数数组的hql语句
	 * 
	 * @param hql
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public List find(String hql, Object[] parameters) {
		List list = null;
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = session.createQuery(hql);
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
				
			}
			list = query.list();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * 分页，参数组查询
	 * 
	 * @param hql
	 * @param parameters
	 * @param pageNumber
	 * @param pageAmount
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public List find(String hql, Object[] parameters, int pageNumber, int pageAmount) {
		List list = null;

		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = session.createQuery(hql);
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
			query.setFirstResult((pageAmount) * (pageNumber - 1));
			query.setMaxResults(pageAmount);
			list = query.list();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * 保存或者更新
	 * 
	 * @param object
	 */
	@Transactional(value="transactionManagerMachineManagement")
	public void saveOrUpdate(Object object) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			session.saveOrUpdate(object);
			session.flush();
			session.clear();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 删除
	 * 
	 * @param object
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public void delete(Object object) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			if(object instanceof List)
			{
				List list=(List) object;
				for(Object obj:list)
				{
					session.delete(obj);
					session.flush();
				}
			}
			else
			{
				session.delete(object);
				session.flush();
			}
		
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

	/**
	 * 非select sql的执行，返回影响的数据的行数
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public int update(String hql) {
		int result = 0;
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = session.createQuery(hql);
			result = query.executeUpdate();
			session.flush();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 非select sql的执行,传递参数数组，返回影响的数据的行数
	 * 
	 * @param hql
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public int update(String hql, Object[] parameters) {
		int result = 0;
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = session.createQuery(hql);
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
			result = query.executeUpdate();
			session.flush();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	// ***********************************************************************以下为原生sql操作*********************************************************************************

	/**
	 * 使用原生sql查询
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public List findBySQL(String sql) {
		List list = null;
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			NativeQuery nativeQuery = session.createNativeQuery(sql);
			list = nativeQuery.list();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * 使用原生sql查询,分页
	 * 
	 * @param sql
	 * @param pageNumber
	 * @param pageAmount
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public List findBySQL(String sql, int pageNumber, int pageAmount) {
		List list = null;
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			NativeQuery nativeQuery = session.createNativeQuery(sql);
			nativeQuery.setFirstResult((pageAmount) * (pageNumber - 1));
			nativeQuery.setMaxResults(pageAmount);
			list = nativeQuery.list();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * 使用原生的sql进行查询，带参数
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public List findBySQL(String sql, Object[] parameters) {
		List list = null;
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			NativeQuery nativeQuery = session.createNativeQuery(sql);
			for (int i = 0; i < parameters.length; i++) {
				nativeQuery.setParameter(i, parameters[i]);
			}
			list = nativeQuery.list();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * 使用原生的sql进行查询，带参数,分页
	 * 
	 * @param sql
	 * @param parameters
	 * @param pageNumber
	 * @param pageAmount
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public List findBySQL(String sql, Object[] parameters, int pageNumber, int pageAmount) {
		List list = null;
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			NativeQuery nativeQuery = session.createNativeQuery(sql);
			for (int i = 0; i < parameters.length; i++) {
				nativeQuery.setParameter(i, parameters[i]);
			}
			nativeQuery.setFirstResult((pageAmount) * (pageNumber - 1));
			nativeQuery.setMaxResults(pageAmount);
			list = nativeQuery.list();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	/**
	 * 使用原生sql执行非select操作，返回影响的数据行数
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public int updateBySQL(String sql) {
		int result = 0;
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			NativeQuery nativeQuery = session.createNativeQuery(sql);
			result = nativeQuery.executeUpdate();
			session.flush();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 使用原生sql执行非select操作，带参数，返回影响的数据行数
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(value="transactionManagerMachineManagement")
	public int updateBySQL(String sql, Object[] parameters) {
		int result = 0;
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			NativeQuery nativeQuery = session.createNativeQuery(sql);
			for (int i = 0; i < parameters.length; i++) {
				nativeQuery.setParameter(i, parameters[i]);
			}
			result = nativeQuery.executeUpdate();
			session.flush();
			//session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

}