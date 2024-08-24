package org.silverbullet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.silverbullet.dto.MopDto;
import org.silverbullet.dto.TestItemDto;
import org.silverbullet.dto.TestPointProgressDto;
import org.silverbullet.entity.NodeEntity;
import org.silverbullet.entity.TestItemEntity;
import org.silverbullet.entity.TestStatus;
import org.silverbullet.mapper.JsonTestItemConverter;
import org.silverbullet.repository.NodeRepository;
import org.silverbullet.repository.TestItemRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@Getter
public class TestItemCollector {

	private List<TestItemDto> testItems = new ArrayList<>();
//	private TestItemRepository testItemRepository;
	private NodeEntity rootNode;
	
	private JsonTestItemConverter testItemConverter = new JsonTestItemConverter();
	
	public TestItemCollector(NodeRepository nodeRepository, TestItemRepository testItemRepository, Long nodeid) {
//		this.testItemRepository = testItemRepository;
		rootNode = nodeRepository.findById(nodeid).get();
		
		colleceRecursive(rootNode);
	}


	private void colleceRecursive(NodeEntity node) {
		if (node.getType().equals("dut")) {
			
			if (node.getMop() == null) {
				return;
			}
//		System.out.println(node.getName());
			List<TestItemEntity> ts = testItemConverter.testItems(node.getMop().getJson());// testItemRepository.findByNode_id(node.getId());
			ts.forEach(t -> testItems.add(TestItemDto.builder()
					.node(node.getName())
					.name(t.getName())
					.criteria(t.getCriteria())
	//				.key(t.getKey())
					.result(t.getResult())
					.status(t.getStatus().name())
					.tool(t.getTool())
					.build()));
		}
		else {
			
			for (NodeEntity c : node.getChildren()) {
				colleceRecursive(c);
			}
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
