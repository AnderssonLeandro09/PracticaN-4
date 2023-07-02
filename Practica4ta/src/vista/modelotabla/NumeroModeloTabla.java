package vista.modelotabla;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.EmptyException;
import controlador.ed.lista.exception.PositionException;

import javax.swing.table.AbstractTableModel;

public class NumeroModeloTabla extends AbstractTableModel{

    private ListaEnlazada<Integer> datos;

    public ListaEnlazada<Integer> getDatos() {
        return datos;
    }

    public void setDatos(ListaEnlazada<Integer> datos) {
        this.datos = datos;
    }
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Integer numero = datos.get(rowIndex);
            
            switch (columnIndex) {
                case 0: return numero.toString();
                
            }
            
        } catch (EmptyException | PositionException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return "Valor";
    }
    
    
}
