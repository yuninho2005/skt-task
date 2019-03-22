package com.sdevelopment.skt.management.tests.unit;

import com.sdevelopment.skt.management.controller.ProductController;
import com.sdevelopment.skt.management.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void shouldRenderCreateProductPage() throws Exception {
        this.mockMvc.perform(get("/create-product"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("newProduct"));
    }

    @Test
    public void shouldRenderListProductsPage() throws Exception {
        this.mockMvc.perform(get("/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("products"))
                .andExpect(view().name("listProducts"));
    }

    @Test
    public void createProductFormSubmitSuccess() throws Exception {
        this.mockMvc.perform(post("/product-form").param("name","New Product"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("listProducts"));
    }

    @Test
    public void createProductFormSubmitError() throws Exception {
        this.mockMvc.perform(post("/product-form").param("name",""))
                .andExpect(status().isOk())
                .andExpect(view().name("formError"))
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeHasFieldErrors("product"));
    }
}
