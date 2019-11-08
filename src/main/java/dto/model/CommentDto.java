package dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pemistahl.lingua.api.Language;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class CommentDto {
	
	public String id;
	
	
    public String comment;
    
    
    public Language lang;
    
    
    public double vect;


	public String getId() {
		 return id;
	}


	public String getComment() {
		// TODO Auto-generated method stub
		return comment;
	}


	


}
