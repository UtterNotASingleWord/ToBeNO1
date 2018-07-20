import org.junit.After;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;


@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class JunitBaseServiceDao  {

	@Before
	public void setUp() throws Exception {
		System.out.println("========== test start ============");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("========== test end ============");
	}
}
