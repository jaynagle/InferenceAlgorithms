/**
 * 
 */
package com.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.model.BayesNet;
import com.model.Node;

/**
 * @author hp
 *
 */
public class RejectionSampling extends InferenceUtil {

	public RejectionSampling(BayesNet alarmNet, Map<Node, String> evidenceMap, List<Node> queryList,
			List<int[][]> sampleList) {
		super(alarmNet, evidenceMap, queryList, sampleList);
	}

	@Override
	public void infer() {
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
		for (Node queryNode : queryList) {
			for (int[][] sampleArray : sampleList) {
				int countQueryAndEvidenceSamples = 0;
				int countEvidenceSamples = 0;
				int sampleCount = 0;

				for (int[] sample : sampleArray) {

					boolean isEvidenceValid = true;
					boolean isQueryValid = true;

					int b_sample = sample[0];
					int e_sample = sample[1];
					int a_sample = sample[2];
					int j_sample = sample[3];
					int m_sample = sample[4];

					if (b_evidence != -1) {
						if (!(b_evidence == b_sample)) {
							isEvidenceValid = false;
						}
					}
					if (e_evidence != -1) {
						if (!(e_evidence == e_sample)) {
							isEvidenceValid = false;
						}
					}
					if (a_evidence != -1) {
						if (!(a_evidence == a_sample)) {
							isEvidenceValid = false;
						}
					}
					if (j_evidence != -1) {
						if (!(j_evidence == j_sample)) {
							isEvidenceValid = false;
						}
					}
					if (m_evidence != -1) {
						if (!(m_evidence == m_sample)) {
							isEvidenceValid = false;
						}
					}

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

					if (isEvidenceValid) {
						if (isQueryValid) {
							countQueryAndEvidenceSamples++;
						}
						countEvidenceSamples++;
						// Only count samples when Evidence is true
						sampleCount++;
					}
				}

//				System.out.println("REMOVE ME: " + sampleCount + "," + countQueryAndEvidenceSamples
//						+ "," + countEvidenceSamples);
				System.out.println(queryNode.getNodeName() + " "
						+ (countQueryAndEvidenceSamples * 1f / countEvidenceSamples));
			}
		}
	}
}