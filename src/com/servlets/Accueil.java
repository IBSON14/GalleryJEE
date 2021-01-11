package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DaoImage;


@WebServlet({"", "/accueil"})
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_ACCUEIL = "/WEB-INF/index.jsp";
       
    
    public Accueil() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("imagesPublics", DaoImage.imagePublics());
		getServletContext().getRequestDispatcher(VUE_ACCUEIL).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
