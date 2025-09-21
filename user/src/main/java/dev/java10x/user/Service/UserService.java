package dev.java10x.user.Service;

import dev.java10x.user.Entity.User;
import dev.java10x.user.Exceptions.EmailAlreadyExistsException;
import dev.java10x.user.Exceptions.UserNotFoundException;
import dev.java10x.user.Producer.UserProduce;
import dev.java10x.user.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserProduce userProduce;


    @Transactional
    public User createAndPublic(User user){

        if (repository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }

        repository.save(user);
        userProduce.PublishEvent(user);
        return user;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
