package com.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import com.model.BayesNet;
import com.model.Node;
import com.model.NodeProbability;

/**
 * Sampler class takes a bayes net, and generates random samples to populate
 * the sampleArray.
 * 
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class Sampler {

	/**
	 * generateSample is a function that generates samples to 
	 * populate the passed int array for the passed BayesNet.
	 * 
	 * @param bayesNet
	 * @param sampleArray
	 * 
	 * @return
	 */
	public int[][] generateSamplesPrior(BayesNet bayesNet, int[][] sampleArray) {

		Random a_random = new Random();
		Random b_random = new Random();
		Random e_random = new Random();
		Random j_random = new Random();
		Random m_random = new Random();

		for (int i = 0; i < sampleArray.length; i++) {

			int a_sample = 0;
			int b_sample = 0;
			int e_sample = 0;
			int j_sample = 0;
			int m_sample = 0;

			// float a_cpt

			float a = a_random.nextFloat();
			float b = b_random.nextFloat();
			float e = e_random.nextFloat();
			float j = j_random.nextFloat();
			float m = m_random.nextFloat();

			NodeProbability nodeProbability;
			nodeProbability = new NodeProbability();

			if (b < bayesNet.getNodeByName("B").getProbability(nodeProbability)) {
				b_sample = 1;
			}

			if (e < bayesNet.getNodeByName("E").getProbability(nodeProbability)) {
				e_sample = 1;
			}

			nodeProbability = new NodeProbability();

			if (b_sample == 0 && e_sample == 0) {
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("B"), "f");
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("E"), "f");
			} else if (b_sample == 0 && e_sample == 1) {
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("B"), "f");
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("E"), "t");
			} else if (b_sample == 1 && e_sample == 0) {
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("B"), "t");
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("E"), "f");
			} else {
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("B"), "t");
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("E"), "t");
			}

			if (a < bayesNet.getNodeByName("A").getProbability(nodeProbability)) {
				a_sample = 1;
			}

			nodeProbability = new NodeProbability();

			if (a_sample == 0) {
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("A"), "f");
			} else {
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("A"), "t");
			}

			if (j < bayesNet.getNodeByName("J").getProbability(nodeProbability)) {
				j_sample = 1;
			}

			if (m < bayesNet.getNodeByName("M").getProbability(nodeProbability)) {
				m_sample = 1;
			}

			sampleArray[i] = new int[] { b_sample, e_sample, a_sample, j_sample, m_sample };
		}

		return sampleArray;
	}
	
	/**
	 * generateSamplesRejection is a function that generates samples 
	 * only which agree with the evidence to populate the passed int 
	 * array for the passed BayesNet.
	 * 
	 * @param bayesNet
	 * @param sampleArray
	 * 
	 * @return
	 */
	public int[][] generateSamplesRejection(
			BayesNet bayesNet, 
			Map<Node, String> evidenceMap, 
			int[][] sampleArray) {

		int b_evidence = -1;
		int e_evidence = -1;
		int a_evidence = -1;
		int j_evidence = -1;
		int m_evidence = -1;

		for (Entry<Node, String> entry : evidenceMap.entrySet()) {
			Node node = entry.getKey();
			String evidence = entry.getValue();

			switch (node.getNodeName()) {
			case "B":
				b_evidence = "t".equals(evidence) ? 1 : 0;
				break;
			case "E":
				e_evidence = "t".equals(evidence) ? 1 : 0;
				break;
			case "A":
				a_evidence = "t".equals(evidence) ? 1 : 0;
				break;
			case "J":
				j_evidence = "t".equals(evidence) ? 1 : 0;
				break;
			case "M":
				m_evidence = "t".equals(evidence) ? 1 : 0;
				break;

			default:
				break;
			}
		}
		
		Random a_random = new Random();
		Random b_random = new Random();
		Random e_random = new Random();
		Random j_random = new Random();
		Random m_random = new Random();

		for (int i = 0; i < sampleArray.length; i++) {

			int a_sample = 0;
			int b_sample = 0;
			int e_sample = 0;
			int j_sample = 0;
			int m_sample = 0;

			// float a_cpt

			float a = a_random.nextFloat();
			float b = b_random.nextFloat();
			float e = e_random.nextFloat();
			float j = j_random.nextFloat();
			float m = m_random.nextFloat();

			NodeProbability nodeProbability;
			nodeProbability = new NodeProbability();

			if (b < bayesNet.getNodeByName("B").getProbability(nodeProbability)) {
				b_sample = 1;
			}

			if (e < bayesNet.getNodeByName("E").getProbability(nodeProbability)) {
				e_sample = 1;
			}

			nodeProbability = new NodeProbability();

			if (b_sample == 0 && e_sample == 0) {
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("B"), "f");
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("E"), "f");
			} else if (b_sample == 0 && e_sample == 1) {
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("B"), "f");
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("E"), "t");
			} else if (b_sample == 1 && e_sample == 0) {
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("B"), "t");
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("E"), "f");
			} else {
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("B"), "t");
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("E"), "t");
			}

			if (a < bayesNet.getNodeByName("A").getProbability(nodeProbability)) {
				a_sample = 1;
			}

			nodeProbability = new NodeProbability();

			if (a_sample == 0) {
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("A"), "f");
			} else {
				nodeProbability.getParentEntry().put(bayesNet.getNodeByName("A"), "t");
			}

			if (j < bayesNet.getNodeByName("J").getProbability(nodeProbability)) {
				j_sample = 1;
			}

			if (m < bayesNet.getNodeByName("M").getProbability(nodeProbability)) {
				m_sample = 1;
			}

			if (b_evidence != -1 && b_sample != b_evidence)	continue;
			if (e_evidence != -1 && e_sample != b_evidence)	continue;
			if (a_evidence != -1 && a_sample != b_evidence)	continue;
			if (j_evidence != -1 && j_sample != b_evidence)	continue;
			if (m_evidence != -1 && m_sample != b_evidence)	continue;
			
			sampleArray[i] = new int[] { b_sample, e_sample, a_sample, j_sample, m_sample };
		}

		return sampleArray;
	}
}
