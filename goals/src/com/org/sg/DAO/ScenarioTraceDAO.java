package com.org.sg.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.sg.POJO.action.ScenarioTrace;

/**
 * A data access object (DAO) providing persistence and search support for
 * ScenarioTrace entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.org.sg.POJO.action.ScenarioTrace
 * @author MyEclipse Persistence Tools
 */
public class ScenarioTraceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ScenarioTraceDAO.class);
	// property constants
	public static final String OBJECTIVES = "objectives";
	public static final String SCENARIO = "scenario";
	public static final String TEXTUAL_SCENARIO = "textualScenario";

	public void save(ScenarioTrace transientInstance) {
		log.debug("saving ScenarioTrace instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ScenarioTrace persistentInstance) {
		log.debug("deleting ScenarioTrace instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ScenarioTrace findById(java.lang.Integer id) {
		log.debug("getting ScenarioTrace instance with id: " + id);
		try {
			ScenarioTrace instance = (ScenarioTrace) getSession().get(
					"com.org.sg.POJO.action.ScenarioTrace", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ScenarioTrace> findByExample(ScenarioTrace instance) {
		log.debug("finding ScenarioTrace instance by example");
		try {
			List<ScenarioTrace> results = (List<ScenarioTrace>) getSession()
					.createCriteria("com.org.sg.POJO.ScenarioTrace")
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
		log.debug("finding ScenarioTrace instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ScenarioTrace as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ScenarioTrace> findByObjectives(Object objectives) {
		return findByProperty(OBJECTIVES, objectives);
	}

	public List<ScenarioTrace> findByScenario(Object scenario) {
		return findByProperty(SCENARIO, scenario);
	}

	public List<ScenarioTrace> findByTextualScenario(Object textualScenario) {
		return findByProperty(TEXTUAL_SCENARIO, textualScenario);
	}

	public List findAll() {
		log.debug("finding all ScenarioTrace instances");
		try {
			String queryString = "from ScenarioTrace";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ScenarioTrace merge(ScenarioTrace detachedInstance) {
		log.debug("merging ScenarioTrace instance");
		try {
			ScenarioTrace result = (ScenarioTrace) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ScenarioTrace instance) {
		log.debug("attaching dirty ScenarioTrace instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ScenarioTrace instance) {
		log.debug("attaching clean ScenarioTrace instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}