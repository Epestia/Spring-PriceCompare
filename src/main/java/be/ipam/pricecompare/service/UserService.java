package be.ipam.pricecompare.service;

import be.ipam.pricecompare.dto.UserEntityDto;
import be.ipam.pricecompare.mapper.UserEntityMapper;
import be.ipam.pricecompare.model.UserEntity;
import be.ipam.pricecompare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<UserEntityDto> getUserById(Long userId) {
        return userRepository.findById(userId).map(userMapper::toDto);
    }

    public UserEntityDto createUser(UserEntityDto userEntityDto) {
        UserEntity userEntity = userMapper.toEntity(userEntityDto);
        userEntity.setPasswordHash(bCryptPasswordEncoder.encode(userEntity.getPasswordHash()));
        userEntity = userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public Optional<UserEntity> findByMail(String email) {
        return userRepository.findByMail(email);
    }

    public Optional<UserEntityDto> updatePartialUser(Long userId, UserEntityDto userEntityDto) {
        return userRepository.findById(userId).map(userEntity -> {
            if (userEntityDto.getFirstName() != null || userEntityDto.getLastName() != null ||
                    userEntityDto.getCity() != null || userEntityDto.getBirthDate() != null ||
                    userEntityDto.getMail() != null || userEntityDto.getPasswordHash() != null) {

                userEntity.setFirstName(Optional.ofNullable(userEntityDto.getFirstName()).orElse(userEntity.getFirstName()));
                userEntity.setLastName(Optional.ofNullable(userEntityDto.getLastName()).orElse(userEntity.getLastName()));
                userEntity.setCity(Optional.ofNullable(userEntityDto.getCity()).orElse(userEntity.getCity()));
                userEntity.setBirthDate(Optional.ofNullable(userEntityDto.getBirthDate()).orElse(userEntity.getBirthDate()));
                userEntity.setMail(Optional.ofNullable(userEntityDto.getMail()).orElse(userEntity.getMail()));
                if (userEntityDto.getPasswordHash() != null) {
                    userEntity.setPasswordHash(userEntityDto.getPasswordHash());
                }
                userEntity = userRepository.save(userEntity);
            }
            return userMapper.toDto(userEntity);
        });
    }
}
