package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.service.RegistaService;

/**
 * Servlet implementation class PrepareUpdateRegistaServlet
 */
@WebServlet("/PrepareUpdateRegistaServlet")
public class PrepareUpdateRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RegistaService registaService;
	
	public PrepareUpdateRegistaServlet() {
		this.registaService=MyServiceFactory.getRegistaServiceInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStringRegista=request.getParameter("idFilm");
		
		if (!NumberUtils.isCreatable(idStringRegista)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		
		try {
			request.setAttribute("update_regista_attr", registaService.caricaSingoloElemento(Long.parseLong(idStringRegista)));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/regista/update.jsp").forward(request, response);
	}

	
}
