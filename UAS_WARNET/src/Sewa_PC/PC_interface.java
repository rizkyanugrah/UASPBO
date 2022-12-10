package Sewa_PC;

import javax.swing.JTable;
import Database.Database;
/**
 *
 * @author Rizky
 */
public interface PC_interface {    
    public void create(String username, String pc, int biaya, int waktu, int total);
    public void read(JTable tabelPC);
    public void update(String username, String pc, int biaya, int waktu, int total, int id);
    public void delete(int NO);    
}
