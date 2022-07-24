package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import com.example.kokogymfinaleproject.model.binding.UserRegisterDTO;
import com.example.kokogymfinaleproject.model.dto.UpdateUserDTO;
import com.example.kokogymfinaleproject.model.entity.CustomerEntity;
import com.example.kokogymfinaleproject.model.entity.ShoppingCartEntity;
import com.example.kokogymfinaleproject.model.entity.TrainerEntity;
import com.example.kokogymfinaleproject.model.entity.UserEntity;
import com.example.kokogymfinaleproject.model.enums.LevelNameEnum;
import com.example.kokogymfinaleproject.model.enums.RoleEnum;
import com.example.kokogymfinaleproject.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private ModelMapper mapper;
    private RoleRepository roleRepository;
    private ShoppingCartRepository shoppingCartRepository;
    private PasswordEncoder passwordEncoder;
    private CustomerRepository customerRepository;
    private LevelRepository levelRepository;
    private TrainerRepository trainerRepository;

    public UserService(UserRepository userRepository, ModelMapper mapper, RoleRepository roleRepository, ShoppingCartRepository shoppingCartRepository, PasswordEncoder passwordEncoder, CustomerRepository customerRepository, LevelRepository levelRepository, TrainerRepository trainerRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.roleRepository = roleRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
        this.levelRepository = levelRepository;
        this.trainerRepository = trainerRepository;
    }

    public void register(UserRegisterDTO userRegisterDTO) {
        UserEntity user = this.mapper.map(userRegisterDTO, UserEntity.class);
        ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
        this.shoppingCartRepository.save(shoppingCart);
        user.setShoppingCart(shoppingCart);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(user);
    }

    public void init() {
        if (this.userRepository.count() == 0) {
            UserEntity user = new UserEntity()
                    .setFirstName("Shefa")
                    .setLastName("Georgiev")
                    .setEmail("shefa@mail.com")
                    .setUsername("shefa")
                    .setPassword(this.passwordEncoder.encode("shefapass"))
                    .setShoppingCart(this.shoppingCartRepository.findAll().get(0))
                    .setRoles(this.roleRepository.findAll())
                    .setBirthDate(LocalDate.of(2001, 1, 15));

            this.userRepository.save(user);

            addUsers();
        }

    }

    private void addUsers() {

        ShoppingCartEntity shoppingCartForCustomer = new ShoppingCartEntity();
        this.shoppingCartRepository.saveAndFlush(shoppingCartForCustomer);
        ShoppingCartEntity shoppingCartForTrainer = new ShoppingCartEntity();
        this.shoppingCartRepository.saveAndFlush(shoppingCartForTrainer);

        UserEntity customer = new UserEntity()
                .setFirstName("Customer1")
                .setLastName("Georgiev")
                .setEmail("customer@mail.com")
                .setUsername("customer1")
                .setPassword(this.passwordEncoder.encode("topsecret"))
                .setShoppingCart(shoppingCartForCustomer)
                .setRoles(List.of(this.roleRepository.findByRole(RoleEnum.CUSTOMER)))
                .setBirthDate(LocalDate.of(2003, 5, 17));
        this.userRepository.save(customer);

        UserEntity trainer = new UserEntity()
                .setFirstName("Trainer1")
                .setLastName("trainerov")
                .setEmail("trainer@mail.com")
                .setUsername("trainer1")
                .setPassword(this.passwordEncoder.encode("topsecret"))
                .setShoppingCart(shoppingCartForTrainer)
                .setRoles(List.of(this.roleRepository.findByRole(RoleEnum.CUSTOMER),
                        this.roleRepository.findByRole(RoleEnum.TRAINER)))
                .setBirthDate(LocalDate.of(2003, 5, 17));
        this.userRepository.save(trainer);

        this.customerRepository.save(new CustomerEntity(customer, this.levelRepository.findByLevel(LevelNameEnum.BEGINNER).get()));
        this.trainerRepository.save(new TrainerEntity(trainer, "GymCoordinator"));

    }


    public Object findCustomerOrTrainerById(Long id) {

        Optional<CustomerEntity> customer = this.customerRepository.findByUserId(id);

        if (customer.isPresent()) {
            return customer.get();
        }
        return this.trainerRepository.findByUserId(id).get();

    }

    public void updateUser(UpdateUserDTO userDTO, Long id) {

        UserEntity user = this.userRepository.findById(id).get();
        user.setUsername(userDTO.getUsername());
        user.setImageUrl(userDTO.getImageUrl());
        this.userRepository.save(user);

        if (userDTO.getTitle() == null) {
            CustomerEntity customer = this.customerRepository.findByUserId(id).get();
            customer
                    .setLevel(this.levelRepository.findByLevel(LevelNameEnum.valueOf(userDTO.getLevel())).get());
            this.customerRepository.save(customer);
        } else {
            TrainerEntity trainer = this.trainerRepository.findByUserId(id).get();
            trainer.setTitle(userDTO.getTitle());
            this.trainerRepository.save(trainer);
        }
        System.out.println();

    }
}
