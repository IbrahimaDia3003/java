package com.ism.data.repository.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import com.ism.core.repository.implement.Repository;
import com.ism.data.entities.User;
import com.ism.data.enums.Role;
import com.ism.data.repository.IUserRepository;

public class UserRepository extends Repository<User> implements IUserRepository {
    public UserRepository() {
        super(User.class);
    }

    @Override
    public List<User> selectAllActifs(int type) {
        if (type <= -1) {
            return selectAll();
        }
        return selectAll().stream()
                .filter(user -> user.isStatus() &&  user.getRole().ordinal() == type)
                .collect(Collectors.toList());
    }  
    //user.isStatus() &&

    @Override
    public List<User> selectAllClients() {
        return selectAll().stream()
                .filter(user -> user.getRole().ordinal() == Role.CLIENT.ordinal())
                .collect(Collectors.toList());
    }

    @Override
    public List<User> selectAllUsers(User userConnect) {
        return selectAll().stream()
                .filter(user -> user.getId() != userConnect.getId() && (user.getRole() == Role.ADMIN || user.getRole() == Role.BOUTIQUIER))
                .collect(Collectors.toList());
    }

    @Override
    public User selectByLogin(String login) {
        return selectAll().stream()
                .filter(user -> user.getLogin().compareTo(login) == 0)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> selectAllUSERComplte() 
    {
        List<User> users = new ArrayList<User>();
        EntityManager em = getEntityManager();
        try {

            TypedQuery<User>  query = em.createQuery("SELECT u FROM User u", User.class);
            users = query.getResultList();
            return users;
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            return null;
        }
        finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }
}
// PasswordUtils.checkPassword(password, user.getPassword())