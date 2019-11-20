package com.afpa.cda.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReponseDto {
	private ReponseStatut status;
	private String msg;
}
