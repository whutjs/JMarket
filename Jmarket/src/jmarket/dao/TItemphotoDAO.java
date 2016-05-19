package jmarket.dao;

import java.util.List;

import jmarket.pojo.TItemphoto;
import jmarket.util.LocalPhotoCache;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (pojo) providing persistence and search support for
 * TItemphoto entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see jmarket.dao.TItemphoto
 * @author MyEclipse Persistence Tools
 */

public class TItemphotoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TItemphotoDAO.class);
	// property constants
	public static final String _PPATH = "PPath";
	public static final String _TItem = "TItem";
	public void save(TItemphoto transientInstance) {
		log.debug("saving TItemphoto instance");
		 Session session = getSession();
		 Transaction tran = session.beginTransaction();
		try {
			session.save(transientInstance);
			tran.commit();
			LocalPhotoCache.getLocalMsgCache().addItemPhoto(transientInstance);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
       
	}

	public void delete(TItemphoto persistentInstance) {
		log.debug("deleting TItemphoto instance");
		Session session = getSession();
		 Transaction tran = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			tran.commit();
			LocalPhotoCache.getLocalMsgCache().removeMsg(persistentInstance);
		} catch (RuntimeException re) {
			tran.rollback();
			log.error("delete failed", re);
			throw re;
		}
	}

	public TItemphoto findById(java.lang.String id) {
		log.debug("getting TItemphoto instance with id: " + id);
		try {
			TItemphoto instance = (TItemphoto) getSession().get(
					"jmarket.pojo.TItemphoto", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TItemphoto instance) {
		log.debug("finding TItemphoto instance by example");
		try {
			List results = getSession()
					.createCriteria("jmarket.pojo.TItemphoto")
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
		log.debug("finding TItemphoto instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TItemphoto as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			List result = queryObject.list();
			return result;
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
	public List findByPPath(Object PPath) {
		return findByProperty(_PPATH, PPath);
	}
	public List findByPItem(Object TItem) {
		return findByProperty(_TItem, TItem);
	}
	public List findAll() {
		log.debug("finding all TItemphoto instances");
		try {
			String queryString = "from TItemphoto";
			Query queryObject = getSession().createQuery(queryString);
			List result = queryObject.list();
			return result;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TItemphoto merge(TItemphoto detachedInstance) {
		log.debug("merging TItemphoto instance");
		Transaction tran=getSession().beginTransaction();
		try {
			TItemphoto result = (TItemphoto) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
	        tran.commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TItemphoto instance) {
		log.debug("attaching dirty TItemphoto instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TItemphoto instance) {
		log.debug("attaching clean TItemphoto instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}