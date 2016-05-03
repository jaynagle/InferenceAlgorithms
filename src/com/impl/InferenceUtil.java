/**
 * 
 */
package com.impl;

import java.util.List;
import java.util.Map;

import com.model.BayesNet;
import com.model.Node;

/**
 * InferenceUtil is the base class with common variables and
 * constructor that are inherited by other sampling methods.
 * 
 * @author Ankit Sadana, Jay Nagle
 *
 */
public abstract class InferenceUtil {
	protected BayesNet alarmNet;
	protected Map<Node, String> evidenceMap;
	protected List<Node> queryList;
	protected int[][] sampleArray;

	public InferenceUtil(BayesNet alarmNet, Map<Node, String> evidenceMap, List<Node> queryList,
			int[][] sampleArray) {
		this.alarmNet = alarmNet;
		this.evidenceMap = evidenceMap;
		this.queryList = queryList;
		this.sampleArray = sampleArray;
	}

	public abstract void infer();
}
