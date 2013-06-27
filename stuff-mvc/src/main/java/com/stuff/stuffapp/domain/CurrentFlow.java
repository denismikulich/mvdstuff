package com.stuff.stuffapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CUR_FLOW")
public class CurrentFlow {

	/**
	 * Stuff unique id
	 */
	private Long id;
	
	private Stuff stuff;
	
	private StuffFlow flow;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "STUFF_ID")
	public Stuff getStuff() {
		return stuff;
	}

	public void setStuff(Stuff stuff) {
		this.stuff = stuff;
	}

	@ManyToOne
	@JoinColumn(name = "FLOW_ID")
	public StuffFlow getFlow() {
		return flow;
	}

	public void setFlow(StuffFlow flow) {
		this.flow = flow;
	}
	
	
}
