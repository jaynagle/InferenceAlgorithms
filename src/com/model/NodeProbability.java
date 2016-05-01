/**
 * 
 */
package com.model;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class NodeProbability {

	// To store values like <Node A, T> 
	private HashMap<Node, Character> parentEntry;
	// To store the corresponding probability
	private float probability;
	
	/**
	 * 
	 */
	public NodeProbability() {
		this.setParentEntry(new HashMap<>());
		this.setProbability(0);
	}
	
	/**
	 * Parameterized constructor for NodeProbability
	 * 
	 * @param parentEntry
	 * @param probability
	 */
	public NodeProbability(HashMap<Node, Character> parentEntry, 
			float probability) {
		this.setParentEntry(parentEntry);
		this.setProbability(probability);
	}
	
	@Override
	public String toString() {
		StringBuilder parentEntryString = new StringBuilder();
		
		Iterator iteratorObj = parentEntry.entrySet().iterator();
	    while (iteratorObj.hasNext()) {
	        HashMap.Entry pair = (HashMap.Entry)iteratorObj.next();
	        Node nodeObj = (Node) pair.getKey();
	        parentEntryString.append(nodeObj.getNodeName() + "=" + pair.getValue() + "\t");
	    }
	        
		return "[" + parentEntryString.toString() + ", probability=" + probability + "]";
	}
	
	public HashMap<Node, Character> getParentEntry() {
		return parentEntry;
	}

	public void setParentEntry(HashMap<Node, Character> entry) {
		this.parentEntry = entry;
	}
	
	public float getProbability() {
		return probability;
	}
	
	public void setProbability(float probability) {
		this.probability = probability;
	}
}
