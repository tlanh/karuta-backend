package eportfolium.com.karuta.consumer.impl.dao;
// Generated 17 juin 2019 11:33:18 by Hibernate Tools 5.2.10.Final

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.activation.MimeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import eportfolium.com.karuta.consumer.contract.dao.ResourceTableDao;
import eportfolium.com.karuta.model.bean.Credential;
import eportfolium.com.karuta.model.bean.ResourceTable;
import eportfolium.com.karuta.util.JavaTimeUtil;

/**
 * Home object implementation for domain model class ResourceTable.
 * 
 * @see dao.ResourceTable
 * @author Hibernate Tools
 */
@Repository
public class ResourceTableDaoImpl extends AbstractDaoImpl<ResourceTable> implements ResourceTableDao {

	@PersistenceContext
	private EntityManager entityManager;

	private static final Log log = LogFactory.getLog(ResourceTableDaoImpl.class);

	public ResourceTableDaoImpl() {
		super();
		setCls(ResourceTable.class);
	}

	public ResourceTable getResource(String nodeUuid) {
		ResourceTable result = null;
		// On recupere d'abord les informations dans la table structures
		String sql = "FROM ResourceTable r";
		sql += " WHERE r.id = :resourceID ";
		TypedQuery<ResourceTable> q = entityManager.createQuery(sql, ResourceTable.class);
		q.setParameter("resourceID", UUID.fromString(nodeUuid));
		try {
			result = q.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return result;
	}

	public UUID getResourceNodeUuidByParentNodeUuid(String nodeParentUuid) {
		return getResourceByNodeParentUuid(nodeParentUuid).getId();
	}

	public UUID getResourceNodeUuidByParentNodeUuid(UUID nodeParentUuid) {
		return getResourceByNodeParentUuid(nodeParentUuid).getId();
	}

	public ResourceTable getResourceByNodeParentUuid(String nodeParentUuid) {
		return getResourceByNodeParentUuid(UUID.fromString(nodeParentUuid));
	}

	public ResourceTable getResourceByNodeParentUuid(UUID nodeParentUuid) {
		String sql;
		ResourceTable res = null;

		// On recupere d'abord les informations dans la table structures
		sql = "SELECT r FROM ResourceTable r";
		sql += " INNER JOIN r.node n WITH n.id = :parentNodeID";
		TypedQuery<ResourceTable> q = entityManager.createQuery(sql, ResourceTable.class);
		q.setParameter("parentNodeID", nodeParentUuid);
		try {
			res = q.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return res;

	}

	public ResourceTable getResource(MimeType outMimeType, String nodeParentUuid, int userId, int groupId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ResourceTable> getResources(MimeType outMimeType, String portfolioUuid, int userId, int groupId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object putResource(MimeType inMimeType, String nodeParentUuid, String in, int userId, int groupId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object postResource(MimeType inMimeType, String nodeParentUuid, String in, int userId, int groupId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Replace was designed to ease the following case:
	 * 
	 * Check, if record with same PK exists If yes, delete the row and insert a new
	 * row for the record with the given one If no, insert a new record As per
	 * documentation REPLACE is equivalent to INSERT, apart from deleting any
	 * existing record having the PK is beeing deleted before.
	 */
	public int updateResource(String uuid, String xsiType, String content, Long userId) {
		int result = 0;
		ResourceTable rt = null;
		try {
			if (xsiType != null) {
				removeById(UUID.fromString(uuid));
				Date now = JavaTimeUtil.toJavaDate(LocalDateTime.now(JavaTimeUtil.date_default_timezone));
				rt = new ResourceTable(UUID.fromString(uuid), xsiType, content, new Credential(userId), userId, now);
				persist(rt);
			} else {
				rt = findById(UUID.fromString(uuid));
				rt.setContent(content);
				rt.setCredential(new Credential(userId));
				rt.setModifUserId(userId);
				merge(rt);
			}
		} catch (Exception e) {
			log.error("");
			result = 1;
		}
		return result;
	}

	public ResourceTable getResourceOfResourceByNodeUuid(String nodeUuid) {
		return getResourceOfResourceByNodeUuid(UUID.fromString(nodeUuid));
	}

	public ResourceTable getResourceOfResourceByNodeUuid(UUID nodeUuid) {
		ResourceTable result = null;
		String sql = " SELECT r FROM ResourceTable r";
		sql += " INNER JOIN r.resNode resNode WITH resNode.id = :nodeUuid";
		TypedQuery<ResourceTable> q = entityManager.createQuery(sql, ResourceTable.class);
		q.setParameter("nodeUuid", nodeUuid);
		try {
			result = q.getSingleResult();
		} catch (NoResultException e) {
		}
		return result;
	}

	public ResourceTable getContextResourceByNodeUuid(String nodeUuid) {
		return getContextResourceByNodeUuid(UUID.fromString(nodeUuid));
	}

	public ResourceTable getContextResourceByNodeUuid(UUID nodeUuid) {
		ResourceTable result = null;
		String sql = " SELECT r FROM ResourceTable r";
		sql += " INNER JOIN r.contextNode contextNode WITH contextNode.id = :nodeUuid";
		TypedQuery<ResourceTable> q = entityManager.createQuery(sql, ResourceTable.class);
		q.setParameter("nodeUuid", nodeUuid);
		try {
			result = q.getSingleResult();
		} catch (NoResultException e) {
		}
		return result;
	}

	public ResourceTable getResourceByNodeUuid(String nodeUuid) {
		return getResourceByNodeUuid(UUID.fromString(nodeUuid));
	}

	public ResourceTable getResourceByNodeUuid(UUID nodeUuid) {
		ResourceTable resourceTable = null;
		String sql = "SELECT r FROM ResourceTable r";
		sql += " INNER JOIN r.node n WITH n.id = :nodeUuid";
		TypedQuery<ResourceTable> q = entityManager.createQuery(sql, ResourceTable.class);
		q.setParameter("nodeUuid", nodeUuid);
		try {
			resourceTable = q.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return resourceTable;
	}

	public String getResNodeContentByNodeUuid(String nodeUuid) {
		ResourceTable rt = getResourceByNodeUuid(nodeUuid);
		return rt != null ? rt.getContent() : "";
	}

	public List<ResourceTable> getResourcesByPortfolioUUID(String portfolioUuid) {
		// On recupere d'abord les informations dans la table structures
		String sql = "SELECT r FROM ResourceTable r";
		sql += " INNER JOIN r.node n WITH n.portfolio.id = :portfolioUuid";
		TypedQuery<ResourceTable> q = entityManager.createQuery(sql, ResourceTable.class);
		q.setParameter("portfolioUuid", UUID.fromString(portfolioUuid));
		List<ResourceTable> results = q.getResultList();
		return results;
	}

	public List<ResourceTable> getResourcesOfResourceByPortfolioUUID(String portfolioUuid) {
		String sql = "SELECT r FROM ResourceTable r";
		sql += " INNER JOIN r.resNode n WITH n.portfolio.id = :portfolioUuid";
		TypedQuery<ResourceTable> q = entityManager.createQuery(sql, ResourceTable.class);
		q.setParameter("portfolioUuid", UUID.fromString(portfolioUuid));
		return q.getResultList();
	}

	public List<ResourceTable> getContextResourcesByPortfolioUUID(String portfolioUuid) {
		String sql = "SELECT r FROM ResourceTable r";
		sql += " INNER JOIN r.contextNode n WITH n.portfolio.id = :portfolioUuid";
		TypedQuery<ResourceTable> q = entityManager.createQuery(sql, ResourceTable.class);
		q.setParameter("portfolioUuid", UUID.fromString(portfolioUuid));
		return q.getResultList();
	}

	public Object deleteResource(String resourceUuid, Long userId, Long groupId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateResource(String nodeUuid, String content, Long userId) {
		return updateResource(UUID.fromString(nodeUuid), content, userId);
	}

	public int updateResource(UUID nodeUuid, String content, Long userId) {
		int result = 0;
		String sql = " SELECT rt FROM ResourceTable rt";
		sql += " INNER JOIN rt.node n WITH n.id = :nodeUuid";
		TypedQuery<ResourceTable> q = entityManager.createQuery(sql, ResourceTable.class);
		q.setParameter("nodeUuid", nodeUuid);
		try {
			ResourceTable rt = q.getSingleResult();
			rt.setContent(content);
			rt.setCredential(new Credential(userId));
			rt.setModifUserId(userId);
			merge(rt);
		} catch (Exception e) {
			result = 1;
		}
		return result;

	}

	public int updateResResource(String nodeUuid, String content, Long userId) {
		return updateResResource(UUID.fromString(nodeUuid), content, userId);
	}

	public int updateResResource(UUID nodeUuid, String content, Long userId) {
		int result = 0;
		try {
			ResourceTable rt = getResourceOfResourceByNodeUuid(nodeUuid);
			rt.setContent(content);
			rt.setCredential(new Credential(userId));
			rt.setModifUserId(userId);
			merge(rt);
		} catch (Exception e) {
			log.error("merge failed", e);
			result = 1;
		}
		return result;
	}

	public int updateContextResource(String nodeUuid, String content, Long userId) {
		return updateContextResource(UUID.fromString(nodeUuid), content, userId);
	}

	public int updateContextResource(UUID nodeUuid, String content, Long userId) {
		int result = 0;
		try {
			ResourceTable rt = getContextResourceByNodeUuid(nodeUuid);
			rt.setContent(content);
			rt.setCredential(new Credential(userId));
			rt.setModifUserId(userId);
			merge(rt);
		} catch (Exception e) {
			result = 1;
		}
		return result;
	}
}
