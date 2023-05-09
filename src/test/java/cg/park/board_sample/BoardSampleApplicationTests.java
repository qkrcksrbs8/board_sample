package cg.park.board_sample;

import cg.park.board_sample.comm.util.Stamp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardSampleApplicationTests {

	@Test
	void contextLoads() {
		Stamp.F001.getCode();
		Stamp.F001.getMessage();
	}

}
