package org.silverbullet;

import java.util.ArrayList;
import java.util.List;

import org.silverbullet.dto.TestItemDto;
import org.silverbullet.dto.TestPointProgressDto;
import org.silverbullet.entity.NodeEntity;
import org.silverbullet.entity.TestStatus;

import lombok.Getter;

@Getter
public class TestItemCollector {

	private List<TestItemDto> testItems = new ArrayList<>();
	private TestItemRepository testItemRepository;
	private NodeEntity rootNode;
	
	public TestItemCollector(NodeRepository nodeRepository, TestItemRepository testItemRepository, Long nodeid) {
		this.testItemRepository = testItemRepository;
		rootNode = nodeRepository.findById(nodeid).get();
		
		colleceRecursive(rootNode);
	}


	private void colleceRecursive(NodeEntity node) {
		testItemRepository.findByNode_id(node.getId()).forEach(t -> testItems.add(TestItemDto.builder().id(t.getId()).node(node.getName()).name(t.getName()).status(t.getStatus().name()).build()));
		for (NodeEntity c : node.getChildren()) {
			colleceRecursive(c);
		}
	}
	
	public TestPointProgressDto progress() {

		TestPointProgressDto ret = TestPointProgressDto.builder()
				.node(rootNode.getName())
				.rest(testItems.stream().filter(t -> t.getStatus().equals(TestStatus.NOT_YET.name())).count())
				.pass(testItems.stream().filter(t -> t.getStatus().equals(TestStatus.PASS.name())).count())
				.fail(testItems.stream().filter(t -> t.getStatus().equals(TestStatus.FAIL.name())).count())
				.total(testItems.stream().count())
				.build();
		
		
		return ret;
	}
}