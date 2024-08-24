package org.silverbullet;

import java.util.ArrayList;
import java.util.List;

import org.silverbullet.dto.TestPointProgressDto;
import org.silverbullet.entity.NodeEntity;
import org.silverbullet.repository.NodeRepository;
import org.silverbullet.repository.TestItemRepository;

import lombok.Getter;

@Getter
public class TestItemSummaryCollector {

	private List<TestPointProgressDto> progresses = new ArrayList<>();
	
	public TestItemSummaryCollector(NodeRepository nodeRepository, TestItemRepository testItemRepository, Long nodeid) {

		NodeEntity node = nodeRepository.findById(nodeid).get();
	
//		{
//			TestItemCollector collector = new TestItemCollector(nodeRepository, testItemRepository, node.getId());
//			TestPointProgressDto progress = collector.progress();
//			progresses.add(progress);
//		}
		
		for (NodeEntity subNode : node.getChildren()) {
//			List<TestPointProgressDto> progresses = new ArrayList<>();
//			lookInto(subNode, progresses);
			
			TestItemCollector collector = new TestItemCollector(nodeRepository, testItemRepository, subNode.getId());
			TestPointProgressDto progress = collector.progress();
			progresses.add(progress);
		}
		
		

	}
	private void lookInto(NodeEntity node, List<TestPointProgressDto> progresses2) {
		
		
		for (NodeEntity subNode : node.getChildren()) {
			
			lookInto(subNode, progresses);
		}
	}
}
