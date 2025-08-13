package mate.academy.repository.impl;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.exeptions.DataProcessingException;
import mate.academy.model.Book;
import mate.academy.repository.BookRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Book save(Book book) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save book into DB:" + book, e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find any books in DB", e);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return Optional.ofNullable(entityManager.find(Book.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Error while finding book by id " + id, e);
        }
    }
}
