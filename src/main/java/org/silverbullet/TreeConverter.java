package org.silverbullet;

import java.util.ArrayList;
import java.util.List;

import org.silverbullet.entity.NodeEntity;



public class TreeConverter {
	
	public List<VuetifyTree> convertTreeNode(List<NodeEntity> nodes) {
		List<VuetifyTree> ret = new ArrayList<>();
		for (NodeEntity node : nodes) {
			ret.add(convertTreeNode(node));
		}
		
		return ret;
	}
	
	public VuetifyTree convertTreeNode(NodeEntity node) {
		VuetifyTree rootTree = VuetifyTree.builder()
				.id(node.getId())
				//.file(node.getClass().getSimpleName())
				.file(node.getType())
//				.children(new ArrayList<VuetifyTree>())
				.title(node.getName()).build();
		recursive(node, rootTree);
		return rootTree;
	}

	private void recursive(NodeEntity node, VuetifyTree parentTree) {
		//TreeNode childTree = TreeNode.builder().id("folder_" + node.getId()).text(node.getName()).children(new ArrayList<TreeNode>()).build();
		//rootTree.getChildren().add(childTree);
		for (NodeEntity child : node.getChildren()) {
			VuetifyTree childTreeNode = VuetifyTree.builder()
					.id(child.getId())
					.file(child.getType())
					//.file(child.getClass().getSimpleName())
					.title(child.getName())
					//.children(new ArrayList<VuetifyTree>())
					.build();
			
			parentTree.addChild(childTreeNode);
			recursive(child, childTreeNode);
		}
//		if (rootTree.getChildren().size() == 0) {
//			rootTree.setChildren(null);
//		}
	}
}
