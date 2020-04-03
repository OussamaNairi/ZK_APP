package zkexample.dao;
 
import java.util.List;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
@Repository
public class CRUDDaoImpl implements CRUDDao {
 
    @Autowired
  SessionFactory sessionFactory;
 
  @SuppressWarnings("unchecked")
  public <T> List<T> getAll(Class<T> klass) {
       return getCurrentSession().createQuery("from " + klass.getName())
               .list();
    }
 
   protected final Session getCurrentSession() {
       return sessionFactory.getCurrentSession();
  }
 
   public <T> void Save(T klass) {
       getCurrentSession().save(klass);
    }
 
   public <T> void delete(T klass) {
     getCurrentSession().delete(klass);
 
  }
 
}