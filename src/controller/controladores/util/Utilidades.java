package controller.controladores.util;

import model.Marca;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Utilidades {

    public static Field getField(Class clazz, String attribute) {
        Field[] fields = clazz.getFields();         // cuando hay herencia
        Field resp = null;
        for (Field field : fields) {
            if (attribute.equalsIgnoreCase(field.getName())) {
                resp = field;
            }
        }
        fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (attribute.equalsIgnoreCase(field.getName())) {
                resp = field;
            }
        }
        return resp;
    }

    public static String getFieldValue(Object obj, String field) throws Exception {
        Field declaredField = getField(Marca.class, field);
        declaredField.setAccessible(true);
        System.out.println(declaredField.get(obj).toString());
        return declaredField.get(obj).toString();
    }

    public static Object getData(Object clas, String attribute) {
        Class clazz = clas.getClass();
        Field field = getField(clazz, attribute);
        Object obj = null;

        if (field != null) {
            String aux = "get" + capitalize(attribute);
            Method method = null;
            for (Method m : clazz.getMethods()) {
                if (m.getName().equalsIgnoreCase(aux)) {
                    method = m;
                    break;
                }
            }
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.getName().equalsIgnoreCase(aux)) {
                    method = m;
                    break;
                }
            }
            if (method != null) {
                try {
                    obj = method.invoke(clas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    public static String capitalize(String str) {
        char[] caracteres = str.toCharArray();
        String aux = String.valueOf(caracteres[0]).toUpperCase();
        caracteres[0] = aux.charAt(0);
        return new String(caracteres);
    }
}
