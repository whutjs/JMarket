package jmarket.dao;

import java.util.List;

import jmarket.pojo.TCollection;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TCollection entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see jmarket.dao.TCollection
 * @author MyEclipse Persistence Tools
 */

public class TCollectionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TCollectionDAO.class);

	// property constants

	public void save(TCollection transientInstance) {
		log.debug("saving TCollection instance");
		Transaction tran=getSession().beginTransaction();
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
        tran.commit();
        getSession().flush();
	}

	public void delete(TCollection persistentInstance) {
		log.debug("deleting TCollection instance");
		Transaction tran=getSession().beginTransaction();
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
        tran.commit();
        getSession().flush();
	}

	public TCollection findById(java.lang.String id) {
		log.debug("getting TCollection instance with id: " + id);
		try {
			TCollection instance = (TCollection) getSession().get(
					"jmarket.pojo.TCollection", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public void closeSession() {
		Session sess = getSession();
		if(sess != null && sess.isOpen()) {
			sess.close();
		}
	}
	
	public List findByExample(TCollection instance) {
		log.debug("finding TCollection instance by example");
		try {
			List results = getSession()
					.createCriteria("jmarket.pojo.TCollection")
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
		log.debug("finding TCollection instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TCollection as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TCollection instances");
		try {
			String queryString = "from TCollection";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TCollection merge(TCollection detachedInstance) {
		log.debug("merging TCollection instance");
		Transaction tran=getSession().beginTransaction();
		try {
			TCollection result = (TCollection) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
	        tran.commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TCollection instance) {
		log.debug("attaching dirty TCollection instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TCollection instance) {
		log.debug("attaching clean TCollection instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}