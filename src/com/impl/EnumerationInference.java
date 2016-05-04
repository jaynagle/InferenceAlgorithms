/**
 * 
 */
package com.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.BayesNet;
import com.model.Node;
import com.model.NodeProbability;

/**
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class EnumerationInference {

	private BayesNet alarmNet;
	private HashMap<Node, String> evidenceMap;
	private List<Node> querList;
	
	public EnumerationInference(BayesNet alarmNet, HashMap<Node, String> evidenceMap, List<Node> queryList) {
		this.alarmNet = alarmNet;
		this.evidenceMap = evidenceMap;
		this.querList = queryList;
	}
	
	public void enumerateAsk() {
		
		for (Node node : querList) {
			List<Node> bayesNodes = alarmNet.getNodes();
			HashMap<Node, String> tmpEvidenceMap = new HashMap<>();
			tmpEvidenceMap.putAll(evidenceMap);
			tmpEvidenceMap.put(node, "t");
			float probability = enumerateAll(bayesNodes, tmpEvidenceMap);
			System.out.println(node.getNodeName() + " " + probability);
		}
	}


	public float enumerateAll(List<Node> remainingNodes, HashMap<Node, String> givenEvidence) {
		if (remainingNodes.isEmpty()) return 1f;
		
		Node node = remainingNodes.get(0);
		remainingNodes.remove(0);
		
		NodeProbability nodeProbability = new NodeProbability();
		for (Node parent : node.getParents()) {
			nodeProbability.getParentEntry().put(parent, givenEvidence.get(parent));
		}
		
		if (givenEvidence.containsKey(node)) {	
			return node.getProbability(nodeProbability) * enumerateAll(remainingNodes, givenEvidence);
		} else {
			HashMap<Node, String> trueMap = new HashMap<>();
			trueMap.putAll(givenEvidence);
			trueMap.put(node, "t");

			HashMap<Node, String> falseMap = new HashMap<>();
			falseMap.putAll(givenEvidence);
			falseMap.put(node, "f");
			
			return (node.getProbability(nodeProbability) * enumerateAll(remainingNodes, trueMap)) 
					+ ((1f - node.getProbability(nodeProbability)) * enumerateAll(remainingNodes, falseMap));
		}
	}
}
