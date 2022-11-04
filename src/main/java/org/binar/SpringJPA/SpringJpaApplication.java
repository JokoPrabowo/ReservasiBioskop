package org.binar.SpringJPA;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.binar.SpringJPA.entities.FilmsEntity;
import org.binar.SpringJPA.entities.SchedulesEntity;
import org.binar.SpringJPA.entities.SeatId;
import org.binar.SpringJPA.entities.SeatsEntity;
import org.binar.SpringJPA.entities.StudiosEntity;
import org.binar.SpringJPA.entities.UsersEntity;
import org.binar.SpringJPA.services.impl.FilmsServiceImpl;
import org.binar.SpringJPA.services.impl.SchedulesServiceImpl;
import org.binar.SpringJPA.services.impl.SeatsServiceImpl;
import org.binar.SpringJPA.services.impl.StudiosServiceImpl;
import org.binar.SpringJPA.services.impl.UsersServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[ ] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UsersServiceImpl user, FilmsServiceImpl film, StudiosServiceImpl studio, SeatsServiceImpl seat, SchedulesServiceImpl schedule){
		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
		return args -> {
			user.create(new UsersEntity("jokoprabs", "jokoprabs@gmail.com", "gaslah"));
			studio.create(new StudiosEntity(null, "Alpha"));
			seat.create(new SeatsEntity(new SeatId('A', 1), 1, null, true));
			seat.create(new SeatsEntity(new SeatId('A', 2), 1, null, true));
			seat.create(new SeatsEntity(new SeatId('A', 3), 1, null, true));
			film.create(new FilmsEntity("F0001", "Toy Story 3", "Kartun", LocalDate.parse("20/12/2022", date)));
			schedule.create(new SchedulesEntity(null, "F0001", null, 1, null, 75000, LocalDate.parse("20/12/2022", date), LocalTime.parse("10:00:00", time), LocalTime.parse("12:30:00", time)));
			schedule.create(new SchedulesEntity(null, "F0001", null, 1, null, 75000, LocalDate.parse("20/12/2022", date), LocalTime.parse("18:30:00", time), LocalTime.parse("21:00:00", time)));
		};
	}

	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
		FirebaseOptions firebaseOptions = FirebaseOptions
				.builder()
				.setCredentials(googleCredentials)
				.build();
		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
		return FirebaseMessaging.getInstance(app);
	}

}
