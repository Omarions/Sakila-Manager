package com.omarionapps.sakila.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Language implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="language_id", columnDefinition="TINYINT")
	private int languageId;
	private String name;
	@Column(name="last_update")
	private Date lastUpdate;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="language")
	private Set<Film> films = new HashSet<>();
	@OneToMany(cascade=CascadeType.ALL, mappedBy="originalLang")
	private Set<Film> orgFilms = new HashSet<>();
	public Language() {

	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@JsonIgnore
	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}
	
	@JsonIgnore
	public Set<Film> getOrgFilms() {
		return orgFilms;
	}

	public void setOrgFilms(Set<Film> orgFilms) {
		this.orgFilms = orgFilms;
	}

	@Override
	public String toString() {
		return "Language [languageId=" + languageId + ", name=" + name + ", lastUpdate=" + lastUpdate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + languageId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		if (languageId != other.languageId)
			return false;
		return true;
	}
	
	
	
}
