package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class AccountDAO {

    private Connection con = null;  // コネクションオブジェクト
    private Statement stmt = null;  // ステートメントオブジェクト
    private ConnectionManager cm; // コネクションマネージャー

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


    public User findAccount (User user) throws DAOException {
    	User returnAb = new User();
        getConnection();
        int count = 0;
        String sql = "SELECT id, name, pass,number FROM member WHERE id = ? AND pass = ?";



        try(PreparedStatement pstmt = con.prepareStatement(sql)) {
        	PreparedStatement ps= con.prepareStatement(sql);

            ps.setString(1, user.getId());
            ps.setString(2, user.getPass());

            ResultSet rs = ps.executeQuery();



            if (rs.next()) {
                // 見つかったアカウント情報を戻り値にセット
                returnAb.setId(rs.getString("id"));
                returnAb.setPass(rs.getString("pass"));
                returnAb.setName(rs.getString("name"));
                returnAb.setNumber(rs.getString("number"));
            } else {
                // アカウントがなければnullを返す
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("sql失敗");
            return null;
        }
        return returnAb;
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