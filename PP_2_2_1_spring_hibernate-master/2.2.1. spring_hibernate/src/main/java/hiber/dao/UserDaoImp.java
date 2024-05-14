package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }
   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public User whoIsUser(String model, int series) {
      String hql = "FROM User user LEFT OUTER JOIN FETCH user.car WHERE user.car.model=:model and user.car.series=:series";
      List<User> users = sessionFactory.getCurrentSession().createQuery(hql, User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList();

      if (users.isEmpty()) {
         System.out.println("Пользователь не найден");
         return null;
      } else {
         User user = users.get(0);
         System.out.println("Атомобилем владеет User: \n" + user);
         return user;
      }
   }
}
