package models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comments {
	//@Id
	private String id;
    private String comment;
    private String lang;
    private int[] vect;
    
	public Comments() {
	}
	public Comments(String comment, String lang, int[] vect) {
		this.comment = comment;
		this.lang = lang;
		this.vect=vect;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public int[] getVect() {
		return vect;
	}
	public void setVect(int[] vect) {
		this.vect=vect;
	}

}

