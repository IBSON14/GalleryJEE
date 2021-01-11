package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DaoAdministrateur;
import com.dao.DaoAlbum;
import com.dao.DaoImage;
import com.dao.DaoUtilisateur;

/**
 * Servlet implementation class AdministrateurServlet
 */
@WebServlet({ "/admin/users", "/admin/albums", "/admin/user/voir", "/admin/user/supprimer", "/admin/album/voir", "/admin/album/supprimer", "/admin/album/photo/supprimer"})
public class AdministrateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_ALBUMS_ADMIN = "/WEB-INF/admin/listAlbums.jsp";
	private static final String VUE_USERS_ADMIN = "/WEB-INF/admin/listUsers.jsp";
	private static final String VUE_USER_DETAILS_ADMIN = "/WEB-INF/admin/detailsUser.jsp";
	private static final String VUE_ALBUMS_PHOTO_ADMIN = "/WEB-INF/admin/photosAlbum.jsp";


    public AdministrateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestedUrl = request.getServletPath();
		if (requestedUrl.equals("/admin/albums")) {
			request.setAttribute("albums", DaoAdministrateur.listerAlbums());
			getServletContext().getRequestDispatcher(VUE_ALBUMS_ADMIN).forward(request, response);
		} 
		else if(requestedUrl.equals("/admin/users")) {
			request.setAttribute("users", DaoAdministrateur.listerUtilisateurs());
			getServletContext().getRequestDispatcher(VUE_USERS_ADMIN).forward(request, response);	
		}
		else if(requestedUrl.equals("/admin/user/voir")) {
			request.setAttribute("user", DaoUtilisateur.getUserByLogin(request.getParameter("login")));
			request.setAttribute("albums", DaoAlbum.albumByUser(request.getParameter("login")));
			getServletContext().getRequestDispatcher(VUE_USER_DETAILS_ADMIN).forward(request, response);	
		}
		else if(requestedUrl.equals("/admin/user/supprimer")) {
		DaoUtilisateur.supprimer(request.getParameter("login"));
		response.sendRedirect(request.getContextPath() + "/admin/users");
		}
		else if(requestedUrl.equals("/admin/album/voir")) {
			request.setAttribute("imagesAlbum", DaoImage.lister(Integer.parseInt(request.getParameter("id"))));
			request.setAttribute("album", DaoAlbum.getAlbumById(Integer.parseInt(request.getParameter("id"))));
			getServletContext().getRequestDispatcher(VUE_ALBUMS_PHOTO_ADMIN).forward(request, response);	
		}
		else if(requestedUrl.equals("/admin/album/supprimer")) {
			DaoAlbum.supprimer(Integer.parseInt(request.getParameter("id")));
			response.sendRedirect(request.getContextPath() + "/admin/albums");
			}
		else if(requestedUrl.equals("/admin/album/photo/supprimer")) {
			DaoImage.supprimer(request.getParameter("url"));
			response.sendRedirect(request.getContextPath() + "/admin/albums");
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
