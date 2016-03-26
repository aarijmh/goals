package com.org.sg.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.sg.POJO.action.Concept;

/**
 * A data access object (DAO) providing persistence and search support for
 * Concept entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.org.sg.POJO.action.Concept
 * @author MyEclipse Persistence Tools
 */
public class ConceptDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ConceptDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String TYPE = "type";

	public void save(Concept transientInstance) {
		log.debug("saving Concept instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Concept persistentInstance) {
		log.debug("deleting Concept instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Concept findById(java.lang.Integer id) {
		log.debug("getting Concept instance with id: " + id);
		try {
			Concept instance = (Concept) getSession().get(
					"com.org.sg.POJO.action.Concept", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Concept> findByExample(Concept instance) {
		log.debug("finding Concept instance by example");
		try {
			List<Concept> results = (List<Concept>) getSession()
					.createCriteria("com.org.sg.POJOaction..Concept")
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
		log.debug("finding Concept instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Concept as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Concept> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Concept> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<Concept> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findAll() {
		log.debug("finding all Concept instances");
		try {
			String queryString = "from Concept";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Concept merge(Concept detachedInstance) {
		log.debug("merging Concept instance");
		try {
			Concept result = (Concept) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Concept instance) {
		log.debug("attaching dirty Concept instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Concept instance) {
		log.debug("attaching clean Concept instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<Concept> findOfProject(Integer projectId) {
		log.debug("finding all Concept instances");
		try {
			String queryString = "from Concept model where model.project.id = :pid";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("pid", projectId);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}