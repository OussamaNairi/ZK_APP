package zkexample.dao;
 
import java.util.List;
 
public interface CRUDDao {
  <T> List<T> getAll(Class<T> klass);
 
   <T> void Save(T klass);
 
   <T> void delete(T klass);
}