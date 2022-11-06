package tacos

import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import tacos.web.WebConfig

@WebMvcTest(WebConfig::class)
internal class HomeControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testHomePage() {
        mockMvc.get("/")
            .andExpect { status { isOk() } }
            .andExpect { view { name("home") } }
            .andExpect { content { string(Matchers.containsString("Welcome to...")) } }
    }
}