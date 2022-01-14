package stplate.utils;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import stplate.beans.Product;
import stplate.beans.UserAccount;
import stplate.beans.Tile;
import stplate.beans.Slab; 
import stplate.beans.Noslab; 


public class DBUtils {
 
    public static UserAccount findUser(Connection conn, //
            String userName, String password) throws SQLException {
 
        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a " //
                + " where a.User_Name = ? and a.password= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String gender = rs.getString("Gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }
 
    public static UserAccount findUser(Connection conn, String userName) throws SQLException {
 
        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "//
                + " where a.User_Name = ? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
 
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String password = rs.getString("Password");
            String gender = rs.getString("Gender");
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            user.setGender(gender);
            return user;
        }
        return null;
    }
 
    public static List<Product> queryProduct(Connection conn) throws SQLException {
        String sql = "Select a.Code, a.Name, a.Price from Product a ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Product> list = new ArrayList<Product>();
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            Product product = new Product();
            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
            list.add(product);
        }
        return list;
    }
 
    public static Product findProduct(Connection conn, String code) throws SQLException {
        String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            Product product = new Product(code, name, price);
            return product;
        }
        return null;
    }
 
    public static void updateProduct(Connection conn, Product product) throws SQLException {
        String sql = "Update Product set Name =?, Price=? where Code=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, product.getName());
        pstm.setFloat(2, product.getPrice());
        pstm.setString(3, product.getCode());
        pstm.executeUpdate();
    }
 
    public static void insertProduct(Connection conn, Product product) throws SQLException {
        String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, product.getCode());
        pstm.setString(2, product.getName());
        pstm.setFloat(3, product.getPrice());
 
        pstm.executeUpdate();
    }
 
    public static void deleteProduct(Connection conn, String code) throws SQLException {
        String sql = "Delete From Product where Code= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, code);
 
        pstm.executeUpdate();
    }   
    
 public static List<Tile> queryTiles(Connection conn) throws SQLException {
     String sql = "Select t.id, t.x, t.y, t.comments from Tiles t ";

     PreparedStatement pstm = conn.prepareStatement(sql);

     ResultSet rs = pstm.executeQuery();
     List<Tile> list = new ArrayList<Tile>();
     while (rs.next()) {
    	 int id = rs.getInt("id");
    	 int x = rs.getInt("x");
    	 int y = rs.getInt("y");
         String comments = rs.getString("comments");
         Tile tile = new Tile();
         tile.setId(id);
         tile.setX(x);
         tile.setY(y);
         tile.setComments(comments);
         list.add(tile);
     }
     return list;
 }

 public static Tile findTile(Connection conn, String idStr) throws SQLException {
     String sql = "Select t.id, t.x, t.y, t.comments from Tiles t where t.id=?";

     PreparedStatement pstm = conn.prepareStatement(sql);
     pstm.setString(1, idStr);

     ResultSet rs = pstm.executeQuery();

     while (rs.next()) {
    	 int id = rs.getInt("id");
    	 int x = rs.getInt("x");
    	 int y = rs.getInt("y");
         String comments = rs.getString("comments");;
         Tile tile = new Tile(id, x, y, comments);
         return tile;
     }
     return null;
 }

 public static void updateTile(Connection conn, Tile tile) throws SQLException {
     String sql = "Update Tiles set x =?, y =?, comments=? where id=? ";

     PreparedStatement pstm = conn.prepareStatement(sql);

     pstm.setInt(1, tile.getX());
     pstm.setInt(2, tile.getY());
     pstm.setString(3, tile.getComments());
     pstm.setInt(4, tile.getId());
     pstm.executeUpdate();
 }

 public static void insertTile(Connection conn, Tile tile) throws SQLException {
     String sql = "Insert into Tiles(id, x, y, comments) values (?,?,?,?)";

     PreparedStatement pstm = conn.prepareStatement(sql);
     
     pstm.setInt(1, tile.getId());
     pstm.setInt(2, tile.getX());
     pstm.setInt(3, tile.getY());
     pstm.setString(4, tile.getComments());
    
     pstm.executeUpdate();
 }

 public static void deleteTile(Connection conn, int id) throws SQLException {
     String sql = "Delete From Tiles where id= ?";

     PreparedStatement pstm = conn.prepareStatement(sql);

     pstm.setInt(1, id);

     pstm.executeUpdate();
 }
 

 public static int  findTileId(Connection conn) throws SQLException {
     String sql = "Select Max(id) as id  from Tiles";
     
     PreparedStatement pstm = conn.prepareStatement(sql);
     ResultSet rs = pstm.executeQuery();

     while (rs.next()) {
    	 int id = rs.getInt("id");
    	 
         return id;
     }
     return 0;
 } 
 
 public static List<Slab> querySlabs(Connection conn, Noslab noslab) throws SQLException {
     String sql = "Select s.id, s.xl, s.xr from Slabs s where id>=? and id<=? limit 80";

     PreparedStatement pstm = conn.prepareStatement(sql);
     pstm.setInt(1, noslab.getIdfirst());
     pstm.setInt(2, noslab.getIdlast());

     ResultSet rs = pstm.executeQuery();
     List<Slab> list = new ArrayList<Slab>();
     while (rs.next()) {
    	 int id = rs.getInt("id");
    	 int xl = rs.getInt("xl");
    	 int xr = rs.getInt("xr");
    	 Slab slab = new Slab();
    	 slab.setId(id);
    	 slab.setXl(xl);
    	 slab.setXr(xr);
         list.add(slab);
     }
     return list;
 } 

 
 public static List<Noslab> queryNoslabs(Connection conn) throws SQLException {
    String sql = "Select n.id, n.slabno, n.idfirst, n.idlast from noslabs n ";

    PreparedStatement pstm = conn.prepareStatement(sql);

    ResultSet rs = pstm.executeQuery();
    List<Noslab> list = new ArrayList<Noslab>();
    while (rs.next()) {
    	 int id = rs.getInt("id");
       	 String slabno = rs.getString("slabno");
 	     int idfirst = rs.getInt("idfirst");
    	 int idlast = rs.getInt("idlast");
         Noslab noslab = new Noslab();
         noslab.setId(id);
         noslab.setSlabno(slabno);
         noslab.setIdfirst(idfirst);
         noslab.setIdlast(idlast);
         list.add(noslab);
    }
    return list;
 }

 public static Noslab findNoslab(Connection conn, int idno) throws SQLException {
    String sql = "Select n.id, n.slabno, n.idfirst, n.idlast from Noslabs n where n.id=?";

    PreparedStatement pstm = conn.prepareStatement(sql);
    pstm.setInt(1, idno);

    ResultSet rs = pstm.executeQuery();

    while (rs.next()) {
    	 int id = rs.getInt("id");
    	 String slabno = rs.getString("slabno");
	     int idfirst = rs.getInt("idfirst");
	     int idlast = rs.getInt("idlast");
	     Noslab noslab = new Noslab(id, slabno, idfirst, idlast);
         return noslab;
    }
    return null;
 }

 public static void insertCurrent(Connection conn, int ncurrent) throws SQLException {
     String sql = "Insert into nocurrent (ncurrent) values (?)";

     PreparedStatement pstm = conn.prepareStatement(sql);
     
     pstm.setInt(1, ncurrent);
    
     pstm.executeUpdate();
 }

 public static int  findCurrent(Connection conn) throws SQLException {
     String sql = "Select ncurrent from nocurrent order by id desc limit 1";
     
     PreparedStatement pstm = conn.prepareStatement(sql);
     ResultSet rs = pstm.executeQuery();

     while (rs.next()) {
    	 int ncurrent = rs.getInt("ncurrent");  	 
         return ncurrent;
     }
     return 0;
 }
 
 public static void insertTileCurrent(Connection conn, int tcurrent) throws SQLException {
     String sql = "Insert into tilecurrent (tcurrent) values (?)";

     PreparedStatement pstm = conn.prepareStatement(sql);
     
     pstm.setInt(1, tcurrent);
    
     pstm.executeUpdate();
 } 
 
 public static int  findTileCurrent(Connection conn) throws SQLException {
     String sql = "Select tcurrent from tilecurrent order by id desc limit 1";
     
     PreparedStatement pstm = conn.prepareStatement(sql);
     ResultSet rs = pstm.executeQuery();

     while (rs.next()) {
    	 int tcurrent = rs.getInt("tcurrent");  	 
         return tcurrent;
     }
     return 0;
 } 
 
 
}