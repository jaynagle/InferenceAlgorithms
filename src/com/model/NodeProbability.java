/**
 * 
 */
package com.model;

import java.util.Map.Entry;

/**
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class NodeProbability {

	private Entry<Node, Character> entry;
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
	public NodeProbability(Entry<Node, Character> entry, float probability) {
		this.setEntry(entry);
		this.setProbability(probability);
	}
	
	public Entry<Node, Character> getEntry() {
		return entry;
	}

	public void setEntry(Entry<Node, Character> entry) {
		this.entry = entry;
	}
	
	public float getProbability() {
		return probability;
	}
	
	public void setProbability(float probability) {
		this.probability = probability;
	}
}
