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
public abstract class InferenceUtil {
	protected Map<Node, String> evidenceMap;
	protected List<Node> queryList;
	protected int[][] sampleArray;

	public InferenceUtil(Map<Node, String> evidenceMap, List<Node> queryList, int[][] sampleArray) {
		this.evidenceMap = evidenceMap;
		this.queryList = queryList;
		this.sampleArray = sampleArray;
	}

	public abstract void infer();
}
