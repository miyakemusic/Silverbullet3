package org.silverbullet.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.silverbullet.dto.HeatmapDto;
import org.silverbullet.dto.HeatmapElementDto;
import org.silverbullet.dto.LatLngDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/geo/v1")
public class GeoAnalyticController {
	@GetMapping("/heatmap")
	public HeatmapDto myAssets(Principal principal) {
		
		double center_x = 37.774546 + Math.random() / 100;
		double center_y = -122.433523  + Math.random() / 100;
		
		List<HeatmapElementDto> heatmap = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			double x = center_x + (double)i / 1000.0 + Math.random() / 100;
			double y = center_y + (double)i / 1000.0 + Math.random() / 100;
			
			heatmap.add(HeatmapElementDto.builder().location(LatLngDto.builder().lat(x).lng(y).build()).weight(1.0).build());
		}
		return HeatmapDto.builder().center(LatLngDto.builder().lat(center_x).lng(center_y).build()).heatmap(heatmap).build();
	}
}
