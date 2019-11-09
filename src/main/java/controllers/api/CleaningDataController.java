package controllers.api;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.model.CommentDto;
import models.Comments;
import repositories.CommentRepository;
import service.DataCleaningService;
import service.FileStorageService;
import service.LanguageDetectionService;
import service.TreatementMethodsService;

@RestController
@RequestMapping("/DataCleaning")
public class CleaningDataController {
	
	@Autowired
	FileStorageService fileStorageService;
	
	@Autowired
	DataCleaningService dataCleaningService;
	
	
	
	@Autowired
    private CommentRepository repository;
	
	@RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
	 public void dataCleaning(@PathVariable("id") ObjectId id, @Valid @RequestBody Comments comments) {
		 boolean countVect;
		 boolean tfidfVect;
		 
		
		if(tfidfVect=true) {
			  CommentDto commentDto=new CommentDto();
			  commentDto.vect= comments.getVect();
			  dataCleaningService.TFIDF(commentDto);
			  comments.setVect(dataCleaningService.TFIDF(commentDto).vect);
		        repository.save(comments);
		}
		else if(countVect=true) {
			CommentDto commentDto=new CommentDto();
			  commentDto.vect= comments.getVect();
			  dataCleaningService.countVectorizer(commentDto);
			  comments.setVect(dataCleaningService.countVectorizer(commentDto).vect);
		        repository.save(comments);
			
			
		}
		
		
		
   
		
   }

	
	
}
