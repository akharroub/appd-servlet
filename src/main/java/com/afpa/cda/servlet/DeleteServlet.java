package com.afpa.cda.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
import com.afpa.cda.tools.Utils;

@WebServlet(urlPatterns = { "/delete.do" })
public class DeleteServlet extends HttpServlet {

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
		String idParam = request.getParameter("id");

		String msg = "";
		ReponseStatut statut = ReponseStatut.OK;

		if (idParam == null || idParam.length() == 0) {
			statut = ReponseStatut.KO;
			msg = "le parametre id est obligatoire";
		} else if (!idParam.matches("^\\p{Digit}+$")) {
			Utils.redirect(request, response, getServletContext(), "/list.do", ReponseStatut.KO,
					"le parametre id doit être digit");
		} else {
			int id = Integer.parseInt(idParam);

			if (this.personneService.deleteById(id)) {
				msg = "suppression reussie avec succes";
			} else {
				statut = ReponseStatut.KO;
				msg = "aucune personne n'a cet id " + id;
			}
		}

		request.setAttribute("reponse", ReponseDto.builder().status(statut).msg(msg).build());

		String nextJSP = "/list.do";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
	}

}
