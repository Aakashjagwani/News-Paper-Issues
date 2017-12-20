package newspaper.Issues.com.client;

import java.net.URI;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import newspaper.Issues.com.VO.registration;

public class RestClient {
	
	public void getNewsPaperByIdDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/aakash/newspaper/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<registration> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, registration.class, 1);
        registration newspaper = responseEntity.getBody();
        System.out.println("Id:"+newspaper.getId()+", Name:"+newspaper.getname()
                 +", City:"+newspaper.getCity());      
    }
	
    public void getAllNewsPapersDemo() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/aakash/newspapers";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<registration[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, registration[].class);
        registration[] newspapers = responseEntity.getBody();
        for(registration newspaper : newspapers) {
              System.out.println("Id:"+newspaper.getId()+", name:"+newspaper.getname()
                      +", City: "+newspaper.getCity());
        }
    }
    public void addNewsPaperDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8090/aakash/newspaper";
		registration objnewspaper = new registration();
		objnewspaper.setname("Times Of India");;
		objnewspaper.setCity("Ahmedabad");
        HttpEntity<registration> requestEntity = new HttpEntity<registration>(objnewspaper, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());    	
    }
    public void updateNewsPaperDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/aakash/newspaper";
		registration objArticle = new registration();
		objArticle.setId(1);
		objArticle.setname("Sandesh");
		objArticle.setCity("Ahmedabad");
	    HttpEntity<registration> requestEntity = new HttpEntity<registration>(objArticle, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deleteNewsPaperDemo() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8090/aakash/newspaper/{id}";
        HttpEntity<registration> requestEntity = new HttpEntity<registration>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);        
    }
    public static void main(String args[]) {
    	RestClient util = new RestClient();
//    	util.getNewsPaperByIdDemo();
    	util.getAllNewsPapersDemo();
//    	util.addNewsPaperDemo();
//    	util.updateNewsPaperDemo();
//    	util.deleteNewsPaperDemo();
    }    
} 