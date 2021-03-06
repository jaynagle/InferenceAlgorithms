package com.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * NodePrability class stores the probabilities in accordance with parents.
 * 
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class NodeProbability {

	// To store values like <Node A, T> 
	private HashMap<Node, String> parentEntry;
	// To store the corresponding probability
	private float probability;
	
	/**
	 * Default constructor
	 */
	public NodeProbability() {
		parentEntry = new HashMap<>();
		probability = 0;
	}
	
	/**
	 * Parameterized constructor for NodeProbability
	 * 
	 * @param parentEntry
	 * @param probability
	 */
	public NodeProbability(HashMap<Node, String> parentEntry, 
			float probability) {
		this.setParentEntry(parentEntry);
		this.setProbability(probability);
	}
	
	// Debugging function to display all probabilities
	@Override
	public String toString() {
		StringBuilder parentEntryString = new StringBuilder();
		if (parentEntry != null) {
			Iterator iteratorObj = parentEntry.entrySet().iterator();
		    while (iteratorObj.hasNext()) {
		        Entry<Node, String> pair = (Entry<Node, String>) iteratorObj.next();
		        parentEntryString.append(pair.getKey().getNodeName() + "=" + pair.getValue() + "\t");
		    }	
		} else {
			parentEntryString.append("null\t");
		}
		return "[" + parentEntryString.toString() + "Pr=" + probability + "]";
	}
	
	public HashMap<Node, String> getParentEntry() {
		return parentEntry;
	}

	public void setParentEntry(HashMap<Node, String> entry) {
		this.parentEntry = entry;
	}
	
	public float getProbability() {
		return probability;
	}
	
	public void setProbability(float probability) {
		this.probability = probability;
	}
}
