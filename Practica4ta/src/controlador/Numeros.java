package controlador;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.EmptyException;
import controlador.ed.lista.exception.PositionException;
import java.util.Random;

public class Numeros {

    private ListaEnlazada<Integer> numeros;
    private int tipo;

    public Numeros() {
        this.numeros = new ListaEnlazada<>();
        generarNumeros();
    }

    private void generarNumeros() {
        Random random = new Random();
        for (int i = 0; i < 20000; i++) {
            numeros.insertar(random.nextInt(1000));
        }
    }

    public void ordenar(int tipo, int metodo) {
        this.tipo = tipo;

        if (metodo == 0) {
            quickSort();
        } else {
            mergeSort();
        }
    }

    public void mergeSort() {
        ListaEnlazada<Integer> aux = numeros;
        numeros = ordenarMergeSort(aux);
    }

    public ListaEnlazada<Integer> ordenarMergeSort(ListaEnlazada<Integer> lista) {
        if (lista.size() <= 1) {
            return lista;
        }

        ListaEnlazada<Integer> listaIzq = new ListaEnlazada<>();
        ListaEnlazada<Integer> listaDer = new ListaEnlazada<>();
        int medio = lista.size() / 2;

        for (int i = 0; i < medio; i++) {
            try {
                listaIzq.insertar(lista.get(i));
            } catch (EmptyException | PositionException e) {
                e.printStackTrace();
            }
        }

        for (int i = medio; i < lista.size(); i++) {
            try {
                listaDer.insertar(lista.get(i));
            } catch (EmptyException | PositionException e) {
                e.printStackTrace();
            }
        }

        listaIzq = ordenarMergeSort(listaIzq);
        listaDer = ordenarMergeSort(listaDer);

        return fusionarListas(listaIzq, listaDer);
    }

    public ListaEnlazada<Integer> fusionarListas(ListaEnlazada<Integer> listaIzq, ListaEnlazada<Integer> listaDer) {
        ListaEnlazada<Integer> listaOrdenada = new ListaEnlazada<>();

        while (!listaIzq.isEmpty() && !listaDer.isEmpty()) {
            try {
                int valorIzq = listaIzq.get(0);
                int valorDer = listaDer.get(0);

                if (tipo == 0 ? valorIzq <= valorDer : valorIzq >= valorDer) {
                    listaOrdenada.insertar(valorIzq);
                    listaIzq.delete(0);
                } else {
                    listaOrdenada.insertar(valorDer);
                    listaDer.delete(0);
                }
            } catch (EmptyException | PositionException e) {
                e.printStackTrace();
            }
        }

        while (!listaIzq.isEmpty()) {
            try {
                listaOrdenada.insertar(listaIzq.get(0));
                listaIzq.delete(0);
            } catch (EmptyException | PositionException e) {
                e.printStackTrace();
            }
        }

        while (!listaDer.isEmpty()) {
            try {
                listaOrdenada.insertar(listaDer.get(0));
                listaDer.delete(0);
            } catch (EmptyException | PositionException e) {
                e.printStackTrace();
            }
        }

        return listaOrdenada;
    }

    public void quickSort() {
        quickSortRecursivo(numeros, 0, numeros.size() - 1);
    }

    private void quickSortRecursivo(ListaEnlazada<Integer> lista, int inicio, int fin) {
        if (inicio < fin) {
            int indicePivote = particion(lista, inicio, fin);
            quickSortRecursivo(lista, inicio, indicePivote - 1);
            quickSortRecursivo(lista, indicePivote + 1, fin);
        }
    }

    private int particion(ListaEnlazada<Integer> lista, int inicio, int fin) {
        int pivote = getPivote(lista, inicio, fin);
        int indicePivote = inicio;

        for (int i = inicio; i < fin; i++) {
            try {
                if (tipo == 0 ? lista.get(i) <= pivote : lista.get(i) >= pivote) {
                    intercambiar(lista, i, indicePivote);
                    indicePivote++;
                }
            } catch (EmptyException | PositionException e) {
                e.printStackTrace();
            }
        }

        intercambiar(lista, indicePivote, fin);

        return indicePivote;
    }

    private int getPivote(ListaEnlazada<Integer> lista, int inicio, int fin) {
        try {
            int medio = (inicio + fin) / 2;
            int valorInicio = lista.get(inicio);
            int valorMedio = lista.get(medio);
            int valorFin = lista.get(fin);

            if (tipo == 0) {
                if (valorInicio <= valorMedio && valorInicio <= valorFin) {
                    return valorInicio;
                } else if (valorMedio <= valorInicio && valorMedio <= valorFin) {
                    return valorMedio;
                } else {
                    return valorFin;
                }
            } else {
                if (valorInicio >= valorMedio && valorInicio >= valorFin) {
                    return valorInicio;
                } else if (valorMedio >= valorInicio && valorMedio >= valorFin) {
                    return valorMedio;
                } else {
                    return valorFin;
                }
            }
        } catch (EmptyException | PositionException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private void intercambiar(ListaEnlazada<Integer> lista, int i, int j) {
        try {
            int temp = lista.get(i);
            lista.set(i, lista.get(j));
            lista.set(j, temp);
        } catch (EmptyException | PositionException e) {
            e.printStackTrace();
        }
    }

    public ListaEnlazada<Integer> getNumeros() {
        return numeros;
    }
}

