package controlador.ed.lista;

import controlador.ed.lista.exception.EmptyException;
import controlador.ed.lista.exception.PositionException;

public class ListaEnlazada<E> {

    private NodoLista<E> cabecera;
    private Integer size = 0;

    public NodoLista<E> getCabecera() {
        return cabecera;
    }

    public void setCabecera(NodoLista<E> cabecera) {
        this.cabecera = cabecera;
    }

    public boolean isEmpty() {
        return cabecera == null;
    }

    public boolean insertar(E info) {
        NodoLista<E> nuevo = new NodoLista<>(info, null);
        if (isEmpty()) {
            this.cabecera = nuevo;
            this.size++;
        } else {
            NodoLista<E> aux = cabecera;
            while (aux.getSiguiente()!= null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
            this.size++;
        }
        return true;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer size() {
        return size;
    }

    public void imprimir() throws EmptyException {
        if (isEmpty()) {
            throw new EmptyException();
        } else {
            NodoLista<E> aux = cabecera;
            System.out.println("----------Lista Enlazada ----------");

            for (int i = 0; i < size(); i++) {
                System.out.println(aux.getInformacion()+ " ");
                aux = aux.getSiguiente();
            }
            System.out.println("");
            System.out.println("---------Lista Enlazada Terminada--------");
        }
    }

    public void deleteAll() {
        this.cabecera = null;
        this.size = 0;
    }

    public void insertarInicio(E info) {
        if (isEmpty()) {
            insertar(info);
        } else {
            NodoLista<E> nuevo = new NodoLista<>(info, null);
            nuevo.setSiguiente(cabecera);
            cabecera = nuevo;
            size++;
        }
    }

    public void insertarPosicion(E info, Integer pos) throws PositionException {
        if (isEmpty()) {
            insertar(info);
        } else if (pos >= 0 && pos < size() && pos == 0) {
            insertarInicio(info);
        } else if (pos >= 0 && pos < size()) {
            NodoLista<E> nuevo = new NodoLista<>(info, null);
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < (pos - 1); i++) {
                aux = aux.getSiguiente();
            }
            NodoLista<E> sig = aux.getSiguiente();
            aux.setSiguiente(nuevo);
            nuevo.setSiguiente(sig);
            size++;
        } else {
            throw new PositionException();
        }
    }

    public E get(Integer pos) throws EmptyException, PositionException {
        if (isEmpty()) {
            throw new EmptyException();
        } else {
            E dato = null;
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    dato = cabecera.getInformacion();
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    dato = aux.getInformacion();
                }
            } else {
                throw new PositionException();
            }
            return dato;
        }
    }

    public void update(Integer pos, E dato) throws EmptyException, PositionException {
        if (isEmpty()) {
            throw new EmptyException();
        } else {
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    cabecera.setInformacion(dato);
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSiguiente();
                    }
                    dato = aux.getInformacion();
                }
            } else {
                throw new PositionException();
            }
        }
    }

    public E delete(Integer pos) throws EmptyException, PositionException {
        if (isEmpty()) {
            throw new EmptyException();
        } else {
            E dato = null;
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    dato = cabecera.getInformacion();
                    cabecera = cabecera.getSiguiente();
                    size--;
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < (pos - 1); i++) {
                        aux = aux.getSiguiente();
                    }

                    NodoLista<E> aux1 = aux.getSiguiente();
                    dato = aux1.getInformacion();

                    NodoLista<E> proximo = aux1.getSiguiente();
                    aux.setSiguiente(proximo);
                    size--;
                }
            } else {
                throw new PositionException();
            }
            return dato;
        }
    }

    public void set(Integer pos, E info) throws PositionException {
        if (isEmpty()) {
            throw new PositionException();
        } else if (pos >= 0 && pos < size()) {
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < pos; i++) {
                aux = aux.getSiguiente();
            }
            aux.setInformacion(info);
        } else {
            throw new PositionException();
        }
    }

    public E[] toArray() {
        E[] matriz = null;
        if (this.size > 0) {
            matriz = (E[]) java.lang.reflect.Array.newInstance(cabecera.getInformacion().getClass(), this.size);
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < this.size; i++) {
                matriz[i] = aux.getInformacion();
                aux = aux.getSiguiente();
            }

        }
        return matriz;
    }

    public ListaEnlazada<E> toList(E[] matriz) {
        this.deleteAll();
        for (int i = 0; i < matriz.length; i++) {
            this.insertar(matriz[i]);
        }
        return this;
    }
}
