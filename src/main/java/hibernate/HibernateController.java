package hibernate;

import dataBaseUtils.HibernateSessionFactory;
import essence.BooksEntity;
import essence.TagsEntity;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;


public class HibernateController {

    static Session session = HibernateSessionFactory.getSessionFactory().openSession();

    public static List<TagsEntity> getAllTags() {
        Query query = session.createQuery("FROM TagsEntity");
        return query.list();
    }

    public static void insertNewTag(TagsEntity te) {
        session.beginTransaction();
        session.save(te);
        session.getTransaction().commit();
    }

}
