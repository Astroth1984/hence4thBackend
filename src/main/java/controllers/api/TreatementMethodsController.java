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
import service.FileStorageService;
import service.LanguageDetectionService;
import service.TreatementMethodsService;

@RestController
@RequestMapping("/comment-treatement")
public class TreatementMethodsController {
	
	@Autowired
	FileStorageService fileStorageService;
	
	@Autowired
	TreatementMethodsService treatementMethodsService;
	
	@Autowired
	LanguageDetectionService languageDetectionService;
	
	@Autowired
    private CommentRepository repository;
	
	
	
	@RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
	 public void Treatement(@PathVariable("id") ObjectId id, @Valid @RequestBody Comments comments) {
		 boolean Emoji =false;
		 boolean Accent=false;
		 boolean SpecialCaratrs=false;
		
		if(Emoji=true) {
			  CommentDto commentDto=new CommentDto();
			  commentDto.comment= comments.getComment();
			  languageDetectionService.LangDetector(commentDto);
			  treatementMethodsService.replaceEmojis(commentDto);
			  comments.setComment(id.toString());
		        repository.save(comments);
		}
		else if(Accent=true) {
			CommentDto commentDto=new CommentDto();
			  commentDto.comment= comments.getComment();
			  languageDetectionService.LangDetector(commentDto);
			  treatementMethodsService.suppLesAccents(commentDto);
			  comments.setComment(id.toString());
		        repository.save(comments);
			
		}
		else {
			CommentDto commentDto=new CommentDto();
			  commentDto.comment= comments.getComment();
			  languageDetectionService.LangDetector(commentDto);
			  treatementMethodsService.removeSpecialCaracteres(commentDto);
			  comments.setComment(id.toString());
		        repository.save(comments);
			
		}
		
		
		
    
		
    }
	
	
	
	
	

}
