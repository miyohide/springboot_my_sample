package mysample.webapp.basic.test;

import java.lang.reflect.Field;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class TestHelper {
    public static MockHttpServletRequestBuilder postForm(String urlTemplate, Object form) throws IllegalAccessException {
        MockHttpServletRequestBuilder request = post(urlTemplate).contentType(MediaType.APPLICATION_FORM_URLENCODED);
        for (Field field : form.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.get(form) == null) {
                request = request.param(field.getName(), "");
            } else {
                request = request.param(field.getName(), field.get(form).toString());
            }
        }
        return request;
    }

    public static void assertEntityByForm(Object entity, Object form) throws IllegalAccessException {
        for (Field entityField : entity.getClass().getDeclaredFields()) {
            entityField.setAccessible(true);
            try {
                Field formField = form.getClass().getDeclaredField(entityField.getName());
                formField.setAccessible(true);
                assertThat(entityField.get(entity), is(formField.get(form)));
            } catch (NoSuchFieldException ignored) {}
        }
    }    
}
