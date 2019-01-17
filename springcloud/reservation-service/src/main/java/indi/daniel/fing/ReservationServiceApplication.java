package indi.daniel.fing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Optional;
import java.util.stream.Stream;

@EnableBinding(Sink.class)
@EnableDiscoveryClient
@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

	@Bean
	ApplicationRunner sampleData(ReservationRepository reservationRepository) {
		return arg -> {
			Stream.of("wangliang", "baijin", "junlu", "daniel", "ethan").forEach(name -> reservationRepository.save(new Reservation(name)));
			reservationRepository.findAll().forEach(System.out::println);
		};
	}
}

@Component
class ReservationConsumer {

	private final ReservationRepository reservationRepository;

	@Autowired
	public ReservationConsumer(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@StreamListener(Sink.INPUT)
	public void write(String name){
		this.reservationRepository.save(new Reservation(name));
	}
}

@RepositoryRestResource
interface ReservationRepository extends JpaRepository<Reservation, Long>{
	@RestResource(path = "by-name")
	Optional<Reservation> findByReservationName(@Param("name") String name);
}

@RestController
@RefreshScope
class MessageController{
	private final String message;
	private final String commonMessage;

	public MessageController(@Value("${reservation.message}")String message, @Value("${message}")String commonMessage){
		this.message = message;
		this.commonMessage = commonMessage;
	}

	@RequestMapping("/message")
	public String getMessage(){
		return this.message;
	}
	@RequestMapping("/common-message")
	public String getCommonMessage(){
		return this.commonMessage;
	}
}

@Entity
class Reservation{
	@Id
	@GeneratedValue
	private Long id;
	private String reservationName;

	public Reservation() {
	}

	public Reservation(String reservationName) {
		this.reservationName = reservationName;
	}

	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", reservationName='" + reservationName + '\'' +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
}