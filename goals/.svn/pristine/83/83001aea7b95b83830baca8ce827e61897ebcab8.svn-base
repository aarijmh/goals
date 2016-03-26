package com.org.sg.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.sg.POJO.action.Pedagogicalresource;

/**
 * A data access object (DAO) providing persistence and search support for
 * Pedagogicalresource entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.org.sg.POJO.action.Pedagogicalresource
 * @author MyEclipse Persistence Tools
 */
public class PedagogicalresourceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PedagogicalresourceDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";

	public void save(Pedagogicalresource transientInstance) {
		log.debug("saving Pedagogicalresource instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Pedagogicalresource persistentInstance) {
		log.debug("deleting Pedagogicalresource instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Pedagogicalresource findById(java.lang.Integer id) {
		log.debug("getting Pedagogicalresource instance with id: " + id);
		try {
			Pedagogicalresource instance = (Pedagogicalresource) getSession()
					.get("com.org.sg.POJO.action.Pedagogicalresource", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Pedagogicalresource> findByExample(Pedagogicalresource instance) {
		log.debug("finding Pedagogicalresource instance by example");
		try {
			List<Pedagogicalresource> results = (List<Pedagogicalresource>) getSession()
					.createCriteria("com.org.sg.POJO.Pedagogicalresource")
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
		log.debug("finding Pedagogicalresource instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Pedagogicalresource as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Pedagogicalresource> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Pedagogicalresource> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all Pedagogicalresource instances");
		try {
			String queryString = "from Pedagogicalresource";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Pedagogicalresource merge(Pedagogicalresource detachedInstance) {
		log.debug("merging Pedagogicalresource instance");
		try {
			Pedagogicalresource result = (Pedagogicalresource) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Pedagogicalresource instance) {
		log.debug("attaching dirty Pedagogicalresource instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Pedagogicalresource instance) {
		log.debug("attaching clean Pedagogicalresource instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<Pedagogicalresource> findOfProject(Integer projectId) {
		log.debug("finding all Pedagogicalresource instances");
		try {
			String queryString = "from Pedagogicalresource model where model.project.id = :pid";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("pid", projectId);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}