package controller.tda_listas;

import controller.tda_listas.exceptions.LlenoException;
import controller.tda_listas.exceptions.VacioExceptions;

public class QueueOperation<E> extends ListaEnlazada<E> {

    // Attributos
    private Integer top;

    // Constructor
    public QueueOperation(Integer top) {
        this.top = top;
    }

    // Getters and Setters
    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    // Metodos
    public Boolean verify() {
        return getSize() <= getTop();

    }

    public void queue(E data) throws LlenoException {
        if (verify()) {
            addLast(data);
        } else {
            throw new LlenoException("Cola llena");

        }
    }

    public E dequeue() throws VacioExceptions {
        if (verify()) {
            throw new VacioExceptions("Cola Vacia");
        } else {
            return deleteFirst();

        }
    }
}
