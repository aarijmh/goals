package com.org.sg.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.sg.POJO.action.PedagogicalGame;

/**
 * A data access object (DAO) providing persistence and search support for
 * PedagogicalGame entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.org.sg.POJO.action.PedagogicalGame
 * @author MyEclipse Persistence Tools
 */
public class PedagogicalGameDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PedagogicalGameDAO.class);
	// property constants
	public static final String VALUE = "value";

	public void save(PedagogicalGame transientInstance) {
		log.debug("saving PedagogicalGame instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PedagogicalGame persistentInstance) {
		log.debug("deleting PedagogicalGame instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PedagogicalGame findById(java.lang.Integer id) {
		log.debug("getting PedagogicalGame instance with id: " + id);
		try {
			PedagogicalGame instance = (PedagogicalGame) getSession().get(
					"com.org.sg.POJO.action.PedagogicalGame", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<PedagogicalGame> findByExample(PedagogicalGame instance) {
		log.debug("finding PedagogicalGame instance by example");
		try {
			List<PedagogicalGame> results = (List<PedagogicalGame>) getSession()
					.createCriteria("com.org.sg.POJO.PedagogicalGame")
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
		log.debug("finding PedagogicalGame instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PedagogicalGame as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<PedagogicalGame> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findAll() {
		log.debug("finding all PedagogicalGame instances");
		try {
			String queryString = "from PedagogicalGame";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PedagogicalGame merge(PedagogicalGame detachedInstance) {
		log.debug("merging PedagogicalGame instance");
		try {
			PedagogicalGame result = (PedagogicalGame) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PedagogicalGame instance) {
		log.debug("attaching dirty PedagogicalGame instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PedagogicalGame instance) {
		log.debug("attaching clean PedagogicalGame instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	

	public List<PedagogicalGame> findOfGameResource(Integer id) {
		log.debug("finding all PedagogicalGame instances");
		try {
			String queryString = "from PedagogicalGame where gameresource.id = :ID ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("ID", id);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteOfGR(Integer id) {
		try {
			String queryString = "delete from PedagogicalGame where  gameresource.id = :CID";
			Query query = getSession().createQuery(queryString);
			query.setInteger("CID", id);
			query.executeUpdate();
			
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}