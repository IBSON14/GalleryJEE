package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.beans.Utilisateur;

public class DaoUtilisateur {
	private static final String INSERT_USER_PREPARED = "INSERT INTO ga_utilisateur VALUES (0, ?, ?, ?, ?, ?)";
	private static final String UPDATE_USER_PREPARED = "UPDATE ga_utilisateur SET prenom = ?, nom = ?, password= ? WHERE login = ?";
	private static final String DELETE_USER_PREPARED = "DELETE FROM ga_utilisateur WHERE login = ?";
	private static final String SELECT_USER_BY_LOGIN_PREPARED = "SELECT * FROM ga_utilisateur WHERE login = ?";
	private static final Connection connexion = ConnexionManager.getInstance();
	private static final String SELECT_USER_BY_LOGIN_PASS_PREPARED = "SELECT * FROM ga_utilisateur WHERE login = ? and password = ?";

	public static void ajouter(Utilisateur utilisateur) throws DaoException {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(INSERT_USER_PREPARED);
			preparedStatement.setString(1, utilisateur.getPrenom());
			preparedStatement.setString(2, utilisateur.getNom());
			preparedStatement.setString(3, utilisateur.getLogin());
			preparedStatement.setString(4, utilisateur.getPassword());
			preparedStatement.setString(5, utilisateur.getRole());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Echec de l'ajout", e);
		}
	}


	public static void modifier(Utilisateur Utilisateur) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(UPDATE_USER_PREPARED);
			preparedStatement.setString(1, Utilisateur.getPrenom());
			preparedStatement.setString(2, Utilisateur.getNom());
			preparedStatement.setString(3, Utilisateur.getPassword());
			preparedStatement.setString(4, Utilisateur.getLogin());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Echec de la mise à jour", e);
		}
	}

	public static void supprimer(String login) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(DELETE_USER_PREPARED);
			preparedStatement.setString(1, login);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("Echec de la suppression", e);
		}
	}

	public static Utilisateur getUserByLogin(String login) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(SELECT_USER_BY_LOGIN_PREPARED);
			preparedStatement.setString(1, login);
			ResultSet resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String password = resultat.getString("password");
				String role = resultat.getString("role");

				return new Utilisateur(nom, prenom, login, password, role);
			}
		} catch (SQLException e) {
			throw new DaoException("Utilisateur Doesn't exist!", e);
		}
		return null;
	}
	public static boolean seConnecter(String login, String password) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connexion.prepareStatement(SELECT_USER_BY_LOGIN_PASS_PREPARED);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("Utilisateur Doesn't exist!", e);
		}
		return false;
	}
}
