package vista.Util;

import javax.swing.JComboBox;

public class Utilidades {
    
    public static void cargarValoresCombo(JComboBox cbx, String combo) {
        cbx.removeAllItems();
        
        if(combo.equals("metodo")) {
            
            String[] metodos = {"QuickSort", "MergeSort"};
                    
            for(String metodo : metodos) {
                cbx.addItem(metodo);
            }
            
        } else {
            
            String[] tipos = {"Ascendente", "Descendente"};
                    
            for(String tipo : tipos) {
                cbx.addItem(tipo);
            }
            
        }
    }
}
