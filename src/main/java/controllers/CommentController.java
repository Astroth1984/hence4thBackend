package controllers;

import models.Comments;
import repositories.CommentRepository;
import service.FileStorageService;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@CrossOrigin( origins = "http://localhost:4200" )
@RestController
@RequestMapping(value = "/comments")


public class CommentController {
	
	  @Autowired
	    private CommentRepository repository;
	  @RequestMapping(value = "", method = RequestMethod.GET)
	    public List<Comments> getAllComments() {
	        return repository.findAll();
	        
	   } 
	 
	   
	  
	  @RequestMapping(value = "/put/{id}", method = RequestMethod.GET)
	    public Comments getCommentById(@PathVariable("id") String id) {
	    	Optional<Comments> optional = repository.findById(id);
	    	Comments comments = new Comments();
	    	if (optional.isPresent()) {
	    		comments = optional.get();
	    	}else {
	    		return null;
	    	}
	        return comments;
	    }
	  @RequestMapping(value = "/put/{id}", method = RequestMethod.PUT)
	    public void updateCommentById(@PathVariable("id") ObjectId id, @Valid @RequestBody Comments comment) {
		  comment.setId(id.toString());
	        repository.save(comment);
	    }
	  
	  @RequestMapping(value = "/post", method = RequestMethod.POST)
	    public Comments addNewComment(@RequestBody Comments comment) {
	    	System.err.println("yes it works!");
	        comment.setId(ObjectId.get().toString());
	        repository.save(comment);
	        return comment;
	    }
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Object> deleteCommentByID(@PathVariable("id") String id) {
	        return repository.findById(id)
	              .map(comments -> {
	              repository.deleteById(id);
	              return ResponseEntity.ok().build();
	              }).orElse(ResponseEntity.notFound().build());
	        }  
	  	
	  
	  @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	    public void deleteComment() {
			MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		    MongoDatabase db = mongoClient.getDatabase("FileDB");
		    MongoCollection<Document> collection1 = db.getCollection("comments");
		    collection1.drop(); 
		    MongoCollection<Document> collection2 = db.getCollection("entities");
		    collection2.drop(); 
	        }  
	   

	  
	  
	  private static final Logger logger = LoggerFactory.getLogger(CommentController.class);


	    
	    private FileStorageService fileStorageService;
	    @Autowired
		public CommentController(FileStorageService fileStorageService) {
			this.fileStorageService = fileStorageService;
		}
	 
		@PostMapping(value = "/files")
		@ResponseStatus(HttpStatus.OK)
		public void handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
			fileStorageService.storeFile(file);
		}
	    
	    

	    
	    @GetMapping("/downloadFile/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
	        Resource resource = fileStorageService.loadFileAsResource(fileName);

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	            logger.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }

	  
	  
}
