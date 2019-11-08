package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

import dto.mapper.CommentMapper;
import dto.model.CommentDto;
import models.Comments;
import repositories.CommentRepository;


@Component
public class DataCleaningServiceImp implements DataCleaningService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public CommentDto TFIDF(CommentDto commentDto) {
		
			String doc;
		
			Optional<Comments> comments = Optional.ofNullable(commentRepository.findById(commentDto.getId()));
	        if (comments.isPresent()) {
	            Comments commentsModel = comments.get();
	            
	            TFIDFCalculator calculator = new TFIDFCalculator();
	            List<String> myList = new ArrayList<String>(Arrays.asList(commentsModel.getComment().split(",")));
	            double tfidf = calculator.tf(myList,"ipsum");
	            commentsModel.setVect(tfidf);
	            
	            return CommentMapper.toCommentDto(commentRepository.save(commentsModel));
	            
	                 
	        }    
		
		
	}
	
	@Override
	public CommentDto countVectorizer(CommentDto commentDto) {
		Optional<Comments> comments = Optional.ofNullable(commentRepository.findById(commentDto.getId()));
        if (comments.isPresent()) {
            Comments commentsModel = comments.get();
            CountVectorizerCalculator calculator =new CountVectorizerCalculator(commentsModel.getComment(),commentDto.comment);
            
        }    
		
		
	}
	
	

    


    


}
