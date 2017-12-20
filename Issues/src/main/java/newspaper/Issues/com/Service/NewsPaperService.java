package newspaper.Issues.com.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import newspaper.Issues.com.DAO.InewsDAO;
import newspaper.Issues.com.VO.registration;

@Service
public class NewsPaperService implements InewsPaperService{

	@Autowired
	private InewsDAO newsDAO;
	@Override
	public registration getNewsPaperById(int NewsPaperId) {
		registration obj = newsDAO.getNewsPaperById(NewsPaperId);
		return obj;
	}	
	@Override
	public List<registration> getAllNewsPapers(){
		return newsDAO.getAllNewsPapers();
	}
	@Override
	public synchronized boolean addNewsPaper(registration NewsPaper){
                if (newsDAO.NewsPaperExists(NewsPaper.getname())) {
    	            return false;
                } else {
    	            newsDAO.addNewsPaper(NewsPaper);;
    	            return true;
                }
	}
	@Override
	public void updateNewsPaper(registration NewsPaper) {
		newsDAO.updateNewsPaper(NewsPaper);
	}
	@Override
	public void deleteNewsPaper(int NewsPaperId) {
		newsDAO.deleteNewsPaper(NewsPaperId);
	}
}