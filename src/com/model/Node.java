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
public class Node {

	private String name;
	private List<Node> parents;
	private List<Node> children;

	public Node(String name) {
		this.name = name;
		parents = new ArrayList<>();
		children = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void addParent(Node node) {
		if (!this.parents.contains(node))
			this.parents.add(node);
	}

	public void addChild(Node node) {
		if (!this.children.contains(node))
			this.children.add(node);
	}
}
