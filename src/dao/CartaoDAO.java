package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import model.Cartao;

public class CartaoDAO {
    
    private static CartaoDAO instance = new CartaoDAO();
    
    public static CartaoDAO getInstance() {
        return instance;
    }
    
    public void salvar(Cartao cartao) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (cartao.getId() != null) {
                em.merge(cartao);
            } else {
                em.persist(cartao);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
    }
    
    public void excluir(Cartao cartao) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(em.getReference(Cartao.class, cartao.getId()));
            tx.commit();
        } catch (Exception e) {
            if (tx != null & tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
    }
    
    public Cartao getCartao(long id) throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Cartao cartao = null;
        try {
            tx.begin();
            cartao = em.find(Cartao.class, id);
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
        return cartao;
    }
    
    public List<Cartao> getAllCartoes() throws RuntimeException {
        EntityManager em = PersistenceUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        List<Cartao> cartoes = null;
        try {
            tx.begin();
            TypedQuery<Cartao> query = 
                    em.createQuery("SELECT c FROM Cartao c", Cartao.class);
            cartoes = query.getResultList();
            tx.commit();
        } catch (Exception e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            PersistenceUtil.close(em);
        }
        return cartoes;
    }

   
}