import com.techwandering.test.k8s.model.StudentNameInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBuilder {
    @Test
    public void testBuilder(){
        StudentNameInfo s = StudentNameInfo.builder().id(1).build();
        Assertions.assertEquals(1,s.getId());
    }
}
