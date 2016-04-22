package Servlets;

import Interfaces.IController;
import Utils.DispatcherControl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet(name = "MainServlet", urlPatterns = "/Action/*")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DispatcherControl dispatcher = new DispatcherControl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatch(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatch(request, response);
	}

	private void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		IController controller = dispatcher.getController(request.getRequestURI());
		if (controller != null) {
			response.sendRedirect(controller.run(request));
		}
	}

}
