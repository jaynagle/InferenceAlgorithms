/**
 * 
 */
package com.impl;

import java.util.List;
import java.util.Map;

import com.model.BayesNet;
import com.model.Node;

/**
 * @author hp
 *
 */
public class LikelihoodWeighting extends InferenceUtil {

	public LikelihoodWeighting(BayesNet alarmNet, Map<Node, String> evidenceMap, List<Node> queryList,
			int[][] sampleArray) {
		super(alarmNet, evidenceMap, queryList, sampleArray);
	}

	@Override
	public void infer() {

	}

}
