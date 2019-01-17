package indi.daniel.fling;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.hateoas.Resources;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/*Stream*/
@EnableBinding(Source.class)
/*熔断*/
@EnableCircuitBreaker
/*Gate way*/
@EnableZuulProxy
/* Registry */
@EnableDiscoveryClient
/*Web 服务客户端*/
@EnableFeignClients
@SpringBootApplication
public class ReservationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationClientApplication.class, args);
	}
}

/* 自带负载均衡和熔断 */
//@FeignClient(url = "http://localhost:8030", value = "new-reservation")
@FeignClient("reservation-service")
interface ReservationRead {
	@RequestMapping(method = RequestMethod.GET, value = "/reservations")
	Resources<Reservation> read();
}

@RestController
class ReservationController {

	private final ReservationRead reservationRead;
	private final MessageChannel messageChannel;

	@Autowired
	public ReservationController(ReservationRead reservationRead,
								 @Qualifier(Source.OUTPUT) MessageChannel messageChannel) {
		this.reservationRead = reservationRead;
		this.messageChannel = messageChannel;
	}

	@PostMapping("/reservations")
	public void write(Reservation reservation) {
		this.messageChannel.send(MessageBuilder.withPayload(reservation.getReservationName()).build());
	}
	/*异常熔断的后续function， 最后可接受一个Throwable*/
	public Collection<String> fallback(/*Throwable throwable*/){
		return new ArrayList<>();
	}

	@HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(method = RequestMethod.GET, value = "/reservations/names")
	public Collection<String> read (){
		return this.reservationRead.read()
				.getContent()
				.stream()
				.map(Reservation::getReservationName)
				.collect(Collectors.toList());

	}
}

class Reservation {
	private String reservationName;

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
}