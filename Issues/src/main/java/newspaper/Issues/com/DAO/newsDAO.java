package newspaper.Issues.com.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import newspaper.Issues.com.VO.registration;

@Transactional
@Repository
public class newsDAO implements InewsDAO{
		@PersistenceContext	
		private EntityManager entityManager;	
		@Override
		public registration getNewsPaperById(int newspaperId) {
			return entityManager.find(registration.class, newspaperId);
		}
		@SuppressWarnings("unchecked")
		@Override
		public  List<registration> getAllNewsPapers() {
			String hql = "FROM registration as nws ORDER BY nws.id";
			return (List<registration>) entityManager.createQuery(hql).getResultList();
		}	
		@Override
		public void addNewsPaper(registration newspaper) {
			entityManager.persist(newspaper);
		}
		@Override
		public void updateNewsPaper(registration newspaper) {
			registration paper = getNewsPaperById(newspaper.getId());
			paper.setname(newspaper.getname());
			paper.setCity(newspaper.getCity());
			entityManager.flush();
		}
		@Override
		public void deleteNewsPaper(int NewsPaperId) {
			entityManager.remove(getNewsPaperById(NewsPaperId));
		}
		@Override
		public boolean NewsPaperExists(String name) {
			String hql = "FROM registration as newspaper WHERE newspaper.name = ? ";
			int count = entityManager.createQuery(hql).setParameter(1, name).getResultList().size();
			return count > 0 ? true : false;
		}
	} 