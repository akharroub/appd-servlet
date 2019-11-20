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

import com.afpa.cda.service.IPersonneService;
@WebServlet(urlPatterns = { "/add.do" })
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IPersonneService personneService;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		personneService = ctx.getBean(IPersonneService.class);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.append("<html>");
		writer.append("<head>");
		writer.append("<link href=\"fontawesome/css/all.min.css\" rel=\"stylesheet\">");
		writer.append("<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		writer.append("</head>");
		writer.append("<body>");
		writer.append("<div class=\"container my-4\">\r\n");
		writer.append("<h1>Ajouter un nouveau Bernie</h1>");
		writer.append("<form method=\"post\">\r\n" + "  <div class=\"form-row\">\r\n"
				+ "    <div class=\"form-group col-md-6\">\r\n" + "      <label for=\"inputNom\">Nom</label>\r\n"
				+ "      <input type=\"text\" class=\"form-control\" name=\"nom\" placeholder=\"nom\">\r\n"
				+ "    </div>\r\n" + "    <div class=\"form-group col-md-6\">\r\n"
				+ "      <label for=\"inputPrenom\">Prenom</label>\r\n"
				+ "      <input type=\"text\" class=\"form-control\" name=\"prenom\" placeholder=\"prenom\">\r\n"
				+ "    </div>\r\n" + "  </div>\r\n"
				+ "  <button type=\"submit\" class=\"btn btn-primary\">Ajouter</button>\r\n" + "</form>");
		writer.append("</div>");
		writer.append("<script src=\"jquery/jquery-3.3.1.slim.min.js\" ></script>");
		writer.append("<script src=\"bootstrap/js/bootstrap.bundle.min.js\" ></script>");
		writer.append("</body>");
		writer.append("</html>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		personneService.ajouterPersonne(nom, prenom);
		response.sendRedirect("list.do");
	}
}