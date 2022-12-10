package Sewa_PC;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import Database.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Rizky
 */

public class PC_CRUD extends Database implements PC_interface{
    public PC_CRUD() throws ClassNotFoundException, SQLException {
        super();
    }
    // *** CRUD (CREATE, READ, UPDATE, DELETE) ***
    
    // CREATE
    @Override
    public void create(String username, String pc, int biaya, int waktu, int total) {
        try {
            // RESET AUTO INCREMENT;
            String resetAI = String.format("ALTER TABLE pc_komputer AUTO_INCREMENT = %d", 0);
            this.setQuery(resetAI);
            this.execute();
            
            
            // TAMBAH DATA
            String sql = String.format("INSERT INTO pc_komputer (username, pc, biaya, waktu, total) VALUE ('%s','%s',%d, %d, %d)", username, pc,
                    biaya,waktu,total);
           
            this.setQuery(sql);
            this.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PC_CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Data Berhasil Di Tambahkan ");

    }
    
    
    // READ 
    @Override
    public void read(JTable tabelPC) {
        String header[] = {"NO", "Username", "PC", "Biaya per jam", "Durasi ( *jam )", "Total bayar"};    
        DefaultTableModel dtm = new DefaultTableModel(null, header);
        try {
             value = stmt.executeQuery("SELECT * FROM pc_komputer");
            while(value.next()){
                String[] row = {value.getString(1),value.getString(2),value.getString(3),value.getString(4),value.getString(5),value.getString(6)};
                dtm.addRow(row);
            }
            tabelPC.setModel(dtm);
        } catch (Exception e) {
        }
    }
    
    
    // UPDATE
    @Override
    public void update(String username, String pc, int biaya, int waktu, int total, int id) {
        String sql = String.format("UPDATE pc_komputer set username = '%s', pc = '%s', biaya = %d, waktu = %d, total = %d WHERE id = %d",
               username, pc, biaya, waktu, total, id );
        this.setQuery(sql);
        try {
            this.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PC_CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Berhasil diubah !");
    }
    
    // DELETE
    @Override
    public void delete(int NO) {
        String sql = String.format("DELETE FROM pc_komputer WHERE id = %d", NO);
        this.setQuery(sql);
        try {
            this.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PC_CRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Berhasil dihapus !");
    }
}
