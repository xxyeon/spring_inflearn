package study.data_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "study.data_jpa.repository")
@EnableJpaAuditing
public class DataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}

	//수정자, 등록자 처리하기 위해 넣어주어야함
	//등록되거나 수정될때 auditorProvider를 호출해서 리턴 값으로 필드를 채운다.
	@Bean
	public AuditorAware<String> auditorProvider() {
		return () -> Optional.of(UUID.randomUUID().toString()); //실무에서는 시큐리티 사용한다면 시큐리티 컨텍스트에서, 아님 세션에서 id를 꺼내서 사용
	}
}
