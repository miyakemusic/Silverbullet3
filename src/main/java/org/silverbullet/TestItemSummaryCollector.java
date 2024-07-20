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
		
		for (NodeEntity subNode : node.getChildren()) {
			TestItemCollector collector = new TestItemCollector(nodeRepository, testItemRepository, subNode.getId());
			TestPointProgressDto progress = collector.progress();
			progresses.add(progress);
		}
		
	}

}
