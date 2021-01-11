package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DaoImage;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image/details")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_DETAIL = "/WEB-INF/image/details.jsp";
   
    public ImageServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestedUrl = request.getServletPath();
		if (requestedUrl.equals("/image/details")) {
			request.setAttribute("imageDetails", DaoImage.getImageByUrl(request.getParameter("url")));
		getServletContext().getRequestDispatcher(VUE_DETAIL).forward(request, response);
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
