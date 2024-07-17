package org.silverbullet;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.silverbullet.dto.TesterDto;
import org.silverbullet.entity.NodeEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/asset/v1")
public class AssetRestController {
	@GetMapping("/testers")
	public List<TesterDto> nodeInfo(Principal principal) {
		
		return Arrays.asList(
				TesterDto.builder().manufacturer("Anritsu").family("Access Master").model("MT9085A-053").url("https://dl.cdn-anritsu.com/images/products/tm-mt9085a-b-c/mt9085-v2.png?h=310&w=420&la=ja-JP").build()
				
				);
	}
}
