package controller.tda_listas;

import controller.tda_listas.exceptions.LlenoException;
import controller.tda_listas.exceptions.VacioExceptions;

public class StackOperation<E> extends ListaEnlazada<E> {

    private Integer top;

    public StackOperation(Integer top) {
        this.top = top;
    }

    public Boolean verify() {
        return getSize() <= top;
    }

    public void push(E dato) throws LlenoException {
        if (verify()) {
            addFist(dato);
        } else {
            throw new LlenoException("Pila Llena");
        }
    }

    public E pop() throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Pila Vacia");
        } else {
            return deleteLast();
        }
    }

    Integer getTop() {
        throw new UnsupportedOperationException("Not supported yet.");  
    }
}
