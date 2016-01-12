package mysample.webapp.basic.web;

import javax.servlet.Filter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import mysample.webapp.basic.Application;
import mysample.webapp.basic.test.TestDataResource;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;
import org.junit.Rule;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class LoginControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Rule
    @Autowired
    public TestDataResource testDataResource;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context)
                .addFilter(springSecurityFilterChain)
                .build();
    }

    @Ignore("org.dbunit.dataset.DataSetException: error getting list of tablesが出てテストが失敗する")
    @Test
    public void testIndex() throws Exception {
        this.mvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("login"));
    }

    @Ignore("org.dbunit.dataset.DataSetException: error getting list of tablesが出てテストが失敗する")
    @Test
    public void testLogin() throws Exception {
        // 存在しないユーザでログインを試みる
        this.mvc.perform(formLogin().user("id", "test").password("password", "ptest"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andExpect(unauthenticated())
                .andExpect(request().sessionAttribute("SPRING_SECURITY_LAST_EXCEPTION", isA(BadCredentialsException.class)));

        // 使用できないユーザ（enabled = 0)でログインを試みる
        this.mvc.perform(formLogin().user("id", "suzuki").password("password", "20140101"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andExpect(unauthenticated())
                .andExpect(request().sessionAttribute("SPRING_SECURITY_LAST_EXCEPTION", isA(BadCredentialsException.class)));

        // ログイン可能なユーザでログインを試みる
        this.mvc.perform(formLogin().user("id", "tanaka").password("password", "tarou"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/countryList"))
                .andExpect(authenticated().withUsername("tanaka"));
    }

    @Ignore("org.dbunit.dataset.DataSetException: error getting list of tablesが出てテストが失敗する")
    @Test
    public void testEncode() throws Exception {
        MvcResult result = this.mvc.perform(get("/encode?password=ptest")).andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andReturn();
        String crypt = result.getResponse().getContentAsString();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        assertThat(passwordEncoder.matches("ptest", crypt), is(true));
    }
}
