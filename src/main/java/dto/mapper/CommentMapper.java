package dto.mapper;

import dto.model.CommentDto;
import models.Comments;

public class CommentMapper {
	
	public static CommentDto toCommentDto (Comments comments) {
		
		return new CommentDto()
                .setId(comments.getId())
                .setComment(comments.getComment())
                .setLang(comments.getLang())
                .setVect(comments.getVect());
               
		
	}

}
