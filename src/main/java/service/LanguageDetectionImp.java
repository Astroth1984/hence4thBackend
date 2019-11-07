package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.mapper.CommentMapper;
import dto.model.CommentDto;
import models.Comments;
import repositories.CommentRepository;

import java.util.List;
import static java.util.Arrays.asList;

import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import com.google.common.base.Optional;
import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.Language;



@Component
public class LanguageDetectionImp implements LanguageDetectionService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
    private CommentMapper commentMapper;
	
	@Override
	public CommentDto LangDetector(CommentDto commentDto) {
		final LanguageDetector detector = LanguageDetectorBuilder.fromLanguages(Language.ENGLISH, Language.GERMAN, Language.ARABIC, Language.FRENCH
				).build();
		
		Optional<Comments> comments = Optional.ofNullable(commentRepository.findById(commentDto.getId()));
		if (comments.isPresent()){
			
			Comments commentModel = comments.get();
			final Language detectedLanguage = detector.detectLanguageOf(commentDto.comment);
			commentModel.setLang(detectedLanguage);
			
			
			 return commentMapper.toCommentDto(commentRepository.save(commentModel));
			
		}
		
		
		
		
				
	}
	
	

}
