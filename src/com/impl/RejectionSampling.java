package com.impl;

import java.util.List;
import java.util.Map;
import com.model.BayesNet;
import com.model.Node;

/**
 * RejectionSampling uses the random samples generated to inference the queries
 * by only selecting those which agree with the given evidence.
 * 
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class RejectionSampling extends InferenceUtil {

	public RejectionSampling(BayesNet alarmNet, Map<Node, String> evidenceMap, List<Node> queryList,
			int[][] sampleArray) {
		super(alarmNet, evidenceMap, queryList, sampleArray);
	}

	/**
	 * infer is used to inference the queries using the samples provided by the
	 * sampler.
	 */
	@Override
	public void infer() {

		// Traversing the query list
		for (Node queryNode : queryList) {

			int countQueryAndEvidenceSamples = 0;

			int sampleCount = 0;

			// Traversing the sampleArray
			for (int[] sample : sampleArray) {

				boolean isQueryValid = true;

				int b_sample = sample[0];
				int e_sample = sample[1];
				int a_sample = sample[2];
				int j_sample = sample[3];
				int m_sample = sample[4];

				// Checking if query param is true in samples
				switch (queryNode.getNodeName()) {
				case "B":
					if (b_sample != 1)
						isQueryValid = false;
					break;
				case "E":
					if (e_sample != 1)
						isQueryValid = false;
					break;
				case "A":
					if (a_sample != 1)
						isQueryValid = false;
					break;
				case "J":
					if (j_sample != 1)
						isQueryValid = false;
					break;
				case "M":
					if (m_sample != 1)
						isQueryValid = false;
					break;

				default:
					break;
				}

				if (isQueryValid) {
					// Incrementing when query and evidence is true
					countQueryAndEvidenceSamples++;
				}
				// Counting the number of samples
				sampleCount++;

			}

			// System.out.println("REMOVE ME: " + sampleCount + "," +
			// countQueryAndEvidenceSamples
			// + "," + countEvidenceSamples);

			System.out.println(queryNode.getNodeName() + " " + (countQueryAndEvidenceSamples * 1f / sampleCount));
		}

	}
}