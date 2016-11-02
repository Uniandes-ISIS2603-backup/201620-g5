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
package co.edu.uniandes.g5.bibliotecas.resources;

import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.dtos.PrestamoDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.PrestamoDetailDTO;
import co.edu.uniandes.g5.bibliotecas.resources.PrestamoResource;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/*
 * Testing URI: companys/{prestamosId: \\d+}/prestamos/
 */
@RunWith(Arquillian.class)
public class PrestamoResourceTest {

    private WebTarget target;

    PodamFactory factory = new PodamFactoryImpl();

    private final int Ok = Status.OK.getStatusCode();
    private final int Created = 200; //Status.CREATED.getStatusCode();
    private final int OkWithoutContent = Status.NO_CONTENT.getStatusCode();

    private final static List<PrestamoEntity> prestamoList = new ArrayList<>();

    private final String bibliotecaPath = "bibliotecas";
    private final String prestamoPath = "prestamos";
    private final String apiPath = "api";
    BibliotecaEntity fatherBibliotecaEntity;

    @ArquillianResource
    private URL deploymentURL;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(PrestamoResource.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
    }

    private WebTarget createWebTarget() {
        return ClientBuilder.newClient().target(deploymentURL.toString()).path(apiPath);
    }

    @PersistenceContext(unitName = "CompanyPU")
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private void clearData() {
        em.createQuery("delete from PrestamoEntity").executeUpdate();
        em.createQuery("delete from CompanyEntity").executeUpdate();
        prestamoList.clear();
    }

    /**
     * Datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    public void insertData() {
        fatherBibliotecaEntity = factory.manufacturePojo(BibliotecaEntity.class);
        fatherBibliotecaEntity.setId(1L);
        em.persist(fatherBibliotecaEntity);

        for (int i = 0; i < 3; i++) {
            PrestamoEntity prestamo = factory.manufacturePojo(PrestamoEntity.class);
            prestamo.setId(i + 1L);
            prestamo.setBiblioteca(fatherBibliotecaEntity);
            em.persist(prestamo);
            prestamoList.add(prestamo);
        }
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUpTest() {

        try {
            utx.begin();
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
        target = createWebTarget()
                .path(bibliotecaPath)
                .path(fatherBibliotecaEntity.getId().toString())
                .path(prestamoPath);
    }

    /**
     * Prueba para crear un Prestamo
     *
     * @generated
     */
    @Test
    public void createPrestamoTest() throws IOException {
        PrestamoDetailDTO prestamo = factory.manufacturePojo(PrestamoDetailDTO.class);

        Response response = target
                .request()
                .post(Entity.entity(prestamo, MediaType.APPLICATION_JSON));

        PrestamoDetailDTO prestamoTest = (PrestamoDetailDTO) response.readEntity(PrestamoDetailDTO.class);

        Assert.assertEquals(Created, response.getStatus());

        Assert.assertEquals(prestamo.getId(), prestamoTest.getId());

        PrestamoEntity entity = em.find(PrestamoEntity.class, prestamoTest.getId());
        Assert.assertNotNull(entity);
    }

    /**
     * Prueba para consultar un Prestamo
     *
     * @generated
     */
    @Test
    public void getPrestamoByIdTest() {

        PrestamoDetailDTO prestamoTest = target
                .path(prestamoList.get(0).getId().toString())
                .request().get(PrestamoDetailDTO.class);

        Assert.assertEquals(prestamoTest.getId(), prestamoList.get(0).getId());




    }

    /**
     * Prueba para consultar la lista de Prestamos
     *
     * @generated
     */
    @Test
    public void listPrestamoTest() throws IOException {

        Response response = target
                .request().get();

        String listPrestamo = response.readEntity(String.class);
        List<PrestamoDTO> listPrestamoTest = new ObjectMapper().readValue(listPrestamo, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(prestamoList.size(), listPrestamoTest.size());
    }

    /**
     * Prueba para actualizar un Prestamo
     *
     * @generated
     */
    @Test
    public void updatePrestamoTest() throws IOException {

        PrestamoDetailDTO prestamo = new PrestamoDetailDTO(prestamoList.get(0));

        PrestamoDTO prestamoChanged = factory.manufacturePojo(PrestamoDTO.class);

        prestamo.setId(prestamoChanged.getId());
      

        Response response = target
                .path(prestamo.getId().toString())
                .request()
                .put(Entity.entity(prestamo, MediaType.APPLICATION_JSON));

        PrestamoDetailDTO prestamoTest = (PrestamoDetailDTO) response.readEntity(PrestamoDetailDTO.class);

        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(prestamo.getId(), prestamoTest.getId());


    }

    /**
     * Prueba para eliminar un Prestamo
     *
     * @generated
     */
    @Test
    public void deletePrestamoTest() {
        Response response = target
                .path(prestamoList.get(0).getId().toString())
                .request().delete();

        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
