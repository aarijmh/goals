package com.org.sg.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.sg.POJO.action.Types;

/**
 * A data access object (DAO) providing persistence and search support for Types
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.org.sg.POJO.action.Types
 * @author MyEclipse Persistence Tools
 */
public class TypesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(TypesDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String TYPE = "type";

	public void save(Types transientInstance) {
		log.debug("saving Types instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Types persistentInstance) {
		log.debug("deleting Types instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Types findById(java.lang.Integer id) {
		log.debug("getting Types instance with id: " + id);
		try {
			Types instance = (Types) getSession().get("com.org.sg.POJO.action.Types",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Types> findByExample(Types instance) {
		log.debug("finding Types instance by example");
		try {
			List<Types> results = (List<Types>) getSession()
					.createCriteria("com.org.sg.POJO.Types")
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
		log.debug("finding Types instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Types as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Types> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Types> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Types> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Types> findAll() {
		log.debug("finding all Types instances");
		try {
			String queryString = "from Types";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Types merge(Types detachedInstance) {
		log.debug("merging Types instance");
		try {
			Types result = (Types) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Types instance) {
		log.debug("attaching dirty Types instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Types instance) {
		log.debug("attaching clean Types instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}