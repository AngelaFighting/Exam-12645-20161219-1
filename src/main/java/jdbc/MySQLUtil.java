package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Film;

public class MySQLUtil {
	private final static String DBURL = 
			"jdbc:mysql://127.0.0.1:3306/sakila?characterEncoding=UTF-8";
	private final static String DBUSER="root";
	private final static String DBPWD="5354";
	
	/**连接数据库**/
	private Connection connect(){
		Connection con = null;
        try {
            //加载MySQL驱动器，其中com.mysql.jdbc.Driver包含在mysqldriver.jar中
            Class jdbcDriver=Class.forName("com.mysql.jdbc.Driver");      
            //创建数据库连接对象：
            con = DriverManager.getConnection(DBURL,DBUSER,DBPWD);
            System.out.println("连接数据库成功！");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
	}	

    /**关闭数据库连接**/
    public void close(Connection con){
        try {
            if(con!=null){
                con.close();
                System.out.println("成功关闭数据库！");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
	/**用户登录**/
	public boolean login(String name){
		Connection con=connect();
		boolean result=false;
        try {
            //创建执行SQL语句的语句(数据库执行对象)
            Statement stmt = con.createStatement();
            //executeQuery(String sql)执行SQL查询语句，返回ResultSet
            ResultSet rs = stmt.executeQuery(
            		"select * from customer where first_name='"+name+"'"); 
            if(rs.next()){
            	//System.out.println(rs.getString(3));
            	result=true;
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally{ 
        	close(con);
        }
        return result;
	}
	
	/**获取所有语言**/
	public Map<Integer,String> getLanguage(){
		Connection con=connect();
		Map<Integer,String> map=new HashMap<Integer,String>();
        try {
            //创建执行SQL语句的语句(数据库执行对象)
            Statement stmt = con.createStatement();
            //executeQuery(String sql)执行SQL查询语句，返回ResultSet
            ResultSet rs = stmt.executeQuery(
            		"select language_id,name from language"); 
            while(rs.next()){
            	map.put(rs.getInt("language_id"),rs.getString("name"));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUtil.class.getName()).log(Level.SEVERE, null, ex);
        }finally{ 
        	close(con);
        }
        return map;
	}
	
	/**获取所有电影信息**/
	public List<Film> list(){
		Connection con=connect();
		List<Film> list=new ArrayList<Film>();
        try {
            //创建执行SQL语句的语句(数据库执行对象)
            Statement stmt = con.createStatement();
            //executeQuery(String sql)执行SQL查询语句，返回ResultSet
            ResultSet rs = stmt.executeQuery(
            		"select film_id,title,description,"
            		+ "language_id from film"); 
            while(rs.next()){
            	Film film=new Film();
            	film.setId(rs.getInt("film_id"));
            	film.setTitle(rs.getString("title"));
            	film.setDescription(rs.getString("description"));
            	film.setLanguage_id(rs.getInt("language_id"));
            	list.add(film);
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally{ 
        	close(con);
        }
        return list;
	}
	
	/**获取某个Film对象**/
	public Film get(int film_id){
		Connection con=connect();
		Film film=new Film();
        try {
            //创建执行SQL语句的语句(数据库执行对象)
            Statement stmt = con.createStatement();
            //executeQuery(String sql)执行SQL查询语句，返回ResultSet
            ResultSet rs = stmt.executeQuery(
            		"select film_id,title,description,"
            		+ "language_id from film where film_id="+film_id); 
            if(rs.next()){
            	film.setId(rs.getInt("film_id"));
            	film.setTitle(rs.getString("title"));
            	film.setDescription(rs.getString("description"));
            	film.setLanguage_id(rs.getInt("language_id"));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally{ 
        	close(con);
        }
        return film;
	}

	/**插入一条Film记录**/
	public boolean insert(Film film){
		Connection con=connect();
		boolean result=false;
        try {
            //创建执行SQL语句的语句(数据库执行对象)
            Statement stmt = con.createStatement();
            //executeUpdate(String sql)执行SQL更新语句，返回受影响记录数
            int count=stmt.executeUpdate("insert into "
            		+ "film (title,description,language_id) "
            		+ "values ('"+film.getTitle()+"','"+
            		film.getDescription()+"',"+film.getLanguage_id()+")");            
            stmt.close();
            System.out.println(count);
            if(count>0) result=true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally{ 
        	close(con);
        }
        return result;
	}
	
	/**修改一条Film记录**/
	public boolean update(Film film){
		Connection con=connect();
		boolean result=false;
        try {
            //创建执行SQL语句的语句(数据库执行对象)
            Statement stmt = con.createStatement();
            //executeUpdate(String sql)执行SQL更新语句，返回受影响记录数
            int count=stmt.executeUpdate("update film set title='"+
            film.getTitle()+"',description='"+film.getDescription()
            +"',language_id="+film.getLanguage_id()+" "
            + "where film_id="+film.getId());            
            stmt.close();
            System.out.println(count);
            if(count>0) result=true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally{ 
        	close(con);
        }
        return result;
	}
	
	/**删除一条Film记录**/
	public boolean delete(int film_id){
		Connection con=connect();
		boolean result=false;
        try {
            //创建执行SQL语句的语句(数据库执行对象)
            Statement stmt = con.createStatement();
            //executeUpdate(String sql)执行SQL更新语句，返回受影响记录数
            int count=stmt.executeUpdate("delete from film "
            		+ "where film_id="+film_id);            
            stmt.close();
            System.out.println(count);
            if(count>0) result=true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally{ 
        	close(con);
        }
        return result;
	}

    public static void main(String args[]){
    	MySQLUtil test=new MySQLUtil();
    	//System.out.println(test.login("lili"));
    	//System.out.println(test.getLanguage());
    	Film film=new Film();
    	//film.setId(1002);
    	film.setTitle("test1");
    	film.setLanguage_id(1);
    	System.out.println(test.delete(1001));
    }
}
