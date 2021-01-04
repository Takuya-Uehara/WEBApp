package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/webdb?characterEncoding=UTF-8&serverTimezone=JST";
    private final String DB_USER = "root";
    private final String DB_PASS = "mysqluehara";

    private Connection con = null;  // コネクションオブジェクト
    private Statement stmt = null;  // ステートメントオブジェクト
    private ConnectionManager cm; // コネクションマネージャー

    public List<Mutter> findAll() {
    	List <Mutter> mutterList = new ArrayList<>();

    	try{
    		getConnection();

	        String sql = "SELECT ID,NAME,TEXT FROM MUTTER ORDER BY ID DESC";
	        PreparedStatement pStmt = con.prepareStatement(sql);

	        ResultSet rs = pStmt.executeQuery();

	        while (rs.next()){
	        	int id = rs.getInt("ID");
	        	String userName = rs.getString("NAME");
	        	String text = rs.getString("TEXT");
	        	Mutter mutter = new Mutter (id,userName,text);
	        	mutterList.add(mutter);
	        }

	        con = null;
	        close();

	    } catch (SQLException e) {
	    	System.out.println("とれていません！！");
	        e.printStackTrace();
	        return null;
	    } catch (DAOException e) {
	    	System.out.println("DAOエラー！");
	        e.printStackTrace();
	        return null;

	    }

    return mutterList;
    }

    public boolean create (Mutter mutter) {



    		try{
        		getConnection();

    	        String sql = "INSERT INTO MUTTER (NAME,TEXT) VALUES(?,?)";
    	        PreparedStatement pStmt =con.prepareStatement(sql);
        		pStmt.setString(1, mutter.getUserName());
        		pStmt.setString(2, mutter.getText());
        		int result =pStmt.executeUpdate();
        		if (result!=1) {
        			return false;
        		}

    	        ResultSet rs = pStmt.executeQuery();

    	        con = null;
    	        close();
    	        return true;

    	    } catch (SQLException e) {
    	    	System.out.println("とれていません！！");
    	        e.printStackTrace();
    	        return false;
    	    } catch (DAOException e) {
    	    	System.out.println("DAOエラー！");
    	        e.printStackTrace();
    	        return false;

    	    }

    	}

    // Connectionの取得
    private void getConnection() throws DAOException{
        if ( this.con != null ){ return;    }
        cm = ConnectionManager.getInstance();
        con = cm.getConnection(); // データベースへの接続の取得
    }

    // Statementの取得
    private void createStmt() throws DAOException{
        if ( this.stmt != null){    return; }
        try {
            stmt =con.createStatement();
        } catch (SQLException e) {  // SQLに関する例外処理
            throw new DAOException("[createStmt]異常", e);
        }
    }

    private void close() throws DAOException {
        try {
            if (stmt != null) { stmt.close(); }
        } catch (SQLException e) {
            throw new DAOException("[closeStatement]異常", e);
        } finally {
            this.stmt = null;
            this.cm = null;
        }
    }

 }

