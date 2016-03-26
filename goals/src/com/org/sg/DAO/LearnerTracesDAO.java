package com.org.sg.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.sg.POJO.action.LearnerTraces;

/**
 * A data access object (DAO) providing persistence and search support for
 * LearnerTraces entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.org.sg.POJO.action.LearnerTraces
 * @author MyEclipse Persistence Tools
 */
public class LearnerTracesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(LearnerTracesDAO.class);
	// property constants
	public static final String EVALUATION = "evaluation";
	public static final String RESPONSETIME = "responsetime";
	public static final String RESPONSE = "response";
	public static final String EVENT = "event";
	public static final String LEVEL = "level";
	public static final String SESSION = "session";
	public static final String OLD_PROFILE = "oldProfile";
	public static final String NEW_PROFILE = "newProfile";

	public void save(LearnerTraces transientInstance) {
		log.debug("saving LearnerTraces instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LearnerTraces persistentInstance) {
		log.debug("deleting LearnerTraces instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LearnerTraces findById(java.lang.Integer id) {
		log.debug("getting LearnerTraces instance with id: " + id);
		try {
			LearnerTraces instance = (LearnerTraces) getSession().get(
					"com.org.sg.POJO.action.LearnerTraces", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<LearnerTraces> findByExample(LearnerTraces instance) {
		log.debug("finding LearnerTraces instance by example");
		try {
			List<LearnerTraces> results = (List<LearnerTraces>) getSession()
					.createCriteria("com.org.sg.POJO.LearnerTraces")
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
		log.debug("finding LearnerTraces instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LearnerTraces as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<LearnerTraces> findByEvaluation(Object evaluation) {
		return findByProperty(EVALUATION, evaluation);
	}

	public List<LearnerTraces> findByResponsetime(Object responsetime) {
		return findByProperty(RESPONSETIME, responsetime);
	}

	public List<LearnerTraces> findByResponse(Object response) {
		return findByProperty(RESPONSE, response);
	}

	public List<LearnerTraces> findByEvent(Object event) {
		return findByProperty(EVENT, event);
	}

	public List<LearnerTraces> findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List<LearnerTraces> findBySession(Object session) {
		return findByProperty(SESSION, session);
	}

	public List<LearnerTraces> findByOldProfile(Object oldProfile) {
		return findByProperty(OLD_PROFILE, oldProfile);
	}

	public List<LearnerTraces> findByNewProfile(Object newProfile) {
		return findByProperty(NEW_PROFILE, newProfile);
	}

	public List findAll() {
		log.debug("finding all LearnerTraces instances");
		try {
			String queryString = "from LearnerTraces";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LearnerTraces merge(LearnerTraces detachedInstance) {
		log.debug("merging LearnerTraces instance");
		try {
			LearnerTraces result = (LearnerTraces) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LearnerTraces instance) {
		log.debug("attaching dirty LearnerTraces instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LearnerTraces instance) {
		log.debug("attaching clean LearnerTraces instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	

	public List<LearnerTraces> findTracesOfLearnerForResource(Integer learnerId, Integer resourceId) {
		log.debug("finding all LearnerTraces instances");
		try {
			String queryString = "from LearnerTraces where learner.id = "+learnerId+"  and pedagogicalresource.id = "+resourceId;
			Query query = getSession().createQuery(queryString);
			return query.list();
		}
		catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}