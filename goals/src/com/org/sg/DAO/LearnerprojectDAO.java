package com.org.sg.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.sg.POJO.action.Learner;
import com.org.sg.POJO.action.Learnerproject;

/**
 * A data access object (DAO) providing persistence and search support for
 * Learnerproject entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.org.sg.POJO.action.Learnerproject
 * @author MyEclipse Persistence Tools
 */
public class LearnerprojectDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(LearnerprojectDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";

	public void save(Learnerproject transientInstance) {
		log.debug("saving Learnerproject instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Learnerproject persistentInstance) {
		log.debug("deleting Learnerproject instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Learnerproject findById(java.lang.Integer id) {
		log.debug("getting Learnerproject instance with id: " + id);
		try {
			Learnerproject instance = (Learnerproject) getSession().get(
					"com.org.sg.POJO.action.Learnerproject", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Learnerproject> findByExample(Learnerproject instance) {
		log.debug("finding Learnerproject instance by example");
		try {
			List<Learnerproject> results = (List<Learnerproject>) getSession()
					.createCriteria("com.org.sg.POJO.Learnerproject")
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
		log.debug("finding Learnerproject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Learnerproject as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Learnerproject> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all Learnerproject instances");
		try {
			String queryString = "from Learnerproject";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Learnerproject merge(Learnerproject detachedInstance) {
		log.debug("merging Learnerproject instance");
		try {
			Learnerproject result = (Learnerproject) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Learnerproject instance) {
		log.debug("attaching dirty Learnerproject instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Learnerproject instance) {
		log.debug("attaching clean Learnerproject instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void deleteOfLearner(Integer learnerId) {

		try {
			String queryString = "delete from Learnerproject as model where model.learner.id = :id";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("id", learnerId);
		    queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	public void deleteLearnerFromProject(Integer learnerId, Integer projectId) {

		try {
			String queryString = "delete from Learnerproject as model where model.learner.id = :id and model.project.id=:pid";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("id", learnerId);
			queryObject.setParameter("pid", projectId);
		    queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Learnerproject> getOfLearner(Integer learnerId) {

		try {
			String queryString = "from Learnerproject as model where model.learner.id = :id";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("id", learnerId);
		    return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Integer> getOfLearnerIds(Integer learnerId) {

		try {
			String queryString = "select model.project.id from Learnerproject as model where model.learner.id = :id";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("id", learnerId);
		    return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<Learner> findLearnersOfProject(Integer userId) {

		try {
			String queryString = "select learner from Learnerproject as model where model.project.id = :id";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public Learnerproject findOfLearnerProject(Integer learnerId, Integer projectId)
	{
		try {
			String queryString = "from Learnerproject as model where model.project.id = :pid and model.learner.id=:lid";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("pid", projectId);
			queryObject.setParameter("lid", learnerId);
			List<Learnerproject> list = queryObject.list();
			
			return list.size() == 0 ? null : list.get(0);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

}