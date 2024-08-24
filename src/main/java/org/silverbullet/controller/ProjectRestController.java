package org.silverbullet.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.silverbullet.TestItemCollector;
import org.silverbullet.TestItemSummaryCollector;
import org.silverbullet.TreeConverter;
import org.silverbullet.VuetifyTree;
import org.silverbullet.dto.AlarmDto;
import org.silverbullet.dto.CostDto;
import org.silverbullet.dto.ImageDto;
import org.silverbullet.dto.MopDto;
import org.silverbullet.dto.MopLineDto;
import org.silverbullet.dto.MopSectionDto;
import org.silverbullet.dto.MopSummary;
import org.silverbullet.dto.NodeDto;
import org.silverbullet.dto.ProgressHistoryDto;
import org.silverbullet.dto.NodeDetailDto;
import org.silverbullet.dto.PairDto;
import org.silverbullet.dto.ProgressDto;
import org.silverbullet.dto.ProgressDto.Type;
import org.silverbullet.dto.ScheduleDto;
import org.silverbullet.dto.TestItemDto;
import org.silverbullet.dto.TestPointProgressDto;
import org.silverbullet.entity.MopEntity;
import org.silverbullet.entity.NodeEntity;
import org.silverbullet.entity.ProjectEntity;
import org.silverbullet.entity.TestItemEntity;
import org.silverbullet.entity.TestStatus;
import org.silverbullet.mapper.JsonTestItemConverter;
import org.silverbullet.mapper.MopMapper;
import org.silverbullet.mapper.NodeMapper;
import org.silverbullet.mapper.ProjectMapper;
import org.silverbullet.mapper.TestItemMapper;
import org.silverbullet.repository.MopRepository;
import org.silverbullet.repository.NodeRepository;
import org.silverbullet.repository.ProjectRepository;
import org.silverbullet.repository.TestItemRepository;
import org.silverbullet.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/project/v1")
public class ProjectRestController {

	@Autowired
	private NodeRepository nodeRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TestItemRepository testItemRepository;
	
	@Autowired
	private MopRepository mopRepository;
	
	@Autowired
	private MopMapper mopMapper;
	
	@Autowired
	private NodeMapper nodeMapper;
	
	@Autowired
	private ProjectMapper projectMapper;
	
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
				for (int j = 0; j < 8; j++) {
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

		//List<VuetifyTree> ret = new TreeConverter().convertTreeNode(rootNode.getChildren());
		//return ret;
		
		return Arrays.asList(new TreeConverter().convertTreeNode(rootNode));
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
	public List<ProjectDto> getConstructionSummary(Principal princiapl, @PathVariable("type") String type) {
		return projectRepository.findByType(type).stream()
				.map(p -> projectMapper.toDto(p))
				.collect(Collectors.toList());
		
	}


	@PostMapping({"/project", "/project/{id}"})
	public ProjectDto postProjectName(Principal principal, @PathVariable(name="id", required=false) Long id, @RequestBody ProjectDto dto) {
		ProjectEntity project = null;
		if (id != null) {
			project = this.projectRepository.findById(id).get();
		}
		else {
			
			NodeEntity rootNode = NodeEntity.builder().name("Root").type("node").build();
			this.nodeRepository.save(rootNode);
			project = ProjectEntity.builder().node(rootNode).build();
		}
		
		project.setType(dto.getType());
		project.setName(dto.getName());
		project.setDescription(dto.getDescription());
		project.setArea(dto.getArea());
		
		if (dto.getSchedule() != null) {
			project.setStart(dto.getSchedule().getStart());
			project.setDeadline(dto.getSchedule().getDeadline());
		}
		
		if (dto.getCost() != null) {
			project.setBudget(dto.getCost().getBudget());
		}
		this.projectRepository.save(project);
		
		return this.getProject(principal, project.getId());
	}
	
	private JsonTestItemConverter testItemConverter = new JsonTestItemConverter();
	
	@GetMapping("/nodeDetail/{nodeid}")
	public NodeDetailDto nodeDtail(Principal princiapl, @PathVariable("nodeid") Long nodeid) {
		NodeEntity node = nodeRepository.findById(nodeid).get();
		TestItemCollector collector = new TestItemCollector(nodeRepository, testItemRepository, nodeid);
		List<TestItemDto> testItems = collector.getTestItems();

		NodeDetailDto ret = NodeDetailDto.builder()
				.name(node.getName())
				.testPointProgress(	collector.progress())
				.testItems( testItems)
				.mopid(node.getMop() != null ? node.getMop().getId() : null)
				.build();
		
		return ret;
	}
	
	@GetMapping("/project/{id}")
	public ProjectDto getProject(Principal princiapl, @PathVariable("id") Long id) {		
		return projectRepository.findById(id).map(
				p -> projectMapper.toDto(p)).get();
	}
	
	@DeleteMapping("/project/{id}")
	public String deleteProject(Principal princiapl, @PathVariable("id") Long id) {		
		this.projectRepository.deleteById(id);
		return "OK";
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

	@GetMapping("/testItems")
	public List<TestItemDto> testItems(Principal princiapl) {
		MopEntity mop = this.mopRepository.findAll().get(0);
		
		List<TestItemDto> ret = new TestItemMapper().toDto(new JsonTestItemConverter().testItems(mop.getJson()));
		
		ret.stream().filter(t -> t.getKey() == null).forEach(t -> t.setKey("Key"));
		
		return ret;
	}
	
	@PostMapping("/childNode/{parentid}")
	public String childNode(Principal princiapl, @PathVariable("parentid") Long parentid, @RequestBody NodeDto dto) {
		NodeEntity parent = this.nodeRepository.findById(parentid).get();
		
		this.nodeRepository.save(NodeEntity.builder()
				.parentNode(parent)
				.name(dto.getName())
				.type(dto.getType())
				.build());
		return "OK";
	}
	
	@GetMapping("/node/{id}")
	public NodeDto getNode(Principal princiapl, @PathVariable("id") Long id) {
		NodeEntity node = this.nodeRepository.findById(id).get();
		
		NodeDto dto = nodeMapper.toDto(node);
		return dto;
	}
	
	@PostMapping("/node/{id}")
	public String node(Principal princiapl, @PathVariable("id") Long id, @RequestBody NodeDto dto) {
		NodeEntity entity = this.nodeRepository.findById(id).get();
		entity.setName(dto.getName());
		entity.setType(dto.getType());

		this.nodeRepository.save(entity);
		return "OK";
	}
	
	@DeleteMapping("/node/{id}")
	public String deleteNode(Principal princiapl, @PathVariable("id") Long id) {
//		this.testItemRepository.deleteByNode_id(id);
		
		this.nodeRepository.deleteById(id);
		return "OK";
	}
	
	@GetMapping("/node/{id}/mop/{mopid}")
	public NodeDto nodeMop(Principal princiapl, @PathVariable("id") Long id, @PathVariable("mopid") Long mopid) throws JsonMappingException, JsonProcessingException {
		NodeEntity node = this.nodeRepository.findById(id).get();
		MopEntity mop = this.mopRepository.findById(mopid).get();
		node.setMop(mop);
	
//		MopDto mopDto = new ObjectMapper().readValue( mop.getJson(), MopDto.class);

//		List<TestItemEntity> testItems = mopDto.getSections().stream().flatMap(section -> section.getLines().stream()).filter(line -> line.getTarget() != null).map(line -> TestItemEntity.builder().id(id).name(line.getTarget().getTitle()).node(node).status(TestStatus.NOT_YET).build()) .collect(Collectors.toList());
//		this.testItemRepository.deleteByNode_id(id);
		
		this.nodeRepository.save(node);
//		this.testItemRepository.saveAll(testItems);
		
		return nodeMapper.toDto(node);
	}

}
