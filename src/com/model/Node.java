package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Node class represents each node in the BayesNet.
 * 
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class Node {

	private String nodeName;
	private List<Node> parents;
	private List<Node> children;
	private List<NodeProbability> nodeProbabilities;

	/**
	 * Constructor to initialize a blank node with a name.
	 */
	public Node(String nodeName) {
		this.setNodeName(nodeName);
		parents = new ArrayList<>();
		children = new ArrayList<>();
		nodeProbabilities = new ArrayList<>();
	}

	public void addParent(Node node) {
		if (!this.parents.contains(node))
			this.parents.add(node);
	}

	public void addChild(Node node) {
		if (!this.children.contains(node))
			this.children.add(node);
	}

	/**
	 * addNodeProbability, takes entries of parents and their values
	 * like <B, t> <E, f> in form of a HashMap and stores their 
	 * corresponding probability in form of a NodeProability class object. 
	 */
	public void addNodeProbability(HashMap<Node, String> parentEntry, 
			float probability) {
		this.nodeProbabilities.add(new NodeProbability(parentEntry, probability));
	}

	/**
	 * getProbability populates the NodeProbability passed as an argument.
	 * This assumes that the passed object contains a HashMap, whose
	 * corresponding probability needs to be retrieved from the BayesNet.
	 */
	public float getProbability(NodeProbability nodeProbabilityObj) {
		for(NodeProbability nodeProbability : nodeProbabilities) {
		if (nodeProbability.getParentEntry() == null) {
			return nodeProbability.getProbability();
		} else if(nodeProbabilityObj.getParentEntry().entrySet().equals(
					nodeProbability.getParentEntry().entrySet())) {
				return nodeProbability.getProbability();
			}
		}
		return 0;
	}
	
	// showNode is a function used to display components of the current node.
	// for debugging
	public void showNode() {
		StringBuilder parentString = new StringBuilder();
		StringBuilder childString = new StringBuilder();
		
		for(Node node: parents) {
			parentString.append(node.getNodeName() + " ");
		}
		for(Node node: children) {
			childString.append(node.getNodeName() + " ");
		}
		System.out.println("Node: " + nodeName + 
				"\t\tParents: " + parentString.toString() + 
				"\tChildren: " + childString.toString());
	}
	
	// showNodeProbabilities is a function used to display each node's
	// probabilities. for debugging.
	public void showNodeProbabilities() {
		for(NodeProbability nodeProbability : nodeProbabilities) {
			System.out.println(nodeProbability.toString());
		}
	}
	
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public List<Node> getParents() {
		return parents;
	}

	public void setParents(List<Node> parents) {
		this.parents = parents;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public List<NodeProbability> getNodeProbabilities() {
		return nodeProbabilities;
	}

	public void setNodeProbabilities(List<NodeProbability> nodeProbabilities) {
		this.nodeProbabilities = nodeProbabilities;
	}

}
