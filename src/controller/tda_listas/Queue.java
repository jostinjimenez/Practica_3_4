package controller.tda_listas;

import controller.tda_listas.exceptions.LlenoException;
import controller.tda_listas.exceptions.VacioExceptions;

public class Queue<E> extends ListaEnlazada<E> {

    private QueueOperation<E> queue;

    public Queue(Integer top) {
        this.queue = new QueueOperation<>(top);
    }

    public Integer getTop() {
        return this.queue.getSize();

    }

    public void queue(E data) throws LlenoException {
        queue.queue(data);
    }

    public void clear() {
        this.queue.clear();
    }

    public String print() {

        System.out.println("Pila");
        System.out.println(queue.print());
        System.out.println("!!!!!!!!!!!!!");
        return null;
    }

    public E dequee() throws VacioExceptions {
        return queue.dequeue();
    }

    public static void main(String[] args) {
        Queue<Integer> pila = new Queue<>(10);
        try {
            pila.queue(1);
            pila.queue(12);
            pila.queue(10);
            pila.print();
            pila.dequee();
            pila.print();
        } catch (Exception ignored) {
        }
    }
}
