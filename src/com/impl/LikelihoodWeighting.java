package com.impl;

import java.util.List;
import java.util.Map;

import com.model.BayesNet;
import com.model.Node;

/**
 * LikelihoodWeighting uses the weighted samples generated to infer the queries.
 * 
 * @author Ankit Sadana, Jay Nagle
 *
 */
public class LikelihoodWeighting extends InferenceUtil {

	protected float[] weightArray;
	float totalSampleWeight;

	public LikelihoodWeighting(BayesNet alarmNet, Map<Node, String> evidenceMap, List<Node> queryList,
			int[][] sampleArray, List<Object> result) {
		super(alarmNet, evidenceMap, queryList, sampleArray);
		this.sampleArray = (int[][]) result.get(0);
		this.weightArray = (float[]) result.get(1);
		this.totalSampleWeight = (float) result.get(2);
	}

	@Override
	public void infer() {

		for (Node node : queryList) {
			float queryWeight = 0f;

			for (int i = 0; i < sampleArray.length; i++) {
				switch (node.getNodeName()) {
				case "A":
					if (sampleArray[i][2] == 1) {
						queryWeight += weightArray[i];
					}
					break;
				case "B":
					if (sampleArray[i][0] == 1) {
						queryWeight += weightArray[i];
					}
					break;
				case "E":
					if (sampleArray[i][1] == 1) {
						queryWeight += weightArray[i];
					}
					break;
				case "J":
					if (sampleArray[i][3] == 1) {
						queryWeight += weightArray[i];
					}
					break;
				case "M":
					if (sampleArray[i][4] == 1) {
						queryWeight += weightArray[i];
					}
					break;
				default:
					break;
				}
			}
			System.out.println(node.getNodeName() + " " + queryWeight / totalSampleWeight);
		}
	}
}
