package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.Album;
import com.beans.Utilisateur;
import com.enumeration.AlbumTheme;
import com.enumeration.AlbumType;

public class DaoAdministrateur {
	private static final String SELECT_ALL_USERS = "SELECT * FROM ga_utilisateur";
	private static final String SELECT_ALL_ALBUMS = "SELECT * FROM ga_album";

	private static final Connection connexion = ConnexionManager.getInstance();

	public static List<Utilisateur> listerUtilisateurs() throws DaoException {
		List<Utilisateur> Utilisateurs = null;
		try {
			String nom, prenom, login, password, role;
			Utilisateurs = new ArrayList<Utilisateur>();
			Statement statement = connexion.createStatement();
			ResultSet reponse = statement.executeQuery(SELECT_ALL_USERS);

			while (reponse.next()) {
				nom = reponse.getString("nom");
				prenom = reponse.getString("prenom");
				login = reponse.getString("login");
				password = reponse.getString("password");
				role = reponse.getString("role");
				Utilisateurs.add(new Utilisateur(nom, prenom, login, password, role));

			}
			return Utilisateurs;
		} catch (SQLException e) {
			throw new DaoException("Une erreur inattendue s'est produite", e);
		}
	}

	public static List<Album> listerAlbums() throws DaoException {
		List<Album> albums = null;
		PreparedStatement statement;
		try {
			int id;
			String nom, proprietaire;
			AlbumType type;
			AlbumTheme theme;
			albums = new ArrayList<Album>();
			statement = connexion.prepareStatement(SELECT_ALL_ALBUMS);
			ResultSet reponse = statement.executeQuery();

			while (reponse.next()) {
				id = reponse.getInt("id");
				nom = reponse.getString("nom");
				type = AlbumType.valueOf(reponse.getString("type"));
				theme = AlbumTheme.valueOf(reponse.getString("theme"));
				proprietaire = reponse.getString("login_user");

				albums.add(new Album(id, nom, type, theme, proprietaire));
			}
			return albums;
		} catch (SQLException e) {
			throw new DaoException("Une erreur inattendue s'est produite", e);
		}
	}

}
