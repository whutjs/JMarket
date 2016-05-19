package jmarket.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import jmarket.pojo.TItem;
import jmarket.util.HibernateSessionFactory;
import jmarket.util.LocalItemCache;
import jmarket.util.Util;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for TItem
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see jmarket.dao.TItem
 * @author MyEclipse Persistence Tools
 */

public class TItemDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TItemDAO.class);
	// property constants
	public static final String _INAME = "IName";
	public static final String _IDESCRIPTION = "IDescription";
	public static final String _IFLAG = "IFlag";
	public static final String _ITYPE = "IType";
	public static final String _IPRICE = "IPrice";
	public static final String _IPLACE = "IPlace";
	public static final String _ISTATE = "IState";
	private static final String _IDATE = "IDate";
	private static final String _IUSER = "TUser";

	public void save(TItem transientInstance) {
		log.debug("saving TItem instance");
		Transaction tran=getSession().beginTransaction();
			try {
				getSession().save(transientInstance);
				tran.commit();
				LocalItemCache.getLocalItemCache().addItem(transientInstance);
			} catch (RuntimeException re) {
				tran.rollback();
				log.error("save failed", re);
				throw re;
			}
		
       
	}

	public void delete(TItem persistentInstance) {
		log.debug("deleting TItem instance");
		Transaction tran=getSession().beginTransaction();
			try {
				getSession().delete(persistentInstance);
				tran.commit();
				LocalItemCache.getLocalItemCache().removetItem(persistentInstance.getIId());
			} catch (RuntimeException re) {
				log.error("delete failed", re);
				tran.rollback();
				throw re;
			}
	}

	public TItem findById(java.lang.String id) {
		log.debug("getting TItem instance with id: " + id);
		try {
			TItem instance = (TItem) getSession().get("jmarket.pojo.TItem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	// 鍒嗛〉鏌ヨ鎵€鏈�
	public List queryAllByPage(int page, int pageSize) {
		try {
			String queryString = "from TItem order by IDate  desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult((page - 1) * pageSize);
			queryObject.setMaxResults(pageSize);
			List result = queryObject.list();
			return result;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	// 鍒嗛〉鏌ヨ
	public List queryByPage(Object IType, int page, int pageSize) {
		try {
			String queryString = "from TItem as model where model.IType"
					 + "= ? order by IDate  desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, IType);
			queryObject.setFirstResult((page - 1) * pageSize);
			queryObject.setMaxResults(pageSize);
			List result = queryObject.list();
			return result;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
		
	public List findByExample(TItem instance) {
		log.debug("finding TItem instance by example");
		try {
			List results = getSession().createCriteria("jmarket.pojo.TItem")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List findByIUser(Object user) {
		return findByProperty(_IUSER, user);
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TItem instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TItem as model where model."
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
	//澶т簬绛変簬鏌ヨ
	public List findByPropertyBiger(String propertyName, Object value) {
		log.debug("finding TItem instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TItem as model where model."
					+ propertyName + ">= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	// 瀹㈡埛绔ā绯婃煡璇�
	public List vagueQueryForMobile(String value, int page, int pageSize) {
		try {
			String queryString = "from TItem i where i.IName like '%"
					+ value + "%' or i.IDescription like '%" + value + "%'  order by IDate desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setFirstResult((page - 1) * pageSize);
			queryObject.setMaxResults(pageSize);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
		
	//妯＄硦鏌ヨ
	public List findByPropertyVague(String propertyName, Object value) {
		log.debug("finding TItem instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TItem as model where model."
					+ propertyName + "like ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0,"%"+value+"%");
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	//闈炲父妯＄硦鐨勬煡璇�
	public List findByPropertyVeryVague(String propertyName, Object value) {
		log.debug("finding TItem instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TItem as model where model."
					+ propertyName + "REGEXP ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0,"["+value+"]+");
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
//鏌ヨ鑼冨洿
	public List findByPropertyRange(String propertyName, Object value1,Object value2) {
		try {
			String queryString = "from TItem as model where model."
					+ propertyName + "between ? and ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value1);
			queryObject.setParameter(1, value2);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByIName(Object IName) {
		return findByProperty(_INAME, IName);
	}
	public List findByINameVeryVague(Object IName) {
		return findByPropertyVeryVague(_INAME, IName);
	}
	public List findByINameVague(Object IName) {
		return findByPropertyVague(_INAME, IName);
	}
	public List findByIDescription(Object IDescription) {
		return findByProperty(_IDESCRIPTION, IDescription);
	}

	public List findByIFlag(Object IFlag) {
		return findByProperty(_IFLAG, IFlag);
	}

	public List findByIType(Object IType) {
		return findByProperty(_ITYPE, IType);
	}

	public List findByIPrice(Object IPrice) {
		return findByProperty(_IPRICE, IPrice);
	}
	public List findByIPriceBiger(Object IPrice) {
		return findByPropertyBiger(_IPRICE, IPrice);
	}
	public List findByIPriceRange(Object IPrice1,Object IPrice2) {
		return findByPropertyRange(_IPRICE, IPrice1,IPrice2);
	}
	public List findByIPlace(Object IPlace) {
		return findByProperty(_IPLACE, IPlace);
	}

	public List findByIState(Object IState) {
		return findByProperty(_ISTATE, IState);
	}
	public List findByIStateRange(Object IState1,Object IState2) {
		return findByPropertyRange(_ISTATE, IState1,IState2);
	}
	public List findByIStateBiger(Object IState) {
		return findByPropertyBiger(_ISTATE, IState);
	}
	public List findAll() {
		log.debug("finding all TItem instances");
		try {
			String queryString = "from TItem";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List getMembersByConditionsVague(String minPrice, String maxPrice, String place, String minState,String maxState,String typeID,String order,String text) throws Exception {
			Session session = HibernateSessionFactory.getSession();
			List list = null;
			String str = "select * from t_item as model where 1=1 ";
			if(minPrice!=null && maxPrice!=null) {
			str = str +" and model.I_price between "+minPrice+" and "+maxPrice; 
			}else if(minPrice!=null && maxPrice==null){
				str = str +" and model.I_price >= "+minPrice;
			}else if(minPrice==null && maxPrice!=null){
				str = str +" and model.I_price <= "+maxPrice;
			}
			if(place!=null){
				str = str +" and model.I_place like '%"+place+"%'";
			}
			if(minState!=null && maxState!=null) {
			str = str +" and model.I_state between "+minState+" and "+maxState; 
			}else if(minState!=null && maxState==null){
				str = str +" and model.I_state >= "+minState;
			}else if(minState==null && maxState!=null){
				str = str +" and model.I_state <= "+maxState;
			}
			if(typeID!=null){
				str = str +" and model.I_type = " + typeID;
			}
			if(text!=null){
				str=str+" and model.I_name REGEXP '%["+text+"]%'";
			}
			if(order!=null){
				if(order=="price"){
				str=str+" order by I_price";
				}
			}else {
				str=str+" order by I_date desc" ;
			}
//			System.out.println(str);
			Query queryObject = getSession().createSQLQuery(str);
			return queryObject.list();
			} 
	public List getMembersByConditions(String minPrice, String maxPrice, String place, String minState,String maxState,String typeID,String order,String text) throws Exception {
		Session session = HibernateSessionFactory.getSession();
		List list = null;
		String str = "select * from t_item as model where 1=1 ";
		if(!Util.checkStrEmpty(minPrice) && !Util.checkStrEmpty(maxPrice)) {
		str = str +" and model.I_price between "+minPrice+" and "+maxPrice; 
		}else if(!Util.checkStrEmpty(minPrice) && Util.checkStrEmpty(maxPrice)){
			str = str +" and model.I_price >= "+minPrice;
		}else if(Util.checkStrEmpty(minPrice) && !Util.checkStrEmpty(maxPrice)){
			str = str +" and model.I_price <= "+maxPrice;
		}
		if(!Util.checkStrEmpty(place)){
			str = str +" and model.I_place like '%"+place+"%'";
		}
		if(!Util.checkStrEmpty(minState) && !Util.checkStrEmpty(maxState)) {
			str = str +" and model.I_state between "+minState+" and "+maxState; 
		}else if(!Util.checkStrEmpty(minState) && Util.checkStrEmpty(maxState)){
			str = str +" and model.I_state >= "+minState;
		}else if(Util.checkStrEmpty(minState) && !Util.checkStrEmpty(maxState)){
			str = str +" and model.I_state <= "+maxState;
		}
		if(!Util.checkStrEmpty(typeID)){
			str = str +" and model.I_type = " + typeID;
		}
		if(!Util.checkStrEmpty(text)){
			str=str+" and model.I_name like '%"+text+"%'";
		}
		if(!Util.checkStrEmpty(order)){
			if(order.equals("price")){
			str=str+" order by I_price";
			}
		}else {
			str=str+" order by I_date desc" ;
		}
//		System.out.println(str);
		Query queryObject = getSession().createSQLQuery(str);
		return queryObject.list();
		} 
	
	public List getMembersByConditionsWithPage(String minPrice, String maxPrice, String place, String minState,String maxState,String typeID,
			String order,String text, int page, int pageSize) throws Exception {
			Session session = getSession();
			List list = null;
			String str = "select * from t_item as model where 1=1 ";
			if(!Util.checkStrEmpty(minPrice) && !Util.checkStrEmpty(maxPrice)) {
			str = str +" and model.I_price between "+minPrice+" and "+maxPrice; 
			}else if(!Util.checkStrEmpty(minPrice) && Util.checkStrEmpty(maxPrice)){
				str = str +" and model.I_price >= "+minPrice;
			}else if(Util.checkStrEmpty(minPrice) && !Util.checkStrEmpty(maxPrice)){
				str = str +" and model.I_price <= "+maxPrice;
			}
			if(!Util.checkStrEmpty(place)){
				str = str +" and model.I_place like '%"+place+"%'";
			}
			if(!Util.checkStrEmpty(minState) && !Util.checkStrEmpty(maxState)) {
				str = str +" and model.I_state between "+minState+" and "+maxState; 
			}else if(!Util.checkStrEmpty(minState) && Util.checkStrEmpty(maxState)){
				str = str +" and model.I_state >= "+minState;
			}else if(Util.checkStrEmpty(minState) && !Util.checkStrEmpty(maxState)){
				str = str +" and model.I_state <= "+maxState;
			}
			if(!Util.checkStrEmpty(typeID)){
				str = str +" and model.I_type = " + typeID;
			}
			if(!Util.checkStrEmpty(text)){
				str=str+" and model.I_name like '%"+text+"%'";
			}
			if(!Util.checkStrEmpty(order) && order.equals("price")){
					str=str+" order by I_price, I_date desc";
			}else {
				str=str+" order by I_date desc" ;
			}
//			System.out.println(str);
			Query queryObject = session.createSQLQuery(str);
			queryObject.setFirstResult((page - 1) * pageSize);
			queryObject.setMaxResults(pageSize);
			List result = queryObject.list();
			return result;
		} 
	
	public void closeSession() {
		Session sess = getSession();
		if(sess != null && sess.isOpen()) {
			sess.close();
		}
	}
	public TItem merge(TItem detachedInstance) {
		log.debug("merging TItem instance");
		Transaction tran=getSession().beginTransaction();
		try {
			TItem result = (TItem) getSession().merge(detachedInstance);
			log.debug("merge successful");			
		    tran.commit();
		    LocalItemCache.getLocalItemCache().addItem(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TItem instance) {
		log.debug("attaching dirty TItem instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TItem instance) {
		log.debug("attaching clean TItem instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}