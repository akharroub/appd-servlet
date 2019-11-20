package com.afpa.cda.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.afpa.cda.dto.ReponseDto;
import com.afpa.cda.dto.ReponseStatut;
import com.afpa.cda.service.IPersonneService;

@WebServlet(urlPatterns = {"/index.html","/list.do"})
public class ListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private IPersonneService personneService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config); 
		ServletContext context = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		personneService = ctx.getBean(IPersonneService.class);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.append("<html>");
		writer.append("<head>");
		writer.append("<link href=\"fontawesome/css/all.min.css\" rel=\"stylesheet\">");
		writer.append("<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		writer.append("</head>");
		writer.append("<body>");
		
		ReponseDto reponse = (ReponseDto)request.getAttribute("reponse");
		if(reponse != null) {
			String typeMsg= reponse.getStatus()==ReponseStatut.OK?"primary":"danger";
			writer.append("<div class=\"alert alert-"+typeMsg+" alert-dismissible fade show\" role=\"alert\">\r\n" + 
					"  <strong>"+reponse.getMsg()+"</strong>\r\n" + 
					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
					"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
					"  </button>\r\n" + 
					"</div>");
		}
		
		writer.append("<div class=\"container my-4\">\r\n" + 
				"  <div class=\"row justify-content-md-center\">\r\n" + 
				"    <div class=\"col col-lg-8\">");
		
		writer.append("<table class=\"table table-hover\">");
		writer.append("<thead class=\"thead-dark\">");
		writer.append("<tr>");
		writer.append("<th>id</th>");
		writer.append("<th>nom</th>");
		writer.append("<th>prenom</th>");
		writer.append("<th></th>");
		writer.append("</tr>");
		writer.append("</thead>");
		
		writer.append("<tbody>");
		this.personneService.chercherToutesLesPersonnes()
		.forEach(p->{
			String idStr = Integer.toString(p.getId());
			writer.append("<tr>");
			writer.append("<td><a href=\"show.do?id=").append(idStr).append("\">").append(idStr).append("</a></td>");
			writer.append("<td><a href=\"show.do?id=").append(idStr).append("\">").append(p.getNom()).append("</a></td>");
			writer.append("<td><a href=\"show.do?id=").append(idStr).append("\">").append(p.getPrenom()).append("</a></td>");
			writer.append("<td><a href=\"delete.do?id=").append(idStr).append("\"><i class=\"fas fa-trash-alt\"></i></a></td>");
			writer.append("</tr>");
		});
		
		writer.append("</table>");
		
		writer.append("</div>\r\n" + 
				"  </div>\r\n" + 
				"</div>");
		
		writer.append("<script src=\"jquery/jquery-3.3.1.slim.min.js\" ></script>");
		writer.append("<script src=\"bootstrap/js/bootstrap.bundle.min.js\" ></script>");
		
		writer.append("</body>");
		writer.append("</html>");
	}
}
