package apap.tutorial.bacabaca;

import apap.tutorial.bacabaca.dto.BukuMapper;
import apap.tutorial.bacabaca.dto.PenerbitMapper;
import apap.tutorial.bacabaca.dto.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.dto.request.CreatePenerbitRequestDTO;
import apap.tutorial.bacabaca.service.BukuService;
import apap.tutorial.bacabaca.service.PenerbitService;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class BacabacaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BacabacaApplication.class, args);
	}

	// CommandLineRunner digunakan untuk execute code saat spring pertama kali start up
	@Bean
	@Transactional
	CommandLineRunner run(BukuService bukuService, PenerbitService penerbitService, BukuMapper bukuMapper, PenerbitMapper penerbitMapper){
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			// Membuat fake data memanfaatkan Java Faker
			var bukuDTO = new CreateBukuRequestDTO();
			var fakeBook = faker.book();
			var fakeDate = faker.date();

			bukuDTO.setHarga(new BigDecimal(Math.random()*1000000));
			bukuDTO.setJudul(fakeBook.title());
			bukuDTO.setTahunTerbit(String.valueOf(fakeDate.past(36500, TimeUnit.DAYS).getYear()+1900));

			var penerbitDTO = new CreatePenerbitRequestDTO();
			penerbitDTO.setNamaPenerbit(fakeBook.publisher());
			penerbitDTO.setEmail(faker.internet().emailAddress());
			penerbitDTO.setAlamat(faker.address().fullAddress());

			// Mapping penerbitDTO ke buku lalu save penerbit ke database
			var penerbit = penerbitMapper.createPenerbitRequestDTOToPenerbit(penerbitDTO);
			penerbit = penerbitService.createPenerbit(penerbit);

			// Mapping bukuDTO ke buku lalu save buku ke database
			var buku = bukuMapper.createBukuRequestDTOToBuku(bukuDTO);
			buku.setPenerbit(penerbit);
			bukuService.saveBuku(buku);
		};
	}
}
