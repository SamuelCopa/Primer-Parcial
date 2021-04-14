package com.emergentes.modelo;

import java.util.ArrayList;
import java.util.Iterator;


public class GestorVacunas {
    private ArrayList<Vacuna> lista;

    public GestorVacunas() {
        lista = new ArrayList<Vacuna>();
    }

    public ArrayList<Vacuna> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Vacuna> lista) {
        this.lista = lista;
    }

    public void insertarRegistro(Vacuna item) {
        lista.add(item);
    }

    public void modificarRegistro(int pos, Vacuna item) {
        lista.set(pos, item);

    }

    public void eliminarRegistro(int pos) {
        lista.remove(pos);
    }

    public int obtenerId() {
        int idaux = 0;
        for (Vacuna item : lista) {
            idaux = item.getId();

        }
        return idaux + 1;

    }

    public int ubicarRegistro(int id) {
        int pos = -1;
        Iterator<Vacuna> it = lista.iterator();

        while (it.hasNext()) {
            ++pos;
            Vacuna aux = it.next();
            if (aux.getId() == id) {
                break;

            }
        }
        return pos;
    }
}
