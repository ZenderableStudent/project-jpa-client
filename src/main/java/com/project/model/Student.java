package com.project.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="student",
		indexes = { @Index(name= "idx_nazwisko", columnList="nazwisko",unique = false),
					@Index(name="idx_nr_indeksu",columnList="nr_indeksu",unique = false)})

public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_id")
	private Integer student_id;
	@Column(name="imie",nullable = false, length = 50)
	private String imie;
	@Column(name="nazwisko",nullable = false, length = 100)
	private String nazwisko;
	@Column(name="nr_indeksu",nullable = false, length = 20)
	private String nr_indeksu;
	@Column(name="email",nullable = false, length = 50)
	private String email;
	@Column(name="stacjonarny",nullable=false)
	private Boolean stacjonarny;
	@ManyToMany(mappedBy="studenci")
	private Set<Projekt> projekty;
	
	public Set<Projekt> getProjekty() {
		return projekty;
	}

	public void setProjekty(Set<Projekt> projekty) {
		this.projekty = projekty;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getNr_indeksu() {
		return nr_indeksu;
	}

	public void setNr_indeksu(String nr_indeksu) {
		this.nr_indeksu = nr_indeksu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStacjonarny() {
		return stacjonarny;
	}

	public void setStacjonarny(Boolean stacjonarny) {
		this.stacjonarny = stacjonarny;
	}

	public Student() {}

	public Student(String imie, String nazwisko, String nr_indeksu, Boolean stacjonarny) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nr_indeksu = nr_indeksu;
		this.stacjonarny = stacjonarny;
	}

	public Student(String imie, String nazwisko, String nr_indeksu, String email, Boolean stacjonarny) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nr_indeksu = nr_indeksu;
		this.email = email;
		this.stacjonarny = stacjonarny;
	}
	

}
