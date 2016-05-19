package jmarket.dao;

import java.util.List;

import jmarket.pojo.TItemtype;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TItemtype entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see jmarket.dao.TItemtype
 * @author MyEclipse Persistence Tools
 */

public class TItemtypeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TItemtypeDAO.class);
	// property constants
	public static final String _TMAINCLASS = "TMainclass";
	public static final String _TSECONDCLASS = "TSecondclass";

	public void save(TItemtype transientInstance) {
		log.debug("saving TItemtype instance");
		 Transaction tran=getSession().beginTransaction();
		try {
			getSession().save(transientInstance);
			tran.commit();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			tran.rollback();
			throw re;
		}
       
       
	}

	public void delete(TItemtype persistentInstance) {
		log.debug("deleting TItemtype instance");
		Transaction tran=getSession().beginTransaction();
		try {
			getSession().delete(persistentInstance);
			tran.commit();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			tran.rollback();
			throw re;
		}
	}

	public TItemtype findById(java.lang.String id) {
		log.debug("getting TItemtype instance with id: " + id);
		try {
			TItemtype instance = (TItemtype) getSession().get(
					"jmarket.pojo.TItemtype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TItemtype instance) {
		log.debug("finding TItemtype instance by example");
		try {
			List results = getSession().createCriteria("jmarket.pojo.TItemtype")
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
		log.debug("finding TItemtype instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TItemtype as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
//			log.error("find by property name failed", re);
			System.out.println("find by property name failed"+re.getMessage());
			throw re;
		}
	}

	public List findByTMainclass(Object TMainclass) {
		return findByProperty(_TMAINCLASS, TMainclass);
	}

	public List findByTSecondclass(Object TSecondclass) {
		return findByProperty(_TSECONDCLASS, TSecondclass);
	}

	public List findAll() {
		log.debug("finding all TItemtype instances");
		try {
			String queryString = "from TItemtype";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TItemtype merge(TItemtype detachedInstance) {
		log.debug("merging TItemtype instance");
		Transaction tran=getSession().beginTransaction();
		try {
			TItemtype result = (TItemtype) getSession().merge(detachedInstance);
			log.debug("merge successful");
	        tran.commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TItemtype instance) {
		log.debug("attaching dirty TItemtype instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TItemtype instance) {
		log.debug("attaching clean TItemtype instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}