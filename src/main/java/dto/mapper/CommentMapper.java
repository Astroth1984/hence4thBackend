package dto.mapper;

import dto.model.CommentDto;
import models.Comments;

public class CommentMapper {
	
	public static CommentDto toCommentDto (Comments comments) {
		
		return new CommentDto()
                .setId(comments.getId())
                .set(trip.getAgency().getCode())
                .setSourceStopCode(trip.getSourceStop().getCode())
                .setSourceStopName(trip.getSourceStop().getName())
                .setDestinationStopCode(trip.getDestStop().getCode())
                .setDestinationStopName(trip.getDestStop().getName())
                .setBusCode(trip.getBus().getCode())
                .setJourneyTime(trip.getJourneyTime())
                .setFare(trip.getFare());
		
	}

}
