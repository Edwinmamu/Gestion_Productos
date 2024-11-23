package com.example;

import com.example.Entidad.Producto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadPersistencia");

    public static void main(String[] args) {
        crearProducto("Carro", 456000, 3);

        Producto producto1 = leerProducto(1L);  
        if (producto1 != null) {
            System.out.println("El producto ha sido encontrado encontrado: " + producto1.getNombre()); 
        }

        actualizarProducto(1L, "Carro Actualizado", 705000, null);;
        eliminarProducto(1L);
    }

    // Crear producto
    public static void crearProducto(String nombre, int precio, int cantidadEnStock) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Producto producto1 = new Producto();
        producto1.setNombre(nombre);
        producto1.setPrecio(precio);;
        producto1.setCantidadStock(nombre);;;

        em.persist(producto1);
        em.getTransaction().commit();
        em.close();
    }

    // Leer producto
    public static Producto leerProducto(long id) {
        EntityManager em = emf.createEntityManager();
        Producto producto1 = em.find(Producto.class, id);  
        em.close();
        return producto1;
    }

    // Actualizar producto
    public static void actualizarProducto(long id, String nuevoNombre, int nuevoPrecio, String nuevaCantidadEnStock) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Producto producto1 = em.find(Producto.class, id);
        if (producto1 != null) {
            producto1.setNombre(nuevoNombre);  
            producto1.setPrecio(nuevoPrecio);  
            producto1.setCantidadStock(nuevaCantidadEnStock);;; 
            em.merge(producto1);
        }
        em.getTransaction().commit();
        em.close();
    }

    // Eliminar producto
    public static void eliminarProducto(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Producto producto1 = em.find(Producto.class, id);
        if (producto1 != null) {
            em.remove(producto1);
        }

        em.getTransaction().commit();
        em.close();
    }
}