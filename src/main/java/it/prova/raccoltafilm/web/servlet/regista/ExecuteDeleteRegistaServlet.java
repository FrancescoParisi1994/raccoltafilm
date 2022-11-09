package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.exceptions.ElementNotFoundException;
import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.service.RegistaService;

/**
 * Servlet implementation class ExecuteDeleteRegistaServlet
 */
@WebServlet("/ExecuteDeleteRegistaServlet")
public class ExecuteDeleteRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RegistaService registaService;

	public ExecuteDeleteRegistaServlet() {
		this.registaService = MyServiceFactory.getRegistaServiceInstance();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStringRegista=request.getParameter("id");
		
		if (!NumberUtils.isCreatable(idStringRegista)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			
			
			
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		
		Set<Film> filmsDelRegista=null;
		try {
			filmsDelRegista=new HashSet<>(registaService.caricaSingoloElementoConFilms(Long.parseLong(idStringRegista)).getFilms());
			if (!filmsDelRegista.isEmpty()) {
				request.setAttribute("filmsRegista_attr", filmsDelRegista);
				request.setAttribute("errorMessage", "Il regista che stai cercando di eliminare ha ancora dei film collegati.");
				request.getRequestDispatcher("/regista/delete.jsp").forward(request, response);
				return;
			}
			registaService.rimuovi(Long.parseLong(idStringRegista));
			request.setAttribute("registi_list_attribute", registaService.listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/regista/list.jsp").forward(request, response);
	}

}
