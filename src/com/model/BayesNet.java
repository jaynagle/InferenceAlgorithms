/**
 * 
 */
package com.model;

import java.util.ArrayList;
import java.util.List;

/**
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
			if (name.equalsIgnoreCase(node.getName()))
				return node;
		}
		return null;
	}
}
