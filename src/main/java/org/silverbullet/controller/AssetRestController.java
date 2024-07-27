package org.silverbullet.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.silverbullet.dto.AssetFilterDto;
import org.silverbullet.dto.OptionEnabled;
import org.silverbullet.dto.AssetDto;
import org.silverbullet.entity.AssetAttributeEntity;
import org.silverbullet.entity.AssetEntity;
import org.silverbullet.entity.AssetInstanceEntity;
import org.silverbullet.entity.ManufacturerEntity;
import org.silverbullet.entity.NodeEntity;
import org.silverbullet.entity.RelationAssetAttributeEntity;
import org.silverbullet.repository.AssetAttributeRepository;
import org.silverbullet.repository.AssetInstanceRepository;
import org.silverbullet.repository.AssetRepository;
import org.silverbullet.repository.ManufacturerReposiroty;
import org.silverbullet.repository.RelationAssetAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/asset/v1")
public class AssetRestController {
	
	@Autowired
	private AssetRepository assetRepository;
	
	@Autowired
	private AssetAttributeRepository assetAttributeRepository;
	
	@Autowired
	private ManufacturerReposiroty manufacturerRepository;
	
	@Autowired
	private RelationAssetAttributeRepository relationAssetAttributeRepository;
	
	@Autowired
	private AssetInstanceRepository assetInstanceRepository;
	
	@GetMapping("/init") 
	public void init() {
		assetInstanceRepository.deleteAll();
		relationAssetAttributeRepository.deleteAll();
		assetRepository.deleteAll();
		assetAttributeRepository.deleteAll();
		manufacturerRepository.deleteAll();
		
		AssetAttributeEntity attributeOtdr = AssetAttributeEntity.builder().attribute("OTDR").build();
		assetAttributeRepository.save(attributeOtdr);

		AssetAttributeEntity attributeOsa = AssetAttributeEntity.builder().attribute("OSA").build();
		assetAttributeRepository.save(attributeOsa);

		AssetAttributeEntity attributeOpm = AssetAttributeEntity.builder().attribute("OPM").build();
		assetAttributeRepository.save(attributeOpm);
	
		AssetAttributeEntity attributeFip = AssetAttributeEntity.builder().attribute("FIP").build();
		assetAttributeRepository.save(attributeFip);
		
		ManufacturerEntity manufacturerAnritsu = ManufacturerEntity.builder().name("Anritsu").build();
		manufacturerRepository.save(manufacturerAnritsu);
	
		ManufacturerEntity manufacturerExfo = ManufacturerEntity.builder().name("EXFO").build();
		manufacturerRepository.save(manufacturerExfo);
		
		ManufacturerEntity manufacturerViavi = ManufacturerEntity.builder().name("Viavi").build();
		manufacturerRepository.save(manufacturerViavi);
		
		AssetEntity assetMt9085 = AssetEntity.builder().family("Access Master").name("MT9085A-053").manufacturer(manufacturerAnritsu).image("https://dl.cdn-anritsu.com/images/products/tm-mt9085a-b-c/mt9085-v2.png?h=310&w=420&la=ja-JP").build();
		assetRepository.save(assetMt9085);
		
		{
			RelationAssetAttributeEntity relationMt9085 = RelationAssetAttributeEntity.builder().build();
			relationMt9085.setAsset(assetMt9085);
			relationMt9085.setAttribute(attributeOtdr);
			relationAssetAttributeRepository.save(relationMt9085);
		}
		{
			RelationAssetAttributeEntity relationMt9085 = RelationAssetAttributeEntity.builder().build();
			relationMt9085.setAsset(assetMt9085);
			relationMt9085.setAttribute(attributeOpm);
			relationAssetAttributeRepository.save(relationMt9085);
		}
		
		for (int i = 0; i < 5; i++) {
			AssetInstanceEntity assetInstance = AssetInstanceEntity.builder().asset(assetMt9085).serial_number("ASN000000"+ i).build();
			assetInstanceRepository.save(assetInstance);
		}
		AssetEntity assetMaxTester = AssetEntity.builder().family("MaxTester").name("720D").manufacturer(manufacturerExfo).image("https://www.exfo.com/contentassets/e9b7aa7dad564d2eba5f0a80496a863d/max-700c_1.jpg?rxy=0.5,0.5&width=690&height=0&rmode=crop").build();
		assetRepository.save(assetMaxTester);
		
		{
			RelationAssetAttributeEntity relationMaxTester = RelationAssetAttributeEntity.builder().build();
			relationMaxTester.setAsset(assetMaxTester);
			relationMaxTester.setAttribute(attributeOtdr);
			relationAssetAttributeRepository.save(relationMaxTester);
		}
		{
			RelationAssetAttributeEntity relationMaxTester = RelationAssetAttributeEntity.builder().build();
			relationMaxTester.setAsset(assetMaxTester);
			relationMaxTester.setAttribute(attributeOpm);
			relationAssetAttributeRepository.save(relationMaxTester);
		}
		
		for (int i = 0; i < 5; i++) {
			AssetInstanceEntity assetInstance = AssetInstanceEntity.builder().asset(assetMaxTester).serial_number("ESN000000"+ i).build();
			assetInstanceRepository.save(assetInstance);
		}
		
		
	}
	
	@GetMapping("/myAssets")
	public List<AssetDto> myAssets(Principal principal) {
	
		return this.assetInstanceRepository.findAll().stream().map(ai -> AssetDto.builder()
				.id(ai.getId())
				.categories(
						//ai.getAsset().getRelationAttribute().stream().map(relation -> relation.getAttribute().getAttribute()).collect(Collectors.joining(", "))
						ai.getAsset().getRelationAttribute().stream().map(relation -> relation.getAttribute().getAttribute()).collect(Collectors.toList())
						)
				.manufacturer(ai.getAsset().getManufacturer().getName())
				.model(ai.getAsset().getName())
				.image(ai.getAsset().getImage())
				.family(ai.getAsset().getFamily())
				.serialNumber(ai.getSerial_number())
				.build()).collect(Collectors.toList());

	}
	
	@PostMapping("/myAsset")
	public String myAsset(Principal principal, @RequestBody AssetDto myAssetDto) {
	
		this.assetInstanceRepository.save(AssetInstanceEntity.builder().asset(this.assetRepository.findById(myAssetDto.getId()).get()).serial_number(myAssetDto.getSerialNumber()).build());
		return "OK";
	}
	
	@GetMapping("/assets")
	public List<AssetDto> assets(Principal principal) {
//		List<TesterDto> ret = new ArrayList<>();
		
		return this.assetRepository.findAll().stream().map(ai -> AssetDto.builder()
				.id(ai.getId())
				.categories(
						//ai.getRelationAttribute().stream().map(relation -> relation.getAttribute().getAttribute()).collect(Collectors.joining(", "))
						ai.getRelationAttribute().stream().map(relation -> relation.getAttribute().getAttribute()).collect(Collectors.toList())
						)
				.manufacturer(ai.getManufacturer().getName())
				.model(ai.getName())
				.image(ai.getImage())
				.family(ai.getFamily())
				.note(ai.getNote())
				.build()).collect(Collectors.toList());
	}
	
	@PostMapping("/assets")
	public String assets(Principal principal, @RequestBody AssetDto asset) {
		AssetEntity entity = AssetEntity.builder()
				.id(asset.getId())
				.family(asset.getFamily())
				.image(asset.getImage())
				.name(asset.getModel())
				.note(asset.getNote())
				.manufacturer(this.manufacturerRepository.findByName(asset.getManufacturer()))
				.build();
		
		this.assetRepository.save(entity);
		
		this.relationAssetAttributeRepository.deleteByAsset_id(entity.getId());
		
		asset.getCategories().forEach(cat -> {
			AssetAttributeEntity attribute = assetAttributeRepository.findByAttribute(cat);
			relationAssetAttributeRepository.save(RelationAssetAttributeEntity.builder().asset(entity).attribute(attribute).build());
		});
		
		//.stream().map(cat -> assetAttributeRepository.findByAttribute(cat.trim())).collect(Collectors.toList());
		
		
		return "OK";
	}
	
	@GetMapping("/attributes")
	public List<String> attributes(Principal principal) {
		return 	this.assetAttributeRepository.findAll().stream().map(att -> att.getAttribute()).collect(Collectors.toList());
	}
	
	@GetMapping("/manufacturers")
	public List<String> manufacturers(Principal principal) {
		return 	this.manufacturerRepository.findAll().stream().map(man -> man.getName()).collect(Collectors.toList());
	}
	
	@GetMapping("/filter")
	public List<AssetFilterDto> assetFilter(Principal principal) {
		
		List<AssetFilterDto> ret = new ArrayList<>();
		
		ret.add(AssetFilterDto.builder().title("Category").options(this.assetAttributeRepository.findAll().stream().map(attribute -> OptionEnabled.builder().title(attribute.getAttribute()).enabled(true).build()).collect(Collectors.toList())).build());
		ret.add(AssetFilterDto.builder().title("Manufacturer").options(this.manufacturerRepository.findAll().stream().map(manufacturer -> OptionEnabled.builder().title(manufacturer.getName()).enabled(true).build()).collect(Collectors.toList())).build());
		ret.add(AssetFilterDto.builder().title("Model").options(this.assetRepository.findAll().stream().map(asset -> OptionEnabled.builder().title(asset.getName()).enabled(true).build()).collect(Collectors.toList())).build());

		return ret;
	}
}
