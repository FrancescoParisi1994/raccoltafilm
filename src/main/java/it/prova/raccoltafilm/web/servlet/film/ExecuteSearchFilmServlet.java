package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.FilmService;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteSearchFilmServlet")
public class ExecuteSearchFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// injection del Service
	private FilmService filmService;

	public ExecuteSearchFilmServlet() {
		this.filmService = MyServiceFactory.getFilmServiceInstance();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String titolo = request.getParameter("titolo");
		String genere = request.getParameter("genere");
		String dataPubblicazione = request.getParameter("dataPubblicazione");
		String minutiDurata = request.getParameter("minutiDurata");
		String registaID = request.getParameter("regista.id");

		Integer minutiDurataControll = null;
		if (NumberUtils.isCreatable(minutiDurata)) {
			minutiDurataControll=Integer.parseInt(minutiDurata);
		}
		Regista registaControll= null;
		if (NumberUtils.isCreatable(registaID)) {
			registaControll=new Regista(Long.parseLong(registaID));
		}

		// da implementare
		Film example = new Film(titolo, genere, UtilityForm.parseDateArrivoFromString(dataPubblicazione),
				minutiDurataControll, registaControll);

		try {
			request.setAttribute("film_list_attribute", filmService.findByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/film/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/film/list.jsp").forward(request, response);
	}

}
