package com.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model.BayesNet;
import com.model.Node;
import com.model.NodeProbability;

/**
 * EnumerationInference uses the BayesNet and evidence map to
 * infer the exact probability for the queryList.
 * 
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
	
	/**
	 * This method computes the probability of every query variable using
	 * enumeration.
	 */
	public void enumerateAsk() {
		
		for (Node node : querList) {
			List<Node> bayesNodes = new ArrayList<>(alarmNet.getNodes());			
			HashMap<Node, String> tmpEvidenceMap = new HashMap<>();
			tmpEvidenceMap.putAll(evidenceMap);
			tmpEvidenceMap.put(node, "t");
			float probability_true = enumerateAll(bayesNodes, tmpEvidenceMap);
			
			bayesNodes = new ArrayList<>(alarmNet.getNodes());
			tmpEvidenceMap = new HashMap<>();
			tmpEvidenceMap.putAll(evidenceMap);
			tmpEvidenceMap.put(node, "f");
			float probability_false = enumerateAll(bayesNodes, tmpEvidenceMap);
			
			float normalized_prob = (probability_true * probability_false)/(probability_true + probability_false);
			System.out.println(node.getNodeName() + " " + normalized_prob * probability_true);
		}
	}


	/**
	 * This method creates enumerations for all the variables of provided
	 * Bayesian network.
	 * 
	 * @param remainingNodes
	 * @param givenEvidence
	 * @return
	 */
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
