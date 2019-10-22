package com.internship.application.model;


import lombok.Data;

@Data
public class Domain {

private int id;
private String fileName;
private String name;
private String lang;
private int[] result;
 
 
 
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public String getLang() {
	return lang;
}
public void setLang(String lang) {
	this.lang=lang;
}
public int[] getResult() {
	return result;
	
}
public void setResult(int[] result) {
	this.result=result;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
}