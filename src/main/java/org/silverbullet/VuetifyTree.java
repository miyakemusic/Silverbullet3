package org.silverbullet;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VuetifyTree {

	private Long id;
	private String title;
	private String file;
	private List<VuetifyTree> children = null;
	public void addChild(VuetifyTree childTreeNode) {
		if (this.children == null) {
			this.children = new ArrayList<VuetifyTree>();
		}
		this.children.add(childTreeNode);
	}
}
