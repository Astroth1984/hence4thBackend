package service;

import static org.junit.Assert.assertEquals;

import java.text.Normalizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

import dto.mapper.CommentMapper;
import dto.model.CommentDto;
import models.Comments;
import repositories.CommentRepository;



@Component
public class TreatementMethodsServiceImp implements TreatementMethodsService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentMapper commentMapper;
	
	
	/**
     * 
     *
     * @param commentDto
     * @return
     */
	@Override
	public CommentDto replaceEmojis(CommentDto commentDto) {
		
		Optional<Comments> comments = Optional.ofNullable(commentRepository.findById(commentDto.getId()));
        if (comments.isPresent()) {
            Comments commentsModel = comments.get();
            String result = EmojiParser.parseToAliases(commentDto.comment);
            assertEquals(result,commentDto.comment);
            
            return CommentMapper.toCommentDto(commentRepository.save(commentsModel));	      
            
        }    
		
	}
	
	/**
     * 
     *
     * @param commentDto
     * @return
     */
	
	 @Override	
	 public CommentDto removeSpecialCaracteres(CommentDto commentDto) {
		 
		 Optional<Comments> comments = Optional.ofNullable(commentRepository.findById(commentDto.getId()));
	        if (comments.isPresent()) {
	            Comments commentsModel = comments.get();
	            commentDto.comment=commentDto.comment.replaceAll("[^a-zA-Z0-9\\s+]", "");
	            commentsModel.setComment(commentDto.getComment());
	            
	            return CommentMapper.toCommentDto(commentRepository.save(commentsModel));
	        }    
		 
	 }
	 /**
	  * 
	  *
      * @param commentDto
      * @return
      */
	 @Override       
	 public CommentDto suppLesAccents(CommentDto commentDto) {
		 
		 Optional<Comments> comments = Optional.ofNullable(commentRepository.findById(commentDto.getId()));
	        if (comments.isPresent()) {
	            Comments commentsModel = comments.get();
	            Normalizer.normalize(commentDto.comment, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	            commentsModel.setComment(commentDto.getComment());
	            
	            return CommentMapper.toCommentDto(commentRepository.save(commentsModel));
	        }    
		 
	 }

}
