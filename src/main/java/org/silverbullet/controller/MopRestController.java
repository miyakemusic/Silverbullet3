package org.silverbullet.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.silverbullet.dto.CriteriaDto;
import org.silverbullet.dto.ImageDto;
import org.silverbullet.dto.MopDto;
import org.silverbullet.dto.MopLineDto;
import org.silverbullet.dto.MopSectionDto;
import org.silverbullet.dto.MopSummary;
import org.silverbullet.entity.MopEntity;
import org.silverbullet.entity.NodeEntity;
import org.silverbullet.repository.MopRepository;
import org.silverbullet.repository.NodeRepository;
import org.silverbullet.repository.UserRepository;
import org.silverbullet.repository.trash.MopLineRepository;
import org.silverbullet.repository.trash.MopSectionRepository;
import org.silverbullet.repository.trash.RelationMopUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/mop/v1")
public class MopRestController {
	private List<MopDto> mops = Arrays.asList(
			MopDto.builder()
			.authors(Arrays.asList("tupac@deathrow.com", "dr.dre@deathrow.com"))
			.title("FTTA Construction")
			.category("FTTA")
			.image("https://www.khrista.co.id/wp-content/uploads/2016/03/ftta.png")
			.description("Once the design of the network has been completed, the lifecycle of a network generally consists of three main phases: construction, activation and maintenance. The following sections highlight some the key testing elements that should be considered during the Construction and Service Validation Phase.")
			.sections(Arrays.asList(
					MopSectionDto.builder()
						.title("Step 1 ; Connector Cleanliness")
						.images(Arrays.asList(
								"https://www.khrista.co.id/wp-content/uploads/2016/03/connector-inspect.png",
								"https://www.tyheatshrink.com/Uploads/image/20140813/20140813081803_65733.jpg"

//								"https://www.google.com/url?sa=i&url=https%3A%2F%2Fkalugastroit-40.ru%2F%3Fm%3Dfiber-to-antenna-ftta-antenna-fiber-optic-fiber-optic-0-mm-zvR85NcJ&psig=AOvVaw0yu5-H9LQQ31StjQIu9uX1&ust=1722041695173000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCNDz8K-_w4cDFQAAAAAdAAAAABBO"
								))
						.lines(Arrays.asList(
							MopLineDto.builder().operation("Clean the connector[A] with the Connector Cleaner.").target(ImageDto.builder().title("Connector[A]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("Cleaner").image("https://www.opticfibertool.com/cdn/shop/products/TB14zh3AVY7gK0jSZKzXcmikpXa_400x.jpg?v=1600273777").build()).build(),
							MopLineDto.builder().operation("Measure the Connector[A] with Fiber Inspection Probe.").target(ImageDto.builder().title("Connector[B]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("FIP").image("https://opticom.bg/images/2/973561890856bbe258abf964e8931f98/fip-400b-mf-usb-2-1550.jpg").build()).criteria(CriteriaDto.builder().criteria(Arrays.asList("IEC 61300-3-35:2022")).build()).build(),
							MopLineDto.builder().operation("Measure the Connector[B] with Fiber Inspection Probe.").target(ImageDto.builder().title("Connector[B]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("FIP").image("https://opticom.bg/images/2/973561890856bbe258abf964e8931f98/fip-400b-mf-usb-2-1550.jpg").build()).criteria(CriteriaDto.builder().criteria(Arrays.asList("IEC 61300-3-35:2022")).build()).build(),
							MopLineDto.builder().operation("Measure the Connector[C] with Fiber Inspection Probe.").target(ImageDto.builder().title("Connector[B]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("FIP").image("https://opticom.bg/images/2/973561890856bbe258abf964e8931f98/fip-400b-mf-usb-2-1550.jpg").build()).criteria(CriteriaDto.builder().criteria(Arrays.asList("IEC 61300-3-35:2022")).build()).build(),
							MopLineDto.builder().operation("Measure the Connector[D] with Fiber Inspection Probe.").target(ImageDto.builder().title("Connector[B]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("FIP").image("https://opticom.bg/images/2/973561890856bbe258abf964e8931f98/fip-400b-mf-usb-2-1550.jpg").build()).criteria(CriteriaDto.builder().criteria(Arrays.asList("IEC 61300-3-35:2022")).build()).build(),

							MopLineDto.builder().operation("Connect the Connector[A] of LEFT side to the OTDR and start Test").target(ImageDto.builder().title("Connector[A]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("OTDR").image("https://www.viavisolutions.com/sites/default/files/styles/d10_scale/public/main-images/smartotdr_with_new_gui.jpg.webp?itok=jTHlzBpP").build()).criteria(CriteriaDto.builder().criteria(Arrays.asList("ORL < -50dB","Total Loss < 1.0dB")).build()).build(),
							MopLineDto.builder().operation("Connect the Connector[B] of RIGHT side to the OTDR and start Test").target(ImageDto.builder().title("Connector[A]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("OTDR").image("https://www.viavisolutions.com/sites/default/files/styles/d10_scale/public/main-images/smartotdr_with_new_gui.jpg.webp?itok=jTHlzBpP").build()).criteria(CriteriaDto.builder().criteria(Arrays.asList("ORL < -50dB","Total Loss < 1.0dB")).build()).build()

								)
					).build(),
						MopSectionDto.builder()
						.title("Step 2 ; Fiber Characterization")
						.images(Arrays.asList("https://www.khrista.co.id/wp-content/uploads/2016/03/iloop.png"))
						.lines(Arrays.asList(
								MopLineDto.builder().operation("Clean the connector[A] with the Connector Cleaner.").target(ImageDto.builder().title("Connector[A]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("Cleaner").image("https://www.opticfibertool.com/cdn/shop/products/TB14zh3AVY7gK0jSZKzXcmikpXa_400x.jpg?v=1600273777").build()).build(),
								MopLineDto.builder().operation("Measure the Connector[A] with Fiber Inspection Probe.").target(ImageDto.builder().title("Connector[B]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("FIP").image("https://opticom.bg/images/2/973561890856bbe258abf964e8931f98/fip-400b-mf-usb-2-1550.jpg").build()).criteria(CriteriaDto.builder().criteria(Arrays.asList("IEC 61300-3-35:2022")).build()).build(),
								MopLineDto.builder().operation("Measure the Connector[B] with Fiber Inspection Probe.").target(ImageDto.builder().title("Connector[B]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("FIP").image("https://opticom.bg/images/2/973561890856bbe258abf964e8931f98/fip-400b-mf-usb-2-1550.jpg").build()).criteria(CriteriaDto.builder().criteria(Arrays.asList("IEC 61300-3-35:2022")).build()).build(),
								MopLineDto.builder().operation("Measure the Connector[C] with Fiber Inspection Probe.").target(ImageDto.builder().title("Connector[B]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("FIP").image("https://opticom.bg/images/2/973561890856bbe258abf964e8931f98/fip-400b-mf-usb-2-1550.jpg").build()).criteria(CriteriaDto.builder().criteria(Arrays.asList("IEC 61300-3-35:2022")).build()).build(),
								MopLineDto.builder().operation("Measure the Connector[D] with Fiber Inspection Probe.").target(ImageDto.builder().title("Connector[B]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("FIP").image("https://opticom.bg/images/2/973561890856bbe258abf964e8931f98/fip-400b-mf-usb-2-1550.jpg").build()).criteria(CriteriaDto.builder().criteria(Arrays.asList("IEC 61300-3-35:2022")).build()).build(),

								MopLineDto.builder().operation("Connect the Connector[A] of LEFT side to the OTDR and start Test").target(ImageDto.builder().title("Connector[A]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("OTDR").image("https://www.viavisolutions.com/sites/default/files/styles/d10_scale/public/main-images/smartotdr_with_new_gui.jpg.webp?itok=jTHlzBpP").build()).criteria(CriteriaDto.builder().criteria(Arrays.asList("ORL < -50dB","Total Loss < 1.0dB")).build()).build(),
								MopLineDto.builder().operation("Connect the Connector[B] of RIGHT side to the OTDR and start Test").target(ImageDto.builder().title("Connector[A]").image("https://resource.fs.com/mall/mainImg/20220711182057s9mjl7.jpg").build()).tool(ImageDto.builder().title("OTDR").image("https://www.viavisolutions.com/sites/default/files/styles/d10_scale/public/main-images/smartotdr_with_new_gui.jpg.webp?itok=jTHlzBpP").build()).criteria(CriteriaDto.builder().criteria(Arrays.asList("ORL < -50dB","Total Loss < 1.0dB")).build()).build()

						)
					).build()
			)).build()
			);
	
	@Autowired
	private MopRepository mopRepository;
	
	@Autowired
	private MopSectionRepository mopSectionRepository;
	
	@Autowired
	private MopLineRepository mopLineRepository;

	@Autowired
	private RelationMopUserRepository relationMopUserRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NodeRepository nodeRepository;
	
	@GetMapping("/debug/init")
	public String init() {
		ObjectMapper mapper = new ObjectMapper();
		
		mops.forEach(mop -> {
			//dtoToEntity(mop);		
			try {
				mopRepository.save(MopEntity.builder().json(mapper.writeValueAsString(mop)).build());
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return "OK";
	}

	@DeleteMapping("/debug/delete")
	public String deleteMop() {
//		this.mopRepository.findAll().forEach(
//				mop -> deleteMopEntity(mop)
//		);
		
		this.mopRepository.deleteAll();
		return "OK";
	}

//	private void deleteMopEntity(MopEntity mop) {
//		mop.getRelationMopUser().forEach(rel -> {
//			relationMopUserRepository.deleteById(rel.getId());
//		});
//		
//		mop.getSections().forEach(section -> {
//			section.getLines().forEach(line -> {
//				mopLineRepository.deleteById(line.getId());
//			});
//			
//			mopSectionRepository.deleteById(section.getId());
//		});
//	}
	
//	private MopEntity dtoToEntity(MopDto mop) {
//		if (mop.getId() == null) {
//			MopEntity mopEntity = MopEntity.builder()
//					.category(mop.getCategory())
//					.title(mop.getTitle())
//					.description(mop.getDescription())
//					.image(mop.getImage())
//					.build();
//			
//			mopRepository.save(mopEntity);		
//		}
//		else {
//			
//		}
//		MopEntity mopEntity = MopEntity.builder()
//				.category(mop.getCategory())
//				.title(mop.getTitle())
//				.description(mop.getDescription())
//				.image(mop.getImage())
//				.build();
//		
//		mopRepository.save(mopEntity);
//		
//		if (mop.getAuthors() != null) {
//			mop.getAuthors().forEach(author -> {
//				UserEntity userEntity = userRepository.findByUsername(author);
//				RelationMopUserEntity relationMopUser = RelationMopUserEntity.builder()
//						.mop(mopEntity)
//						.user(userEntity)
//						.build();
//				
//				relationMopUserRepository.save(relationMopUser);
//			});
//		}
//		
//		mop.getSections().forEach(section -> {
//			MopSectionEntity sectionEntity = MopSectionEntity.builder()
//					.images(section.getImages())
//					.mop(mopEntity)
//					.title(section.getTitle())
//					.build();
//			
//			mopSectionRepository.save(sectionEntity);
//			
//			section.getLines().forEach(line -> {
//				MopLineEntity lineEntity = MopLineEntity.builder()
//						.mopSection(sectionEntity)
//						.operation(line.getOperation())
//						.target(line.getTarget() != null ? line.getTarget().getTitle() : null)
//						.targetImage(line.getTarget() != null ? line.getTarget().getImage() : null)
//						.tool(line.getTool()!= null ? line.getTool().getTitle(): null)
//						.toolImage(line.getTool() != null ? line.getTool().getImage() : null)
//						.criteria(line.getCriteria()!= null ?  line.getCriteria().getCriteria(): null)
//						.build();
//				
//				mopLineRepository.save(lineEntity);
//				
//			});
//		});
//		
//		return mopEntity;
//	}
	
	@GetMapping("/mops/{id}")
	public MopDto mops(Principal principal, @PathVariable("id") Long id) throws JsonMappingException, JsonProcessingException {
		MopEntity mopEntity = mopRepository.findById(id).get();
		MopDto ret = new ObjectMapper().readValue(mopEntity.getJson(), MopDto.class);
		ret.setId(id);
		return ret;
		//return mops.stream().filter(mop -> mop.getId().equals(id)).findFirst().get();
	}

//	private MopDto entityToDto(MopEntity mopEntity, AtomicInteger i) {
//		MopDto dto = MopDto.builder()
//				.id(mopEntity.getId())
//				.authors(mopEntity.getRelationMopUser().stream().map(mu -> mu.getUser().getUsername()).collect(Collectors.toList()))
//				.category(mopEntity.getCategory())
//				.description(mopEntity.getDescription())
//				.image(mopEntity.getImage())
//				.sections(mopEntity.getSections().stream().map(s -> MopSectionDto.builder()
//						.images(s.getImages())
//						.lines(s.getLines().stream().map(line -> MopLineDto.builder()
//								.criteria(CriteriaDto.builder().criteria(line.getCriteria()).build())
//								.number(i.incrementAndGet())
//								.operation(line.getOperation())
//								.target(ImageDto.builder().title(line.getTarget()).image(line.getTargetImage()).build())
//								.tool(ImageDto.builder().title(line.getTool()).image(line.getToolImage()).build())
//								.build()).collect(Collectors.toList()))
//						.title(s.getTitle())
//						.build()).collect(Collectors.toList()))
//				.title(mopEntity.getTitle())
//				.build();
//		return dto;
//	}
	
	@GetMapping("/summary")
	public List<MopSummary> mopSummary(Principal principal) {
		ObjectMapper mapper = new ObjectMapper();
		return this.mopRepository.findAll().stream().map(mop -> {
			try {
				MopDto dto = mapper.readValue(mop.getJson(), MopDto.class);
				return MopSummary.builder()
					.category(dto.getCategory())
					.authors(dto.getAuthors())
					.id(mop.getId())
					.summary(dto.getDescription())
					.title(dto.getTitle()).build();

			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
		
//		return this.mopRepository.findAll().stream().map(mop -> MopSummary.builder()
//				.authors(mop.getRelationMopUser().stream().map(u -> u.getUser().getUsername()).collect(Collectors.toList()))
//				.category(mop.getCategory())
//				.id(mop.getId())
//				.summary(mop.getDescription())
//				.title(mop.getTitle())
//				.build()).collect(Collectors.toList());
		
	}
	
	@DeleteMapping("/mop/{id}")
	public String delete(Principal principal, @PathVariable("id") Long id) throws Exception {
		List<NodeEntity> nodes = this.nodeRepository.findByMop_id(id);
		System.out.println(nodes.size());
		if (nodes.size() > 0) {
			String message = "Cannot delete. This is used by " + nodes.stream().map(node -> node.getMop().getName() + ",").collect(Collectors.joining());
			throw new MopInUseException(message);
		}
		try {
			this.mopRepository.deleteById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "OK";
	}
	
	Integer index = 0;
	@PostMapping("/mop/{id}")
	public String duplicate(Principal principal, @PathVariable("id") Long id, @RequestBody MopDto mop) throws JsonProcessingException {
		String key = "";
		
		//mop.getSections().forEach(sec -> sec.getLines().forEach(line -> line.setKey("K" + index++)));
		
		//IntStream.range(0, mop.getSections().size()).forEach(indexSection -> mop.getSections().get(indexSection).getLines().forEach(line -> line.getClass()));
		
		for (int section = 0; section < mop.getSections().size(); section++) {
			MopSectionDto sectionDto = mop.getSections().get(section);
			for (int line = 0; line < sectionDto.getLines().size(); line++) {
				sectionDto.getLines().get(line).setKey(String.valueOf(section + 1) + "-" +  String.valueOf(line + 1));
			}
		}
		String json = new ObjectMapper().writeValueAsString(mop);

		if (id == -1) {
			this.mopRepository.save(MopEntity.builder()
					.name(mop.getTitle())
					.json(json).build());
		}
		else {
			MopEntity e = this.mopRepository.findById(id).get();
			e.setJson(json);
			e.setName(mop.getTitle());
			this.mopRepository.save(e);
		}

		return "OK";
	}
	
	@GetMapping("/mop/publish/{id}")
	public MopDto publish(Principal principal, @PathVariable("id") Long id) {
		MopEntity mop = this.mopRepository.findById(id).get();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			AtomicInteger count=new AtomicInteger(0);
			MopDto dto = mapper.readValue(mop.getJson(), MopDto.class);
			dto.getSections().forEach(section -> section.getLines().forEach(line -> line.setKey(line.getTarget().getTitle().substring(0, 1) + line.getTool().getTitle().substring(0, 1) +  count.incrementAndGet())));
			mop.setJson(mapper.writeValueAsString(dto));
			this.mopRepository.save(mop);
			return dto;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
