package com.omarionapps.sakila.model;

public enum Rating {
	G("G"), PG("PG"), PG13("PG-13"), R("R"), NC17("NC-17");
	
	private String rating;
	
	public String getRating() {
		return this.rating;
	}
	
	private Rating(String rating){
		this.rating = rating;
	}
	
	
}
