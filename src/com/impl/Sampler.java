package com.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.model.BayesNet;
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
	private int[][] generateSample(BayesNet bayesNet, int[][] sampleArray) {

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
	 * This method creates a list of different sample sizes as specified in the
	 * question.
	 * 
	 * @param bayesNet
	 * @return
	 */
	public List<int[][]> getSamples(BayesNet bayesNet) {
		int[] sampleSizeArray = { 10, 50, 100, 200, 500, 1000, 10000 };
		List<int[][]> sampleList = new ArrayList<>();

		for (int i : sampleSizeArray) {
			sampleList.add(generateSample(bayesNet, new int[i][5]));
		}
		return sampleList;
	}
	
	/**
	 * This method creates samples based on the given size
	 * 
	 * @param bayesNet			Bayes Net created in Launcher
	 * @param numberOfSamples	Number of samples passed as an argument by the user
	 * 
	 * @return Sample array
	 */
	public int[][] getSamples(BayesNet bayesNet, int numberOfSamples) {
		int[][] samples = generateSample(bayesNet, new int[numberOfSamples][5]);
		
		return samples;
	}

}
