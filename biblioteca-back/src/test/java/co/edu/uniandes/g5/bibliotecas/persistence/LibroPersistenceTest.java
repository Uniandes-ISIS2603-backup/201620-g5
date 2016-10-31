/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.g5.bibliotecas.persistence;

import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.persistence.LibroPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class LibroPersistenceTest {

    /**
     * @return el jar que se desplegará para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LibroEntity.class.getPackage())
                .addPackage(LibroPersistence.class.getPackage())
                .addPackage(BibliotecaEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Biblioteca que contiene los libros. La relación entre Biblioteca y Libro
     * es "composite"
     */
    BibliotecaEntity fatherEntity;

    /**
     * Lista de los libros que serán utilizados en las pruebas. La relación
     * entre Biblioteca y Libro es "composite"
     */
    private List<LibroEntity> data = new ArrayList<>();

    @Inject
    private LibroPersistence libroPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de cada método de prueba.
     *
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete  from LibroEntity").executeUpdate();
        em.createQuery("delete  from BibliotecaEntity").executeUpdate();
    }

    /**
     * Para el correcto funcionamiento de las pruebas, inserta los datos
     * iniciales en la base de datos utilizando un manejador de persistencia.
     *
     * Crea una compañía y luego le adiciona tres libros.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        fatherEntity = factory.manufacturePojo(BibliotecaEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
            entity.setBiblioteca(fatherEntity);
            data.add(entity);
            em.persist(entity);
        }

    }

    /**
     * Prueba para crear un Libro.
     *
     *
     */
    @Test
    public void testCreate() {
        PodamFactory factory = new PodamFactoryImpl();
        LibroEntity newEntity = factory.manufacturePojo(LibroEntity.class);
        newEntity.setBiblioteca(fatherEntity);
        LibroEntity result = libroPersistence.create(newEntity);

        Assert.assertNotNull(result);

        LibroEntity entity = em.find(LibroEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Libros.
     *
     *
     */
    @Test
    public void testFindAllInBiblioteca() {
        List<LibroEntity> list = libroPersistence.findAllInBiblioteca(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (LibroEntity ent : list) {
            boolean found = false;
            for (LibroEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void testFindAll() {
        List<LibroEntity> list = libroPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (LibroEntity ent : list) {
            boolean found = false;
            for (LibroEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Libro.
     *
     *
     */
    @Test
    public void testFind() {
        LibroEntity entity = data.get(0);
        LibroEntity newEntity = libroPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void testFindByName() {
        LibroEntity entity = data.get(0);
        LibroEntity newEntity = libroPersistence.findByName(entity.getName(), fatherEntity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(fatherEntity.getId(), newEntity.getBiblioteca().getId());
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    @Test
    public void testFindByISBN() {
        LibroEntity entity = data.get(0);
        LibroEntity newEntity = libroPersistence.findByISBN(entity.getIsbn(), fatherEntity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(fatherEntity.getId(), newEntity.getBiblioteca().getId());
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar un Libro.
     *
     *
     */
    @Test
    public void testDelete() {
        LibroEntity entity = data.get(0);
        libroPersistence.delete(entity.getId());
        LibroEntity deleted = em.find(LibroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Libro.
     *
     *
     */
    @Test
    public void testUpdate() {
        LibroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LibroEntity newEntity = factory.manufacturePojo(LibroEntity.class);

        newEntity.setId(entity.getId());

        libroPersistence.update(newEntity);

        LibroEntity resp = em.find(LibroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
