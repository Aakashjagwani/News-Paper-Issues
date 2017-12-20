package newspaper.Issues.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import newspaper.Issues.com.Service.InewsPaperService;
import newspaper.Issues.com.VO.registration;

@Controller
@RequestMapping("aakash")
@CrossOrigin(origins = {"http://localhost:4200"})
public class news_controller {

	@Autowired
	private InewsPaperService newsService;
	@GetMapping("newspaper")
	public ResponseEntity<registration> getNewsPaperById(@RequestParam("id") int id) {
		registration newspaper = newsService.getNewsPaperById(id);
		return new ResponseEntity<registration>(newspaper, HttpStatus.OK);
	}
	@GetMapping("newspapers")
	public ResponseEntity<List<registration>> getAllNewsPapers() {
		List<registration> list = newsService.getAllNewsPapers();
		return new ResponseEntity<List<registration>>(list, HttpStatus.OK);
	}
	@PostMapping("newspaper")
	public ResponseEntity<Void> addNewsPaper(@RequestBody registration newspaper, UriComponentsBuilder builder) {
                boolean flag = newsService.addNewsPaper(newspaper);
                if (flag == false) {
        	    return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/newspaper/{id}").buildAndExpand(newspaper.getId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("newspaper")
	public ResponseEntity<registration> updateNewsPaper(@RequestBody registration newspaper) {
		newsService.updateNewsPaper(newspaper);
		return new ResponseEntity<registration>(newspaper, HttpStatus.OK);
	}
	@DeleteMapping("newspaper")
	public ResponseEntity<Void> deleteNewsPaper(@RequestParam("id") int id) {
		newsService.deleteNewsPaper(id);;
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}  

