/**
 * 
 */
package com.impl;

import java.util.List;
import java.util.Map;

import com.model.Node;

/**
 * @author hp
 *
 */
public class RejectionSampling extends InferenceUtil {

	public RejectionSampling(Map<Node, String> evidenceMap, List<Node> queryList, int[][] sampleArray) {
		super(evidenceMap, queryList, sampleArray);
	}

	@Override
	public void infer() {

	}

}