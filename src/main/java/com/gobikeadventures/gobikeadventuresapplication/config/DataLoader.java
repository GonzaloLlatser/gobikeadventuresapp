package com.gobikeadventures.gobikeadventuresapplication.config;

import com.gobikeadventures.gobikeadventuresapplication.domain.port.in.PasswordEncoderPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.UserRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.domain.port.out.RoleRepositoryPort;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.*;
import com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

import static com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Payment.PaymentStatus.PAID;
import static com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Payment.PaymentStatus.PENDING;
import static com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.Reservation.ReservationStatus.*;
import static com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.TypeExperience.ExperienceType.RENTAL;
import static com.gobikeadventures.gobikeadventuresapplication.infrastructure.persistence.entity.TypeExperience.ExperienceType.TOUR;

@Component
public class DataLoader implements CommandLineRunner {
  private final SpringUserRepository springUserRepository;
  private final SpringRoleRepository springRoleRepository;
  private final SpringPaymentMethodRepository springPaymentMethodRepository;
  private final SpringPersonalDataRepository springPersonalDataRepository;
  private final SpringBikeRepository springBikeRepository;
  private final SpringCostRepository springCostRepository;
  private final SpringTypeExperienceRepository springTypeExperienceRepository;
  private final SpringExperienceRepository springExperienceRepository;
  private final SpringPaymentRepository springPaymentRepository;
  private final SpringReservationRepository springReservationRepository;
  private final PasswordEncoderPort passwordEncoderPort;

  public DataLoader(UserRepositoryPort userRepositoryPort, RoleRepositoryPort roleRepositoryPort, SpringUserRepository springUserRepository, SpringRoleRepository springRoleRepository, SpringPaymentMethodRepository springPaymentMethodRepository, SpringPersonalDataRepository springPersonalDataRepository, SpringBikeRepository springBikeRepository, SpringCostRepository springCostRepository, SpringTypeExperienceRepository springTypeExperience, SpringExperienceRepository springExperienceRepository, SpringPaymentRepository springPaymentRepository, SpringReservationRepository springReservationRepository, PasswordEncoderPort passwordEncoderPort) {
    this.springUserRepository = springUserRepository;
    this.springRoleRepository = springRoleRepository;
    this.springPaymentMethodRepository = springPaymentMethodRepository;
    this.springPersonalDataRepository = springPersonalDataRepository;
    this.springBikeRepository = springBikeRepository;
    this.springCostRepository = springCostRepository;
    this.springTypeExperienceRepository = springTypeExperience;
    this.springExperienceRepository = springExperienceRepository;
    this.springPaymentRepository = springPaymentRepository;
    this.springReservationRepository = springReservationRepository;
    this.passwordEncoderPort = passwordEncoderPort;
  }

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    // Create roles to populate the database
    Role adminRole = Role.builder()
      .name("ADMIN")
      .permissionDescription("Admin full access")
      .build();

    Role userRole = Role.builder()
      .name("USER")
      .permissionDescription("Normal user access")
      .build();

    Role managerRole = Role.builder()
      .name("MANAGER")
      .permissionDescription("Manager user access")
      .build();

    springRoleRepository.saveAll(Arrays.asList(adminRole, userRole));

    // Create users to populate the database
    User admin = User.builder()
      .email("gllatser@gobike.com")
      .password(passwordEncoderPort.encode("admin123"))
      .rol(adminRole)
      .build();

    admin = springUserRepository.save(admin);

    User user = User.builder()
      .email("aliciaquiroga@gmail.com")
      .password(passwordEncoderPort.encode("user123"))
      .rol(userRole)
      .build();

    user = springUserRepository.save(user);

    System.out.println("Base de datos inicializada!");

    // Create PaymentMethod to populate the database
    PaymentMethod paymentExample1 = PaymentMethod.builder()
      .name("card")
      .provider("Personal")
      .build();

    paymentExample1 = springPaymentMethodRepository.save(paymentExample1);

    PaymentMethod paymentExample2 = PaymentMethod.builder()
      .name("cash")
      .provider("Bank")
      .build();

    paymentExample2 = springPaymentMethodRepository.save(paymentExample2);

    // Create personalData to populate the database
    PersonalData adminData = PersonalData.builder()
      .user(admin)
      .firstName("Gonzalo Manuel")
      .lastName("Llatser")
      .phonePrimary("654207965")
      .phoneSecondary("915008632")
      .address("Calle San Juan 23, Valencia")
      .paymentMethod(paymentExample1)
      .build();

    springPersonalDataRepository.save(adminData);

    PersonalData userData = PersonalData.builder()
      .user(user)
      .firstName("Alicia")
      .lastName("Quiroga")
      .phonePrimary("698423665")
      .phoneSecondary("641298555")
      .address("Av. Cristobal de la Plana 2, Madrid")
      .paymentMethod(paymentExample2)
      .build();

    springPersonalDataRepository.save(userData);

    // Create Bikes to populate the database
    Bike bike1 = Bike.builder()
      .serialNumber("MTB-12345")
      .brand("Trek")
      .model("X-Caliber 8")
      .year("2023")
      .color("Negro")
      .status(Bike.BikeStatus.AVAILABLE)
      .type(Bike.BikeType.MOUNTAIN)
      .build();

    Bike bike2 = Bike.builder()
      .serialNumber("ROAD-54321")
      .brand("Specialized")
      .model("Tarmac SL7")
      .year("2022")
      .color("Rojo")
      .status(Bike.BikeStatus.RENTED)
      .type(Bike.BikeType.ROAD)
      .build();

    Bike bike3 = Bike.builder()
      .serialNumber("EBIKE-77777")
      .brand("Giant")
      .model("Explore E+")
      .year("2021")
      .color("Azul")
      .status(Bike.BikeStatus.MAINTENANCE)
      .type(Bike.BikeType.ELECTRIC)
      .build();

    springBikeRepository.save(bike1);
    springBikeRepository.save(bike2);
    springBikeRepository.save(bike3);

    // Create Cost to populate the database
    Cost cost1 = Cost.builder()
      .valuePerHour(2.5)
      .name("Ordinary")
      .build();

    Cost cost2 = Cost.builder()
      .valuePerHour(1.3)
      .name("Promo2X1")
      .build();

    Cost cost3 = Cost.builder()
      .valuePerHour(1.9)
      .name("SpecialDay")
      .build();

    cost1 = springCostRepository.save(cost1);
    cost2 = springCostRepository.save(cost2);
    cost3 = springCostRepository.save(cost3);

    // Create TypeExperience to populate the database
    TypeExperience typeExperience1 = TypeExperience.builder()
      .cost(cost1)
      .name(RENTAL)
      .build();

    TypeExperience typeExperience2 = TypeExperience.builder()
      .cost(cost1)
      .name(TOUR)
      .build();

    typeExperience1 = springTypeExperienceRepository.save(typeExperience1);
    typeExperience2 = springTypeExperienceRepository.save(typeExperience2);

    // Create Experience to populate the database
    Experience experience1 = Experience.builder()
      .type(typeExperience1)
      .bike(bike2)
      .build();
    experience1 = springExperienceRepository.save(experience1);

    Experience experience2 = Experience.builder()
      .type(typeExperience2)
      .bike(bike3)
      .build();
    experience1 = springExperienceRepository.save(experience2);

    // Create Payment to populate the database
    Payment payment1 = Payment.builder()
      .transactionId("1265987454ADER78")
      .date(LocalDateTime.now())
      .status(PENDING)
      .build();

    Payment payment2 = Payment.builder()
      .transactionId("FD78787458787YY")
      .date(LocalDateTime.now())
      .status(PENDING)
      .build();

    Payment payment3 = Payment.builder()
      .transactionId("OPJYFFD9854654GT")
      .date(LocalDateTime.now().minusDays(56))
      .status(PAID)
      .build();

    springPaymentRepository.save(payment1);
    springPaymentRepository.save(payment2);
    springPaymentRepository.save(payment3);

    // Create Reservation to populate the database
    Reservation reservation1 = Reservation.builder()
      .user(user)
      .payment(payment1)
      .experience(experience1)
      .status(CONFIRMED)
      .build();

    springReservationRepository.save(reservation1);

    Reservation reservation2 = Reservation.builder()
      .user(user)
      .payment(payment1)
      .experience(experience2)
      .status(CANCELLED)
      .build();
    springReservationRepository.save(reservation2);
  }
}