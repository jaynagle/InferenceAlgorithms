/**
 * 
 */
package com.impl;

import java.util.List;
import java.util.Map;

import com.model.BayesNet;
import com.model.Node;

/**
 * @author Ankit Sadana, Jay Nagle
 *
 */
public abstract class InferenceUtil {
	protected BayesNet alarmNet;
	protected Map<Node, String> evidenceMap;
	protected List<Node> queryList;
	protected List<int[][]> sampleList;

	public InferenceUtil(BayesNet alarmNet, Map<Node, String> evidenceMap, List<Node> queryList,
			List<int[][]> sampleList) {
		this.alarmNet = alarmNet;
		this.evidenceMap = evidenceMap;
		this.queryList = queryList;
		this.sampleList = sampleList;
	}

	public abstract void infer();
}
