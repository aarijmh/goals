package com.org.sg.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.sg.POJO.action.Functions;

/**
 * A data access object (DAO) providing persistence and search support for
 * Functions entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.org.sg.POJO.action.Functions
 * @author MyEclipse Persistence Tools
 */
public class FunctionsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FunctionsDAO.class);
	// property constants
	public static final String FUNC = "func";
	public static final String DESCRIPTION = "description";
	public static final String PARAMETERS = "parameters";

	public void save(Functions transientInstance) {
		log.debug("saving Functions instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Functions persistentInstance) {
		log.debug("deleting Functions instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Functions findById(java.lang.Integer id) {
		log.debug("getting Functions instance with id: " + id);
		try {
			Functions instance = (Functions) getSession().get(
					"com.org.sg.POJO.action.Functions", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Functions> findByExample(Functions instance) {
		log.debug("finding Functions instance by example");
		try {
			List<Functions> results = (List<Functions>) getSession()
					.createCriteria("com.org.sg.POJO.Functions")
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
		log.debug("finding Functions instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Functions as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Functions> findByFunc(Object func) {
		return findByProperty(FUNC, func);
	}

	public List<Functions> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Functions> findByParameters(Object parameters) {
		return findByProperty(PARAMETERS, parameters);
	}

	public List<Functions> findAll() {
		log.debug("finding all Functions instances");
		try {
			String queryString = "from Functions";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Functions merge(Functions detachedInstance) {
		log.debug("merging Functions instance");
		try {
			Functions result = (Functions) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Functions instance) {
		log.debug("attaching dirty Functions instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Functions instance) {
		log.debug("attaching clean Functions instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}