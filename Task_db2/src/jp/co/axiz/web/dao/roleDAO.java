package jp.co.axiz.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.axiz.web.entity.role;

public class roleDAO {

	private Connection connection;

	public roleDAO(Connection connection) {
		this.connection = connection;
	}

	public roleDAO() {

	}


	private static final String ROLE_SELECT_ALL = "select * from role ";
	private static final String ROLE_SELECT_ID = "select * from role where role_id = ?";

	public List<role> findAll() {
		List<role> list = new ArrayList<role>();
		try (PreparedStatement stmt = connection.prepareStatement(ROLE_SELECT_ALL)) {
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				role p= new role(rs.getInt("role_id"), rs.getString("role_name"));
				list.add(p);
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}


	public role findId(Integer id) {
		try(PreparedStatement stmt = connection.prepareStatement(ROLE_SELECT_ID)){
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new role(rs.getInt("role_id"), rs.getString("role_name"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	}




