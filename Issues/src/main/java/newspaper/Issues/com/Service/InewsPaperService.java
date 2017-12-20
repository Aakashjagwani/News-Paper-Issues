package newspaper.Issues.com.Service;
import java.util.List;
import newspaper.Issues.com.VO.registration;

public interface InewsPaperService {
	List<registration> getAllNewsPapers();
	registration getNewsPaperById(int NewsPaperId);
	boolean addNewsPaper(registration NewsPaper);
    void updateNewsPaper(registration NewsPaper);
    void deleteNewsPaper(int NewsPaperId);
}
