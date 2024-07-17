package org.silverbullet;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.silverbullet.dto.AlarmDto;
import org.silverbullet.dto.CostDto;
import org.silverbullet.dto.ProgressHistoryDto;
import org.silverbullet.dto.NodeSummaryDto;
import org.silverbullet.dto.ProgressDto;
import org.silverbullet.dto.ProgressDto.Type;
import org.silverbullet.dto.ScheduleDto;
import org.silverbullet.dto.TestItemDto;
import org.silverbullet.dto.TestPointProgressDto;
import org.silverbullet.entity.NodeEntity;
import org.silverbullet.entity.ProjectEntity;
import org.silverbullet.entity.TestItemEntity;
import org.silverbullet.entity.TestStatus;
import org.silverbullet.dto.ProjectSummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project/v1")
public class ProjectRestController {

	@Autowired
	private NodeRepository nodeRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TestItemRepository testItemRepository;
	
	@GetMapping("/nodeInfo/{nodeid}")
	public String nodeInfo(Principal principal, @PathVariable("nodeid") Long nodeid) {
		NodeEntity nodeEntity = nodeRepository.findById(nodeid).get();
		
		return nodeEntity.getName();
	}
	
	@GetMapping("/initTree")
	public String initTree(Principal princiapl) {
		
		// Fiber Construction
		for (int p = 0; p < 2; p++) {	
			NodeEntity root = NodeEntity.builder().type("project").name("Fiber Construction#" + String.format("%02d", p)).build();
			nodeRepository.save(root);
			
			ProjectEntity project = ProjectEntity.builder()
					.node(root)
					.type("Construction")
					.name("FTTH"+ p).description("FTTH Construction")
					.start(new Date()).deadline(new Date())
					.cost_unit("$").budget(1000).expenditure(100)		
					.build();
			
			projectRepository.save(project);		
						
			for (int i = 0; i < 2; i++) {
				NodeEntity cable = NodeEntity.builder().type("node").name("Cable#" + String.format("%03d", i)).parentNode(root).build();
				nodeRepository.save(cable);

				//project.setNode(cable);
				projectRepository.save(project);
				
		//		List<String> testName = Arrays.asList("FIP@Otemachi", );
				for (int j = 0; j < 16; j++) {
					NodeEntity fiber = NodeEntity.builder().type("dut").name("Fiber#" + String.format("%03d", j)).parentNode(cable).build();
					nodeRepository.save(fiber);

					int max = (int)(Math.random() * 10) + 1;
					for (int k = 0; k < max; k++) {
						TestItemEntity testItem = TestItemEntity.builder().name("TestItem#" + k).node(fiber).status(randStatus()).build();
						testItemRepository.save(testItem);
					}
					
//					nodeRepository.save(NodeEntity.builder().type("testPoint").name("Port A").parentNode(fiber).build());
//					nodeRepository.save(NodeEntity.builder().type("testPoint").name("Port B").parentNode(fiber).build());
//					nodeRepository.save(NodeEntity.builder().type("testPoint").name("Link A->B").parentNode(fiber).build());
//					nodeRepository.save(NodeEntity.builder().type("testPoint").name("Link B->A").parentNode(fiber).build());
//					nodeRepository.save(NodeEntity.builder().type("testPoint").name("Link").parentNode(fiber).build());
				}
			}
		}
		
		// Anntena Construction
		for (int p = 0; p < 2; p++) {
			NodeEntity root = NodeEntity.builder().type("project").name("FTTA Construction#" + String.format("%02d", p)).build();
			nodeRepository.save(root);

			ProjectEntity project = ProjectEntity.builder()
					.node(root)
					.type("Construction")
					.name("FTTA"+ p).description("FTTA Construction")
					.start(new Date()).deadline(new Date())
					.cost_unit("$").budget(1000).expenditure(100)		
					.build();
			
			projectRepository.save(project);
			
			NodeEntity fronthaulFiber = NodeEntity.builder().type("dut").name("Fronthaul").build();
			nodeRepository.save(fronthaulFiber);
			
//			nodeRepository.save(NodeEntity.builder().type("testPoint").name("Port A").parentNode(fronthaulFiber).build());
//			nodeRepository.save(NodeEntity.builder().type("testPoint").name("Port B").parentNode(fronthaulFiber).build());
//			nodeRepository.save(NodeEntity.builder().type("testPoint").name("Link A->B").parentNode(fronthaulFiber).build());
//			nodeRepository.save(NodeEntity.builder().type("testPoint").name("Link B->A").parentNode(fronthaulFiber).build());
//			nodeRepository.save(NodeEntity.builder().type("testPoint").name("Link").parentNode(fronthaulFiber).build());
			
			List<String> dutName = Arrays.asList("Upstream", "Downstream");
			
			NodeEntity ftta = NodeEntity.builder().type("node").name("Anntena").parentNode(root).build();
			nodeRepository.save(ftta);
			
			for (int i = 0; i < dutName.size(); i++) {
				NodeEntity fiber = NodeEntity.builder().type("dut").name(dutName.get(i)).parentNode(ftta).build();
							
				nodeRepository.save(fiber);
				
				for (int k = 0; k < 4; k++) {
					TestItemEntity testItem = TestItemEntity.builder().name("TestItem#" + k).node(fiber).status(randStatus()).build();
					testItemRepository.save(testItem);
				}

//				nodeRepository.save(NodeEntity.builder().type("testPoint").name("Port A").parentNode(fiber).build());
//				nodeRepository.save(NodeEntity.builder().type("testPoint").name("Port B").parentNode(fiber).build());
//				nodeRepository.save(NodeEntity.builder().type("testPoint").name("Link A->B").parentNode(fiber).build());
//				nodeRepository.save(NodeEntity.builder().type("testPoint").name("Link B->A").parentNode(fiber).build());
//				nodeRepository.save(NodeEntity.builder().type("testPoint").name("Link").parentNode(fiber).build());
			}
//			nodeRepository.save(root);
		}
		
		// Monitoring
		for (int p = 0; p < 3; p++) {	
			NodeEntity root = NodeEntity.builder().type("project").name("Fiber Construction#" + String.format("%02d", p)).build();
			nodeRepository.save(root);
			
			ProjectEntity project = ProjectEntity.builder()
					.node(root)
					.type("Monitoring")
					.name("MON"+ p).description("Monitoring")
					.start(new Date()).deadline(new Date())
					.cost_unit("$").budget(1000).expenditure(100)		
					.build();
			
			projectRepository.save(project);		

			for (int i = 0; i < 2; i++) {
				NodeEntity cable = NodeEntity.builder().type("node").name("Cable#" + String.format("%03d", i)).parentNode(root).build();
				nodeRepository.save(cable);

				//project.setNode(cable);
				projectRepository.save(project);
				
				for (int j = 0; j < 8; j++) {
					NodeEntity fiber = NodeEntity.builder().type("dut").name("Fiber#" + String.format("%03d", j)).parentNode(cable).build();
					nodeRepository.save(fiber);

					for (int k = 0; k < 4; k++) {
						TestItemEntity testItem = TestItemEntity.builder().name("TestItem#" + k).node(fiber).status(randStatus()).build();
						testItemRepository.save(testItem);
					}
//					nodeRepository.save(NodeEntity.builder().type("testPoint").name("Port A").parentNode(fiber).build());
//					nodeRepository.save(NodeEntity.builder().type("testPoint").name("Port B").parentNode(fiber).build());
//					nodeRepository.save(NodeEntity.builder().type("testPoint").name("Link A->B").parentNode(fiber).build());
//					nodeRepository.save(NodeEntity.builder().type("testPoint").name("Link B->A").parentNode(fiber).build());
//					nodeRepository.save(NodeEntity.builder().type("testPoint").name("Link").parentNode(fiber).build());
				}
			}
		}
		return "OK";
	}
	
	private TestStatus randStatus() {
		Random rand = new Random();
	    int num = rand.nextInt(3);
	    
	    TestStatus s = TestStatus.values()[num];
	    
		return s;
	}

	@GetMapping("/tree/{id}")
	public List<VuetifyTree> vuetifyTree(Principal princiapl, @PathVariable("id") String id) {
		NodeEntity rootNode = projectRepository.findById(Long.valueOf(id)).get().getNode();
//		List<NodeEntity> root = projectRepository.findAll().stream().map(p -> p.getNode()).collect(Collectors.toList());
		//List<NodeEntity> root = nodeRepository.findByType("project");
		List<VuetifyTree> ret = new TreeConverter().convertTreeNode(rootNode.getChildren());
		
		return ret;
	}
	
//	@DeleteMapping("/v1/tree/{id}")
//	public void deleteNode(Principal princiapl, @PathVariable("id") Long id) {
//		nodeRepository.deleteById(id);
//	}
	
	@DeleteMapping("/treeAll")
	public String deleteNode(Principal princiapl) {
		projectRepository.deleteAll();
		nodeRepository.deleteAll();
		
		return "OK";
	}
	
	@GetMapping("/projectSummary/{type}")
	public List<ProjectSummaryDto> getConstructionSummary(Principal princiapl, @PathVariable("type") String type) {
//		return projectRepository.findByType(type).stream().map(p -> ProjectSummaryDto.builder().build()).collect(Collectors.toList());
		
		return projectRepository.findByType(type).stream()
				.map(p -> ProjectSummaryDto.builder()
						.id(p.getId()).name(p.getName()).description(p.getDescription())
						.schedule(ScheduleDto.builder().start(p.getStart()).deadline(p.getDeadline()).build())
						.progress(ProgressDto.builder().type(ProgressDto.Type.delay).days(4).build())
						.cost(CostDto.builder().budget(p.getBudget()).expenditure(p.getExpenditure()).build())
						.alarm(AlarmDto.builder().type(AlarmDto.Type.alarm).message("Throughput").build())
						.build())
				.collect(Collectors.toList());
		
	}

	@GetMapping("/nodeSummary/{nodeid}")
	public NodeSummaryDto nodeDtail(Principal princiapl, @PathVariable("nodeid") Long nodeid) {
		NodeEntity node = nodeRepository.findById(nodeid).get();
		TestItemCollector collector = new TestItemCollector(nodeRepository, testItemRepository, nodeid);
		List<TestItemDto> testItems = collector.getTestItems();
		
		return NodeSummaryDto.builder()
				.name(node.getName())
				.testPointProgress(	collector.progress())
				.testItems(testItems)
//				.testItems(testItems.stream().map(
//						t -> TestItemDto.builder().id(t.getId()).name(t.getName()).status(t.getStatus().name()).node(t.getNode()).build())
//						.collect(Collectors.toList()))
				.build();
	}
	


	@GetMapping("/project/{id}")
	public ProjectSummaryDto getProject(Principal princiapl, @PathVariable("id") Long id) {		
		return projectRepository.findById(id).map(
				p -> ProjectSummaryDto.builder()
				.id(p.getId()).name(p.getName()).description(p.getDescription())
				.schedule(ScheduleDto.builder().start(p.getStart()).deadline(p.getDeadline()).build())
				.progress(ProgressDto.builder().type(ProgressDto.Type.delay).days(4).build())
				.cost(CostDto.builder().budget(p.getBudget()).expenditure(p.getExpenditure()).build())
				.alarm(AlarmDto.builder().type(AlarmDto.Type.alarm).message("Throughput").build())
				.build()).get();
	}
	
	
	@GetMapping("/progress")
	public TestPointProgressDto testItemProgress(Principal princiapl, @RequestParam(required=false, name="projectid") Long projectid, @RequestParam(required=false, name="nodeid") Long nodeid) {
		if (nodeid == null) {
			nodeid = this.projectRepository.findById(projectid).get().getNode().getId();
		}
		TestItemCollector collector = new TestItemCollector(nodeRepository, testItemRepository, nodeid);

		return collector.progress();
	}
	
	@GetMapping("/progress/date")
	public ProgressDto testItemProgressDate(Principal princiapl, @RequestParam(required=false, name="projectid") Long projectid, @RequestParam(required=false, name="nodeid") Long nodeid) {
		if (nodeid == null) {
			nodeid = this.projectRepository.findById(projectid).get().getNode().getId();
		}
		return ProgressDto.builder().days(1).deadline(new Date()).estimatedCompletionDate(new Date()).startDate(new Date()).type(Type.delay).build();
	}
	
	@GetMapping("/progress/item/history")
	public ProgressHistoryDto progressItemHistory(Principal princiapl, @RequestParam(required=false, name="projectid") Long projectid, @RequestParam(required=false, name="nodeid") Long nodeid) {
		if (nodeid == null) {
			nodeid = this.projectRepository.findById(projectid).get().getNode().getId();
		}
		return ProgressHistoryDto.builder()
				.time(Arrays.asList("2024/1", "2024/2", "2024/3", "2024/4", "2024/5", "2024/6", "2024/7", "2024/8", "2024/9", "2024/10", "2024/11", "2024/12"))
				.plan(Arrays.asList(900, 800, 700, 600, 500, 400, 300, 200, 100, 0))
				.actual(Arrays.asList(900, 800, 750, 660, 570, 490))
				.build();
	}
	
	@GetMapping("/progress/cost/history")
	public ProgressHistoryDto progressCostHistory(Principal princiapl, @RequestParam(required=false, name="projectid") Long projectid, @RequestParam(required=false, name="nodeid") Long nodeid) {
		if (nodeid == null) {
			nodeid = this.projectRepository.findById(projectid).get().getNode().getId();
		}
		return ProgressHistoryDto.builder()
				.time(Arrays.asList("2024/1", "2024/2", "2024/3", "2024/4"))
				.plan(Arrays.asList(100, 200, 300, 400))
				.actual(Arrays.asList(110, 230, 330, 440))
				.build();
	}
	
	@GetMapping("/testItemSummary")
	public List<TestPointProgressDto> testItesmSummary(Principal princiapl, @RequestParam(required=false, name="projectid") Long projectid, @RequestParam(required=false, name="nodeid") Long nodeid) {
		if (nodeid == null) {
			nodeid = this.projectRepository.findById(projectid).get().getNode().getId();
		}
		TestItemSummaryCollector collector = new TestItemSummaryCollector(nodeRepository, testItemRepository, nodeid);

		return collector.getProgresses();
	}
}
