package controller.tda_listas;

import controller.tda_listas.exceptions.VacioExceptions;

import java.util.Iterator;

import static java.lang.reflect.Array.newInstance;

public class ListaEnlazada<E> implements Iterable<E> {

    private Nodo<E> head;
    private Nodo<E> last;
    private Integer size;

    public ListaEnlazada() {
        head = null;
        last = null;
        size = 0;
    }

    public Integer getSize() {
        return size;
    }

    public Nodo<E> getHead() {
        return head;
    }

    // Metodos
    public Boolean isEmpty() {
        return head == null || size == 0;
    }

    protected void addFist(E data) {
        if (isEmpty()) {
            Nodo<E> aux = new Nodo<>(data, null);
            head = aux;
            last = aux;
        } else {
            Nodo<E> headOld = head;
            head = new Nodo<>(data, headOld);
        }
        size++;
    }

    public void addLast(E data) {
        if (isEmpty()) {
            addFist(data);
        } else {
            Nodo<E> aux = new Nodo<>(data, null);
            last.setNext(aux);
            last = aux;
            size++;
        }

    }

    public void add(E data) {
        addLast(data);
    }

    public void add(E data, Integer post) throws VacioExceptions {
        if (post == 0) {
            addFist(data);
        } else if (post == size - 1) {
            addLast(data);
        } else {
            Nodo<E> search_preview = getNode(post - 1);
            Nodo<E> search = getNode(post);
            Nodo<E> aux = new Nodo<>(data, search);
            search_preview.setNext(aux);
            size++;
        }
    }

    public E getFirst() throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Error, Lista vacia");
        } else {
            return head.getData();
        }
    }

    // Metodo para obtener el ultimo elemento de la lista
    public E getLast() throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Error, Lista vacia");
        } else {
            return last.getData();
        }
    }

    // Metodo para obtener un elemento en una posicion especifica
    public E get(Integer index) throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Error, Lista vacia");
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Error, Esta fuera del limite de la lista");
        } else if (index == 0) {
            return getFirst();
        } else if (index == (size - 1)) {
            return getLast();
        } else {
            Nodo<E> search = getNode(index);
            return search.getData();
        }
    }

    // Metodo para obtener un nodo en una posicion especifica
    private Nodo<E> getNode(Integer post) throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Error, Lista vacia");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error, Esta fuera del limite de la lista");
        } else if (post == 0) {
            return head;
        } else if (post == (size - 1)) {
            return last;
        } else {
            Nodo<E> search = head;
            int cont = 0;
            while (cont < post) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    // Metodo para imprimir la lista
    public String print() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append(":Lista vacia");
        } else {
            Nodo<E> aux = head;
            while (aux != null) {
                sb.append(aux.getData().toString()).append("\n");
                aux = aux.getNext();
            }
        }
        return sb.toString();
    }

    // Metodo para actualizar un elemento en una posicion especifica
    public void update(Integer pos, E data) throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Lista Vacia");
        } else if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("Error, esta fuera de los limites");
        } else if (pos == 0) {
            head.setData(data);
        } else if (pos == (size - 1)) {
            last.setData(data);
        } else {
            Nodo<E> search = head;
            Integer cont = 0;
            while (cont < pos) {
                cont++;
                search = search.getNext();
            }
            search.setData(data);
        }
    }

    // Metodo para eliminar el primer elemento de la lista
    public E deleteFirst() throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Lista Vacia");
        } else {
            E element = head.getData();
            Nodo<E> aux = head.getNext();
            head = aux;
            if (size.intValue() == 1) {
                last = null;
            }
            size--;

            return element;
        }

    }

    // Metodo para eliminar el ultimo elemento de la lista
    public E deleteLast() throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Lista Vacia");
        } else {
            E element = last.getData();
            Nodo<E> aux = getNode(size - 2);
            if (aux == null) {
                last = null;
                if (size == 2) {
                    last = head;
                } else {
                    head = null;
                }
            } else {
                last = null;
                last = aux;
                last.setNext(null);
            }
            size--;
            return element;
        }

    }

    // Metodo para eliminar un elemento en una posicion especifica
    public E delete(Integer post) throws VacioExceptions {
        if (isEmpty()) {
            throw new VacioExceptions("Lista Esta Vacia ");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error esta fuera de los limites de la lista");
        } else if (post == 0) {
            return deleteFirst();
        } else if (post == (size - 1)) {
            return deleteLast();
        } else {
            Nodo<E> preview = getNode(post - 1);
            Nodo<E> actually = getNode(post);
            E element = preview.getData();
            Nodo<E> next = actually.getNext();
            actually = null;
            preview.setNext(next);
            size--;
            return element;
        }
    }

    // Metodo para vaciar la lista
    public void clear() {
        head = null;
        last = null;
        size = 0;
    }

    public static void main(String[] args) {
        ListaEnlazada<Integer> numerics = new ListaEnlazada<>();
        for (int i = 0; i < 10; i++) {
            Integer aux = (int) (Math.random() * 1000);
            numerics.add(aux);
        }

        System.out.println("Lista original:");
        System.out.println(numerics.print());
        System.out.println("Tamaño de la lista: " + numerics.getSize());

        try {
            // Supongamos que queremos actualizar el elemento en la posición 3 con un nuevo valor
            int posToUpdate = 3;
            int newValue = 999;

            System.out.println("---------------------------------------------");
            System.out.println("Actualizando elemento en la posición " + posToUpdate + " con el valor " + newValue);
            numerics.update(posToUpdate, newValue);
            System.out.println("Lista actualizada:");
            System.out.println(numerics.print());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Nodo<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }


    // Metodo para invertir la lista
    public void reverse() {
        Nodo<E> prev = null;
        Nodo<E> next = null;
        Nodo<E> actual = head;
        while (actual != null) {
            next = actual.getNext();
            actual.setNext(prev);
            prev = actual;
            actual = next;
        }
        head = prev;
    }

    // Metodo para convertir la lista en un array
    public E[] toArray() {
        Class clazz = null;
        E[] matriz = null;
        if (this.size > 0) {
            clazz = head.getData().getClass();
            matriz = (E[]) newInstance(clazz, size);

            Nodo<E> aux = head;
            for (int i = 0; i < size; i++) {
                matriz[i] = aux.getData();
                aux = aux.getNext();
            }

        }
        return matriz;
    }

    public ListaEnlazada<E> toList(E[] m) {
        clear();
        for (E e : m) {
            this.add(e);
        }
        return this;
    }

}
