/**
 * 
 */
package com.model;

import java.util.AbstractMap.SimpleEntry;

/**
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class NodeProbability {

	// To store values like <Node A, T> 
	private SimpleEntry<Node, Character> entry;
	// To store the corresponding probability
	private float probability;
	/**
	 * 
	 */
	public NodeProbability() {
		this.setEntry(null);
		this.setProbability(0);
	}
	
	/**
	 * Parameterized constructor for NodeProbability
	 * 
	 * @param entry
	 * @param probability
	 */
	public NodeProbability(Node nodeObj, String characterValue, 
			float probability) {
		SimpleEntry<Node, Character> simpleEntryObj = 
				new SimpleEntry<Node, Character>(nodeObj, characterValue.charAt(0));
		this.setEntry(simpleEntryObj);
		this.setProbability(probability);
	}
	
	public SimpleEntry<Node, Character> getEntry() {
		return entry;
	}

	public void setEntry(SimpleEntry<Node, Character> entry) {
		this.entry = entry;
	}
	
	public float getProbability() {
		return probability;
	}
	
	public void setProbability(float probability) {
		this.probability = probability;
	}
}
