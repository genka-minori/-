package jp.co.axiz.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.co.axiz.web.entity.UserInfo;

public class userDAO {

	private Connection connection;

	public userDAO(Connection connection) {
		this.connection = connection;
	}

	public userDAO() {

	}

	private static final String USER_SELECT_ID_AND_PASS = "select * from user_info where login_id = ? and password = ?";
	private static final String USER_SELECT_ALL = "select * from user_info u join role r on u.role_id = r.role_id ";

	private static final String USER_SELECT = "select * from user_info u join role r on u.role_id = r.role_id where user_name = ? and telephone = ?";

	private static final String USER_SELECT_NAME = "select * from user_info u join role r on u.role_id = r.role_id where user_name = ?";
	private static final String USER_SELECT_TEL = "select * from user_info u join role r on u.role_id = r.role_id where telephone = ?";

	private static final String USER_SELECT_ID = "select * from user_info where login_id = ?";
	private static final String INSERT_USER = "insert into user_info (login_id, user_name, telephone, password, role_id) values (?, ?, ?, ?, ?)";

	private static final String SELECT_IDID ="select * from user_info where user_id <> ? and login_id = ?";
	private static final String UPDATE ="update user_info set login_id = ?, user_name = ?, telephone = ?, password =?, role_id =? where user_id = ?";
	private static final String DELETE = "delete from user_info where user_id = ?";

	private static final String UPDATE_DT = "insert into user_info (update_datetime) values (?) where user_id = ?";
	private static final String INSERT_DT = "insert into user_info (create_datetime) values (?) where user_id = ?";

	public List<UserInfo> updateDt(Timestamp updateDatetime, Integer user_id) {
		List<UserInfo> list = new ArrayList<UserInfo>();

		try (PreparedStatement stmt = connection.prepareStatement(UPDATE_DT)) {
			stmt.setTimestamp(1, updateDatetime);
			stmt.setInt(2, user_id);

			int i = stmt.executeUpdate();

			System.out.println("結果：" + i);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public List<UserInfo> insertDt(Timestamp insertDatetime, Integer user_id) {
		List<UserInfo> list = new ArrayList<UserInfo>();

		try (PreparedStatement stmt = connection.prepareStatement(INSERT_DT)) {
			stmt.setTimestamp(1, insertDatetime);
			stmt.setInt(2, user_id);

			int i = stmt.executeUpdate();

			System.out.println("結果：" + i);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}



	public List<UserInfo> delete(Integer user_id) {
		List<UserInfo> list = new ArrayList<UserInfo>();

		try (PreparedStatement stmt = connection.prepareStatement(DELETE)) {
			stmt.setInt(1,user_id);

			int i = stmt.executeUpdate();

			System.out.println("結果：" + i);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}


	public List<UserInfo> update(String login_id, String user_name, String telephone, String password, Integer role_id, Integer user_id) {
		List<UserInfo> list = new ArrayList<UserInfo>();

		try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
			stmt.setString(1, login_id);
			stmt.setString(2, user_name);
			stmt.setString(3, telephone);
			stmt.setString(4, password);
			stmt.setInt(5, role_id);
			stmt.setInt(6, user_id);

			int i = stmt.executeUpdate();

			System.out.println("結果：" + i);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}


	public List<UserInfo> findIdId(Integer user_id, String login_id) {
		List<UserInfo> list = new ArrayList<UserInfo>();

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_IDID)) {
			stmt.setInt(1,user_id);
			stmt.setString(2, login_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserInfo u = new UserInfo(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"),
						rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"));
				list.add(u);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}




	public UserInfo findByIdAndPass(String id, String pass) {
		try (PreparedStatement stmt = connection.prepareStatement(USER_SELECT_ID_AND_PASS)) {
			stmt.setString(1, id);
			stmt.setString(2, pass);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new UserInfo(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"),
						rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<UserInfo> find() {
		List<UserInfo> list = new ArrayList<UserInfo>();

		try (PreparedStatement stmt = connection.prepareStatement(USER_SELECT_ID_AND_PASS)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserInfo u = new UserInfo(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"),
						rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"));
				list.add(u);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}


	public List<UserInfo> findAll() {
		List<UserInfo> list = new ArrayList<UserInfo>();

		try (PreparedStatement stmt = connection.prepareStatement(USER_SELECT_ALL)) {

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserInfo u = new UserInfo(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"),
						rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"), rs.getString("role_name"));
				list.add(u);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}



	public List<UserInfo> findByIdAndTelephone(String name, String telephone) {
		List<UserInfo> list = new ArrayList<UserInfo>();

		try (PreparedStatement stmt = connection.prepareStatement(USER_SELECT)) {
			stmt.setString(1, name);
			stmt.setString(2, telephone);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserInfo u = new UserInfo(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"),
						rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"), rs.getString("role_name"));
				list.add(u);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}


	public List<UserInfo> findByName(String name) {
		List<UserInfo> list = new ArrayList<UserInfo>();

		try (PreparedStatement stmt = connection.prepareStatement(USER_SELECT_NAME)) {
			stmt.setString(1, name);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserInfo u = new UserInfo(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"),
						rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"), rs.getString("role_name"));
				list.add(u);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}


	public List<UserInfo> findByTelephone(String telephone) {
		List<UserInfo> list = new ArrayList<UserInfo>();

		try (PreparedStatement stmt = connection.prepareStatement(USER_SELECT_TEL)) {
			stmt.setString(1, telephone);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserInfo u = new UserInfo(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"),
						rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"), rs.getString("role_name"));
				list.add(u);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}





	public List<UserInfo> findById(String id) {
		List<UserInfo> list = new ArrayList<UserInfo>();

		try (PreparedStatement stmt = connection.prepareStatement(USER_SELECT_ID)) {
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserInfo u = new UserInfo(rs.getInt("user_id"), rs.getString("login_id"), rs.getString("user_name"),
						rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"));
				list.add(u);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	/*
				public UserInfo findByIdB(String id) {
					try (PreparedStatement stmt = connection.prepareStatement(USER_SELECT_ID)) {
						stmt.setString(1, id);

						ResultSet rs = stmt.executeQuery();

						if (rs.next()) {
							return new UserInfo(rs.getInt("user_id") ,rs.getString("login_id"), rs.getString("user_name"), rs.getString("telephone"),rs.getString("password"), rs.getInt("role_id"));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return null;
				}

	*/

			public List<UserInfo> addA(String id, String name, String tel, String pass, Integer roleId ) {
				List<UserInfo> list = new ArrayList<UserInfo>();

				try (PreparedStatement stmt = connection.prepareStatement(INSERT_USER)) {
					stmt.setString(1, id);
					stmt.setString(2, name);
					stmt.setString(3, tel);
					stmt.setString(4, pass);
					stmt.setInt(5, roleId);

					int i = stmt.executeUpdate();

					System.out.println("結果：" + i);
			/*ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserInfo u = new UserInfo(rs.getString("login_id"), rs.getString("user_name"),
						rs.getString("telephone"), rs.getString("password"), rs.getInt("role_id"));
				list.add(u);*/

				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				return list;
			}


}
