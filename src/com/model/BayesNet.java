package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * BayesNet is a class that stores and uses Node variables
 * to form a Baye's Net.
 * 
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class BayesNet {

	List<Node> nodes;

	public BayesNet() {
		nodes = new ArrayList<Node>();
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public void addNode(Node node) {
		if (!this.nodes.contains(node))
			this.nodes.add(node);
	}

	public Node getNodeByName(String name) {
		for (Node node : nodes) {
			if (name.equalsIgnoreCase(node.getNodeName()))
				return node;
		}
		System.out.println("ERROR: Node " + name + " not found.");
		return null;
	}
	
	// for debugging
	public void showBayesNet() {
		System.out.println("\nCurrent Bayes Net: \n");
		for(Node thisNode : nodes) {
			thisNode.showNode();
			thisNode.showNodeProbabilities();
		}
	}
}
