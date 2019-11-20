package com.afpa.cda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.afpa.cda.dto.PersonneDto;
import com.afpa.cda.dto.ReponseStatut;
import com.afpa.cda.service.IPersonneService;
import com.afpa.cda.tools.Utils;

@WebServlet(urlPatterns = { "/show.do" })
public class ShowServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IPersonneService personneService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = getServletContext();
		WebApplicationContext ctx =
				WebApplicationContextUtils.getWebApplicationContext(context);
		personneService = ctx.getBean(IPersonneService.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");
		
		if (idParam == null || idParam.length() == 0) {
			Utils.redirect(
					request,
					response,
					getServletContext(),
					"/list.do",
					ReponseStatut.KO,
					"le parametre id est obligatoire");
			return;
		} else if (!idParam.matches("^\\p{Digit}+$")) {
			Utils.redirect(request, response, getServletContext(), 
					"/list.do", ReponseStatut.KO,
					"le parametre id doit être digit");
		}
		else {
			int id = Integer.parseInt(idParam);

			Optional<PersonneDto> res = this.personneService.findById(id);
			
			if(res.isPresent()) {
				PersonneDto personne = res.get();
				PrintWriter writer = response.getWriter();
				writer.append("<html>");
				writer.append("<head>");
				writer.append("<link href=\"fontawesome/css/all.min.css\" rel=\"stylesheet\">");
				writer.append("<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
				writer.append("</head>");
				writer.append("<body>");
				
				writer.append("<div class=\"container my-4\">\r\n" + 
						"  <div class=\"row justify-content-md-center\">\r\n" + 
						"    <div class=\"col card col-lg-8\">");
				
				writer.append("<form>\r\n" + 
						"  <div class=\"form-group row\">\r\n" + 
						"    <label for=\"idPers\" class=\"col-sm-2 col-form-label\">identifiant</label>\r\n" + 
						"    <div class=\"col-sm-10\">\r\n" + 
						"      <input type=\"text\" readonly class=\"form-control-plaintext\" id=\"idPers\" value=\""+personne.getId()+"\">\r\n" + 
						"    </div>\r\n" + 
						"  </div>\r\n" + 
						"  <div class=\"form-group row\">\r\n" + 
						"    <label for=\"nomPers\" class=\"col-sm-2 col-form-label\">nom</label>\r\n" + 
						"    <div class=\"col-sm-10\">\r\n" + 
						"      <input type=\"text\" readonly class=\"form-control-plaintext\" id=\"nomPers\" value=\""+personne.getNom()+"\">\r\n" + 
						"    </div>\r\n" + 
						"  </div>\r\n" +
						"</form>");
				writer.append("<a class=\"btn btn-secondary\" href=\"list.do\" role=\"button\">retour vers la liste</a>");
				writer.append("</div>\r\n" + 
						"  </div>\r\n" + 
						"</div>");
				
				writer.append("<script src=\"jquery/jquery-3.3.1.slim.min.js\" ></script>");
				writer.append("<script src=\"bootstrap/js/bootstrap.bundle.min.js\" ></script>");
				
				writer.append("</body>");
				writer.append("</html>");
			} else {
				Utils.redirect(
						request,
						response,
						getServletContext(),
						"/list.do",
						ReponseStatut.KO,
						"aucune personne n'a cet id "+id);
				return;
			}
		}
	}

}
