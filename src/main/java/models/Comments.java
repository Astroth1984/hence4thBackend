package models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.github.pemistahl.lingua.api.Language;

@Document(collection = "comments")
public class Comments {
	//@Id
	private String id;
    private String comment;
    private Language lang;
    private double vect;
    
	public Comments() {
	}
	public Comments(String comment, Language lang, double vect) {
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
	public Language getLang() {
		return lang;
	}
	public void setLang(Language lang) {
		this.lang = lang;
	}
	public double getVect() {
		return vect;
	}
	public void setVect(double vect) {
		this.vect=vect;
	}
	

}

