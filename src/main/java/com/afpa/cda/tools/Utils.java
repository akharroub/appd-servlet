package com.afpa.cda.tools;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.afpa.cda.dto.ReponseDto;
import com.afpa.cda.dto.ReponseStatut;

public class Utils {
	public static void redirect(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext,String urlRedirection, ReponseStatut statut, String msg) throws ServletException, IOException {
		request.setAttribute("reponse", 
				ReponseDto.builder()
				.status(statut)
				.msg(msg).build());
		
		RequestDispatcher dispatcher = servletContext.getRequestDispatcher(urlRedirection);
		dispatcher.forward(request, response);
	}
}
