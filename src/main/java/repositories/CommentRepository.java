package repositories;

import org.springframework.stereotype.Repository;
import models.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface CommentRepository extends MongoRepository<Comments,String> {
}