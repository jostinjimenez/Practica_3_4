package controller.tda_listas;

import controller.tda_listas.exceptions.LlenoException;
import controller.tda_listas.exceptions.VacioExceptions;

public class Stack<E> {
    private StackOperation<E> stackOperation;
    public Stack(Integer cant){
        this.stackOperation = new StackOperation<>(cant);
    }
    public void push(E dato) throws LlenoException {
        stackOperation.push(dato);
    }
    public Integer getSize(){
        return this.stackOperation.getSize();
    }
    public void clear(){
        this.stackOperation.clear();
    }
    public Integer getTop(){
        return this.stackOperation.getTop();
    }
    public void print(){
        System.out.print("Pila");
        System.out.print(stackOperation.print());
        System.out.print("*******");
    }

    public E top()throws VacioExceptions {
        return stackOperation.pop();
    }

    public E pop()throws VacioExceptions {
        return stackOperation.pop();
    }
}