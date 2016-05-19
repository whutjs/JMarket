package jmarket.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import jmarket.pojo.TMessage;
import jmarket.util.LocalMessageCache;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TMessage entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see jmarket.dao.TMessage
 * @author MyEclipse Persistence Tools
 */

public class TMessageDAO extends BaseHibernateDAO {
	public static final String _TITEM = "TItem";
	private static final Logger log = LoggerFactory
			.getLogger(TMessageDAO.class);
	// property constants
	public static final String _MCONTENT = "MContent";
	
	public void save(TMessage transientInstance) {
		log.debug("saving TMessage instance");
		Session session = getSession();
		 Transaction tran = session.beginTransaction();
		try {
			session.save(transientInstance);
			tran.commit();
			LocalMessageCache.getLocalMsgCache().addMsg(transientInstance);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			tran.rollback();
			throw re;
		}
	}

	public void delete(TMessage persistentInstance) {
		log.debug("deleting TMessage instance");
		Transaction tran=getSession().beginTransaction();
		try {
			getSession().delete(persistentInstance);
			tran.commit();
			LocalMessageCache.getLocalMsgCache().removeMsg(persistentInstance);
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TMessage findById(java.lang.String id) {
		log.debug("getting TMessage instance with id: " + id);
		try {
			TMessage instance = (TMessage) getSession().get(
					"jmarket.pojo.TMessage", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TMessage instance) {
		log.debug("finding TMessage instance by example");
		try {
			List results = getSession().createCriteria("jmarket.pojo.TMessage")
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
		log.debug("finding TMessage instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TMessage as model where model."
					+ propertyName + "= ? order by model.MDate";
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
	public List findByTItem(Object item) {
		return findByProperty(_TITEM, item);
	}

	public List findByMContent(Object MContent) {
		return findByProperty(_MCONTENT, MContent);
	}

	public List findAll() {
		log.debug("finding all TMessage instances");
		try {
			String queryString = "from TMessage";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TMessage merge(TMessage detachedInstance) {
		log.debug("merging TMessage instance");
		Transaction tran=getSession().beginTransaction();
		try {
			TMessage result = (TMessage) getSession().merge(detachedInstance);
			log.debug("merge successful");
	        tran.commit();
	        LocalMessageCache.getLocalMsgCache().addMsg(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TMessage instance) {
		log.debug("attaching dirty TMessage instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public List getItemtIDByComment(){
		Session session=getSession();
		String sqlstr="select "+_TITEM+" from TMessage group by "+_TITEM+" order by count("+_TITEM+") desc";
		Query queryObject = getSession().createQuery(sqlstr);
		return queryObject.list();		
	}
	public List getItemtIDByCommentInRange(List<String> itemids){
		Session session=getSession();
		String sqlstr="select "+_TITEM+" from TMessage where "+_TITEM+"in: itemids group by "+_TITEM+"order by count("+_TITEM+") desc";
		Query queryObject = getSession().createQuery(sqlstr);
		return queryObject.list();		
	}
	public void attachClean(TMessage instance) {
		log.debug("attaching clean TMessage instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}