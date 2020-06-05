package com.revature.g2g.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Entity
@Table(name = "G2G_Contributor")
@Data
public class Contributor implements Serializable {
	private static final long serialVersionUID = 1244712294121270538L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	@Column(name = "contributor_id")
	private long contributorId;

	@Column(name = "professional_name")
	private String professionalName;
	
	@Column(name = "github")
	private String github;
	
	@Column(name = "linked_in")
	private String linkedIn;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "bio")
	@Size(min = 50, max = 4000, message = "Bios must be between 50 and 4000 characters.")
	private String bio;
	
	@OneToOne(optional = false)
	private Player playerId;
	
	@OneToMany(mappedBy = "contributor", fetch = FetchType.LAZY)
	private List<Contribution> contributions;
}
