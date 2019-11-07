package repositories;



import org.springframework.stereotype.Repository;
import models.Comments;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface CommentRepository extends MongoRepository<Comments,String> {
	Optional<Comments> findById(String id);
}