package com.org.sg.DAO;

import com.org.sg.POJO.action.AssociatedProjects;

import java.util.Date;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * AssociatedProjects entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.org.sg.POJO.action.AssociatedProjects
 * @author MyEclipse Persistence Tools
 */
public class AssociatedProjectsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AssociatedProjectsDAO.class);
	// property constants
	public static final String MODIFIED = "modified";
	public static final String COMMENTS = "comments";

	public void save(AssociatedProjects transientInstance) {
		log.debug("saving AssociatedProjects instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssociatedProjects persistentInstance) {
		log.debug("deleting AssociatedProjects instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AssociatedProjects findById(java.lang.Integer id) {
		log.debug("getting AssociatedProjects instance with id: " + id);
		try {
			AssociatedProjects instance = (AssociatedProjects) getSession().get("com.org.sg.POJO.action.AssociatedProjects", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<AssociatedProjects> findByExample(AssociatedProjects instance) {
		log.debug("finding AssociatedProjects instance by example");
		try {
			List<AssociatedProjects> results = (List<AssociatedProjects>) getSession().createCriteria("com.org.sg.POJO.action.AssociatedProjects").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding AssociatedProjects instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from AssociatedProjects as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<AssociatedProjects> findByModified(Object modified) {
		return findByProperty(MODIFIED, modified);
	}

	public List<AssociatedProjects> findByComments(Object comments) {
		return findByProperty(COMMENTS, comments);
	}

	public List findAll() {
		log.debug("finding all AssociatedProjects instances");
		try {
			String queryString = "from AssociatedProjects";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<AssociatedProjects> findAssociatedProjects(Integer admin, Integer teacher) {
		log.debug("finding all AssociatedProjects instances");
		try {
			String queryString = "from AssociatedProjects where userByAdmin.id = :ADMIN and userByTeacher.id = :TEACHER";			
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("ADMIN", admin);
			queryObject.setParameter("TEACHER", teacher);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssociatedProjects merge(AssociatedProjects detachedInstance) {
		log.debug("merging AssociatedProjects instance");
		try {
			AssociatedProjects result = (AssociatedProjects) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssociatedProjects instance) {
		log.debug("attaching dirty AssociatedProjects instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssociatedProjects instance) {
		log.debug("attaching clean AssociatedProjects instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public int deleteOfUser(Integer userId) {
		try {
			String queryString = "delete from AssociatedProjects as model where model.userByTeacher.id = :UID";			
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("UID", userId);
			return queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}