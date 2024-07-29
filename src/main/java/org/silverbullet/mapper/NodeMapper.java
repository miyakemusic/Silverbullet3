package org.silverbullet.mapper;

import org.silverbullet.dto.NodeDto;
import org.silverbullet.dto.PairDto;
import org.silverbullet.entity.NodeEntity;

public class NodeMapper {


	public NodeDto toDto(NodeEntity node) {
		return  NodeDto.builder().name(node.getName()).type(node.getType())
				.mop(node.getMop() != null ? PairDto.builder().key(node.getMop().getId().toString()).value(node.getMop().getName()).build() : null)
				
				.build();
	}

}
