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
public class RejectionSampling extends InferenceUtil {

	public RejectionSampling(BayesNet alarmNet, Map<Node, String> evidenceMap, List<Node> queryList,
			List<int[][]> sampleList) {
		super(alarmNet, evidenceMap, queryList, sampleList);
	}

	@Override
	public void infer() {

	}

}
