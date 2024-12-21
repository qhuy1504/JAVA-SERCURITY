package iuh.fit.se;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuanLySanPhamApplication {
	private final static Logger logger = LoggerFactory.getLogger(QuanLySanPhamApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(QuanLySanPhamApplication.class, args);
		logger.info("QuanLySanPhamApplication Start");
	}

}
