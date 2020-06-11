package jp.co.axiz.web.service;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import jp.co.axiz.web.dao.roleDAO;
import jp.co.axiz.web.dao.userDAO;
import jp.co.axiz.web.entity.UserInfo;
import jp.co.axiz.web.entity.role;
import jp.co.axiz.web.util.DbUtil;
public class UserService {



	 public List<UserInfo> updateDt(Timestamp updateDatetime, Integer user_id){
	    	try (Connection conn = DbUtil.getConnection()){
	    		userDAO userDao = new userDAO(conn);

	    		return userDao.updateDt(updateDatetime, user_id);
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return null;
	    }


	 public List<UserInfo> insertDb(Timestamp insertDatetime, Integer user_id){
	    	try (Connection conn = DbUtil.getConnection()){
	    		userDAO userDao = new userDAO(conn);

	    		return userDao.insertDt(insertDatetime, user_id);
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return null;
	    }


	 public UserInfo authentication(String id, String pass) {
	        try (Connection conn = DbUtil.getConnection()) {
	            userDAO userDao = new userDAO(conn);
	            UserInfo user = userDao.findByIdAndPass(id, pass);

	            return user;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


	 public List<UserInfo> find(){
	    	try (Connection conn = DbUtil.getConnection()){
	    		userDAO userDao = new userDAO(conn);
	    		return userDao.find();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return Collections.emptyList();
	    }


	 public List<role> findAll(){
	    	try (Connection conn = DbUtil.getConnection()){
	    		roleDAO roleDao = new roleDAO(conn);
	    		return roleDao.findAll();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return Collections.emptyList();
	    }


	 public List<UserInfo> selectAll() {
	        try (Connection conn = DbUtil.getConnection()) {
	            userDAO userDao = new userDAO(conn);
	            return userDao.findAll();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


	 public List<UserInfo> listup(String name, String telephone) {
	        try (Connection conn = DbUtil.getConnection()) {
	            userDAO userDao = new userDAO(conn);
	            return userDao.findByIdAndTelephone(name, telephone);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


	 public List<UserInfo> listupByName(String name) {
	        try (Connection conn = DbUtil.getConnection()) {
	            userDAO userDao = new userDAO(conn);
	            return userDao.findByName(name);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


	 public List<UserInfo> listupByTelephone(String telephone) {
	        try (Connection conn = DbUtil.getConnection()) {
	            userDAO userDao = new userDAO(conn);
	            return userDao.findByTelephone(telephone);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }



	 public List<UserInfo> findById(String id){
	    	try (Connection conn = DbUtil.getConnection()){
	    		userDAO userDao = new userDAO(conn);

	    		return userDao.findById(id);
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return null;
	    }

		 public role findId(Integer id) {

			 try (Connection conn = DbUtil.getConnection()){
				 roleDAO roleDao = new roleDAO(conn);
				 role user = roleDao.findId(id);
				 return user;
			 }catch (Exception e) {
				 e.printStackTrace();
			 }
			 return null;
		 }

		 public List<UserInfo> addA(String id, String name, String tel, String pass, Integer roleId ){
		    	try (Connection conn = DbUtil.getConnection()){
		    		userDAO userDao = new userDAO(conn);

		    		return userDao.addA(id, name, tel, pass, roleId);
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    	}
		    	return null;
		    }


		 public List<UserInfo> findIdId(Integer user_id, String login_id) {
		        try (Connection conn = DbUtil.getConnection()) {
		            userDAO userDao = new userDAO(conn);
		            return userDao.findIdId(user_id, login_id);

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return null;
		    }


		 public List<UserInfo> update(String login_id, String user_name, String telephone, String password, Integer role_id, Integer user_id){
		    	try (Connection conn = DbUtil.getConnection()){
		    		userDAO userDao = new userDAO(conn);

		    		return userDao.update(login_id, user_name, telephone, password, role_id, user_id);
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    	}
		    	return null;
		    }

		 public List<UserInfo> delete(Integer user_id){
		    	try (Connection conn = DbUtil.getConnection()){
		    		userDAO userDao = new userDAO(conn);

		    		return userDao.delete(user_id);
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    	}
		    	return null;
		    }



}
