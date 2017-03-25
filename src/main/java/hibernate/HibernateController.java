package hibernate;

import dataBaseUtils.HibernateSessionFactory;
import essence.BooksEntity;
import essence.LinksEntity;
import essence.TagsEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class HibernateController {

    //static Session session = HibernateSessionFactory.getSessionFactory().openSession();

    public static List<TagsEntity> getAllTags() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM TagsEntity ORDER BY tagName");
        //session.close();
        return query.list();
    }

    public static void insertNewTag(TagsEntity te) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(te);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteTag(int tId) {

        /*session.beginTransaction();
        Query query = session.createQuery("FROM TagsEntity tgs where tgs.tagId=:tId");
        query.setParameter("tId", tId);
        session.getTransaction().commit();
        session.close();*/

        /*Session session;
        TagsEntity TagsEntity;

        session = HibernateSessionFactory.getSessionFactory().openSession();
        TagsEntity = (TagsEntity) session.load(TagsEntity.class, tId);
        System.out.println("TagsEntity=" + TagsEntity);
        session.delete(TagsEntity);
        System.out.println("AFTER");

        //This makes the pending delete to be done
        session.flush();*/
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("delete TagsEntity where id =:taId");
            q.setParameter("taId", tId);
            q.executeUpdate();
            session.flush();
            tx.commit();
        } catch (Exception e) {
            System.out.println("Delete FAIL = " + e);
        }

    }

    public static void insertSeveralLinks(ArrayList<LinksEntity> linkEntity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        int counter = 0;
        for (LinksEntity le : linkEntity) {
            session.save(le);
            counter++;
            if (counter == 20) {
                session.flush();
                session.clear();
                counter = 0;
            }
        }
        tx.commit();
        session.close();
    }
}
