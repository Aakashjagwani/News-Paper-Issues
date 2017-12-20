package newspaper.Issues.com.DAO;

import java.util.List;

import newspaper.Issues.com.VO.registration;

public interface InewsDAO {
	List<registration> getAllNewsPapers();
	registration getNewsPaperById(int NewsPaperId);
	void addNewsPaper(registration NewsPaper);
    void updateNewsPaper(registration NewsPaper);
    void deleteNewsPaper(int NewsPaperId);
    boolean NewsPaperExists(String name);
}
