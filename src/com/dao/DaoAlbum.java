package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.beans.Album;
import com.enumeration.AlbumTheme;
import com.enumeration.AlbumType;

public class DaoAlbum {
	private static final String INSERT_ALBUM_PREPARED = "INSERT INTO ga_album VALUES (0, ?, ?, ?,?)";
	private static final String SELECT_ALBUMS_BY_USER = "SELECT * FROM ga_album WHERE login_user = ?";
	private static final Connection connexion = ConnexionManager.getInstance();
	private static final String SELECT_ALBUM_BY_ID = "SELECT * FROM ga_album WHERE id = ?";
	private static final String UPDATE_ALBUM_PREPARED = "UPDATE ga_album set nom = ?, type = ?, theme = ?, login_user = ? where id = ?";
	private static final String DELETE_Album_PREPARED = "DELETE FROM ga_album WHERE id = ?";
	

	public static void ajouter(Album album) throws DaoException {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(INSERT_ALBUM_PREPARED);
			preparedStatement.setString(1, album.getNom());
			preparedStatement.setString(2, String.valueOf(album.getType()));
			preparedStatement.setString(3, String.valueOf(album.getTheme()));
			preparedStatement.setString(4, album.getProprietaire());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Echec de l'ajout", e);
		}
	}

	public static List<Album> albumByUser(String login) throws DaoException {
		List<Album> albums = null;
		PreparedStatement statement;
		try {
			int id;
			String nom, proprietaire;
			AlbumType type;
			AlbumTheme theme;
			albums = new ArrayList<Album>();
			statement = connexion.prepareStatement(SELECT_ALBUMS_BY_USER);
			statement.setString(1, login);

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
	
	public static Album getAlbumById(int id) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(SELECT_ALBUM_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				int idAlbum = resultat.getInt("id");
				String nom = resultat.getString("nom");
				AlbumType type = AlbumType.valueOf(resultat.getString("type"));
				AlbumTheme theme = AlbumTheme.valueOf(resultat.getString("theme"));
				String proprietaire = resultat.getString("login_user");
				
				return new Album(idAlbum, nom, type, theme, proprietaire);
			}
		} catch (SQLException e) {
			throw new DaoException("UtilisateurServlet Doesn't exist!", e);
		}
		return null;
	}

	public static void modifier(Album album) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(UPDATE_ALBUM_PREPARED);
			preparedStatement.setString(1, album.getNom());
			preparedStatement.setString(2, String.valueOf(album.getType()));
			preparedStatement.setString(3, String.valueOf(album.getTheme()));
			preparedStatement.setString(4, album.getProprietaire());
			preparedStatement.setInt(5, album.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Echec Update", e);
		}		
	}

	public static void supprimer(int id) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(DELETE_Album_PREPARED);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Echec de la suppression", e);
		}	
	}


}
