package mydomain.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mydomain.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfiguration.class)
public class PersionRepositoryTest {
  @Autowired
  PersonRepository personDao;

  @Test
  public void testFind() {
    Person person = personDao.findOne(1L);
  }

}
