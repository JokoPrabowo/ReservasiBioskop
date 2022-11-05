package org.binar.bioskop;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.binar.bioskop.dto.FilmModel;
import org.binar.bioskop.dto.ScheduleModel;
import org.binar.bioskop.dto.SeatModel;
import org.binar.bioskop.dto.StudioModel;
import org.binar.bioskop.dto.UserModel;
import org.binar.bioskop.services.impl.FilmsServiceImpl;
import org.binar.bioskop.services.impl.SchedulesServiceImpl;
import org.binar.bioskop.services.impl.SeatsServiceImpl;
import org.binar.bioskop.services.impl.StudiosServiceImpl;
import org.binar.bioskop.services.impl.UsersServiceImpl;
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
		String filmCode = "F0001";
		String showDate = "20/12/2022";
		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
		return args -> {
			user.create(new UserModel("jokoprabs", "jokoprabs@gmail.com", "gaslah"));
			studio.create(new StudioModel("Alpha"));
			seat.create(new SeatModel('A', 1, 1, true));
			seat.create(new SeatModel('A', 2, 1, true));
			seat.create(new SeatModel('A', 3, 1, true));
			film.create(new FilmModel(filmCode, "Toy Story 3", "Kartun", LocalDate.parse(showDate, date)));
			schedule.create(new ScheduleModel(filmCode, 1, 75000, LocalDate.parse(showDate, date), LocalTime.parse("10:00:00", time), LocalTime.parse("12:30:00", time)));
			schedule.create(new ScheduleModel(filmCode, 1, 75000, LocalDate.parse(showDate, date), LocalTime.parse("18:30:00", time), LocalTime.parse("21:00:00", time)));
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
