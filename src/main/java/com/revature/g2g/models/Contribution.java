package com.revature.g2g.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Entity
@Table(name = "G2G_CONTRIBUTION")
@Data
public class Contribution implements Serializable {
	private static final long serialVersionUID = -4267824207894194294L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	@Column(name = "contribution_name")
	private String contributionName;
	
	@Column(name = "contribution_description")
	private String contributionDescription;
	
	@Column(name = "contribution_weight")
	private int contributionWeight;
	
	@ManyToOne(optional = false)
	private Contributor contributor;
}