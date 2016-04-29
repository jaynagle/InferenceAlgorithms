package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class Node {

	private String nodeName;
	private List<Node> parents;
	private List<Node> children;

	public Node(String nodeName) {
		this.setNodeName(nodeName);
		parents = new ArrayList<>();
		children = new ArrayList<>();
	}

	public void addParent(Node node) {
		if (!this.parents.contains(node))
			this.parents.add(node);
	}

	public void addChild(Node node) {
		if (!this.children.contains(node))
			this.children.add(node);
	}

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
}
