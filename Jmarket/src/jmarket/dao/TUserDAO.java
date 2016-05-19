package jmarket.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


import jmarket.pojo.TUser;
import jmarket.util.LocalUserCache;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for TUser
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see jmarket.dao.TUser
 * @author MyEclipse Persistence Tools
 */

public class TUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TUserDAO.class);
	// property constants
	public static final String _UNAME = "UName";
	public static final String _UPASSWORD = "UPassword";
	public static final String _USEX = "USex";
	public static final String _UEMAIL = "UEmail";
	public static final String _UTYPE = "UType";
	public static final String _UQQ = "UQq";
	public static final String _UWECHAT = "UWechat";
	public static final String _UIMAGE = "UImage";
	public static final String _UFLAG = "UFlag";

	public void save(TUser transientInstance) {
		log.debug("saving TUser instance");
		 Session session = getSession();
		 Transaction tran = session.beginTransaction();
		try {
			session.save(transientInstance);
			tran.commit();
			LocalUserCache.getLocalUserCache().addUser(transientInstance);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			tran.rollback();
			throw re;
		}
	}

	public void delete(TUser persistentInstance) {
		log.debug("deleting TUser instance");
		 Session session = getSession();
		 Transaction tran = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			tran.commit();
			LocalUserCache.getLocalUserCache().removeUser(persistentInstance.getUId());
		} catch (RuntimeException re) {
			tran.rollback();
			log.error("delete failed", re);
			throw re;
		}
		
		
	}

	public TUser findById(java.lang.String id) {
		log.debug("getting TUser instance with id: " + id);
		try {
			TUser instance = (TUser) getSession().get("jmarket.pojo.TUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TUser instance) {
		log.debug("finding TUser instance by example");
		try {
			List results = getSession().createCriteria("jmarket.pojo.TUser")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public void closeSession() {
		Session sess = getSession();
		if(sess != null && sess.isOpen()) {
			sess.close();
		}
	}
	public List findByUName(Object UName) {
		return findByProperty(_UNAME, UName);
	}

	public List findByUPassword(Object UPassword) {
		return findByProperty(_UPASSWORD, UPassword);
	}

	public List findByUSex(Object USex) {
		return findByProperty(_USEX, USex);
	}

	public List findByUEmail(Object UEmail) {
		return findByProperty(_UEMAIL, UEmail);
	}

	public List findByUType(Object UType) {
		return findByProperty(_UTYPE, UType);
	}

	public List findByUQq(Object UQq) {
		return findByProperty(_UQQ, UQq);
	}

	public List findByUWechat(Object UWechat) {
		return findByProperty(_UWECHAT, UWechat);
	}

	public List findByUImage(Object UImage) {
		return findByProperty(_UIMAGE, UImage);
	}

	public List findByUFlag(Object UFlag) {
		return findByProperty(_UFLAG, UFlag);
	}

	public List findAll() {
		log.debug("finding all TUser instances");
		try {
			String queryString = "from TUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TUser merge(TUser detachedInstance) {
		log.debug("merging TUser instance");
		Transaction tran=getSession().beginTransaction();
		try {
			TUser result = (TUser) getSession().merge(detachedInstance);
			log.debug("merge successful");
	        tran.commit();
	        LocalUserCache.getLocalUserCache().addUser(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TUser instance) {
		log.debug("attaching dirty TUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TUser instance) {
		log.debug("attaching clean TUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}