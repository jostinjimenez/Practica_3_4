package controller.DAO;

import com.thoughtworks.xstream.XStream;
import controller.tda_listas.ListaEnlazada;

import java.io.FileOutputStream;
import java.io.FileReader;

public class DataAccessObject<T> implements TransferObject {

    private XStream xStream;
    private Class<T> clazz;
    private String URL;

    public DataAccessObject(Class<T> clazz) {
        this.clazz = clazz;
        xStream = Connection.getxStream();
        URL = Connection.getURL() + this.clazz.getSimpleName() + ".json";
    }

    @Override
    public Boolean save(Object data) {
        ListaEnlazada<T> list = list_All();
        list.add((T) data);
        try {
            this.xStream.toXML(list, new FileOutputStream(URL));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Integer generarID() {
        return list_All().getSize() + 1;
    }

    @Override
    public Boolean update(Object data, Integer index) {
        ListaEnlazada<T> list = list_All();

        if (index >= 0 && index < list.getSize()) {
            try {
                {
                    list.update(index, (T) data);
                    this.xStream.alias(list.getClass().getName(), ListaEnlazada.class);
                    this.xStream.toXML(list, new FileOutputStream(URL));
                    return true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public ListaEnlazada<T> list_All() {
        // Guardar una lista enlazada
        ListaEnlazada<T> list = new ListaEnlazada<>();
        try {
            list = (ListaEnlazada<T>) xStream.fromXML(new FileReader(URL));
        } catch (Exception e) {
        }
        return list;
    }



    @Override
    public Object find(Integer id) {
        return null;
    }
}
