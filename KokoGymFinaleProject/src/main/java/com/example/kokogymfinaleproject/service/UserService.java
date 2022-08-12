package com.example.kokogymfinaleproject.service;

import com.example.kokogymfinaleproject.model.KokoGymUserDetails;
import com.example.kokogymfinaleproject.model.binding.UserRegisterBindingModel;
import com.example.kokogymfinaleproject.model.dto.UpdateUserDTO;
import com.example.kokogymfinaleproject.model.entity.*;
import com.example.kokogymfinaleproject.model.enums.LevelNameEnum;
import com.example.kokogymfinaleproject.model.enums.RoleEnum;
import com.example.kokogymfinaleproject.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final RoleRepository roleRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final LevelRepository levelRepository;
    private final TrainerRepository trainerRepository;
    private final CartItemRepository cartItemRepository;

    public UserService(UserRepository userRepository, ModelMapper mapper, RoleRepository roleRepository, ShoppingCartRepository shoppingCartRepository, PasswordEncoder passwordEncoder, CustomerRepository customerRepository, LevelRepository levelRepository, TrainerRepository trainerRepository, CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.roleRepository = roleRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
        this.levelRepository = levelRepository;
        this.trainerRepository = trainerRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        UserEntity user = this.mapper.map(userRegisterBindingModel, UserEntity.class);
        ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
        this.shoppingCartRepository.save(shoppingCart);
        user.setShoppingCart(shoppingCart);
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        this.userRepository.save(user);
        CustomerEntity customer = new CustomerEntity();
        customer.setUser(user);
        customer.setLevel(this.levelRepository.findByLevel(LevelNameEnum.BEGINNER).get());
        this.customerRepository.save(customer);

    }

    public void init() {
        if (this.userRepository.count() == 0) {
            UserEntity user = new UserEntity()
                    .setFirstName("Shefa")
                    .setLastName("Georgiev")
                    .setEmail("shefa@mail.com")
                    .setUsername("shefa")
//                    .setImageUrl("k")
                    .setImageUrl("https://toppng.com/uploads/preview/boss-baby-1-115628583062lbof9qtgg.png")
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
//                .setImageUrl("k")
                .setImageUrl("https://media.gettyimages.com/photos/happy-personal-trainer-working-at-the-gym-picture-id852401732?s=612x612")
                .setBirthDate(LocalDate.of(2003, 5, 17));
        this.userRepository.save(customer);
        this.customerRepository.save(new CustomerEntity(customer, this.levelRepository.findByLevel(LevelNameEnum.BEGINNER).get()));

        UserEntity trainer = new UserEntity()
                .setFirstName("Trainer1")
                .setLastName("trainerov")
                .setEmail("trainer@mail.com")
                .setUsername("trainer1")
                .setPassword(this.passwordEncoder.encode("topsecret"))
                .setShoppingCart(shoppingCartForTrainer)
                .setRoles(List.of(this.roleRepository.findByRole(RoleEnum.CUSTOMER),
                        this.roleRepository.findByRole(RoleEnum.TRAINER)))
                .setImageUrl("https://media.istockphoto.com/photos/muscular-trainer-writing-on-clipboard-picture-id675179390?k=20&m=675179390&s=612x612&w=0&h=7LP7-OamGu-b8XG-VKcJuamK5s80ke-4oJ5siUrjFVA=")
//                .setImageUrl("k")
                .setBirthDate(LocalDate.of(2003, 5, 17));
        this.userRepository.save(trainer);
        this.trainerRepository.save(new TrainerEntity(trainer, "GymCoordinator"));

        UserEntity trainer2 = new UserEntity()
                .setFirstName("Trainer2")
                .setLastName("trainerov2")
                .setEmail("trainer2@mail.com")
                .setUsername("trainer2")
                .setPassword(this.passwordEncoder.encode("topsecret"))
                .setShoppingCart(shoppingCartForTrainer)
                .setRoles(List.of(this.roleRepository.findByRole(RoleEnum.CUSTOMER),
                        this.roleRepository.findByRole(RoleEnum.TRAINER)))
                .setImageUrl("https://media.gettyimages.com/photos/happy-personal-trainer-working-at-the-gym-picture-id852401732?s=612x612")
//                .setImageUrl("k")
                .setBirthDate(LocalDate.of(1997, 3, 12));
        this.userRepository.save(trainer2);
        this.trainerRepository.save(new TrainerEntity(trainer2, "Personal trainer"));

        UserEntity trainer3 = new UserEntity()
                .setFirstName("Trainer3")
                .setLastName("trainerov3")
                .setEmail("trainer3@mail.com")
                .setUsername("trainer3")
                .setPassword(this.passwordEncoder.encode("topsecret"))
                .setShoppingCart(shoppingCartForTrainer)
                .setRoles(List.of(this.roleRepository.findByRole(RoleEnum.CUSTOMER),
                        this.roleRepository.findByRole(RoleEnum.TRAINER)))
                .setImageUrl("https://media.istockphoto.com/photos/confident-gym-owner-picture-id1324042769?b=1&k=20&m=1324042769&s=170667a&w=0&h=jAwDr6qkVDFxds70ODp0rlzaofDKXNhdaKZyfM_l-eQ=")
//                .setImageUrl("k")
                .setBirthDate(LocalDate.of(1956, 1, 13));
        this.userRepository.save(trainer3);
        this.trainerRepository.save(new TrainerEntity(trainer3, "Hiit Master"));

        UserEntity trainer4 = new UserEntity()
                .setFirstName("Trainer4")
                .setLastName("trainerov4")
                .setEmail("trainer4@mail.com")
                .setUsername("trainer4")
                .setPassword(this.passwordEncoder.encode("topsecret"))
                .setShoppingCart(shoppingCartForTrainer)
//                .setImageUrl("k")
//                .setImageUrl("https://image.shutterstock.com/image-photo/african-personal-trainer-helping-man-260nw-232353118.jpg")
                .setImageUrl("data:image/jpeg;base64,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")
                .setRoles(List.of(this.roleRepository.findByRole(RoleEnum.CUSTOMER),
                        this.roleRepository.findByRole(RoleEnum.TRAINER)))
                .setBirthDate(LocalDate.of(2003, 5, 17));
        this.userRepository.save(trainer4);
        this.trainerRepository.save(new TrainerEntity(trainer4, "IdkTitle"));

    }


    public Object findCustomerOrTrainerById(Long id) {

        Optional<CustomerEntity> customer = this.customerRepository.findByUserId(id);

        if (customer.isPresent()) {
            return customer.get();
        }
        Optional<TrainerEntity> trainer = this.trainerRepository.findByUserId(id);
        if (trainer.isPresent()) {
            return trainer.get();
        }

        return this.userRepository.findById(id);

    }

    public void updateUser(UpdateUserDTO userDTO, Long id) {

        UserEntity user = this.userRepository.findById(id).get();
        user.setUsername(userDTO.getUsername());
        user.setImageUrl(userDTO.getImageUrl());
        this.userRepository.save(user);

        if (userDTO.getTitle() == null && userDTO.getLevel() != null) {
            CustomerEntity customer = this.customerRepository.findByUserId(id).get();
            customer
                    .setLevel(this.levelRepository.findByLevel(userDTO.getLevel()).get());
            this.customerRepository.save(customer);
        } else if (userDTO.getTitle() != null && userDTO.getLevel() == null) {
            TrainerEntity trainer = this.trainerRepository.findByUserId(id).get();
            trainer
                    .setTitle(userDTO.getTitle());
            this.trainerRepository.save(trainer);
        }

    }

    public List<TrainerEntity> findAllTrainers() {

        return this.trainerRepository.findAll();


    }

    public UserEntity findById(Long id) {

            return this.userRepository.findById(id).get();

    }

    public void increaseQuantityInShoppingCart(Long id, KokoGymUserDetails userDetails) {

        CartItemEntity cartItemEntity = userDetails
                .getShoppingCart()
                .getCartItems()
                .stream()
                .filter(cartItem -> cartItem.getId() == id).findFirst().get();

        if(cartItemEntity.getQuantity() < cartItemEntity.getProduct().getStockQuantity()) {
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + 1);
        }

    }

    public void decreaseQuantityInShoppingCart(Long id, KokoGymUserDetails userDetails) {

            CartItemEntity cartItemEntity = userDetails
                    .getShoppingCart()
                    .getCartItems()
                    .stream()
                    .filter(cartItem -> cartItem.getId() == id).findFirst().get();

        if(cartItemEntity.getQuantity() > 1) {
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() - 1);
        }


    }

    public void removeFromShoppingCart(Long id, KokoGymUserDetails userDetails) {

        userDetails
                .getShoppingCart()
                .getCartItems()
                .remove(userDetails
                        .getShoppingCart()
                        .getCartItems()
                        .stream()
                        .filter(cartItem -> cartItem.getId() == id).findFirst().get());

        this.cartItemRepository.deleteById(id);


    }

    public void removeAllFromShoppingCart(KokoGymUserDetails userDetails) {
        this.cartItemRepository.deleteAll(
                userDetails
                        .getShoppingCart()
                        .getCartItems()
        );
    }

    public boolean containsEMail(String email) {

        return this.userRepository.findByEmail(email).isPresent();

    }

    public void makeTrainer(KokoGymUserDetails userDetails) {

        UserEntity userById = this.userRepository.getById(userDetails.getId());

        CustomerEntity customer=this.customerRepository.findByUserId(userDetails.getId()).get();
        this.customerRepository.delete(customer);

        TrainerEntity trainer = new TrainerEntity();
        trainer.setUser(userById);
        trainer.setTitle("none");
        this.trainerRepository.save(trainer);

        userById.getRoles().add(this.roleRepository.findByRole(RoleEnum.TRAINER));
        this.userRepository.save(userById);

    }

    public UserEntity findByUsername(String username) {

        return this.userRepository.findByUsername(username).orElse(null);

    }

    public UserEntity findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElse(null);
    }
}
