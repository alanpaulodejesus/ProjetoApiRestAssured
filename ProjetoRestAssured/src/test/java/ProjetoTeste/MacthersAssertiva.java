package ProjetoTeste;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MacthersAssertiva {

    @Test
    public void testComMatherHamcrest(){

        Assert.assertThat("maria", Matchers.is("maria"));

        List<Integer> impar = Arrays.asList(1,2,3,4,5);
        Assert.assertThat(impar, Matchers.hasSize(5));
        Assert.assertThat(impar, Matchers.contains(1,2,3,4,5));
        Assert.assertThat(impar, Matchers.containsInAnyOrder(5,4,3,2,1));
        Assert.assertThat(impar, Matchers.hasItem(1));
        Assert.assertThat(impar, Matchers.hasItems(1,5));
        Assert.assertThat("joaquina", Matchers.allOf(Matchers.startsWith("jo"), Matchers.endsWith("na"),Matchers.containsString("qui")));
    }
}

