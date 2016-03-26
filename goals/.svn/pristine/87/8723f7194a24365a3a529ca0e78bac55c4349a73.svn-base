package com.org.sg.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.sg.POJO.action.PrProperty;

/**
 * A data access object (DAO) providing persistence and search support for
 * PrProperty entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.org.sg.POJO.action.PrProperty
 * @author MyEclipse Persistence Tools
 */
public class PrPropertyDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PrPropertyDAO.class);
	// property constants
	public static final String PARAMETER = "parameter";
	public static final String NAME = "name";
	public static final String VALUE = "value";

	public void save(PrProperty transientInstance) {
		log.debug("saving PrProperty instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PrProperty persistentInstance) {
		log.debug("deleting PrProperty instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PrProperty findById(java.lang.Integer id) {
		log.debug("getting PrProperty instance with id: " + id);
		try {
			PrProperty instance = (PrProperty) getSession().get(
					"com.org.sg.POJO.action.PrProperty", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<PrProperty> findByExample(PrProperty instance) {
		log.debug("finding PrProperty instance by example");
		try {
			List<PrProperty> results = (List<PrProperty>) getSession()
					.createCriteria("com.org.sg.POJO.PrProperty")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PrProperty instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PrProperty as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<PrProperty> findByParameter(Object parameter) {
		return findByProperty(PARAMETER, parameter);
	}

	public List<PrProperty> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<PrProperty> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findAll() {
		log.debug("finding all PrProperty instances");
		try {
			String queryString = "from PrProperty";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PrProperty merge(PrProperty detachedInstance) {
		log.debug("merging PrProperty instance");
		try {
			PrProperty result = (PrProperty) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PrProperty instance) {
		log.debug("attaching dirty PrProperty instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PrProperty instance) {
		log.debug("attaching clean PrProperty instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<PrProperty> findOfPedagogicalResource(Integer id) {
		log.debug("finding all PrProperty instances");
		try {
			String queryString = "from PrProperty where pedagogicalresource.id = :ID";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("ID", id);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteOfPR(Integer id) {
		try {
			String queryString = "delete from PrProperty where  pedagogicalresource.id = :CID";
			Query query = getSession().createQuery(queryString);
			query.setInteger("CID", id);
			query.executeUpdate();
			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}