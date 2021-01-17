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

	        String sql = "SELECT ID,NAME,TEXT,USERNUMBER FROM MUTTER ORDER BY ID DESC";
	        PreparedStatement pStmt = con.prepareStatement(sql);

	        ResultSet rs = pStmt.executeQuery();

	        while (rs.next()){
	        	int id = rs.getInt("ID");
	        	String userName = rs.getString("NAME");
	        	String text = rs.getString("TEXT");
	        	String userNumber = rs.getString("usernumber");
	        	Mutter mutter = new Mutter (id,userName,text,userNumber);
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

    	        String sql = "INSERT INTO MUTTER (NAME,TEXT,USERNUMBER) VALUES(?,?,?)";
    	        PreparedStatement pStmt =con.prepareStatement(sql);
        		pStmt.setString(1, mutter.getUserName());
        		pStmt.setString(2, mutter.getText());
        		pStmt.setString(3, mutter.getUserNumber());
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


    //TODO 1件削除メソッド追加
    public boolean delete (String mutterNumber) {

		try{
    		getConnection();

	        String sql = "DELETE FROM MUTTER WHERE ID = ?";
	        PreparedStatement pStmt =con.prepareStatement(sql);
    		pStmt.setString(1, mutterNumber);
    		int result =pStmt.executeUpdate();
    		if (result!=1) {
    			return false;
    		}

	        ResultSet rs = pStmt.executeQuery();

	        con = null;
	        close();
	        return true;

	    } catch (SQLException e) {
	    	System.out.println("DAO接続できない");
	        e.printStackTrace();
	        return false;
	    } catch (DAOException e) {
	    	System.out.println("DAOエラー！");
	        e.printStackTrace();
	        return false;

	    }

	}


  //TODO 1件編集メソッド追加
    public boolean edit (String mutterNumber,String text) {

		try{
    		getConnection();

	        String sql = "UPDATE MUTTER SET TEXT=? WHERE ID = ?";
	        PreparedStatement pStmt =con.prepareStatement(sql);
    		pStmt.setString(1, text);
    		pStmt.setString(2, mutterNumber);
    		int result =pStmt.executeUpdate();
    		if (result!=1) {
    			return false;
    		}

	        ResultSet rs = pStmt.executeQuery();

	        con = null;
	        close();
	        return true;

	    } catch (SQLException e) {
	    	System.out.println("DAO接続できない");
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

    //TODO 1件削除メソッド追加
    //TODO 1件UPDATEpublic boolean create (Mutter mutter) {

}

