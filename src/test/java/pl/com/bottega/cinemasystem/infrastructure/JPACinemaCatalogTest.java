package pl.com.bottega.cinemasystem.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cinemasystem.api.CinemaCatalog;
import pl.com.bottega.cinemasystem.api.ListAllCinemasResponse;
import pl.com.bottega.cinemasystem.domain.Cinema;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration({"/application.xml", "/mock-auth-context.xml"})
@TestPropertySource({"/jdbc-test.properties", "/hibernate-test.properties"})
@WebAppConfiguration

public class JPACinemaCatalogTest {

    @Autowired
    CinemaCatalog jpaCinemaCatalog;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    @Transactional
    public void shouldListAllCinemas() {
        //given
        Cinema cinema1 = new Cinema("Plaza", "Lublin");
        Cinema cinema2 = new Cinema("Felicity", "Lublin");
        entityManager.persist(cinema1);
        entityManager.persist(cinema2);

        //when
        ListAllCinemasResponse listAllCinemasResponse = jpaCinemaCatalog.listAll();

        //then
        assertNotNull(listAllCinemasResponse);
    }
}