package com.org.sg.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.sg.POJO.action.LearnerConcept;

/**
 * A data access object (DAO) providing persistence and search support for
 * LearnerConcept entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.org.sg.POJO.action.LearnerConcept
 * @author MyEclipse Persistence Tools
 */
public class LearnerConceptDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(LearnerConceptDAO.class);
	// property constants
	public static final String VALUE = "value";
	public static final String DESCRIPTION = "description";

	public void save(LearnerConcept transientInstance) {
		log.debug("saving LearnerConcept instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LearnerConcept persistentInstance) {
		log.debug("deleting LearnerConcept instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LearnerConcept findById(java.lang.Integer id) {
		log.debug("getting LearnerConcept instance with id: " + id);
		try {
			LearnerConcept instance = (LearnerConcept) getSession().get(
					"com.org.sg.POJO.action.LearnerConcept", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<LearnerConcept> findByExample(LearnerConcept instance) {
		log.debug("finding LearnerConcept instance by example");
		try {
			List<LearnerConcept> results = (List<LearnerConcept>) getSession()
					.createCriteria("com.org.sg.POJO.LearnerConcept")
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
		log.debug("finding LearnerConcept instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LearnerConcept as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<LearnerConcept> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List<LearnerConcept> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all LearnerConcept instances");
		try {
			String queryString = "from LearnerConcept";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LearnerConcept merge(LearnerConcept detachedInstance) {
		log.debug("merging LearnerConcept instance");
		try {
			LearnerConcept result = (LearnerConcept) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LearnerConcept instance) {
		log.debug("attaching dirty LearnerConcept instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LearnerConcept instance) {
		log.debug("attaching clean LearnerConcept instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public LearnerConcept findConceptInProfile(Integer learnerId,
			Integer conceptId) {
		log.debug("Finding concept value of learner");
		try {

			String queryString = "from LearnerConcept where learner.id = :LID and concept.id = :CID";
			Query query = getSession().createQuery(queryString);
			query.setInteger("LID", learnerId);
			query.setInteger("CID", conceptId);

			List<LearnerConcept> list = query.list();

			log.debug("attach successful");

			return list.size() == 0 ? null : list.get(0);

		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<LearnerConcept> findLearnerConceptsOfProject(Integer projectId,
			Integer learnerId) {

		try {
			String queryString = "from LearnerConcept as model where model.concept.project.id = "
					+ projectId
					+ " and model.learner.id = "
					+ learnerId
					+ "  order by model.concept.name asc";

			Query q = getSession().createQuery(queryString);
			return q.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public void deleteOfLearner(Integer id) {
		try {
			String queryString = "delete from LearnerConcept as model where model.learner.id = :ID";

			Query q = getSession().createQuery(queryString);
			q.setParameter("ID", id);
			q.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	

	public List<LearnerConcept> findOfLearner(Integer id) {
		try {
			String queryString = "from LearnerConcept as model where model.learner.id = :ID";

			Query q = getSession().createQuery(queryString);
			q.setParameter("ID", id);
			return q.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List<LearnerConcept> findOfLearnerProject(Integer id, Integer PID) {
		try {
			String queryString = "from LearnerConcept as model where model.learner.id = :ID and model.concept.project.id = :PID";

			Query q = getSession().createQuery(queryString);
			q.setParameter("ID", id);
			q.setParameter("PID", PID);
			return q.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

}